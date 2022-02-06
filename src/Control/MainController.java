package Control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BasicDataSource;
import Model.JdbcTemplate;
import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.StudentDAO;
import Model.StudentDAO_MariaImpl;

public class MainController extends HttpServlet {
	
	private static class MethodAndTarget {
		Method method = null;
		Object target = null;
		
		MethodAndTarget( Method method, Object target ) {
			this.method = method;
			this.target = target;
		}
	}
	
	private Map<String, MethodAndTarget> methodMap = null;
	
	public MainController() { methodMap = new Hashtable<String, MethodAndTarget>(); }
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()");
		// DataSource 를 구성해준다.
		String l = config.getInitParameter("dataSourceSettings");
		// 0:driverClassName, 1:url, 2:username, 3:password
		String[] dataSourceSettings = l.split(",");
		String driverClassName = dataSourceSettings[0];
		String url = dataSourceSettings[1];
		String username = dataSourceSettings[2];
		String password = dataSourceSettings[3];
		BasicDataSource dataSource = new BasicDataSource(driverClassName.trim(), url.trim(), username.trim(), password.trim());
		
		// DataSource 를 멤버변수로 가지는 JTPL 을 만든다.
		JdbcTemplate jtpl = new JdbcTemplate(dataSource);
		
		PostDAO postDAO = new PostDAO_MariaImpl(jtpl);
		StudentDAO studentDAO = new StudentDAO_MariaImpl(jtpl);
		
