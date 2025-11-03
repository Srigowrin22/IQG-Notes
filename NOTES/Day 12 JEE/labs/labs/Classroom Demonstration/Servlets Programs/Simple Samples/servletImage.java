import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class servletImage extends HttpServlet
{
public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	String imgFile = getServletContext().getRealPath("/images/clock.gif");
	System.out.println("image path is " + imgFile);
	String mimetype = getServletContext().getMimeType(imgFile);
	res.setContentType(mimetype);
	ServletOutputStream sos = res.getOutputStream();
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
			System.out.println("writing 1024 bytes");
			sos.write(buf,0,bytesread);
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
	sos.close();
}
}
