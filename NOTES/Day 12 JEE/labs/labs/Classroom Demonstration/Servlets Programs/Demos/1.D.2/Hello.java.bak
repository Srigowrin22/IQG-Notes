
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

public class Hello extends HttpServlet
{
Connection dbcon;

public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{

// Establishing the connection with the database
//-----------------------------------------------
	try
		{
			Class.forName("com.mysql.jdbc.Driver");
            dbcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/Products","root","root");
             System.out.println("Connection established");
			
			}catch(ClassNotFoundException e)
			{
					System.out.println("Database driver not found");
					System.out.println(e.toString());
		                 	}
			catch (Exception e)
			{
			System.out.println(e.toString());
			} // end catch

// This program records the details in the Registration table
//---------------------------------------------------------
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();

	String firstname=req.getParameter("firstname");
	String lastname=req.getParameter("lastname");
	String address=req.getParameter("address");
	String accounttype=req.getParameter("account");
	String income=req.getParameter("income");
                 	String phonenumber=req.getParameter("phonenumber");
	double annualincome=0.0;
	Double temp=Double.valueOf(income);
	annualincome=temp.doubleValue();

// inserting the values in the registration table
//-------------------------------------------
int rows=0;
try
{
        PreparedStatement s=dbcon.prepareStatement("insert into registration(cFirst_name,cLast_name,cAddress,cAccount_type,mAnnual_income,cPhone_no) values(?,?,?,?,?,?)");
	s.setString(1,firstname);
	s.setString(2,lastname);
	s.setString(3,address);
	s.setString(4,accounttype);
	s.setDouble(5,  annualincome);
	s.setString(6,phonenumber);

  	rows=s.executeUpdate();
}
catch (Exception e)
{
  System.out.println(e.toString());
}
 if (rows==0)
{
  System.out.println("Error inserting data in the registration table");
}
else
{
  System.out.println("The values have been inserted in the table successfully");
}      

// Picking up the registration number from the table for the user 
//----------------------------------------------------------------
String regno=new String(" ");
try
{
PreparedStatement s=dbcon.prepareStatement("select  max(cRegistration_id) from registration");
ResultSet r=s.executeQuery();
r.next();
regno=r.getString(1);
}
catch(Exception e)
{
  System.out.println(e.toString());
}
	
	out.println("<html>");
	out.println("<head><title>Online Banking with Earnest</title></head>");
	out.println("<body bgcolor=pink>");
                  out.println("Thank you for registering in our Web site  " + firstname);
	out.println("<BR>");
	out.println("Your registration ID is: <b> " +regno+"</b>");
                  out.println("<BR>");
                  	out.println("</body></html>");


try
{
	dbcon.close();
}
catch(Exception e)
{
  System.out.println(e.toString());
}

}
}