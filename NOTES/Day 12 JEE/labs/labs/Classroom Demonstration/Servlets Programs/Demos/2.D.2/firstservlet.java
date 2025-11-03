import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.util.*;

public class firstservlet extends HttpServlet
{

static Connection dbcon;
static String resulttosecond="NA";
	
	
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		dbcon=DriverManager.getConnection("jdbc:odbc:servletcomm","sa","password");
								
	}catch(ClassNotFoundException e)
	{
		System.out.println("Database driver not found");
		System.out.println(e.toString());
      }
	catch (Exception e)
	{
		System.out.println("UNKNOWN!?");
            System.out.println(e.toString());
	} // end catch

// Creating a shared attribute
//----------------------------

HttpSession session = req.getSession();
//ServletContext context = getServletContext();
//context.setAttribute("accountnumber"," ");

session.setAttribute("accountnumber"," ");


String accnum=req.getParameter("accnum");
String pinnum=req.getParameter("pinnum");

/*Check whether the accnum and the pinnum are valid*/
try
{
	PreparedStatement s=dbcon.prepareStatement("select * from login where cAccount_ID=? and cPin_no=?");
	s.setString(1,accnum);
	s.setString(2,pinnum);
	ResultSet result=s.executeQuery();
	boolean rowfound=false;
	rowfound=result.next();
if (rowfound==true)
{
	     String accNumber=result.getString(1);             
         session.setAttribute("accountnumber",accNumber);

	   // if the account number is valid
	   // call the second servlet
	   //----------------------------------------------------------------
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/secondservlet");
            		if(dispatcher==null)
		{
	
            			res.sendError(res.SC_NO_CONTENT);
		}
		dispatcher.forward(req,res);

		try
		{
			dbcon.close();
		}catch(Exception e)
		{
			System.out.println("Error closing database");
			System.out.println(e.toString());
		} // end catch
       }               
	if (rowfound==false)
	{
              
        	PrintWriter out=res.getWriter();	          
		res.setContentType("text/html");
     		resulttosecond="NA";
            out.println("<html>");
            out.println("<body bgcolor=pink>"); 
           	out.println("Pls check the values that you have entered");
            out.println("</body>");
            out.println("</html>");  
           	out.close();
	}
 
}// end try
catch(SQLException e)
{
System.out.println(e.toString());
} //end catch
} // end doPost
} // end class definition
