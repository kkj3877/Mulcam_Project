package Model;

public class StudentVO {
	
	// 학번
	private Integer stid = null;
	public Integer getStid() { return stid; }
	public void setStid( Integer stid ) { this.stid = stid; }
	
	// 비밀번호
	private String pw = null;
	public String getPw() { return pw; }
	public void setPw(String pw) { this.pw = pw; }
	
	// 학생 이름
	private String name = null;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	// 메일 주소
	private String mail = null;
	public String getMail() { return mail; }
	public void setMail(String mail) { this.mail = mail; }
	
}
