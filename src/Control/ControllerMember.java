package Control;

import Model.JdbcTemplate;

@Control
public class ControllerMember {
	
	private JdbcTemplate jtpl = null;
	public JdbcTemplate getJtpl() { return jtpl; }
	public void setJtpl(JdbcTemplate jtpl) { this.jtpl = jtpl; }
	
	
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
	
	
	@RequestMapping("/login.do")
	public String login() throws Exception
	{
		System.out.println("ControllerMember:: login");
		
		return "login";
	}
	
}