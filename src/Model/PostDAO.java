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
	
}
