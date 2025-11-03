import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*; 

public class CookieDemo extends HttpServlet { 
      public void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
		String name=null;
		int visCount = -1;
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		// retrieve cookies 
		Cookie cookies[] = req.getCookies();
		if (cookies != null)
		{
			for (int i=0; i<cookies.length ;i++ )
			{
				if (cookies[i].getName().equals("Name"))
				{
					name = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("Count"))
				{
					visCount = Integer.parseInt(cookies[i].getValue());
					System.out.println("on server: count is " + visCount);
					visCount++;
					cookies[i].setValue(String.valueOf(visCount));
					res.addCookie(cookies[i]);
				}
				
			}
		} 
		pw.println("<HEAD><TITLE>Cookie Demo servlet </TITLE></HEAD><BODY>");
		if (name == null)
		{
			pw.println("<form action=http://localhost:8080/servlet/CookieDemo method=post> </a>");
			pw.println("<p>Enter your name please   ");
			pw.println("<input type=text name=username> </p>");
			pw.println("<input type=submit value=Store>");
		}	
		else 
		{
			pw.println("<p> Mr. " + name + ", Welcome to this happy browsing page </p>");
			pw.println("<p> It was nice of you to have visited us " + visCount + " times </p>");
		}
		pw.println("</BODY>");
		pw.close();

	}
      public void doPost(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
	{
		Cookie tempn = new Cookie("Name","Noname");
		Cookie tempc = new Cookie("Count","0");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		Enumeration values = req.getParameterNames();
		boolean gotelement = false;
		while (values.hasMoreElements() && !gotelement)
		{
			String tname = (String) values.nextElement();
			if (tname.equals("username"))
			{
				gotelement = true;
				tempn.setValue(req.getParameterValues(tname)[0]);
			}
		}
		res.addCookie(tempn);
		res.addCookie(tempc);
		pw.println("<HEAD><TITLE>Thanks  </TITLE></HEAD><BODY>");
		pw.println("<p> Thanks Mr. " + tempn.getValue() + ". Your name is added to list </p>");
		pw.println("</BODY>");
		pw.close();
		
	}
} 

