<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<html>  <head> 
  <title>Security login example</title>  </head> 
  <body> 
  <blockquote>  
  <h1> Security Login Example </h1>  
  <p> Welcome <%= request.getRemoteUser() %>!  
  <p> If you are an administrator, you can configure the background color of the Web Application.  
  <br> 
  <b>
  <a href="../admin/edit.jsp">Configure background</a></b>.  
  
      <p>
        &nbsp;
      </p>
      <p>
        Click <a href="../common/shoppingcart.jsp">here</a> to go to the Shopping Cart page.
      </p>
    </blockquote>
  </body>
  </html>