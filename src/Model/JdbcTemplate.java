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
	
	// SQL 문의 ? 를 채우고 update 처리하여 영향받은 레코드 수를 반환하는 함수
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
	
	// PreparedStatment 를 만드는 규칙이 명시된 psc 를 입력받아 update 처리하는 함수
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
	
	
	// ? 가 필요 없는 SQL문에 대한 Query 명령을 처리하고 List<T> 를 반환하는 함수 
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
			
			// mapRow에 명시되어 있는 규칙에 따라 레코드를 인스턴스화 하여 List에 추가
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
	
	// ? 가 있는 SQL 문에 대한 Query 명령을 처리하고 List<T> 를 반환하는 함수
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
			pss.setValues(stmt); // setValues 에 명시된 규칙에 따라 ? 를 채워넣음
			rs = stmt.executeQuery();
			
			// mapRow에 명시되어 있는 규칙에 따라 레코드를 인스턴스화 하여 List에 추가
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
	
	
	// query 문으로 하나의 레코드를 찾아 인스턴스를 반환하는 함수
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
