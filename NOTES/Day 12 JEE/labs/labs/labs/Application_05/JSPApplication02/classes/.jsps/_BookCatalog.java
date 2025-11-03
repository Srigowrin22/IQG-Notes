
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import oracle.jsp.el.*;
import javax.el.*;


public class _BookCatalog extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _BookCatalog page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      oracle.jsp.CartBean cart;
      synchronized (session) {
        if ((cart = (oracle.jsp.CartBean) pageContext.getAttribute( "cart", PageContext.SESSION_SCOPE)) == null) {
          cart = (oracle.jsp.CartBean) new oracle.jsp.CartBean();
          pageContext.setAttribute( "cart", cart, PageContext.SESSION_SCOPE);
        }
      }
      out.write(__oracle_jsp_text[2]);
      OracleJspRuntime.__jspSetAllParams(request, (Object)cart);
      out.write(__oracle_jsp_text[3]);
           
             	cart.processRequest();
      
      out.write(__oracle_jsp_text[4]);
      out.write("");
      out.write(__oracle_jsp_text[5]);
      out.write(__oracle_jsp_text[6]);
      
      String res = "";
      res = request.getParameter("item");
      out.print(cart.showDetails(res));
      
      out.write(__oracle_jsp_text[7]);
       
      	String[] items = cart.getItems();
      	for (int i=0; i<items.length; i++) {
      
      out.write(__oracle_jsp_text[8]);
       out.print((items[i])); 
      out.write(__oracle_jsp_text[9]);
      
      	}
      
      out.write(__oracle_jsp_text[10]);

    }
    catch (java.lang.Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (java.lang.Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  
  private static final char __oracle_jsp_text[][]=new char[11][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n<html>\n ".toCharArray();
    __oracle_jsp_text[2] = 
    "\n\n".toCharArray();
    __oracle_jsp_text[3] = 
    "\n".toCharArray();
    __oracle_jsp_text[4] = 
    "\n\n".toCharArray();
    __oracle_jsp_text[5] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<html>\n<head>\n    <title>Book Catalog</title>\n</head>\n<form type=POST action=\"BookCatalog.jsp\">\n  <p>&nbsp;</p>\n  <p>&nbsp;</p>\n  <h1 align=\"center\"><font face=\"Comic Sans MS\">BookCart Application</font></h1>\n  <p>&nbsp;</p>\n  <p><font face=\"Comic Sans MS\">The JSP application shows the usage of a Java \n    Bean in a JSP application.</font><br/>\n    <br/>\n    <font face=\"Comic Sans MS\">Add/Remove Book: </font> \n    <SELECT NAME=\"item\" title=\"Select Books\">\n      <OPTION>The Time Machine \n      <OPTION>The Count of Monte Cristo \n      <OPTION>The Lord of the Rings \n      <OPTION>Pride and Prejudice \n      <OPTION>The Adventures of Sherlock Holmes \n      <OPTION>Alice's Adventures in Wonderland \n    </SELECT>\n    <br/>\n    <br/>\n    <INPUT TYPE=submit name=\"submit\" value=\"Add\"/>\n    <INPUT TYPE=submit name=\"submit\" value=\"Remove\"/>\n    <input type=\"submit\" name=\"submit\" value=\"Show_Details\"/>\n  </p>\n  <p align=\"center\">&nbsp;</p>\n<hr>\n  <br/>\n</form>  \n</body>\n</html>\n\n".toCharArray();
    __oracle_jsp_text[6] = 
    "\n<div align=\"center\">\n  <table width=\"66%\" border=\"0\" height=\"159\" align=\"left\">\n    <tr> \n      <td width=\"54%\" height=\"206\"> \n        <p align=\"left\"><font face=\"Comic Sans MS\">Book Details: </font><br>\n          <br/>\n          ".toCharArray();
    __oracle_jsp_text[7] = 
    "\n        </p>\n        <p align=\"left\">&nbsp;</p>\n        <p align=\"left\">&nbsp;</p>\n        </td>\n      <td width=\"46%\" height=\"206\"> \n        <p align=\"left\"><font face=\"Comic Sans MS\">You have the following items \n          in your cart: </font></p>\n        <p align=\"left\"> \n          <select name=\"select\" size=\"10\">\n            ".toCharArray();
    __oracle_jsp_text[8] = 
    "\n            <option> \n            ".toCharArray();
    __oracle_jsp_text[9] = 
    "\n            </option>\n            ".toCharArray();
    __oracle_jsp_text[10] = 
    "\n          </select>\n        </p>\n      </td>\n    </tr>\n  </table>\n</div>\n<p align=\"center\">&nbsp;</p>\n<p align=\"center\">&nbsp;</p>\n<p align=\"center\">&nbsp;</p>\n<p align=\"center\">&nbsp;</p>\n<p align=\"center\">&nbsp;</p>\n<p align=\"center\">&nbsp;</p>\n<p align=\"center\">&nbsp; </p>\n<hr>\n<hr>\n<p>&nbsp;</p>\n<p align=\"center\">&nbsp; </p>\n<br/>\n<br/>\n</html>\n".toCharArray();
    }
    catch (java.lang.Throwable th) {
      java.lang.System.err.println(th);
    }
  }
}
