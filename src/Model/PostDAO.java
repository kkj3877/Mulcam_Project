package Model;

import java.util.List;

// �Խñ۰� ���õ� �۾����� �����ϴ� �������̽�
// ��� ���̺� : ���� ���̺� ( Subject_T )
public interface PostDAO {
	
	// ���̺� ���ڵ带 �ϳ� �߰��ϴ� �Լ�
	public int add( String subject, PostVO pvo ) throws Exception;
	
	// Bang_T ���� Ư�� PK �� ���� ���ڵ带 �����ϴ� �Լ�
	public int delByNo( String subject, PostVO pvo ) throws Exception;
	
	// ���̺��� ��� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	public List<PostVO> findAll( String subject ) throws Exception;
	
	// ���̺��� Ư�� é�� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	public List<PostVO> findPostByCh( String subject, Integer ch ) throws Exception;
	
	// ���̺��� Ư�� ��ȣ ���ڵ带 �ν��Ͻ�ȭ�Ͽ� ��ȯ�ϴ� �Լ�
	public PostVO findPostByNo( String subject, Integer no ) throws Exception;
	
}
