package atm1;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.sql.*;
import java.math.*;
import java.util.*;
public class ATMTag extends TagSupport
{
	Connection connect = null;
	Statement state = null;
	ResultSet result = null;
	public ATMTag() throws ClassNotFoundException
	{
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	
	public int doStartTag() throws JspTagException
	{
		return SKIP_BODY;
	}

	public int doEndTag() throws JspTagException
	{
	   try
	   {
				JspWriter out=pageContext.getOut();
				
				connect=DriverManager.getConnection("jdbc:odbc:MyDataSource","sa","sa");
				
				
				String strQuery = " Select cCounter_id, cAddress,mCashBalance from Counter where mCashBalance<=10000; " ;
				System.out.println("Query: "+strQuery);
				state = connect.createStatement();
				ResultSet result = state.executeQuery(strQuery);
				String sId=null;
				String sAddress=null;
				String sBalance=null;
				Vector v=new Vector();
			while(result.next())
			{
				
				sId  = result.getString(1);
				sAddress  = result.getString(2);
				sBalance  = result.getString(3);
				v.add(sId);
				v.add(sAddress);
				v.add(sBalance);

			}
			    	for(int i=0;i<v.size();i++)
				{
					String str=(String)v.elementAt(i);
					pageContext.getOut().write(str);
				}
	       }catch(Exception ioe)
		{
			System.out.println(" Error in getting results"+ioe.toString());
		}
			return EVAL_PAGE;
	}

}
