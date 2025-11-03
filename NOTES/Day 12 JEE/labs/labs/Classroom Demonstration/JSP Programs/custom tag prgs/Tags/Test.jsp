<%@ page import="TestTag" %>
<%@ taglib uri="TestTaglib.tld" prefix="first" %>
<%@ page errorPage="errorpage.jsp" %>
<!DocType HTML Public "-//W3C//DTD HTML 4.0 Translational//EN ">
<HTML>
 <HEAD>
  <title> Using custom tags</title>
 </HEAD>
 
 <BODY>
Hello, Welcome!
     <first:welcome/>

   </BODY>
</HTML>
