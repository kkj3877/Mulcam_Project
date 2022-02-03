package Control;

import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.PostVO;
import Model.JdbcTemplate;

@Control
public class ControllerPost {
	
	private JdbcTemplate jtpl = null;
	public JdbcTemplate getJtpl() { return jtpl; }
	public void setJtpl(JdbcTemplate jtpl) { this.jtpl = jtpl; }
	
	
	@RequestMapping("/subs.do")
	public String subs() throws Exception {
		System.out.println("ControllerPost:: subs");
		
		return "subs";
	}
	
	
	@RequestMapping("/sub_board.do")
	public ModelAndView sub_board(@RequestParam("subject") String subject) throws Exception {
		System.out.println("ControllerPost:: sub_board:: " + subject );
		
		PostDAO dao = new PostDAO_MariaImpl(jtpl);
		dao.findAll(subject);
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("sub_board");
		mnv.addObject("subject", subject);
		
		return mnv;
	}
	
	
	@RequestMapping("/ask.do")
	public String ask
		(@RequestParam("subject") String subject) throws Exception
	{
		System.out.println("ControllerPost:: ask:: " + subject );
		
		return "ask.jsp?subject="+subject;
	}
	
	
	@RequestMapping("/add.do")
	public String add
	(@RequestParam("subject") String subject, @ModelAttribute("PostVO") PostVO pvo)
		throws Exception
	{
		System.out.println("ControllerPost:: add");
		
		PostDAO dao = new PostDAO_MariaImpl(jtpl);
		dao.findAll(subject);
		
		return "redirect:sub_board?sub="+subject;
	}
	
	
	@RequestMapping("/del.do")
	public String del
	(@RequestParam("sub") String subject, @ModelAttribute("PostVO") PostVO pvo)
		throws Exception
	{
		System.out.println("ControllerPost:: del");
		
		PostDAO dao = new PostDAO_MariaImpl(jtpl);
		dao.findAll(subject);
		
		return "redirect:sub_board?sub="+subject;
	}
	
}