import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.util.*;
import java.util.Calendar;
import javax.sql.*;
import java.sql.*;

public class fourthservlet extends HttpServlet
{
String accountnumber;
Connection dbcon;     

public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{
// Establishing the connection with the database
//-----------------------------------------------
	try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			dbcon=DriverManager.getConnection("jdbc:odbc:servletcomm","sa","password");
			System.out.println("connection est");
			
			}catch(ClassNotFoundException e)
			{
			System.out.println("Database driver not found");
			System.out.println(e.toString());
			throw new UnavailableException(this, "Cannot connect to the database");
                 	}
			catch (Exception e)
			{
			System.out.println("UNKNOWN!?");
			} // end catch
//----------------------------------------------------


// Accessing the accout number from the servlet context
ServletContext context = getServletContext();
Object obj = context.getAttribute("accountnumber");
String accnum=obj.toString();
double amount=0.0;
PrintWriter out=res.getWriter();
res.setContentType("text/html");
try
{
PreparedStatement s=dbcon.prepareStatement("select mBalance from Account_Holder where cAccount_id=?");
s.setString(1,accnum);
ResultSet result=s.executeQuery();
result.next();
	if (result==null)
	{
	 System.out.println("Error executing the query");
      }
	else
	{
	 amount=result.getDouble(1);
	}
}catch(SQLException e)
{
System.out.println(e.toString());
} //end catch



out.println("<HTML>");
out.println("<BODY bgcolor=pink>");
out.println("<head>");
out.println("<title>");
out.println("Balance");
out.println("</title>");
out.println("</head>");
out.println("<b><u>Your balance details</u></b>");
out.println("<br>");
out.println("Account number: "+accnum);
out.println("<br>");
out.println("Balance after the previous transaction: "+amount);
out.println("<br>");
out.println("<b><font size=5 color=green> Balance statement </font> </b>");
// printing the last 20 transactions made by the user
//---------------------------------------------------
boolean rowfound=true;
out.println("<hr>");
out.println("<table cellpadding =20>");
out.println("<tr>");
out.println("<td>");
out.println("Account Number  ");
out.println("</td>");
out.println("<td>");
out.println("Date   ");
out.println("</td>");
out.println("<td>");
out.println("Amount Deposited (Rs)   ");
out.println("</td>");
out.println("</tr>");
out.println("</table>");
out.println("<hr>");


// Fetching the details from the account_holder_transaction table and displaying them
//-----------------------------------------------------------------------------------
int totalrows=0;
try
{
PreparedStatement s1=dbcon.prepareStatement("select count(*) from account_holder_transaction where cAccount_id=?");
s1.setString(1,accnum);
ResultSet r=s1.executeQuery();
r.next();
totalrows=r.getInt(1);
totalrows-=20;
}
catch (Exception e)
{
}
try
{
PreparedStatement s2=dbcon.prepareStatement("Select cAccount_id,datepart(d,dDate_of_transaction),datepart(m,dDate_of_transaction),datepart(yy,dDate_of_transaction),mAmount from account_holder_transaction where cAccount_id=?");
s2.setString(1,accnum);
ResultSet result=s2.executeQuery();
rowfound=result.next();
int numrows=0;
int dd,mm,yy;
dd=mm=yy=0;
if (rowfound)
 numrows=1;
String d=new String(" ");
while (rowfound)
{
    if (numrows> totalrows)
    {
        dd=result.getInt(2);
        mm=result.getInt(3);
        yy=result.getInt(4);
        double mdeposit=result.getDouble(5);
        d=dd+"/"+mm+"/"+yy;
        out.println("&nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp");
	  out.println(accnum);
        out.println("&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp");
        out.println(d);
        out.println("&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp");
        out.println(mdeposit);
        out.println("<br>");
    }
   rowfound=result.next();
   numrows++;
}// end while

}
catch (Exception e)
{
  System.out.println(e.toString());
}
out.println("</BODY>");
out.println("</HTML>");
out.close();
   
} // End doPost
} // End class definition



    