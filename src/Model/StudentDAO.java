package Model;

import java.util.List;

public interface StudentDAO {
	
	// 학생을 테이블에 추가하는 함수
	public int add( StudentVO pvo ) throws Exception;
	
	// 학번으로 학생을 찾아 테이블에서 제거하는 함수
	public int delStudentByStid( StudentVO pvo ) throws Exception;
	
	// 모든 학생 List 를 반환하는 함수
	public List<StudentVO> findAll() throws Exception;
	
	// 학번으로 학생을 찾는 함수. 해당 학번 학생이 있으면 이름을, 없으면 NULL 반환
	public String findNameByStid( StudentVO pvo ) throws Exception;
	
	// 학생의 id/pw 로 로그인 시도를 하고
	// 성공하면 학생의 이름을, 없는 학번이면 NULL, 비밀번호가 틀리면 '0'을 반환
	public String loginTry( StudentVO pvo ) throws Exception;
	
}
