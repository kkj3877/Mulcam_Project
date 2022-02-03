package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	private BasicDataSource dataSource = null;
	
	public JdbcTemplate( BasicDataSource dataSource ) {
		this.dataSource = dataSource;
	}
	
	// SQL ���� ? �� ä��� update ó���Ͽ� ������� ���ڵ� ���� ��ȯ�ϴ� �Լ�
	public int update(String sql, PreparedStatementSetter pss) throws Exception {
		int uc = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			pss.setValues(stmt);
			uc = stmt.executeUpdate();
		}
		catch ( SQLException e ) { throw e; }
		finally {
			try {
				if ( stmt != null ) stmt.close();
				if ( conn != null ) conn.close();				
			}
			catch ( SQLException e ) { throw e; }
		}
		
		return uc;
	}
	
	// PreparedStatment �� ����� ��Ģ�� ��õ� psc �� �Է¹޾� update ó���ϴ� �Լ�
	public int update(PreparedStatementCreator psc) throws Exception {
		int uc = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = psc.createPreparedStatement(conn);
			if ( stmt == null ) {
				throw new Exception("invalid Statment");
			}
			uc = stmt.executeUpdate();
		}
		catch ( SQLException e ) { throw e; }
		finally {
			try {
				if ( stmt != null ) stmt.close();
				if ( conn != null ) conn.close();				
			}
			catch ( SQLException e ) { throw e; }
		}
		
		return uc;
	}
	
	
	// ? �� �ʿ� ���� SQL���� ���� Query ����� ó���ϰ� List<T> �� ��ȯ�ϴ� �Լ� 
	public <T> List<T> query(String sql, RowMapper<T> rowMapper )
		throws ClassNotFoundException, SQLException
	{
		List<T> rl = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			// mapRow�� ��õǾ� �ִ� ��Ģ�� ���� ���ڵ带 �ν��Ͻ�ȭ �Ͽ� List�� �߰�
			while ( rs.next() ) {
				T vo = rowMapper.mapRow(rs);
				rl.add(vo);
			}
		}
		catch ( SQLException e ) { throw e; }
		finally {
			try {
				if ( rs != null ) rs.close();
				if ( stmt != null ) stmt.close();
				if ( conn != null ) conn.close();
			}
			catch ( SQLException e ) { throw e; }
		}
		
		return rl;
	}
	
	// ? �� �ִ� SQL ���� ���� Query ����� ó���ϰ� List<T> �� ��ȯ�ϴ� �Լ�
	public <T> List<T> query
		(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper )
			throws Exception
	{
		List<T> rl = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		System.out.println(sql);
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			pss.setValues(stmt); // setValues �� ��õ� ��Ģ�� ���� ? �� ä������
			rs = stmt.executeQuery();
			
			// mapRow�� ��õǾ� �ִ� ��Ģ�� ���� ���ڵ带 �ν��Ͻ�ȭ �Ͽ� List�� �߰�
			while ( rs.next() ) {
				T vo = rowMapper.mapRow(rs);
				rl.add(vo);
			}
		}
		catch ( SQLException e ) { throw e; }
		finally {
			try {
				if ( rs != null ) rs.close();
				if ( stmt != null ) stmt.close();
				if ( conn != null ) conn.close();
			}
			catch ( SQLException e ) { throw e; }
		}
		
		return rl;
	}
	
	
	// query ������ �ϳ��� ���ڵ带 ã�� �ν��Ͻ��� ��ȯ�ϴ� �Լ�
	public <T> T queryForObject
		(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper)
			throws Exception
	{
		T vo = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			pss.setValues(stmt);
			rs = stmt.executeQuery();
			if ( rs.next() ) {
				vo = rowMapper.mapRow(rs);
				if ( rs.next() ) {
					throw new SQLException("too many record");
				}
			}
		}
		catch ( SQLException e ) { throw e; }
		finally {
			if ( rs != null ) rs.close();
			if ( stmt != null ) stmt.close();
			if ( conn != null ) conn.close();
		}
		
		return vo;
	}
	
}
