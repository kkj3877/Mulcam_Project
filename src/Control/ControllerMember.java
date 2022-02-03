package Control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.JdbcTemplate;
import Model.StudentDAO;
import Model.StudentDAO_MariaImpl;
import Model.StudentVO;

@Control
public class ControllerMember {
	
	private JdbcTemplate jtpl = null;
	public JdbcTemplate getJtpl() { return jtpl; }
	public void setJtpl(JdbcTemplate jtpl) { this.jtpl = jtpl; }
	
	
	@RequestMapping("/start.do")
	public String start() throws Exception
	{
		System.out.println("ControllerMember:: start");
		
		return "start";
	}
	
	
	@RequestMapping("/signup.do")
	public String signup() throws Exception
	{
		System.out.println("ControllerMember:: signup");
		
		return "signup";
	}
	
	
	@RequestMapping("/signuptry.do")
	public String signuptry
		(@ModelAttribute("StudentVO") StudentVO pvo, HttpServletResponse response )
			throws Exception
	{
		System.out.println("ControllerMember:: signuptry");
		System.out.println(">> stid : " + pvo.getStid());
		System.out.println(">>   pw : " + pvo.getPw());
		System.out.println(">> name : " + pvo.getName());
		System.out.println(">> mail : " + pvo.getMail());
		
		StudentDAO dao = new StudentDAO_MariaImpl(jtpl);
		
		/*
		if (ls.size() != 0) {
			System.out.println("�̹� ������ �й��Դϴ�( " + ls.get(0).getName() + ")");
			isExist = true;
		}
		else {
			System.out.println("���� ������ �й��Դϴ�.");
		}
		*/
		
		if (dao.findByStid(pvo)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('�̹� ������ �й��Դϴ�'); location.href='login.jsp'</script>");
			return null;
		}
		else {
			dao.add(pvo);
		}
		return "login";
	}
	
	
	@RequestMapping("/login.do")
	public String login() throws Exception
	{
		System.out.println("ControllerMember:: login");
		
		return "login";
	}
	
	@RequestMapping("/logintry.do")
	public String logintry
		(@ModelAttribute("StudentVO") StudentVO pvo)
			throws Exception
	{
		System.out.println("ControllerMember:: logintry");
		System.out.println(">> stid : " + pvo.getStid());
		System.out.println(">>   pw : " + pvo.getPw());
		
		StudentDAO dao = new StudentDAO_MariaImpl(jtpl);
		int result = dao.loginTry(pvo);
		if (result == 1) { return "redirect:start.do?wrong_stid"; }
		else if (result == 2) { return "redirect:login.do?wrong_pw"; }
		
		return "redirect:subs.do";
	}
	
}