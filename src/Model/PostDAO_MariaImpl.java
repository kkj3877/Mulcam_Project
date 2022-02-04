package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostDAO_MariaImpl implements PostDAO {
	
	private JdbcTemplate jtpl = null;
	
	public PostDAO_MariaImpl(JdbcTemplate jtpl) {
		this.jtpl = jtpl;
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T 의 모든 레코드를 PostVO 인스턴스화시켜 List에 저장한 후 List 를 반환하는 함수
	@Override
	public List<PostVO> findAll(String subject) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findAll("+subject+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM12 "+tableName+" ORDER BY no DESC";
		
		RowMapper<PostVO> rowMapper = new RowMapper<PostVO>() {
			@Override
			public PostVO mapRow(ResultSet rs) throws SQLException {
				PostVO vo = new PostVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setStid(rs.getInt("stid"));
				vo.setCh(rs.getInt("ch"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAns(rs.getString("ans"));
				vo.setFsn_a(rs.getString("fsn_q"));
				vo.setFsn_q(rs.getString("fsn_a"));
				
				return vo;
			}
		};
		
		// SQL 문을 실행하고, rowMapper의 mapRow에 명시된 규칙대로 레코드들을
		// List 화 시켜 반환한다.
		return jtpl.query(sql, rowMapper);
	}
	
	// -------------------------------------------------------------------------------
	// Bang_T 에 레코드를 하나 추가하는 함수
	@Override
	public int add(String subject, PostVO pvo) throws Exception {
		System.out.println("PostDAO_MariaImpl:: add("+subject+")");
		
		/*
		String sql = "INSERT INTO ? VALUES(DEFAULT,?,?)";
		String content = pvo.getContent();
		String author = pvo.getAuthor();
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setString(1, content);
				stmt.setString(2, author);
			}
		};
		
		return jtpl.update(sql, pss);
		*/
		return 0;
	}
	
	// -------------------------------------------------------------------------------
	// Bang_T 에서 특정 PK 를 가진 레코드를 제거하는 함수
	@Override
	public int delByPK(String subject, PostVO pvo) throws Exception {
		System.out.println("PostDAO_MariaImpl:: delByPK("+subject+")");
		
		
		/*
		String sql = "DELETE FROM Bang_T where no=?";
		int no = pvo.getNo();
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				
				return stmt;
			}
		};
		
		return jtpl.update(psc);
		*/
		return 0;
	}

}