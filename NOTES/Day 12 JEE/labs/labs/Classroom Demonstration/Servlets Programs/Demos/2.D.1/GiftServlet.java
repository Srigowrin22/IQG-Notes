import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class GiftServlet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
	boolean cookieFound=false;
	Cookie myCookie=null;
	Cookie[] cookieset=req.getCookies();
	res.setContentType("text/html");
	PrintWriter pw=res.getWriter();
	pw.println("<HTML>");
	pw.println("<BODY>");
	for (int i=0;i<cookieset.length;i++)
	{
		if (cookieset[i].getName().equals("logincount"))	
		{
			cookieFound=true;
			myCookie=cookieset[i];
		}
	}
	if (cookieFound)
	{
		int temp=Integer.parseInt(myCookie.getValue());
		temp++;
		if (temp==5)
		 pw.println("Congratulations!!!!!!!!!!!!!!!!!!!, a gift is awaiting you");
		pw.println("The number of times you have logged in is:  "+String.valueOf(temp));
		myCookie.setValue(String.valueOf(temp));
		int age=60*60*24*30;
		myCookie.setMaxAge(age);
		res.addCookie(myCookie);
		cookieFound=false;
	}			
	else
	{
		int temp=1;
		pw.println("This is the first time you have logged on to the server");
		myCookie=new Cookie("logincount",String.valueOf(temp));
		int age=60*60*24;
		myCookie.setMaxAge(age);
		res.addCookie(myCookie);
	}
	pw.println("</BODY>");
	pw.println("</HTML>");
        }
}