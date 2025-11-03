<%@ page language="java %>
<jsp:useBean id="BA" scope="application" class="com.sun.BankAccount" >
	<jsp:setProperty name="BA" property="sAccountID" param="sAccountID" />
	<jsp:setProperty name="BA" property="sPin" param="sPin" />
</jsp:useBean>

<HTML>
<HEAD>
<TITLE> Result page </TITLE>
</HEAD>

<BODY>
		
	<% String sAccountID=BA.getsAccountID();
	   String sPin=BA.getsPin();
	   boolean validate=BA.AccountValidate();
	   if(validate==true)
	   {
		out.println(" The User is Valid.....");
	   }
	   else
	   {
		out.println(" The User is Invalid.....");
	   }
	%>
</BODY>
</HTML>