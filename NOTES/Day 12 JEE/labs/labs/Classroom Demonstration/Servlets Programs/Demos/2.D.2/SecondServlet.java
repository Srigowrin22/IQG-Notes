import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.util.*;
import java.sql.*;

public class SecondServlet extends HttpServlet
{

public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{


	PrintWriter out=res.getWriter();
	res.setContentType("text/html");


	// Accessing the accout number from the servlet context
	HttpSession session = req.getSession();
	Object obj = session.getAttribute("accountnumber");
	String value=obj.toString();

          
     	out.println("<HTML>");
      out.println("<BODY bgcolor=pink>");
      out.println("<center>");
      out.println("<h1> Earnest Bank </h1>");
      out.println("</center>");
      out.println("<FORM method=post action=http://localhost:8000/servlet/thirdservlet>");
      out.println("<b>Click the deposit button to deposit your money</b>");
      out.println("<table>");
      out.println("<tr>");
      out.println("<td>"); 
      out.println("Account number: </td> <td>" + value);
      out.println("</td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td>"); 
      out.println("Cheque number: </td> <td> <input type=text name=checknum>");
      out.println("</td>");
      out.println("</tr>");
      out.println("<tr>");  
      out.println("<td>");
      out.println("Enter the amount to be deposited: </td><td><input type=text name=amount value=0>");
      out.println("</td>");
      out.println("</tr>");
      out.println("</table>");
      out.println("<input type=submit value=deposit>");
      out.println("<br>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");

}
}



