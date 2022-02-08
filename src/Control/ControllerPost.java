package Control;

import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.PostVO;
import Model.StudentDAO;
import Model.StudentDAO_MariaImpl;
import Model.StudentVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			return "redirect:write.do?subject="+subject+"&ecode="+errorString;
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
	public ModelAndView sub_board(@RequestParam("subject") String subject, @RequestParam("ch") Integer ch, HttpSession session)
			throws Exception
	{
		System.out.println("ControllerPost:: sub_board:: " + subject );
		
		if ( subject != null && ch != null ) {
			System.out.println("subject: " + subject + ", ch: "+ ch);
		}
		else { System.out.println("Something is wrong"); }
		
		ModelAndView mnv = new ModelAndView();
		
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		List<PostVO> rList = null;
		
		if ( ch == null ) rList = postDAO.findAll(subject);
		else rList = postDAO.findPostByCh(subject, ch);
		
		
		mnv.setViewName("sub_board");
		mnv.addObject("subject", subject);
		mnv.addObject("rList", rList);
		
		return mnv;
	}
	
	
	@RequestMapping("/view_article.do")
	public ModelAndView viewArticle
		(@RequestParam("subject") String subject, @RequestParam("no") Integer no,
			HttpSession session) throws Exception
	{
		System.out.println("ControllerPost:: viewArticle:: " + subject + ", " + no);
		ModelAndView mnv = new ModelAndView();
		
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		PostVO vo = postDAO.findPostByNo(subject, no);
		
		if ( vo == null ) {
			System.out.println("post(no="+no+")is not in DB");
			mnv.setViewName("redirect:sub_board.do?subject="+subject+"&ecode=articleDB_error");
			return mnv;
		}
		
		mnv.setViewName("view_article");
		// 게시글의 레코드를 article 이라는 이름의 속성으로 넣는다.
		mnv.addObject("article", vo);
		mnv.addObject("subject", subject);
		
		// 게시글에 사진이 등록돼있다면 사진의 경로를 보내준다.
		if ( vo.getFsn_q() != null ) {
			mnv.addObject("fsn_q", Util.fileDir() + vo.getFsn_q());
			System.out.println("fsn_q: " + Util.fileDir() + vo.getFsn_q());
		}
		if ( vo.getFsn_a() != null ) {
			mnv.addObject("fsn_a", Util.fileDir() + vo.getFsn_a());
			System.out.println("fsn_a: " + Util.fileDir() + vo.getFsn_a());
		}
		
		return mnv;
	}
	
	
	@RequestMapping("/view_pic.do")
	public void viewPic
		(@RequestParam("pic_name") String picName, HttpServletResponse response) throws Exception
	{
		if (picName == null) { picName = "Tree.jpg"; }
		
		String extension = picName.substring(picName.lastIndexOf(".") + 1);
		System.out.println("picName: " + picName);
		System.out.println("extension: " + extension);
		
		File file = new File( Util.uploadDir() + picName );
		if ( file.exists() ) {
			InputStream in = new FileInputStream(file);
			String contentType = "image/"+extension;
			response.setContentType(contentType);
			
			OutputStream out = response.getOutputStream();
			int len = 0;
			byte[] buf = new byte[1024];
			
			while ( (len = in.read( buf )) != -1 ) {
				out.write( buf, 0, len );
				out.flush();
			}
			out.close();
			in.close();
		}
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
