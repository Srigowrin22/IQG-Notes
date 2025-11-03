<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <jsp:useBean id="cart" scope="session" class="oracle.jsp.CartBean"/>
  <jsp:setProperty name="cart" property="*"/>
  <%
    cart.processRequest();
%>
  <%@ include file="/Books.html"%>
  <div align="center">
    <table width="66%" border="0" height="159" align="left">
      <tr>
        <td width="54%" height="206">
          <p align="left">
            <font face="Comic Sans MS">Book Details:</font><br></br>
             
            <br/>
             
            <%
                String res = "";
                res = request.getParameter("item");
                out.print(cart.showDetails(res));
          %>
          </p>
          <p align="left">&nbsp;</p>
          <p align="left">&nbsp;</p>
        </td>
        <td width="46%" height="206">
          <p align="left">
            <font face="Comic Sans MS">You have the following items in your cart:</font>
          </p>
          <p align="left">
            <select name="select" size="10">
              <%
              String[] items=cart.getItems();
              for(int i=0;i<items.length;i++)
              {
              %>
              <option>
                <% out.print((items[i])); %>
              </option>
                  <%
                     }
                %>
            </select>
          </p>
        </td>
      </tr>
    </table>
  </div>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <p align="center">&nbsp;</p>
  <hr></hr>
  <hr></hr>
  <p>&nbsp;</p>
  <p align="center">&nbsp;</p>
  <br/>
  <br/>
</html>