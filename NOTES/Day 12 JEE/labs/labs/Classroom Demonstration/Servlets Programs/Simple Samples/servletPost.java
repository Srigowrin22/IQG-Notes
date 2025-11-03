import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; import java.util.*;

public class servletPost extends HttpServlet
{
String str;
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	Enumeration values = req.getParameterNames();
	StringBuffer sb = new StringBuffer();
	while (values.hasMoreElements())
	{
		String name = (String) values.nextElement();
		String val[] = req.getParameterValues(name);
		sb.append(name +  " ::: ");
		for (int i=0;i<val.length ;i++ )
		{
		   sb.append(val[i] +",");
		}
		sb.append("\n");
	}
	String qurstr = req.getQueryString();
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>MY SERVLET </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center> MY FIRST Servlet </h1>");
	pw.println("<h1 align=center> having only a doPOST method </h1>");
	pw.println("<p> data sent through the post was : </p>" );
	pw.println("<pre> " + sb.toString() + "</pre>");
	pw.println("<p> query info : </p>" );
	pw.println("<pre> " + qurstr + "</pre>");
	pw.println("</BODY>");
	pw.close();
	}

}
