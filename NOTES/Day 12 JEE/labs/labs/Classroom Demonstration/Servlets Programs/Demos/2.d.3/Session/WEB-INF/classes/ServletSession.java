import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletSession extends HttpServlet
{
	private String name="Raju";

	private int age=23;
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		String action=req.getParameter("action");

		if((action!=null)&&(action.equals("invalidate")))
		{
			HttpSession session=req.getSession();
			session.invalidate();
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();

			out.println("<html>");
			out.println("<body>");
			out.println("<p> Your Session has been Invalidated .</p>");
			String LifeCycleURL="/Session/lifecycle";
			out.println("<a href=\"" +LifeCycleURL + "?action=newSession\">");
			out.println(" Create a New Session ");
			out.println("</body></html>");
		}
		else
		{
			HttpSession session=req.getSession();
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();

			out.println("<html>");
			out.println("<body>");
			out.println("<H1> <center>Session LifeCycle .</center></H1>");
	
			out.println("<br> Session Status : ");
			if(session.isNew())
			{
				out.println(" IT's A New Session ");
			}
			else
			{
				out.println(" IT's A Old Session ");
			}

			out.println("<br> Session ID : ");
			out.println(session.getId());

			out.println("<br> Creation Time  : ");
			out.println(new Date(session.getCreationTime()));
	
			out.println("<br> Last Accessed Time : ");
			out.println(new Date(session.getLastAccessedTime()));

			out.println("<br> Maximum Inactive Interval  : ");
			out.println(session.getMaxInactiveInterval());

			String LifeCycleURL="/Session/lifecycle";
			out.println("<br> <a href=\"" +LifeCycleURL + "?action=invalidate\">");
			out.println(" INvalidate The Session </a>");
			out.println("<br> <a href=\""+LifeCycleURL + "\">");
			out.println(" Reload This Page </a>");
			out.println("</body></html>");
			out.close();
		}
	}
}
