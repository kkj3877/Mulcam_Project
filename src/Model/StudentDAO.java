package Model;

import java.util.List;

public interface StudentDAO {
	
	// ��� �л� List �� ��ȯ�ϴ� �Լ�
	public List<StudentVO> findAll() throws Exception;
	
	// �й����� �л��� ã�� �Լ� ������ true, ������ false ��ȯ
	public boolean findByStid( StudentVO pvo ) throws Exception;
	
	// �л��� ���̺� �߰��ϴ� �Լ�
	public int add( StudentVO pvo ) throws Exception;
	
	// �л��� id/pw �� �α��� �õ��� �ϰ�
	// �����ϸ� 0, ���� ���̵�� 1, ��й�ȣ�� Ʋ���� 2�� ��ȯ
	public int loginTry( StudentVO pvo ) throws Exception;
	
}
