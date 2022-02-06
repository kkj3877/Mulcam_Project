package Model;

import java.util.List;

public interface StudentDAO {
	
	// �л��� ���̺� �߰��ϴ� �Լ�
	public int add( StudentVO pvo ) throws Exception;
	
	// �й����� �л��� ã�� ���̺��� �����ϴ� �Լ�
	public int delStudentByStid( StudentVO pvo ) throws Exception;
	
	// ��� �л� List �� ��ȯ�ϴ� �Լ�
	public List<StudentVO> findAll() throws Exception;
	
	// �й����� �л��� ã�� �Լ�. �ش� �й� �л��� ������ �̸���, ������ NULL ��ȯ
	public String findNameByStid( StudentVO pvo ) throws Exception;
	
	// �л��� id/pw �� �α��� �õ��� �ϰ�
	// �����ϸ� �л��� �̸���, ���� �й��̸� NULL, ��й�ȣ�� Ʋ���� '0'�� ��ȯ
	public String loginTry( StudentVO pvo ) throws Exception;
	
}
