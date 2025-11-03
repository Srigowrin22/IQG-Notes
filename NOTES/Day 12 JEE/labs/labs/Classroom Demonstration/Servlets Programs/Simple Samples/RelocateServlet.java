import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RelocateServlet extends HttpServlet
{
public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	res.setStatus(res.SC_MOVED_TEMPORARILY);
		// no message is visible, the browser automatically goes to new location
//	res.setHeader("Location","http://localhost:8080/servlet/servletGet"); 
	// display the info and go automatically after 9 seconds to new location
	res.setHeader("Refresh","9; URL=http://localhost:8080/servlet/servletGet");

	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>Relocate SERVLET </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center> File you requested has moved. </h1>");
	pw.println("<p>The browser will take you to this site in 5 seconds. </p>");
	pw.println("<a href=\"http://localhost:8080/servlet/servletGet\">Click here to go immediately</a>");
	pw.println("</BODY>");
	pw.close();

}

}
