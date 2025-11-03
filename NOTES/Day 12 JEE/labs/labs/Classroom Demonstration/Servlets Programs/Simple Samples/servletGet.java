import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class servletGet extends HttpServlet
{
public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	// change to res.setContentType("text/plain"); and observe the difference
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>MY SERVLET </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center> MY FIRST Servlet </h1>");
	pw.println("<h1 align=center> having only a doGet method </h1>");
	pw.println("</BODY>");
	pw.close();
}


}
