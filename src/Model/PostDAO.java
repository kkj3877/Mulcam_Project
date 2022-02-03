package Model;

import java.util.List;

// 게시글과 관련된 작업들을 수행하는 인터페이스
public interface PostDAO {
	// 대상 테이블 : 질문 테이블 ( Subject_T )
	
	// 테이블의 모든 레코드를 리스트에 저장해 반환하는 함수
	public List<PostVO> findAll( String subject ) throws Exception;
	
	// 테이블에 레코드를 하나 추가하는 함수
	public int add( String subject, PostVO pvo ) throws Exception;
	
	// Bang_T 에서 특정 PK 를 가진 레코드를 제거하는 함수
	public int delByPK( String subject, PostVO pvo ) throws Exception;
}
