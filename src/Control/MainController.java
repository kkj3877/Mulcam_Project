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
		// DataSource �� �������ش�.
		String l = config.getInitParameter("dataSourceSettings");
		// 0:driverClassName, 1:url, 2:username, 3:password
		String[] dataSourceSettings = l.split(",");
		String driverClassName = dataSourceSettings[0];
		String url = dataSourceSettings[1];
		String username = dataSourceSettings[2];
		String password = dataSourceSettings[3];
		BasicDataSource dataSource = new BasicDataSource(driverClassName.trim(), url.trim(), username.trim(), password.trim());
		
		// DataSource �� ��������� ������ JTPL �� �����.
		JdbcTemplate jtpl = new JdbcTemplate(dataSource);
		
		PostDAO postDAO = new PostDAO_MariaImpl(jtpl);
		StudentDAO studentDAO = new StudentDAO_MariaImpl(jtpl);
		
		// ServletConfig �κ��� controller ���� �̸��� �޾ƿ� ',' �� �������� �и��Ѵ�.
		l = config.getInitParameter("controllerNames");
		String[] controllerNames = l.split(",");
		for ( String controllerName : controllerNames ) {
			try {
				// �ش� �̸��� �ش��ϴ� Ŭ������ Controller Ŭ�������� Ȯ���� �� methodMap ����
				Class<?> cls = Class.forName( controllerName.trim() ); // trim : ������ ������
				Control annotControl = cls.getAnnotation(Control.class);
				if ( annotControl != null ) {
					// ��Ʈ�ѷ��鿡�� JTPL �� ���� �����͸� �Ѱ��ش�.
					Object obj = cls.newInstance();
					
					// �� Ŭ�����鿡�� �Լ����� ���� RequestMapping ������̼��� �ִ��� Ȯ���Ѵ�.
					Method[] mtds = cls.getMethods();
					for ( Method mtd : mtds ) {
						RequestMapping annotRM = mtd.getAnnotation(RequestMapping.class);
						
						// ���� RequestMapping �� ��õǾ� �ִٸ� uri-(�Լ�, �ν��Ͻ�) �� �ʿ� �־��ش�. 
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
	
	// mtd �� idx ��° �Ű������� annot ������̼��� ��õǾ� �ִ��� �˻����ִ� �Լ�
	// ��õǾ� �ִٸ� �ش� ������̼� Ŭ������, �׷��� �ʴٸ� null �� ��ȯ
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
		// URL ���� uri �� �����Ѵ�.
		String fullUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String uri = fullUri.substring(contextPath.length());
		
		// ��û�� uri �� ��Ī�Ǵ� MethodAndTarget(MAT)�� ��´�.
		// ���� uri �� �ش��ϴ� MAT �� ���ٸ� �׷��� ���� ���ٰ� ����ϰ�,
		// ��Ī�Ǵ� MAT �� �ִٸ� �ش� method �� invoke �Ѵ�.
		MethodAndTarget MAT = methodMap.get(uri);
		if ( MAT == null ) {
			System.out.println( uri + " �� �ش��ϴ� ��Ʈ�ѷ��� �����ϴ�.");
		}
		else { // ��Ī�Ǵ� MAT �� �ִ� ���
			Method mtd = MAT.method;
			Object target = MAT.target;
			
			// �Լ��� �´� parameter �迭 ����
			Class<?>[] paramTypes = mtd.getParameterTypes();
			Object[] params = new Object[paramTypes.length];
			for (int i = 0; i < params.length; i++) {
				// �Լ��� �Ű������� �µ��� �Ű������� �� ������ param �迭�� �־��ش�.
				if (paramTypes[i] == HttpServletRequest.class) params[i] = request;
				else if (paramTypes[i] == HttpServletResponse.class) params[i] = response;
				else if (paramTypes[i] == ServletContext.class) params[i] = request.getServletContext();
				else if (paramTypes[i] == HttpSession.class) params[i] = request.getSession(true);
				else if (paramTypes[i] == ServletConfig.class) params[i] = this.getServletConfig();
				else {
					// i ��° �Ű������� @RequestParam ������̼��� ��õǾ� �ִٸ�
					// request �� ���� �ش� ������ ã�� �������ش�.
					RequestParam annotRP = (RequestParam)getAnnotationInParam(mtd, i, RequestParam.class );
					if ( annotRP != null ) { // RequestParam ������̼��� ��õǾ� �ִ� ���
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
					
					// i ��° �Ű������� @ModelAttribute ������̼��� ��õǾ� �ִٸ�
					// request �� parameter ��� �ν��Ͻ��� �����Ͽ� �Ű������� ����
					ModelAttribute annotMA = (ModelAttribute)getAnnotationInParam(mtd, i, ModelAttribute.class);
					if ( annotMA != null ) {
						// �ڷ����� �´� instance �� �����Ͽ� request�� ���޵� ����� setter�� ȣ��
						Object p = null;
						try {
							p = paramTypes[i].newInstance();
							// populate : �ν��Ͻ��� setter �Լ����� ȣ���Ͽ� �Ű��������� setting
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
				// uri �� �ش��ϴ� ��Ʈ�ѷ��� �Լ��� �����ϰ� ����� ��ȯ�޴´�.
				Object obj = mtd.invoke(target, params);
				String prefix = "";
				// String prefix = "/WEB-INF/jsp/";
				String suffix = ".jsp";
				
				if (obj == null) { System.out.println("obj == null"); }
				else {
					if (obj instanceof String) {
						String responseText = (String)obj;
						
						// @ResponseBody �� ������ ���� �ܼ� ��� or ���� ó��
						ResponseBody annotRB = mtd.getAnnotation(ResponseBody.class);
						if ( annotRB != null ) { // @ResponseBody �Լ� ó��(�ܼ� ���)
							response.setContentType("text/plain; charset=utf-8");
							OutputStream out = response.getOutputStream();
							out.write( responseText.getBytes("utf-8"));
							out.flush();
							out.close();
						}
						else { // ����( redirect or distpatch ) ó��
							// ���� responseText �� 'redirect:' �� �����ϸ� �ش� uri �� redirect ������ ������. 
							if ( responseText.startsWith("redirect:")) {
								response.sendRedirect( responseText.substring("redirect:".length()) );
							}
							// responseText �� 'redirect:' �� �������� �ʴ´ٸ� jsp ������ ã�� dispatch �����ش�.
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
						
						// ���� viewName �� 'redirect:' �� �����ϸ� �ش� uri �� redirect ������ ������.
						if ( viewName.startsWith("redirect:")) {
							response.sendRedirect( viewName.substring("redirect:".length()) );
						}
						
						// viewName �� 'redirect:' �� �������� �ʴ´ٸ� jsp ������ ã�� dispatch �����ش�.
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
