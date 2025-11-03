import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class servletImage2 extends HttpServlet
{
public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	String imgFile = getServletContext().getRealPath("/images/arrow.gif");
	String imgFile2 = getServletContext().getRealPath("/images/clock.gif");
	String mimetype = getServletContext().getMimeType(imgFile);
	res.setContentType(mimetype);
	ByteArrayOutputStream boas = new ByteArrayOutputStream();
	int totalbytes=0;
	FileInputStream fis = null;
	try
	{
		File f = new File(imgFile);
		System.out.println("size of image file is " + f.length());
		fis = new FileInputStream(imgFile);
		byte [] buf = new byte[1024];
		int bytesread;
		while ((bytesread = fis.read(buf)) != -1 )
		{
			totalbytes += bytesread;
			System.out.println("writing 1024 bytes");
			boas.write(buf,0,bytesread);
		}
		fis.close();
		f = new File(imgFile2);
		System.out.println("size of image file is " + f.length());
		fis = new FileInputStream(imgFile2);
		while ((bytesread = fis.read(buf)) != -1 )
		{
			totalbytes += bytesread;
			System.out.println("writing 1024 bytes");
			boas.write(buf,0,bytesread);
		}
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	finally {
		if (fis != null)
		{
			fis.close();
		}
	}
	System.out.println("total content set is " + totalbytes);
	res.setContentLength(totalbytes);
	boas.writeTo(res.getOutputStream());
}


}
