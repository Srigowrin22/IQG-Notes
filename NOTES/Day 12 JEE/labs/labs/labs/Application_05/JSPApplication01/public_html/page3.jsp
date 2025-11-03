<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>page3</title>
  </head>
  <body><p>
      First Name: <c:out value="${sessionScope.firstName}"/>
    </p><p>
      Last Name: 
      <c:out value="${sessionScope.lastName} "/>
    </p><p>
      Street Address: 
      <c:out value="${param.streetAddress}"/>
    </p><p>
      City: 
      <c:out value="${param.city}"/>
    </p></body>
</html>