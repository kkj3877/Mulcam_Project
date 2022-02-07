package Control;

import java.io.IOException;

public class Util {
	
	// 접근 환경에 따른 파일 업로드 디렉토리를 지정해주는 함수
	public static String uploadDir() {
		String t = System.getProperty("os.name");
		
		String upload = "/pukyung08/mathcafe_upload/";
		if ( t.indexOf("indows") != -1 ) {
			upload = "C:\\mathcafe_upload\\";
		}
		return upload;
	}
	
	// 8859_1 의 문자열을 utf-8 인코딩하여 반환하는 함수
	public static String han( String a ) {
		try {
			byte[] bs = a.getBytes("8859_1");
			a = new String( bs, "utf-8" );
		}
		catch( IOException e ) {}
		return a;
	}
}