		// ServletConfig 로부터 controller 들의 이름을 받아와 ',' 를 기준으로 분리한다.
		l = config.getInitParameter("controllerNames");
		String[] controllerNames = l.split(",");
		for ( String controllerName : controllerNames ) {
			try {
				// 해당 이름에 해당하는 클래스가 Controller 클래스인지 확인한 후 methodMap 구성
				Class<?> cls = Class.forName( controllerName.trim() ); // trim : 공백을 없애줌
				Control annotControl = cls.getAnnotation(Control.class);
				if ( annotControl != null ) {
					// 컨트롤러들에게 JTPL 에 대한 포인터를 넘겨준다.
					Object obj = cls.newInstance();
					
					// 각 클래스들에서 함수들을 보며 RequestMapping 어노테이션이 있는지 확인한다.
					Method[] mtds = cls.getMethods();
					for ( Method mtd : mtds ) {
						RequestMapping annotRM = mtd.getAnnotation(RequestMapping.class);
						
						// 만약 RequestMapping 이 명시되어 있다면 uri-(함수, 인스턴스) 를 맵에 넣어준다. 
						if ( annotRM != null ) {
							String uri = annotRM.value();
							methodMap.put(uri, new MethodAndTarget(mtd, obj));
						}
						else {
							String mtdName = mtd.getName();
							if (mtdName.equals("setJtpl")) mtd.invoke(obj, jtpl);
							else if (mtdName.equals("setPostDAO")) mtd.invoke(obj, postDAO);
							else if (mtdName.equals("setStudentDAO")) mtd.invoke(obj, studentDAO);
						}
					}
				}
			}
			catch ( Exception e ) { e.printStackTrace(); }
		}
	}
	
	// mtd 의 idx 번째 매개변수에 annot 어노테이션이 명시되어 있는지 검사해주는 함수
	// 명시되어 있다면 해당 어노테이션 클래스를, 그렇지 않다면 null 을 반환
	public static Annotation getAnnotationInParam( Method mtd, int idx, Class<?> annot ) {
		Annotation[] paramAnnotations = mtd.getParameterAnnotations()[idx];
		for ( int i = 0; i < paramAnnotations.length; i++ ) {
			if ( paramAnnotations[i].annotationType() == annot ) {
				return paramAnnotations[i];
			}
		}
		return null;
	}
	
	@Override
	protected void service
		(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// URL 에서 uri 를 추출한다.
		String fullUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String uri = fullUri.substring(contextPath.length());
		
		// 요청된 uri 에 매칭되는 MethodAndTarget(MAT)을 얻는다.
		// 만약 uri 에 해당하는 MAT 가 없다면 그러한 것이 없다고 출력하고,
		// 매칭되는 MAT 가 있다면 해당 method 를 invoke 한다.
		MethodAndTarget MAT = methodMap.get(uri);
		if ( MAT == null ) {
			System.out.println( uri + " 에 해당하는 컨트롤러가 없습니다.");
		}
		else { // 매칭되는 MAT 가 있는 경우
			Method mtd = MAT.method;
			Object target = MAT.target;
			
			// 함수에 맞는 parameter 배열 생성
			Class<?>[] paramTypes = mtd.getParameterTypes();
			Object[] params = new Object[paramTypes.length];
			for (int i = 0; i < params.length; i++) {
				// 함수의 매개변수에 맞도록 매개변수로 들어갈 값들을 param 배열에 넣어준다.
				if (paramTypes[i] == HttpServletRequest.class) params[i] = request;
				else if (paramTypes[i] == HttpServletResponse.class) params[i] = response;
				else if (paramTypes[i] == ServletContext.class) params[i] = request.getServletContext();
				else if (paramTypes[i] == HttpSession.class) params[i] = request.getSession(true);
				else if (paramTypes[i] == ServletConfig.class) params[i] = this.getServletConfig();
				else {
					// i 번째 매개변수에 @RequestParam 어노테이션이 명시되어 있다면
					// request 로 부터 해당 변수를 찾아 대입해준다.
					RequestParam annotRP = (RequestParam)getAnnotationInParam(mtd, i, RequestParam.class );
					if ( annotRP != null ) { // RequestParam 어노테이션이 명시되어 있는 경우
						String paramName = annotRP.value();
						if (paramTypes[i] == String.class) params[i] = request.getParameter(paramName);
						else if (paramTypes[i] == Integer.class) {
							try {
								params[i] = Integer.parseInt(request.getParameter(paramName));
							}
							catch ( NumberFormatException e ) {
								params[i] = null;
								continue;
							}
						}
					}
					
					// i 번째 매개변수에 @ModelAttribute 어노테이션이 명시되어 있다면
					// request 의 parameter 들로 인스턴스를 생성하여 매개변수로 전달
					ModelAttribute annotMA = (ModelAttribute)getAnnotationInParam(mtd, i, ModelAttribute.class);
					if ( annotMA != null ) {
						// 자료형에 맞는 instance 를 생성하여 request로 전달된 값들로 setter를 호출
						Object p = null;
						try {
							p = paramTypes[i].newInstance();
							// populate : 인스턴스의 setter 함수들을 호출하여 매개변수들을 setting
							BeanUtil.populate(request, p);
						}
						catch (InstantiationException | IllegalAccessException e) {
							e.printStackTrace();
						}
						finally { params[i] = p; }
						
					}
				}
			}
			
			try {
				// uri 에 해당하는 컨트롤러와 함수를 실행하고 결과를 반환받는다.
				Object obj = mtd.invoke(target, params);
				String prefix = "";
				// String prefix = "/WEB-INF/jsp/";
				String suffix = ".jsp";
				
				if (obj == null) { System.out.println("obj == null"); }
				else {
					if (obj instanceof String) {
						String responseText = (String)obj;
						
						// @ResponseBody 의 유무에 따라 단순 출력 or 매핑 처리
						ResponseBody annotRB = mtd.getAnnotation(ResponseBody.class);
						if ( annotRB != null ) { // @ResponseBody 함수 처리(단순 출력)
							response.setContentType("text/plain; charset=utf-8");
							OutputStream out = response.getOutputStream();
							out.write( responseText.getBytes("utf-8"));
							out.flush();
							out.close();
						}
						else { // 매핑( redirect or distpatch ) 처리
							// 만약 responseText 가 'redirect:' 로 시작하면 해당 uri 로 redirect 응답을 보낸다. 
							if ( responseText.startsWith("redirect:")) {
								response.sendRedirect( responseText.substring("redirect:".length()) );
							}
							// responseText 가 'redirect:' 로 시작하지 않는다면 jsp 파일을 찾아 dispatch 시켜준다.
							else {
								String view = prefix + responseText + suffix;
								RequestDispatcher rd = request.getRequestDispatcher( view );
								rd.forward(request, response);
							}
						}
					}
					else if (obj instanceof ModelAndView) {
						ModelAndView mnv = (ModelAndView)obj;
						String viewName = mnv.getViewName();
						
						// 만약 viewName 이 'redirect:' 로 시작하면 해당 uri 로 redirect 응답을 보낸다.
						if ( viewName.startsWith("redirect:")) {
							response.sendRedirect( viewName.substring("redirect:".length()) );
						}
						
						// viewName 이 'redirect:' 로 시작하지 않는다면 jsp 파일을 찾아 dispatch 시켜준다.
						mnv.plant(request);
						System.out.println("ModelAndView dispatch");
						String view = prefix + viewName + suffix;
						RequestDispatcher rd = request.getRequestDispatcher( view );
						rd.forward(request, response);
					}
				}
			}
			catch ( Exception e ) { e.printStackTrace(); }
			
		}
	}
}
