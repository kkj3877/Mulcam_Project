package Model;

public interface StudentDAO {
	
	// �й����� �л��� ã�� �Լ� ������ 1, ������ 0 ��ȯ
	public boolean findByStid( StudentVO pvo ) throws Exception;
	
	// �л��� ���̺� �߰��ϴ� �Լ�
	public int add( StudentVO pvo ) throws Exception;
}
