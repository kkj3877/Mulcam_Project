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
		(@RequestParam("stid") String rsaStid, @RequestParam("pw") String rsaPw,
			HttpSession session, HttpServletResponse response ) throws Exception
	{
		System.out.println("ControllerMember:: logintry");
		
		// stid �� pw �� �ϳ��� ����� �ǳʿ��� �ʾҴٸ� 
		if (rsaStid == null) return "redirect:login.do?ecode=blank_stid";
		if (rsaPw == null) return "redirect:login.do?ecode=blank_pw";
		
		System.out.println(">> rsastid : " + rsaStid);
		System.out.println(">>   rsapw : " + rsaPw);
		
		// ��ȣȭ�� �����͸� ��ȣȭ
		Integer stid = Integer.parseInt(rsaStid.substring(0, rsaStid.length() - 3));
		String pw = rsaPw.substring(0, rsaPw.length() - 3);
		
		System.out.println(">> stid : " + stid);
		System.out.println(">>   pw : " + pw);
		
		StudentVO pvo = new StudentVO();
		pvo.setStid(stid);
		pvo.setPw(pw);
		
		String name = studentDAO.loginTry(pvo);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if (name == null) { // �й��� ���� ���
			writer.println("<script>alert('���Ե��� ���� �й��Դϴ�.'); location.href='login.do';</script>");
			writer.close();
			return null;
		}
		if (name.equals("0")) { // ��й�ȣ�� Ʋ�� ���
			writer.println("<script>alert('��й�ȣ�� Ʋ�Ƚ��ϴ�'); location.href='login.do';</script>");
			writer.close();
			return null;
		}
		
		session.setAttribute("stid", stid);
		
		writer.println("<script>alert('ȯ���մϴ� "+name+" ��'); location.href='subs.do';</script>");
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
			System.out.println("�̹� ������ �й��Դϴ�( " + ls.get(0).getName() + ")");
			isExist = true;
		}
		else {
			System.out.println("���� ������ �й��Դϴ�.");
		}
		*/
		
		if (studentDAO.findNameByStid(pvo) != null) {
			System.out.println("here");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('�̹� ������ �й��Դϴ�'); location.href='login.do';</script>");
			// writer.println("<script>alert('�̹� ������ �й��Դϴ�');</script>");
			writer.close();
			return null;
		}
		else {
			studentDAO.add(pvo);
		}
		return "redirect:login.do";
	}
	
}
