package Model;

public class PostVO {
	
	// 게시글 번호
	private Integer no = null;
	public Integer getNo() { return no; }
	public void setNo(Integer no) { this.no = no; }

	// 학번(FK From StudentVO)
	private Integer stid = null;
	public Integer getStid() { return stid; }
	public void setStid( Integer stid ) { this.stid = stid; }
	
	// 챕터 번호
	private Integer ch = null;
	public Integer getCh() { return ch; }
	public void setCh(Integer ch) { this.ch = ch; }
	
	// 질문 제목
	private String title = null;
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	// 질문 내용
	private String content = null;
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	// 답변 내용
	private String ans = null;
	public String getAns() { return ans; }
	public void setAns(String ans) { this.ans = ans; }

	// 질문 사진 UUID
	private String fsn_q = null;
	public String getFsn_q() { return fsn_q; }
	public void setFsn_q(String fsn_q) { this.fsn_q = fsn_q; }

	// 답변 사진 UUID
	private String fsn_a = null;
	public String getFsn_a() { return fsn_a; }
	public void setFsn_a(String fsn_a) { this.fsn_a = fsn_a; }
	
}
