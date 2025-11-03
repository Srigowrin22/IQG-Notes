import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*; 

public class SessionDemo extends HttpServlet { 
      public void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException 
    {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		HttpSession ss = req.getSession(true);

		Integer count = (Integer)ss.getAttribute("tracker.count");
		if (count == null)
		{
			count = new Integer(1);
		}
		else 
		{
			count = new Integer(count.intValue() + 1);
		}
		ss.setAttribute("tracker.count1",count);

		;
		pw.println("<HEAD><TITLE>Session Demo servlet </TITLE></HEAD><BODY>");
		pw.println("<H1> session tracking </H1>");
		pw.println("<p> you have visited this page " + count + (count.intValue() ==1 ? " time " : "times"));
		pw.println("</p>");
		pw.println("<p> id used was " + ss.getId() + "</p>");
		pw.println("<h2> here is the session data </h2>");
		Enumeration names[] = ss.getAttributeNames();
		for (int i = 0;i<names.length ; i++)
		{
			pw.println(names[i] + ": " + ss.getAttribute(names[i]) + "<BR>");
		}
		pw.println("</BODY>");
		pw.close();
	}
} 

