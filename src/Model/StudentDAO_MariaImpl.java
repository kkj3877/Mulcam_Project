package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO_MariaImpl implements StudentDAO {
	
	private JdbcTemplate jtpl = null;
	
	public StudentDAO_MariaImpl(JdbcTemplate jtpl) {
		this.jtpl = jtpl;
	}
	
	
	// 학번으로 학생을 찾는 함수 있으면 1, 없으면 0 반환
	@Override
	public boolean findByStid(StudentVO pvo) throws Exception {
		boolean isExist = false;
		
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
				vo.setName(rs.getString("name"));
				return vo;
			}
		};
		
		List<StudentVO> ls = jtpl.query(sql, pss, rowMapper);
		if (ls.size() != 0) {
			isExist = true;
		}
		
		return isExist;
	}


	@Override
	public int add(StudentVO pvo) throws Exception {
		System.out.println("StudnetDAO::add( )");
		
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

}
