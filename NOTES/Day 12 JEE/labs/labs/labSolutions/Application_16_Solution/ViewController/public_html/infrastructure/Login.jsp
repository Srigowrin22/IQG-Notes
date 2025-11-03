<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"                                                                                                                 errorPage="/infrastructure/Errors.jsp"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Demo Application Login</title>
    <link type="text/css" rel="stylesheet"/>
  </head>
  <body><h1>
  Login to Sample Application
</h1>
<form action="j_security_check" method="POST">
  <table cellspacing="2" cellpadding="3" border="0">
    <tr>
      <td>Username</td>
      <td>
        <input type="text" name="j_username" size="30"/>
      </td>
    </tr>
    <tr>
      <td>Password</td>
      <td>
        <input type="password" name="j_password" size="30"/>
      </td>
    </tr>
  </table>
  <p>
    <input type="submit" name="logon" value="Sign On"/>
  </p>
</form>
</body>
</html>