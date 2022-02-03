package Model;

import java.util.List;

// �Խñ۰� ���õ� �۾����� �����ϴ� �������̽�
public interface PostDAO {
	// ��� ���̺� : ���� ���̺� ( Subject_T )
	
	// ���̺��� ��� ���ڵ带 ����Ʈ�� ������ ��ȯ�ϴ� �Լ�
	public List<PostVO> findAll( String subject ) throws Exception;
	
	// ���̺� ���ڵ带 �ϳ� �߰��ϴ� �Լ�
	public int add( String subject, PostVO pvo ) throws Exception;
	
	// Bang_T ���� Ư�� PK �� ���� ���ڵ带 �����ϴ� �Լ�
	public int delByPK( String subject, PostVO pvo ) throws Exception;
}
