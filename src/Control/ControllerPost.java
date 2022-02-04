package Control;

import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.PostVO;
import Model.StudentDAO;
import Model.StudentDAO_MariaImpl;
import Model.StudentVO;

import java.util.List;

import Model.JdbcTemplate;

@Control
public class ControllerPost {
	
	private JdbcTemplate jtpl = null;
	public JdbcTemplate getJtpl() { return jtpl; }
	public void setJtpl(JdbcTemplate jtpl) { this.jtpl = jtpl; }
	
	private PostDAO postDAO = null;
	public void setPostDAO(PostDAO_MariaImpl dao) {
		this.postDAO = dao;
	}
	private StudentDAO studentDAO = null;
	public void setStudentDAO(StudentDAO_MariaImpl dao) {
		this.studentDAO = dao;
	}
	
	@RequestMapping("/status.do")
	public ModelAndView status() throws Exception {
		System.out.println("ControllerPost:: status");
		
		List<StudentVO> Students = studentDAO.findAll();
		
		List<PostVO> Basic = postDAO.findAll("Basic");
		List<PostVO> Calc = postDAO.findAll("Calc");
		List<PostVO> Linear = postDAO.findAll("Linear");
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("status");
		mnv.addObject("Students", Students);
		mnv.addObject("Basic", Basic);
		mnv.addObject("Calc", Calc);
		mnv.addObject("Linear", Linear);
		
		return mnv;
	}
	
	
	@RequestMapping("/subs.do")
	public String subs() throws Exception {
		System.out.println("ControllerPost:: subs");
		
		return "subs";
	}
	
	
	@RequestMapping("/sub_board.do")
	public ModelAndView sub_board(@RequestParam("subject") String subject) throws Exception {
		System.out.println("ControllerPost:: sub_board:: " + subject );
		
		List<PostVO> rList = postDAO.findAll(subject);
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("sub_board");
		mnv.addObject("subject", subject);
		mnv.addObject("rList", rList);
		
		return mnv;
	}
	
	
	@RequestMapping("/ask.do")
	public String ask
		(@RequestParam("subject") String subject) throws Exception
	{
		System.out.println("ControllerPost:: ask:: " + subject );
		
		return "write.jsp?subject="+subject;
	}
	
	
	@RequestMapping("/add.do")
	public String add
	(@RequestParam("subject") String subject, @ModelAttribute("PostVO") PostVO pvo)
		throws Exception
	{
		System.out.println("ControllerPost:: add:: " + subject);
		
		postDAO.add(subject, null);
		
		return "redirect:sub_board.do?subject="+subject;
	}
	
	
	@RequestMapping("/del.do")
	public String del
	(@RequestParam("sub") String subject, @ModelAttribute("PostVO") PostVO pvo)
		throws Exception
	{
		System.out.println("ControllerPost:: del:: " + subject);
		
		postDAO.delByPK(subject, null);
		
		return "redirect:sub_board.do?subject="+subject;
	}
	
}