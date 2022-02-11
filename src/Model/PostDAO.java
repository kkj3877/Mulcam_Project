package Model;

import java.util.List;

// �Խñ۰� ���õ� �۾����� �����ϴ� �������̽�
// ��� ���̺� : ���� ���̺� ( Subject_T )
public interface PostDAO {
	
	// ���̺� ���ڵ带 �ϳ� �߰��ϴ� �Լ�
	public int add( String subject, PostVO pvo ) throws Exception;
	
	// �Խñ��� �亯�� �����ϴ� �Լ�
	public int ansToPost( String subject, PostVO pvo ) throws Exception;
	
	// Subject_T ���� Ư�� PK �� ���� ���ڵ带 �����ϴ� �Լ�
	public int delByNo( String subject, PostVO pvo ) throws Exception;
	
	// Subject_T �� Ư�� PK �� ���� ���ڵ��� �����͸� �����ϴ� �Լ�
	public int changePost( String subject, PostVO pvo ) throws Exception;
	
	// Subject_T �� ��� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	public List<PostVO> findAll( String subject ) throws Exception;
	
	// Subject_T �� Ư�� é�� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	public List<PostVO> findPostByCh( String subject, Integer ch ) throws Exception;
	
	// Subject_T �� Ư�� ��ȣ ���ڵ带 �ν��Ͻ�ȭ�Ͽ� ��ȯ�ϴ� �Լ�
	public PostVO findPostByNo( String subject, Integer no, Integer stid ) throws Exception;
	
	// Subject_T �� Ư�� �й� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	public List<PostVO> findPostByStid( String subject, Integer stid ) throws Exception;
	
}
