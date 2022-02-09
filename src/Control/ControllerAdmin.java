package Control;

import java.io.File;
import java.util.List;

import Model.JdbcTemplate;
import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.PostVO;
import Model.StudentDAO;
import Model.StudentVO;

@Control
public class ControllerAdmin {
	
	private JdbcTemplate jtpl = null;
	public JdbcTemplate getJtpl() { return jtpl; }
	public void setJtpl(JdbcTemplate jtpl) { this.jtpl = jtpl; }
	
	private PostDAO postDAO = null;
	public void setPostDAO(PostDAO_MariaImpl dao) {
		this.postDAO = dao;
	}
	
	private StudentDAO studentDAO = null;
	public void setStudentDAO(StudentDAO dao) {
		this.studentDAO = dao;
	}
	
	
	@RequestMapping("/delPostFromStatus.do")
	public String delPostFromStatus
	(@RequestParam("subject") String subject, @RequestParam("no") Integer no)
		throws Exception
	{
		System.out.println("ControllerPost:: delPostFromStatus:: " + subject+", "+no);
		
		PostVO pvo = postDAO.findPostByNo(subject, no);
		
		int uc = postDAO.delByNo(subject, pvo);
		
		String fsn_q = pvo.getFsn_q();
		if ( uc == 1 && fsn_q != null ) {
			File file = new File( Util.uploadDir() + fsn_q );
			if ( file.exists() ) file.delete();
		}
		
		String fsn_a = pvo.getFsn_a();
		if ( uc == 1 && fsn_a != null ) {
			File file = new File( Util.uploadDir() + fsn_a );
			if ( file.exists() ) file.delete();
		}
		
		return "redirect:status.do";
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
	
	
	@RequestMapping("/status.do")
	public ModelAndView status() throws Exception {
		System.out.println("ControllerPost:: status");
		ModelAndView mnv = new ModelAndView();
		
		List<StudentVO> Students = studentDAO.findAll();
		List<PostVO> Basic = postDAO.findAll("Basic");
		List<PostVO> Calc = postDAO.findAll("Calc");
		List<PostVO> Linear = postDAO.findAll("Linear");
		
		
		mnv.setViewName("status");
		mnv.addObject("Students", Students);
		mnv.addObject("Basic", Basic);
		mnv.addObject("Calc", Calc);
		mnv.addObject("Linear", Linear);
		
		return mnv;
	}
	
}
