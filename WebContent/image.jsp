<%@ page pageEncoding="euc-kr" 
	import="java.io.*,Control.Util"%><%

	String fname = request.getParameter("fname");
	
	if( fname != null && !fname.equals("")) {
		String extension = fname.substring(fname.lastIndexOf(".") + 1);
		String contentType = "image/" + extension;
		
		response.setContentType(contentType);
		
		File file = new File(Util.uploadDir() + fname);
		if (file.exists()) {
			InputStream in2 = new FileInputStream( file );

			OutputStream out2 = response.getOutputStream();
			
			byte[] buf = new byte[1024*8];
			int len = 0;
			
			while( ( len = in2.read( buf ) ) != -1 ) {
				out2.write( buf, 0, len );
				out2.flush();
			}

			out2.close();
			in2.close();
		}
		
	}

%>