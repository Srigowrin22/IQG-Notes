import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyCounterServSyn extends HttpServlet
{
private int count;
//default constructor
public MyCounterServSyn() {
	count = 0;
}

public void doGet(HttpServletRequest reg, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>MY SERVLET </TITLE></HEAD><BODY>");
	pw.println("<h1 > Counter Servlet (Synchronized) </h1>");
	pw.println("<h1 > accessed ");
	int localCount ;
	localCount = count;
	synchronized(this) {
		count ++;
		localCount = count;
	}
	pw.println(" "+ count + " times </h1>");
	pw.println("</BODY>");
	pw.close();
}


}
