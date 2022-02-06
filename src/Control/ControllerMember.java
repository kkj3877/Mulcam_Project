package Control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.JdbcTemplate;
import Model.StudentDAO;
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
	
	
	@RequestMapping("/delStudent.do")
	public String delStudent(@RequestParam("stid") Integer stid) throws Exception
	{
		System.out.println("ControllerMember:: delStudent");
		
		String sql = "DELETE FROM Student_T where stid=?";
		
		StudentVO pvo = new StudentVO();
		pvo.setStid(stid);
		studentDAO.delStudentByStid(pvo);
		
		return "redirect:status.do";
	}
	
	
	@RequestMapping("/login.do")
	public String login(HttpSession session) throws Exception
	{
		System.out.println("ControllerMember:: login");
		
		if (session.getAttribute("stid") != null) {
			System.out.println("removeAttribute 'stid'");
			session.removeAttribute("stid");
		}
		
		return "login";
	}
	
	
	@RequestMapping("/logintry.do")
	public String loginTry
		(@ModelAttribute("StudentVO") StudentVO pvo,
			HttpSession session, HttpServletResponse response ) throws Exception
	{
		System.out.println("ControllerMember:: logintry");
		
		Integer stid = pvo.getStid();
		if (stid == null) return "redirect:login.do?ecode=blank_stid";
		
		String pw = pvo.getPw();
		if (pw == null) return "redirect:login.do?ecode=blank_pw";
		
		System.out.println(">> stid : " + stid);
		System.out.println(">>   pw : " + pw);
		
		String name = studentDAO.loginTry(pvo);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if (name == null) { // 학번이 없는 경우
			writer.println("<script>alert('가입되지 않은 학번입니다.'); location.href='login.do';</script>");
			writer.close();
			return null;
		}
		if (name.equals("0")) { // 비밀번호가 틀린 경우
			writer.println("<script>alert('비밀번호가 틀렸습니다'); location.href='login.do';</script>");
			writer.close();
			return null;
		}
		
		session.setAttribute("stid", stid);
		
		writer.println("<script>alert('환영합니다 "+name+" 님'); location.href='subs.do';</script>");
		writer.close();
		return null;
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
		
		if (studentDAO.findNameByStid(pvo) != null) {
			System.out.println("here");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 가입한 학번입니다'); location.href='login.do';</script>");
			// writer.println("<script>alert('이미 가입한 학번입니다');</script>");
			writer.close();
			return null;
		}
		else {
			studentDAO.add(pvo);
		}
		return "redirect:login.do";
	}
	
}
