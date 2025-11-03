import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyCounterServ extends HttpServlet
{
private int count;
// default constructor
public MyCounterServ() {
	count = 0;
}
public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	count++;
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>MY SERVLET </TITLE></HEAD><BODY>");
	pw.println("<h1 > Counter Servlet </h1>");
	pw.println("<h1 > accessed "+ count + " times </h1>");
	pw.println("</BODY>");
	pw.close();
}


}
