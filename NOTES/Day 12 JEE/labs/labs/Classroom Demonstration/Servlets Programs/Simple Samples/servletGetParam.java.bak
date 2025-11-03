import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; import java.util.*;

public class servletGetParam extends HttpServlet
{
String str;
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	str = req.getQueryString();
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
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	pw.println("<HEAD><TITLE>MY third SERVLET </TITLE></HEAD><BODY>");
	pw.println("<h1 align=center> MY 3333 Servlet </h1>");
	pw.println("<h1 align=center> having  a doGet method </h1>");
	pw.println("<p> data sent through the get (getparams) was : </p>" );
	pw.println("<pre> " + sb.toString() + "</pre>");
	pw.println("<p> query info : </p>" );
	pw.println("<pre> " + str + "</pre>");
	pw.println("</BODY>");
	pw.close();
	}

}
