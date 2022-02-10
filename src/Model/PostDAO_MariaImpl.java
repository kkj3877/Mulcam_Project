package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostDAO_MariaImpl implements PostDAO {
	
	private JdbcTemplate jtpl = null;
	
	public PostDAO_MariaImpl(JdbcTemplate jtpl) {
		System.out.println("Create Instance : PostDAO_MariaImpl");
		this.jtpl = jtpl;
	}
	
	
	// -------------------------------------------------------------------------------
	// Subject_T �� ���ڵ带 �ϳ� �߰��ϴ� �Լ�
	@Override
	public int add(String subject, PostVO pvo) throws Exception {
		System.out.println("PostDAO_MariaImpl:: add("+subject+")");
		String tableName = subject+"_T";
		String sql = "INSERT INTO "+tableName+" VALUES(DEFAULT,?,?,?,?,NULL,?,NULL,0)";
		
		final Integer stid = pvo.getStid();
		final Integer ch = pvo.getCh();
		final String title = pvo.getTitle();
		final String content = pvo.getContent();
		final String fsn_q = pvo.getFsn_q();
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, stid);
				stmt.setInt(2, ch);
				stmt.setString(3, title);
				stmt.setString(4, content);
				stmt.setString(5, fsn_q);
			}
		};
		
		System.out.println(stid+", "+ch+", "+title+", "+content+", "+fsn_q);
		return jtpl.update(sql, pss);
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T �� Ư�� PK �� ���� ���ڵ忡 �亯 ������ �����ϴ� �Լ� 
	@Override
	public int ansToPost(String subject, PostVO pvo) throws Exception {
		String tableName = subject+"_T";
		Integer no = pvo.getNo();
		System.out.println("PostDAO_MariaImpl:: ansToPost("+subject+", "+no+")");
		
		String sql = "UPDATE "+tableName+" SET ans=?, fsn_a=? WHERE no=?";
		
		String ans = pvo.getAns();
		String fsn_a = pvo.getFsn_a();
				
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, ans);
				stmt.setString(2, fsn_a);
				stmt.setInt(3, no);
				
				return stmt;
			}
		};
		
		int uc = jtpl.update(psc);
		
		return uc;
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T �� Ư�� PK �� ���� ���ڵ��� �����͸� �����ϴ� �Լ�
	@Override
	public int changePost(String subject, PostVO pvo) throws Exception {
		String tableName = subject+"_T";
		Integer no = pvo.getNo();
		System.out.println("PostDAO_MariaImpl:: changePost("+subject+", "+no+")");
		
		String sql = "UPDATE "+tableName+" SET ch=?, title=?, content=?, fsn_q=? WHERE no = ?;";
		Integer ch = pvo.getCh();
		String title = pvo.getTitle();
		String content = pvo.getContent();
		String fsn_q = pvo.getFsn_q();
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, ch);
				stmt.setString(2, title);
				stmt.setString(3, content);
				stmt.setString(4, fsn_q);
				stmt.setInt(5, no);
			}
		};
		
		int uc = jtpl.update(sql, pss);
		
		return uc;
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T ���� Ư�� PK �� ���� ���ڵ带 �����ϴ� �Լ�
	@Override
	public int delByNo(String subject, PostVO pvo) throws Exception {
		String tableName = subject+"_T";
		Integer no = pvo.getNo();
		System.out.println("PostDAO_MariaImpl:: delByNo("+subject+", "+no+")");
		
		String sql = "DELETE FROM "+tableName+" where no=?";
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				
				return stmt;
			}
		};
		
		int uc = jtpl.update(psc);
		
		return uc;
	}
	
	
	// -------------------------------------------------------------------------------
	// Subject_T �� ��� ���ڵ带 PostVO �ν��Ͻ�ȭ���� List�� ������ �� List �� ��ȯ�ϴ� �Լ�
	@Override
	public List<PostVO> findAll(String subject) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findAll("+subject+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" ORDER BY no DESC";
		
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
				vo.setFsn_q(rs.getString("fsn_q"));
				vo.setFsn_a(rs.getString("fsn_a"));
				vo.setViews(rs.getInt("views"));
				
				return vo;
			}
		};
		
		// SQL ���� �����ϰ�, rowMapper�� mapRow�� ��õ� ��Ģ��� ���ڵ����
		// List ȭ ���� ��ȯ�Ѵ�.
		return jtpl.query(sql, rowMapper);
	}
	
	
	// -------------------------------------------------------------------------------
	// Subject_T �� Ư�� é�� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	@Override
	public List<PostVO> findPostByCh(String subject, Integer ch) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByCh("+subject+", "+ch+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE ch=? ORDER BY no DESC";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, ch);
			}
		};
		
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
				vo.setFsn_q(rs.getString("fsn_q"));
				vo.setFsn_a(rs.getString("fsn_a"));
				vo.setViews(rs.getInt("views"));
				
				return vo;
			}
		};
		
		// SQL ���� �����ϰ�, rowMapper�� mapRow�� ��õ� ��Ģ��� ���ڵ����
		// List ȭ ���� ��ȯ�Ѵ�.
		return jtpl.query(sql, pss, rowMapper);
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T �� Ư�� ��ȣ ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	@Override
	public PostVO findPostByNo(String subject, Integer no) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByNo("+subject+", "+no+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE no=?";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, no);
			}
		};
		
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
				vo.setFsn_q(rs.getString("fsn_q"));
				vo.setFsn_a(rs.getString("fsn_a"));
				vo.setViews(rs.getInt("views") + 1);
				
				return vo;
			}
		};
		
		PostVO vo = jtpl.queryForObject(sql, pss, rowMapper);
		if (vo != null) {
			String sql2 = "UPDATE "+tableName+" SET views = views + 1 WHERE no = ?;";
			jtpl.update(sql2, pss);
		}
		
		return vo;
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T �� Ư�� �й��� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	@Override
	public List<PostVO> findPostByStid(String subject, Integer stid) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByStid("+subject+", "+stid+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE stid=? ORDER BY no DESC";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, stid);
			}
		};
		
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
				vo.setFsn_q(rs.getString("fsn_q"));
				vo.setFsn_a(rs.getString("fsn_a"));
				vo.setViews(rs.getInt("views"));
				
				return vo;
			}
		};
		
		// SQL ���� �����ϰ�, rowMapper�� mapRow�� ��õ� ��Ģ��� ���ڵ����
		// List ȭ ���� ��ȯ�Ѵ�.
		return jtpl.query(sql, pss, rowMapper);
	}
	
}