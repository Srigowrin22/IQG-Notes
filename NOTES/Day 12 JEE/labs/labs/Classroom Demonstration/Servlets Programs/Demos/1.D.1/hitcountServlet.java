import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class hitcountServlet extends HttpServlet
{

public void init(ServletConfig config)
throws ServletException 
{
//The ServletConfig object must be passed to the super class
super.init(config);
// Do your Initailization here......
}
// A counter to keep track of the number of users visiting the website
static int count;
//Process the HTTP Get request
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
response.setContentType("text/html");
PrintWriter out=response.getWriter();
count++;
out.println("<html>");
out.println("<head><title>BasicServlet</title></head>");
out.println("<body>");
out.println("You are user number   " + String.valueOf(count)+ "  visting our web site"+ "\n");
out.println("</body></html>");

getServletInfo();
}
//Get Servlet information
public String getServletInfo(){
return "BasicServlet Information";
}
} 
