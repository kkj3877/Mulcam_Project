package Model;

public interface StudentDAO {
	
	// 학번으로 학생을 찾는 함수 있으면 1, 없으면 0 반환
	public boolean findByStid( StudentVO pvo ) throws Exception;
	
	// 학생을 테이블에 추가하는 함수
	public int add( StudentVO pvo ) throws Exception;
}
