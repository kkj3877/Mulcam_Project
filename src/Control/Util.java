package Control;

import java.io.IOException;

public class Util {
	
	// ���� ȯ�濡 ���� ���� ���ε� ���丮�� �������ִ� �Լ�
	public static String uploadDir() {
		String t = System.getProperty("os.name");
		
		String upload = "/kkj3877/mathcafe_upload/";
		if ( t.indexOf("indows") != -1 ) {
			upload = "C:\\mathcafe_upload\\";
		}
		return upload;
	}
	
	// ���� ȯ�濡 ���� ���� ���丮�� �������ִ� �Լ�
		public static String csvDir() {
			String t = System.getProperty("os.name");
			
			String upload = "/kkj3877/mathcafe_upload/csv/";
			if ( t.indexOf("indows") != -1 ) {
				upload = "C:\\mathcafe_upload\\csv\\";
			}
			return upload;
		}
	
	// 8859_1 �� ���ڿ��� utf-8 ���ڵ��Ͽ� ��ȯ�ϴ� �Լ�
	public static String han( String a ) {
		try {
			byte[] bs = a.getBytes("8859_1");
			a = new String( bs, "utf-8" );
		}
		catch( IOException e ) {}
		return a;
	}
}