import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*; 

public class CookieDel extends HttpServlet { 
      public void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
		// retrieve cookies 
		Cookie cookies[] = req.getCookies();
		if (cookies != null)
		{
			for (int i=0; i<cookies.length ;i++ )
			{
				if (cookies[i].getName().equals("Name"))
				{
					cookies[i].setMaxAge(0); 
					System.out.println("Deleting Cookie " + cookies[i].getName());
					res.addCookie(cookies[i]);
				}
				if (cookies[i].getName().equals("Count"))
				{
					cookies[i].setMaxAge(0); 
					System.out.println("Deleting Cookie " + cookies[i].getName());
					res.addCookie(cookies[i]);
				}
			}
		} 
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.println("<HEAD><TITLE>Cookie Deletion servlet </TITLE></HEAD><BODY>");
		pw.println("<h1>  all cookies here are deleted </h1>");
		pw.println("</BODY>");
		pw.close();
	}
} 

