package Control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		System.out.println("ControllerAdmin:: delPostFromStatus:: " + subject+", "+no);
		
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
		System.out.println("ControllerAdmin:: delStudent");
		
		String sql = "DELETE FROM Student_T where stid=?";
		
		StudentVO pvo = new StudentVO();
		pvo.setStid(stid);
		studentDAO.delStudentByStid(pvo);
		
		return "redirect:status.do";
	}
	
	@RequestMapping("/toCsv.do")
	public String toCsv() throws Exception {
		System.out.println("ControllerAdmin:: toCsv");
		System.out.println("csvDir: " + Util.csvDir());
		File file = new File(Util.csvDir());
		if (!file.exists()) {
			file.mkdir();
			System.out.println("Directory Created");
		}
		
		LocalDateTime LDT = LocalDateTime.now();		
		String formatedTime = "mathcafe_"+LDT.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
		
		String fileDir = Util.csvDir() + formatedTime + ".csv";
		System.out.println("fildDir: " + fileDir);
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileDir));
		
		StringBuffer sb = new StringBuffer();
		sb.append("학생\n");
		sb.append("학번,pw,이름,메일").append("\n");
		List<StudentVO> sList = studentDAO.findAll();
		
		for ( StudentVO vo : sList ) {
			sb.append(vo.getStid()).append(",");
			sb.append(vo.getPw()).append(",");
			sb.append(vo.getName()).append(",");
			sb.append(vo.getMail()).append("\n");
		}
		
		String[] subjects = {"Basic", "Calc", "Linear"};
		String[] subjects_kor = {"기초수학", "미적분학", "선형대수학"};
		List<PostVO> pList = null;
		
		int idx = -1;
		for ( String subject : subjects ) {
			idx++;
			sb.append("\n\n").append(subjects_kor[idx]).append("\n");
			sb.append("번호,학번,챕터,제목,내용,답변,질문사진,답변사진\n");
			pList = postDAO.findAll(subject);
			for (PostVO vo : pList) {
				sb.append(vo.getNo()).append(",");
				sb.append(vo.getStid()).append(",");
				sb.append(vo.getCh()).append(",");
				String title = vo.getTitle();
				sb = (title==null) ? sb.append(",") :
					sb.append(title.replaceAll("\r\n", "@").replace(',', '.')).append(",");
				String content = vo.getContent();
				sb = (content==null) ? sb.append(",") :
					sb.append(content.replaceAll("\r\n", "@").replace(',', '.')).append(",");
				String ans = vo.getAns();
				sb = (ans==null) ? sb.append(",") :
					sb.append(ans.replaceAll("\r\n", "@").replace(',', '.')).append(",");
				sb = (vo.getFsn_q() == null) ? sb.append("X,") : sb.append("O,");
				sb = (vo.getFsn_a() == null) ? sb.append("X\n") : sb.append("O\n");
			}
		}
		
		out.write(sb.toString());
		
		out.flush();
		out.close();
		return "redirect:status.do";
	}
	
	
	@RequestMapping("/status.do")
	public ModelAndView status() throws Exception {
		System.out.println("ControllerAdmin:: status");
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
