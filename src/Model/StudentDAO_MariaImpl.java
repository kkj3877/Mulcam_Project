package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO_MariaImpl implements StudentDAO {
	
	private JdbcTemplate jtpl = null;
	
	public StudentDAO_MariaImpl(JdbcTemplate jtpl) {
		System.out.println("Create Instance : PostDAO_MariaImple");
		this.jtpl = jtpl;
	}
	
	
	// -------------------------------------------------------------------------------
	// 학생을 테이블에 추가하는 함수
	@Override
	public int add(StudentVO pvo) throws Exception {
		System.out.println("StudentDAO::add()");
		
		int uc = -1;
		
		String sql = "INSERT INTO Student_T VALUES(?,?,?,?)";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, pvo.getStid());
				stmt.setString(2, pvo.getPw());
				stmt.setString(3, pvo.getName());
				stmt.setString(4, pvo.getMail());
			}
		};
		
		uc = jtpl.update(sql, pss);
		
		return uc;
	}
	
	// -------------------------------------------------------------------------------
	// 학번으로 학생을 찾아 테이블에서 제거하는 함수
	@Override
	public int delStudentByStid(StudentVO pvo) throws Exception {
		System.out.println("StudentDAO::delStudentByStid( )");
		
		Integer stid = pvo.getStid();
		if (stid == null) return -1;
		
		String sql = "DELETE FROM Student_T WHERE stid=?";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, stid);
			}
		};
		
		int uc = jtpl.update(sql, pss);
		
		if (uc == 1) System.out.println("Student("+stid+") is deleted from table");
		else System.out.println("failed to delete Student("+stid+") from table");
		
		return uc;
	}
	
	
	// -------------------------------------------------------------------------------
	// 모든 학생 리스트를 반환하는 함수
	@Override
	public List<StudentVO> findAll() throws Exception {
		System.out.println("StudentDAO::findAll( )");
		
		String sql = "SELECT * FROM Student_T ORDER BY stid";
		
		RowMapper<StudentVO> rowMapper = new RowMapper<StudentVO>() {
			@Override
			public StudentVO mapRow(ResultSet rs) throws SQLException {
				StudentVO vo = new StudentVO();
				vo.setStid(rs.getInt("stid"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
				vo.setMail(rs.getString("mail"));
				return vo;
			}
		};
		
		return jtpl.query(sql, rowMapper);
	}
	
	
	// -------------------------------------------------------------------------------
	// 학번으로 학생을 찾는 함수. 해당 학번 학생이 있으면 이름을, 없으면 NULL 반환
	@Override
	public String findNameByStid(StudentVO pvo) throws Exception {
		System.out.println("StudentDAO::findByStid( )");
		
		String name = null;
		
		String sql = "SELECT * FROM Student_T where stid=?";
		Integer stid = pvo.getStid();
		// final Integer stid = pvo.getStid();
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, stid);
			}
		};
		
		RowMapper<StudentVO> rowMapper = new RowMapper<StudentVO>() {
			@Override
			public StudentVO mapRow(ResultSet rs) throws SQLException {
				StudentVO vo = new StudentVO();
				vo.setName(rs.getString("name"));
				return vo;
			}
		};
		
		List<StudentVO> ls = jtpl.query(sql, pss, rowMapper);
		if (!ls.isEmpty()) {
			name = ls.get(0).getName();
			System.out.println("name = "+name);
		}
		else {
			System.out.println("There is no student have stid:"+stid);
		}
		
		return name;
	}
	
	
	// -------------------------------------------------------------------------------
	// 학생의 id/pw 로 로그인 시도를 하고
	// 성공하면 학생의 이름을, 없는 학번이면 NULL, 비밀번호가 틀리면 '0'을 반환
	@Override
	public String loginTry(StudentVO pvo) throws Exception {
		System.out.println("StudentDAO::loginTry( )");
		
		String sql = "SELECT * FROM Student_T where stid=?";
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws Exception {
				stmt.setInt(1, pvo.getStid());
			}
		};
		
		RowMapper<StudentVO> rowMapper = new RowMapper<StudentVO>() {
			@Override
			public StudentVO mapRow(ResultSet rs) throws SQLException {
				StudentVO vo = new StudentVO();
				vo.setStid(rs.getInt("stid"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
				return vo;
			}
		};
		
		StudentVO vo = jtpl.queryForObject(sql, pss, rowMapper);
		if ( vo == null ) { return null; }
		if ( !pvo.getPw().equals(vo.getPw()) ) { return "0"; }
		return vo.getName();
	}

}
