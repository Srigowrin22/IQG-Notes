<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>page2</title>
    </head>
    <body>
    <p>
      &nbsp;
    </p><p>
      <c:out value="${param.firstName}"/>
    </p><p>
      <c:out value="${param.lastName}"/>
    </p><p>
      <c:set value="${param.firstName}" var="firstName" scope="session"/>
    </p><p>
      <c:set value="${param.lastName}" var="lastName" scope="session"/>
    </p><form action="page3.jsp">
      <p>
        Street Address: 
        <input type="text" name="streetAddress"/>
      </p>
      <p>
        City: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="city"/>
      </p>
      <p>
        <input type="submit" value="Next Page"/>
      </p>
    </form>

  </body>
</html>