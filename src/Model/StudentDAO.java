package Model;

import java.util.List;

public interface StudentDAO {
	
	// 모든 학생 List 를 반환하는 함수
	public List<StudentVO> findAll() throws Exception;
	
	// 학번으로 학생을 찾는 함수 있으면 true, 없으면 false 반환
	public boolean findByStid( StudentVO pvo ) throws Exception;
	
	// 학생을 테이블에 추가하는 함수
	public int add( StudentVO pvo ) throws Exception;
	
	// 학생의 id/pw 로 로그인 시도를 하고
	// 성공하면 0, 없는 아이디면 1, 비밀번호가 틀리면 2를 반환
	public int loginTry( StudentVO pvo ) throws Exception;
	
}
