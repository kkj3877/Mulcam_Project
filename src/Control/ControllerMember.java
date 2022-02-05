package Control;

import java.io.PrintWriter;

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
	
	private StudentDAO studentDAO = null;
	public void setStudentDAO(StudentDAO dao) {
		this.studentDAO = dao;
	}
	
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
		
		Integer stid = pvo.getStid();
		if (stid == null) return "redirect:signup.do?ecode=blank_stid";
		if (stid == -1) return "redirect:signup.do?ecode=blank_stid";
		
		String pw = pvo.getPw();
		if (pw == null) return "redirect:signup.do?ecode=blank_pw";
		
		String name = pvo.getName();
		if (name == null) return "redirect:signup.do?ecode=blank_name";
		
		String mail = pvo.getMail();
		if (mail == null) return "redirect:signup.do?ecode=blank_mail";
		
		System.out.println(">> stid : " + stid);
		System.out.println(">>   pw : " + pw);
		System.out.println(">> name : " + name);
		System.out.println(">> mail : " + mail);
		
		/*
		if (ls.size() != 0) {
			System.out.println("이미 가입한 학번입니다( " + ls.get(0).getName() + ")");
			isExist = true;
		}
		else {
			System.out.println("가입 가능한 학번입니다.");
		}
		*/
		
		if (studentDAO.findByStid(pvo)) {
			System.out.println("here");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 가입한 학번입니다'); location.href='login.do';</script>");
			// writer.println("<script>alert('이미 가입한 학번입니다');</script>");
			writer.close();
		}
		else {
			studentDAO.add(pvo);
		}
		return null;
		// return "login.do";
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
		
		int result = studentDAO.loginTry(pvo);
		if (result == 1) { return "redirect:start.do?ecode=wrong_stid"; }
		if (result == 2) { return "redirect:login.do?ecode=wrong_pw"; }
		
		return "redirect:subs.do";
	}
}
