package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicDataSource {
	private String driverClassName = null;
	public String getDriverClassName() { return driverClassName; }
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	private String url = null;
	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	
	private String username = null;
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	private String password = null;
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }	
	
	
	public BasicDataSource() { System.out.println(">> BasicDataSource()"); }
	
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		}
		catch ( ClassNotFoundException | SQLException e ) { throw e; }
		
		return conn;
	}
	
	
	public void close() {}
}
