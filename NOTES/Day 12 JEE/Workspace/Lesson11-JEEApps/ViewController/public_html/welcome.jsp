<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
      <title>welcome</title>
    </head>
    <body>
      <h:form>
        <center>
            <h2> Welcome to new era of developing web application using JSF,
                <h:outputText value= "#{loginRef.username}" />
            </h2>
        </center>
      </h:form>
    </body>
  </html>
</f:view>