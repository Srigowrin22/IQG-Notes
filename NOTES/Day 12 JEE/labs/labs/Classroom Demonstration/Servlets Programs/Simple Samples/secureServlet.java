import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class secureServlet extends HttpServlet
{
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>secure servlet data </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center>  Servlets request object additional information </h1>");
	pw.println("<h2 align=center> Authorization set on the server </h2>");
	pw.println("<pre>");
	pw.println("Login info " );
	pw.println("user: " +  req.getRemoteUser());
	pw.println("Authentication: " +  req.getAuthType());
	pw.println("</pre>");
	pw.println(" </body> </HTML>");

	pw.close();
}

public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
{
		doGet(req,res);
}

}