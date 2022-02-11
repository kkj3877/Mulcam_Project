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
		String sql = "INSERT INTO "+tableName+" VALUES(DEFAULT,?,?,?,?,NULL,?,NULL,0, 0)";
		
		final Integer stid = pvo.getStid();
		final Integer ch = pvo.getCh();
		final String title = pvo.getTitle();
		final String content = pvo.getContent();
		final String fsn_q = pvo.getFsn_q();
		
		// SQL ���� ? �� ä���ֱ� ���� setValues �������̵�
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
				
		// SQL ������ stmt �� ����� ? �� ä���־� �غ�����ִ� psc
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
		
		// SQL ���� ? �� ä���ֱ� ���� setValues �������̵�
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
		
		// SQL ������ stmt �� ����� ? �� ä���־� �غ�����ִ� psc
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				
				return stmt;
			}
		};
		
		// Subject_T ���� Ư�� PK �� ���ڵ带 �����ϴ� SQL �� ����
		int uc = jtpl.update(psc);
		
		// ���� �Խù� ���ڵ尡 �����Ǿ��ٸ� �ش� �Խù��� ������ ��ϵ� ����
		if (uc != 0) {
			String viewTableName = subject+"_Viewer_T";
			String sql2 = "DELETE FROM "+viewTableName+" WHERE no = ?";
			System.out.println(sql2);
			
			PreparedStatementSetter pssNo = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement stmt) throws Exception {
					stmt.setInt(1, no);
				}
			};
			int count = jtpl.update(sql2, pssNo);
			System.out.println("count:" + count);
		}
		
		return uc;
	}
	
	
	// -------------------------------------------------------------------------------
	// Subject_T �� ��� ���ڵ带 PostVO �ν��Ͻ�ȭ���� List�� ������ �� List �� ��ȯ�ϴ� �Լ�
	@Override
	public List<PostVO> findAll(String subject) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findAll("+subject+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" ORDER BY no DESC";
		
		// Record �� �ν��Ͻ�ȭ �ϱ� ���� mapRow �������̵�
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
				vo.setViewer(rs.getInt("viewer"));
				
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
		
		// SQL ���� ? �� ä���ֱ� ���� setValues �������̵�
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, ch);
			}
		};
		
		// Record �� �ν��Ͻ�ȭ �ϱ� ���� mapRow �������̵�
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
				vo.setViewer(rs.getInt("viewer"));
				
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
	public PostVO findPostByNo(String subject, Integer no, Integer stid) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByNo("+subject+", "+no+") by-"+stid);
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE no=?";
		
		// SQL ���� ? �� ä���ֱ� ���� setValues �������̵�
		PreparedStatementSetter pssNo = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, no);
			}
		};
		
		// Record �� �ν��Ͻ�ȭ �ϱ� ���� mapRow �������̵�
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
				vo.setViewer(rs.getInt("viewer"));
				
				return vo;
			}
		};
		
		// Ư�� ���ڵ带 ã�Ƽ� �ν��Ͻ�ȭ �Ͽ� ��ȯ
		PostVO vo = jtpl.queryForObject(sql, pssNo, rowMapper);
		
		// ���� ������ �����ϴ� ���ڵ带 ã�Ҵٸ� ������ ��� �ֽ�ȭ
		if (vo != null) {
			String viewTableName = subject+"_Viewer_T";
			sql = "SELECT * FROM "+viewTableName+" WHERE no = ? AND stid = ?";
			System.out.println(sql);
			
			// SQL ���� ? �� ä���ֱ� ���� setValues �������̵�
			PreparedStatementSetter pssNoStid = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement stmt) throws Exception {
					stmt.setInt(1, no);
					stmt.setInt(2, stid);
				}
			};
			
			// �ش� �Խù��� ���� �α��� �� ����� �� �� �ִ��� �˻�
			int count = jtpl.checkObject(sql, pssNoStid);
			System.out.println("count:" + count);
			
			// ���� ���� �α��� �� ����� �ش� �Խù��� ó�� ���ٸ� ������ ��Ͽ�
			// �Խñ�-���� �� ���� �� �Խñ��� ������ ���� ��ȸ���� 1�� ����
			if (count == 0) {
				String sql2 = "INSERT INTO "+viewTableName+ " VALUES(?,?)";
				PreparedStatementSetter pss2 = new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement stmt) throws Exception {
						stmt.setInt(1, no);
						stmt.setInt(2, stid);
					}
				};
				jtpl.update(sql2, pss2);
				
				sql = "UPDATE "+tableName+" SET views = views + 1, viewer = viewer + 1 WHERE no = ?;";
			}
			// ���� �α��� �� ����� �ش� �Խù��� ���� �ִٸ� ��ȸ���� 1 ����
			else sql = "UPDATE "+tableName+" SET views = views + 1 WHERE no = ?;";
			
			jtpl.update(sql, pssNo);
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
		
		// SQL ���� ? �� ä���ֱ� ���� setValues �������̵�
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, stid);
			}
		};
		
		// Record �� �ν��Ͻ�ȭ �ϱ� ���� mapRow �������̵�
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
				vo.setViewer(rs.getInt("viewer"));
				
				return vo;
			}
		};
		
		// SQL ���� �����ϰ�, rowMapper�� mapRow�� ��õ� ��Ģ��� ���ڵ����
		// List ȭ ���� ��ȯ�Ѵ�.
		return jtpl.query(sql, pss, rowMapper);
	}
	
}