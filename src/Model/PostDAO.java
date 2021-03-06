package Model;

import java.util.List;

// 게시글과 관련된 작업들을 수행하는 인터페이스
// 대상 테이블 : 질문 테이블 ( Subject_T )
public interface PostDAO {
	
	// 테이블에 레코드를 하나 추가하는 함수
	public int add( String subject, PostVO pvo ) throws Exception;
	
	// 게시글의 답변을 저장하는 함수
	public int ansToPost( String subject, PostVO pvo ) throws Exception;
	
	// Subject_T 에서 특정 PK 를 가진 레코드를 제거하는 함수
	public int delByNo( String subject, PostVO pvo ) throws Exception;
	
	// Subject_T 의 특정 PK 를 가진 레코드의 데이터를 변경하는 함수
	public int changePost( String subject, PostVO pvo ) throws Exception;
	
	// Subject_T 의 모든 레코드를 리스트에 저장해 반환하는 함수
	public List<PostVO> findAll( String subject ) throws Exception;
	
	// Subject_T 의 특정 챕터 레코드를 리스트에 저장해 반환하는 함수
	public List<PostVO> findPostByCh( String subject, Integer ch ) throws Exception;
	
	// Subject_T 의 특정 번호 레코드를 인스턴스화하여 반환하는 함수
	public PostVO findPostByNo( String subject, Integer no, Integer stid ) throws Exception;
	
	// Subject_T 의 특정 학번 레코드를 리스트에 저장해 반환하는 함수
	public List<PostVO> findPostByStid( String subject, Integer stid ) throws Exception;
	
}
