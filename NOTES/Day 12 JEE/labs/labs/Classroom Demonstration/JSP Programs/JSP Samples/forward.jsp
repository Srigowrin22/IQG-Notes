<%
	if((request.getParameter("username").equals("CGI"))&&(request.getParameter("password").equals("CGI")))
	{
%>

	<jsp:forward page="forward2.jsp" />

<%
	}
	else
	{
%>

<%@ include file="forward.html" %>

<%
	}
	
%>