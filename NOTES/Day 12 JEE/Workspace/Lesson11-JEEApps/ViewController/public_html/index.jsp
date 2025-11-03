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
      <title>index</title>
    </head>
    <body>
      <h:form>
        <center>
            <h2> Please provide your Login details </h2>
            <table>
                <tr>
                    <td> Username: </td>
                    <td> <h:inputText value = "#{loginRef.username}" /> </td>
                </tr>
                <tr>
                    <td> Password: </td>
                    <td> <h:inputSecret value = "#{loginRef.password}"
                                        required="true">
                                    <f:validateLength minimum="6" maximum="20"/>
                                    <f:convertNumber/>
                                    <f:valueChangeListener type="training.iqgateway.view.OurValueChangeListener"/>
                                </h:inputSecret> </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td> <h:commandButton value = "Login"
                                          action="#{loginRef.navigate}">
                                    <f:actionListener type="training.iqgateway.view.OurActionListener"/>
                                </h:commandButton> </td>
                </tr>
            </table>
            <h:messages/>
        </center>
      </h:form>
    </body>
  </html>
</f:view>
<%-- 
    oracle-jdev-comment:preferred-managed-bean-name:loginRef
--%>