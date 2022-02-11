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
	// Subject_T 에 레코드를 하나 추가하는 함수
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
		
		// SQL 문의 ? 를 채워주기 위한 setValues 오버라이딩
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
	// Subject_T 의 특정 PK 를 가진 레코드에 답변 정보를 저장하는 함수 
	@Override
	public int ansToPost(String subject, PostVO pvo) throws Exception {
		String tableName = subject+"_T";
		Integer no = pvo.getNo();
		System.out.println("PostDAO_MariaImpl:: ansToPost("+subject+", "+no+")");
		
		String sql = "UPDATE "+tableName+" SET ans=?, fsn_a=? WHERE no=?";
		
		String ans = pvo.getAns();
		String fsn_a = pvo.getFsn_a();
				
		// SQL 문으로 stmt 를 만들고 ? 를 채워넣어 준비시켜주는 psc
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
	// Subject_T 의 특정 PK 를 가진 레코드의 데이터를 변경하는 함수
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
		
		// SQL 문의 ? 를 채워주기 위한 setValues 오버라이딩
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
	// Subject_T 에서 특정 PK 를 가진 레코드를 제거하는 함수
	@Override
	public int delByNo(String subject, PostVO pvo) throws Exception {
		String tableName = subject+"_T";
		Integer no = pvo.getNo();
		System.out.println("PostDAO_MariaImpl:: delByNo("+subject+", "+no+")");
		
		String sql = "DELETE FROM "+tableName+" where no=?";
		
		// SQL 문으로 stmt 를 만들고 ? 를 채워넣어 준비시켜주는 psc
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				
				return stmt;
			}
		};
		
		// Subject_T 에서 특정 PK 의 레코드를 제거하는 SQL 문 실행
		int uc = jtpl.update(psc);
		
		// 만약 게시물 레코드가 삭제되었다면 해당 게시물의 열람자 목록도 삭제
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
	// Subject_T 의 모든 레코드를 PostVO 인스턴스화시켜 List에 저장한 후 List 를 반환하는 함수
	@Override
	public List<PostVO> findAll(String subject) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findAll("+subject+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" ORDER BY no DESC";
		
		// Record 를 인스턴스화 하기 위한 mapRow 오버라이딩
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
		
		// SQL 문을 실행하고, rowMapper의 mapRow에 명시된 규칙대로 레코드들을
		// List 화 시켜 반환한다.
		return jtpl.query(sql, rowMapper);
	}
	
	
	// -------------------------------------------------------------------------------
	// Subject_T 의 특정 챕터 레코드를 리스트에 저장해 반환하는 함수
	@Override
	public List<PostVO> findPostByCh(String subject, Integer ch) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByCh("+subject+", "+ch+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE ch=? ORDER BY no DESC";
		
		// SQL 문의 ? 를 채워주기 위한 setValues 오버라이딩
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, ch);
			}
		};
		
		// Record 를 인스턴스화 하기 위한 mapRow 오버라이딩
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
		
		// SQL 문을 실행하고, rowMapper의 mapRow에 명시된 규칙대로 레코드들을
		// List 화 시켜 반환한다.
		return jtpl.query(sql, pss, rowMapper);
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T 의 특정 번호 레코드를 리스트에 저장해 반환하는 함수
	@Override
	public PostVO findPostByNo(String subject, Integer no, Integer stid) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByNo("+subject+", "+no+") by-"+stid);
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE no=?";
		
		// SQL 문의 ? 를 채워주기 위한 setValues 오버라이딩
		PreparedStatementSetter pssNo = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, no);
			}
		};
		
		// Record 를 인스턴스화 하기 위한 mapRow 오버라이딩
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
		
		// 특정 레코드를 찾아서 인스턴스화 하여 반환
		PostVO vo = jtpl.queryForObject(sql, pssNo, rowMapper);
		
		// 만약 조건을 만족하는 레코드를 찾았다면 열람자 목록 최신화
		if (vo != null) {
			String viewTableName = subject+"_Viewer_T";
			sql = "SELECT * FROM "+viewTableName+" WHERE no = ? AND stid = ?";
			System.out.println(sql);
			
			// SQL 문의 ? 를 채워주기 위한 setValues 오버라이딩
			PreparedStatementSetter pssNoStid = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement stmt) throws Exception {
					stmt.setInt(1, no);
					stmt.setInt(2, stid);
				}
			};
			
			// 해당 게시물을 현재 로그인 한 사람이 본 적 있는지 검사
			int count = jtpl.checkObject(sql, pssNoStid);
			System.out.println("count:" + count);
			
			// 만약 현재 로그인 한 사람이 해당 게시물을 처음 본다면 열람자 목록에
			// 게시글-유저 를 넣은 후 게시글의 열람자 수와 조회수에 1을 더함
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
			// 현재 로그인 한 사람이 해당 게시물을 본적 있다면 조회수만 1 더함
			else sql = "UPDATE "+tableName+" SET views = views + 1 WHERE no = ?;";
			
			jtpl.update(sql, pssNo);
		}
		
		return vo;
	}
	
	// -------------------------------------------------------------------------------
	// Subject_T 의 특정 학번의 레코드를 리스트에 저장해 반환하는 함수
	@Override
	public List<PostVO> findPostByStid(String subject, Integer stid) throws Exception {
		System.out.println("PostDAO_MariaImpl:: findPostByStid("+subject+", "+stid+")");
		String tableName = subject+"_T";
		String sql = "SELECT * FROM "+tableName+" WHERE stid=? ORDER BY no DESC";
		
		// SQL 문의 ? 를 채워주기 위한 setValues 오버라이딩
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, stid);
			}
		};
		
		// Record 를 인스턴스화 하기 위한 mapRow 오버라이딩
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
		
		// SQL 문을 실행하고, rowMapper의 mapRow에 명시된 규칙대로 레코드들을
		// List 화 시켜 반환한다.
		return jtpl.query(sql, pss, rowMapper);
	}
	
}