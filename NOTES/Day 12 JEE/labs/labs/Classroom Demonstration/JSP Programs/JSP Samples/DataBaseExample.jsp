<%@ page language="java" import="java.sql.*, javax.sql.*" %>
<HTML>
<HEAD>
<TITLE> Connection with DataBase  </TITLE>
</HEAD>

<BODY>
		<%
			Connection con=null;
			try
			{
				System.out.println(request.getServerName());
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","srdemo","oracle");
				Statement stat=con.createStatement();
				ResultSet rs=stat.executeQuery("select * from Products");

			%>
			
			<TABLE border=2>

			<%
				while(rs.next())
				{
			%>
			<TR>
				<TD><%= rs.getString(1) %> </TD>
				<TD><%= rs.getString(2) %> </TD>
				<TD><%= rs.getString(3) %> </TD>			
			</TR>

			<%
				}
			%>
			</TABLE>
			<%
				}
				catch(Exception e)
				{
					out.println(" Cannot Display the Records ");
					System.out.println(e);

				}
				%>
</BODY>
</HTML> 
