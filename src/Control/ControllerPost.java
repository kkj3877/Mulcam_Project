package Control;

import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.PostVO;
import Model.StudentDAO;
import Model.StudentDAO_MariaImpl;
import Model.StudentVO;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

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
	
	
	@RequestMapping("/add.do")
	public String add
	(@RequestParam("subject") String subject, @ModelAttribute("PostVO") PostVO pvo,
		HttpSession session)
			throws Exception
	{
		System.out.println("ControllerPost:: add:: " + subject);
		Integer stid = (Integer)session.getAttribute("stid");
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		postDAO.add(subject, null);
		
		return "redirect:sub_board.do?subject="+subject;
	}
	
	
	@RequestMapping("/delPostFromStatus.do")
	public String delPostFromStatus
	(@RequestParam("subject") String subject, @RequestParam("no") Integer no)
		throws Exception
	{
		System.out.println("ControllerPost:: delPostFromStatus:: " + subject+", "+no);
		
		PostVO pvo = new PostVO();
		pvo.setNo(no);
		
		postDAO.delByNo(subject, pvo);
		
		return "redirect:status.do";
	}
	
	
	@RequestMapping("/question.do")
	public String question(HttpServletRequest request, HttpSession session) throws Exception
	{
		System.out.println("ControllerPost:: question:: ");
		
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		postDAO.getClass();
		MultipartRequest mpr = new MultipartRequest(request, Util.uploadDir(), 1024*1024*16, "utf-8", null);
		
		// 과목명
		String subject = mpr.getParameter("subject");
		if ( subject == null ) return "redirect:subs.do?ecode=invalid_subject";
		System.out.println("subject : " + subject);
		
		String errorString = null;
		// 질문 제목
		String title = mpr.getParameter("title");
		if ( title.equals("") ) errorString = "invalid_title";
		
		// 질문 챕터
		String ch = mpr.getParameter("ch");
		if ( ch == null ) errorString = "invalid_ch";
		System.out.println("ch : " + ch);
		
		// 질문 내용
		String content = mpr.getParameter("content");
		System.out.println("content : " + content);
		
		System.out.println("errorString=["+errorString+"]");
		if ( errorString != null ) {
			return "redirect:ask.do?subject="+subject+"&ecode="+errorString;
		}
		
		PostVO pvo = new PostVO();
		pvo.setStid(stid);
		pvo.setTitle(title);
		pvo.setCh(Integer.parseInt(ch));
		pvo.setContent(content);
		
		// 질문 사진이 있다면 사진 저장 및 UUID 저장
		String ofn = mpr.getOriginalFileName("fsn_q");
		if ( ofn != null ) {
			// 사진 파일의 확장자 따로 분리하여 저장
			int dotIdx = ofn.lastIndexOf('.');
			String extension = ofn.substring(dotIdx);
			System.out.println("extension["+extension+"]");
			
			File file = mpr.getFile("fsn_q");
			
			String fsn_q = UUID.randomUUID().toString().substring(0, 31) + extension;
			file.renameTo( new File( Util.uploadDir() + fsn_q) );
			System.out.println("fsn_q : " + fsn_q);
			pvo.setFsn_q(fsn_q);
		}
		
		postDAO.add(subject, pvo);
		
		return "redirect:sub_board.do?subject="+subject;
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
	
	
	@RequestMapping("/subs.do")
	public String subs(HttpSession session) throws Exception {
		System.out.println("ControllerPost:: subs");
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		return "subs";
	}
	
	
	@RequestMapping("/sub_board.do")
	public ModelAndView sub_board(@RequestParam("subject") String subject, HttpSession session)
			throws Exception
	{
		System.out.println("ControllerPost:: sub_board:: " + subject );
		ModelAndView mnv = new ModelAndView();
		
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		List<PostVO> rList = postDAO.findAll(subject);
		
		
		mnv.setViewName("sub_board");
		mnv.addObject("subject", subject);
		mnv.addObject("rList", rList);
		
		return mnv;
	}
	
	
	@RequestMapping("/write.do")
	public ModelAndView ask
		(@RequestParam("subject") String subject, HttpSession session) throws Exception
	{
		System.out.println("ControllerPost:: ask:: " + subject );
		ModelAndView mnv = new ModelAndView();
		
		Integer stid = (Integer)session.getAttribute("stid");
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		mnv.setViewName("write");
		mnv.addObject("subject", subject);
		
		return mnv;
	}
	
}