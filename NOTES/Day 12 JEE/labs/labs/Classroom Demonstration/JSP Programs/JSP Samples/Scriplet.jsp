
<HTML>
<HEAD>
<TITLE> Scriplet Example </TITLE>

</HEAD>

<BODY>
		<h1> This is Scriplet Example </h1>
		
		<%
			for(int i=0; i<10;i++)
			{
				out.println(" <B> Hello My Friends in CGI... This is Scriplet Test " + i +"</b><BR> ");
				System.out.println(" This goes to the System.out Stream " + i);
			}
		%>
</BODY>
</HTML>
