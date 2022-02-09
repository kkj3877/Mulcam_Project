package Control;

import Model.PostDAO;
import Model.PostDAO_MariaImpl;
import Model.PostVO;

import java.io.File;
import java.io.PrintWriter;
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
	
	
	@RequestMapping("/add.do")
	public String add
	(@RequestParam("subject") String subject, @ModelAttribute("PostVO") PostVO pvo,
		HttpSession session)
			throws Exception
	{
		System.out.println("ControllerPost:: add:: " + subject);
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		postDAO.add(subject, null);
		
		return "redirect:sub_board.do?subject="+subject;
	}
	
	
	@RequestMapping("/del_post.do")
	public String delPost
	(@RequestParam("subject") String subject, @RequestParam("no") Integer no,
		HttpSession session, HttpServletResponse response)
			throws Exception
	{
		System.out.println("ControllerPost:: delPost:: " + subject+", "+no);
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		PostVO pvo = postDAO.findPostByNo(subject, no);
		System.out.println("LI_stid: " + stid);
		System.out.println("DB_stid: " + pvo.getStid());
		
		if (stid.compareTo(pvo.getStid()) != 0) {
			System.out.println("other student");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			String href = "view_article.do?subject="+subject+"&no="+no;
			writer.println("<script>alert('�ۼ��ڸ� ������ �� �ֽ��ϴ�.'); location.href='"+href+"';</script>");
			writer.close();
			
			return null;
		}
		
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
		
		return "redirect:sub_board.do?subject="+subject;
	}
	
	@RequestMapping("/mypost")
	public ModelAndView myPost(HttpSession session) throws Exception
	{
		System.out.println("ControllerPost:: myPost:: ");
		ModelAndView mnv = new ModelAndView();
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		// �� ���� ��θ� ���� �ش� �й��� �ۼ��� �� �ִ��� ã��
		List<PostVO> List_Basic = postDAO.findPostByStid("Basic", stid);
		List<PostVO> List_Calc = postDAO.findPostByStid("Calc", stid);
		List<PostVO> List_Linear = postDAO.findPostByStid("Linear", stid);
		
		// �� ������� ������ List �� ��Ƽ� ������
		mnv.addObject("rList_Basic", List_Basic);
		mnv.addObject("rList_Calc", List_Calc);
		mnv.addObject("rList_Linear", List_Linear);
		mnv.setViewName("mypost");
		
		return mnv;
	}
	
	
	@RequestMapping("/question.do")
	public String question
		(HttpServletRequest request, HttpSession session) throws Exception
	{
		System.out.println("ControllerPost:: question:: ");
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		postDAO.getClass();
		MultipartRequest mpr = new MultipartRequest(request, Util.uploadDir(), 1024*1024*16, "utf-8", null);
		
		// �����
		String subject = mpr.getParameter("subject");
		if ( subject == null ) return "redirect:subs.do?ecode=invalid_subject";
		System.out.println("subject : " + subject);
		
		String errorString = null;
		// ���� ����
		String title = mpr.getParameter("title");
		if ( title.equals("") ) errorString = "invalid_title";
		
		// ���� é��
		String ch = mpr.getParameter("ch");
		if ( ch == null ) errorString = "invalid_ch";
		System.out.println("ch : " + ch);
		
		// ���� ����
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
		
		// ���� ������ �ִٸ� ���� ���� �� UUID ����
		String ofn = mpr.getOriginalFileName("fsn_q");
		if ( ofn != null ) {
			// ���� ������ Ȯ���� ���� �и��Ͽ� ����
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
	
	
	@RequestMapping("/rewrite.do")
	public ModelAndView rewrite
		(@RequestParam("subject") String subject, @RequestParam("no") Integer no,
			HttpSession session, HttpServletResponse response) throws Exception
	{
		System.out.println("ControllerPost:: rewrite:: " + subject );
		ModelAndView mnv = new ModelAndView();
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		// �ش� ��ȣ�� ���� ã�� �Խ��ڿ� ���� ����� �����ι����� Ȯ���Ѵ�.
		PostVO pvo = postDAO.findPostByNo(subject, no);
		if (stid.compareTo(pvo.getStid()) != 0) { // �ٸ��ٸ� �ȳ��� ��� �� �Խñ۷� redirect
			System.out.println("other student");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			String href = "view_article.do?subject="+subject+"&no="+no;
			writer.println("<script>alert('�ۼ��ڸ� ������ �� �ֽ��ϴ�.'); location.href='"+href+"';</script>");
			writer.close();
			
			return null;
		}
		
		mnv.setViewName("rewrite");
		mnv.addObject("subject", subject);
		mnv.addObject("article", pvo);
		
		return mnv;
	}
	
	
	@RequestMapping("/subs.do")
	public String subs(HttpSession session) throws Exception {
		System.out.println("ControllerPost:: subs");
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			return "redirect:login.do?ecode=invalid_session";
		}
		
		return "subs";
	}
	
	
	@RequestMapping("/sub_board.do")
	public ModelAndView sub_board
		(@RequestParam("subject") String subject, @RequestParam("ch") Integer ch, HttpSession session)
			throws Exception
	{
		System.out.println("ControllerPost:: sub_board:: " + subject );
		
		ModelAndView mnv = new ModelAndView();
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
		Integer stid = (Integer)session.getAttribute("stid");
		System.out.println("stid:: " + stid);
		if (stid == null) {
			System.out.println("session is NULL");
			mnv.setViewName("redirect:login.do?ecode=invalid_session");
			return mnv;
		}
		
		// ������ ��������� �ʴٸ� subs.do �� redirect
		if ( subject == null ) {
			System.out.println("select subject");
			mnv.setViewName("redirect:subs.do");
			return mnv;
		}
		
		// �Ķ���� ���� ���� ��û �м� �� rList ä���ֱ�
		List<PostVO> rList = null;
		
		if ( ch == null ) { // �⺻ ǥ��
			System.out.println("subject: " + subject);
			rList = postDAO.findAll(subject);
		}
		else { // Ư�� é�͸� ����
			System.out.println("subject: " + subject + ", ch: "+ ch);
			rList = postDAO.findPostByCh(subject, ch);
		}
		
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
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
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
		// �Խñ��� ���ڵ带 article �̶�� �̸��� �Ӽ����� �ִ´�.
		mnv.addObject("article", vo);
		mnv.addObject("subject", subject);
		
		// �Խñۿ� ������ ��ϵ��ִٸ� ������ �̸��� �����ش�.
		if ( vo.getFsn_q() != null ) {
			mnv.addObject("fsn_q", vo.getFsn_q());
			System.out.println("fsn_q: " + vo.getFsn_q());
		}
		if ( vo.getFsn_a() != null ) {
			mnv.addObject("fsn_a", vo.getFsn_a());
			System.out.println("fsn_a: " + vo.getFsn_a());
		}
		
		return mnv;
	}
	
	
	@RequestMapping("/write.do")
	public ModelAndView write
		(@RequestParam("subject") String subject, HttpSession session) throws Exception
	{
		System.out.println("ControllerPost:: ask:: " + subject );
		ModelAndView mnv = new ModelAndView();
		
		// �α��εǾ����� �ʴٸ� login.do �� redirect
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
