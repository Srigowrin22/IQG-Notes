import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class servletImageHTML extends HttpServlet
{
public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	ByteArrayOutputStream by = new ByteArrayOutputStream(1024);
	PrintWriter pw = new PrintWriter(by,true); // flush the data
	pw.println("<HEAD><TITLE>resp demo servlet </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center> using html tags </h1>");
	pw.println("<h1 align=center> and a byte array stream </h1>");
	pw.println("<CENTER><img src=\"/images/clock.gif\"></CENTER>");
	pw.println("<CENTER><img src=\"/images/arrow.gif\"></CENTER>");
	pw.println("</BODY>");
	// all data sent is now in bytearrayoutputStream
	res.setContentLength(by.size());

	by.writeTo(res.getOutputStream());
}


}
