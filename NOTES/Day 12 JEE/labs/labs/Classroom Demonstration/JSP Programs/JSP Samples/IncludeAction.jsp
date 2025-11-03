
<HTML>
<HEAD>
<TITLE> Include Action Test </TITLE>
</HEAD>

<BODY>
		<h1> Include Action Test Page</h1>

		<h2> Using Include Directive </h2>

		<%@ include file="include.html" %>
		<%@ include file="include.jsp" %>

		<h2> Using Include Action </h2>

		<jsp:include page="include.html" flush="true" />
		<jsp:include page="include.jsp" flush="true" />

</BODY>
</HTML>
