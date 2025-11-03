import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class helloservletGet extends HttpServlet
{
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>info about SERVLETREQUEST data </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center>  Servlets request object contains </h1>");
	pw.println("<h2 align=center> following information </h2>");
	pw.println("<pre>");
	pw.println("Client side info " );
	pw.println("host: " +  req.getRemoteHost());
	pw.println("address: " +  req.getRemoteAddr());
	pw.println("scheme: " +  req.getScheme());
	pw.println("protocol: " +  req.getProtocol());
	pw.println("--------------------------------");
	pw.println("Server side info " );
	pw.println("server name : " + req.getServerName());
	pw.println("server port : " + req.getServerPort());
	pw.println("--------------------------------");
	pw.println("HTTP specifics");
	pw.println("path: " +  req.getPathInfo());
	pw.println("servlet path: " +  req.getServletPath());
	pw.println("requested URI: " + req.getRequestURI());
	pw.println("method: " +  req.getMethod());
	pw.println("</pre>");
	pw.println("Request Headers " );
    pw.println("<table border=1 cellpadding=0 cellspacing=0>");
    pw.println("<tr>");
    pw.println("<td><b>Header Name</b></td>");
    pw.println("<td><b>Value</b></td>");
    pw.println("</tr>");
	Enumeration e = req.getHeaderNames();
	int i = 1;
	while (e.hasMoreElements())
	{
		String st = (String)e.nextElement();
		String st2 = req.getHeader(st);
		pw.println("<td>" + st + "</td>");
		if(st2 != null)
				pw.println("<td>" +st2+ "</td>" );
		else
				pw.println("<td><i>Null Value</i></td>" );
		pw.println("</tr>");
//		pw.println(" header " + i + " : " + st + " |-> =  "  + st2);
		i++;
	}
	pw.println("</table> </body> </HTML>");

	pw.close();
}

public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
{
		doGet(req,res);
}

}