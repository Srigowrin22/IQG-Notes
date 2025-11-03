package jsp_servlet;

import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class __bookcatalog extends  weblogic.servlet.jsp.JspBase  implements weblogic.servlet.jsp.StaleIndicator {

    private static void _releaseTags(javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag t) {
        while (t != null) {
            weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, t);
            if(t instanceof javax.servlet.jsp.tagext.Tag) {
                javax.servlet.jsp.tagext.Tag tmp = (javax.servlet.jsp.tagext.Tag)t;
                t = ((javax.servlet.jsp.tagext.Tag) t).getParent();
                try {
                    tmp.release();
                } catch(Exception ignore) {}
            }
            else {
                t = ((javax.servlet.jsp.tagext.SimpleTag)t).getParent();
            }
        }
    }

    public boolean _isStale(){
        boolean _stale = _staticIsStale((weblogic.servlet.jsp.StaleChecker) getServletConfig().getServletContext());
        return _stale;
    }

    public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
        if (sci.isResourceStale("/BookCatalog.jsp", 1252054657859L ,"10.3.0.0","Europe/Prague")) return true;
        if (sci.isResourceStale("/Books.html", 1252054657890L ,"10.3.0.0","Europe/Prague")) return true;
        return false;
    }

    private static boolean _WL_ENCODED_BYTES_OK = true;
    private static final String _WL_ORIGINAL_ENCODING = "windows-1252".intern();

    private static byte[] _getBytes(String block){
        try {
            return block.getBytes(_WL_ORIGINAL_ENCODING);
        } catch (java.io.UnsupportedEncodingException u){
            _WL_ENCODED_BYTES_OK = false;
        }
        return null;
    }

    private final static String  _wl_block0 ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n\"http://www.w3.org/TR/html4/loose.dtd\">\r\n";
    private final static byte[]  _wl_block0Bytes = _getBytes( _wl_block0 );

    private final static String  _wl_block1 ="\r\n<html>\r\n ";
    private final static byte[]  _wl_block1Bytes = _getBytes( _wl_block1 );

    private final static String  _wl_block2 ="\r\n\r\n";
    private final static byte[]  _wl_block2Bytes = _getBytes( _wl_block2 );

    private final static String  _wl_block3 ="\r\n";
    private final static byte[]  _wl_block3Bytes = _getBytes( _wl_block3 );

    private final static String  _wl_block4 ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n    <title>Book Catalog</title>\r\n</head>\r\n<form type=POST action=\"BookCatalog.jsp\">\r\n  <p>&nbsp;</p>\r\n  <p>&nbsp;</p>\r\n  <h1 align=\"center\"><font face=\"Comic Sans MS\">BookCart Application</font></h1>\r\n  <p>&nbsp;</p>\r\n  <p><font face=\"Comic Sans MS\">The JSP application shows the usage of a Java \r\n    Bean in a JSP application.</font><br/>\r\n    <br/>\r\n    <font face=\"Comic Sans MS\">Add/Remove Book: </font> \r\n    <SELECT NAME=\"item\" title=\"Select Books\">\r\n      <OPTION>The Time Machine \r\n      <OPTION>The Count of Monte Cristo \r\n      <OPTION>The Lord of the Rings \r\n      <OPTION>Pride and Prejudice \r\n      <OPTION>The Adventures of Sherlock Holmes \r\n      <OPTION>Alice\'s Adventures in Wonderland \r\n    </SELECT>\r\n    <br/>\r\n    <br/>\r\n    <INPUT TYPE=submit name=\"submit\" value=\"Add\"/>\r\n    <INPUT TYPE=submit name=\"submit\" value=\"Remove\"/>\r\n    <input type=\"submit\" name=\"submit\" value=\"Show_Details\"/>\r\n  </p>\r\n  <p align=\"center\">&nbsp;</p>\r\n<hr>\r\n  <br/>\r\n</form>  \r\n</body>\r\n</html>\r\n\r\n";
    private final static byte[]  _wl_block4Bytes = _getBytes( _wl_block4 );

    private final static String  _wl_block5 ="\r\n<div align=\"center\">\r\n  <table width=\"66%\" border=\"0\" height=\"159\" align=\"left\">\r\n    <tr> \r\n      <td width=\"54%\" height=\"206\"> \r\n        <p align=\"left\"><font face=\"Comic Sans MS\">Book Details: </font><br>\r\n          <br/>\r\n          ";
    private final static byte[]  _wl_block5Bytes = _getBytes( _wl_block5 );

    private final static String  _wl_block6 ="\r\n        </p>\r\n        <p align=\"left\">&nbsp;</p>\r\n        <p align=\"left\">&nbsp;</p>\r\n        </td>\r\n      <td width=\"46%\" height=\"206\"> \r\n        <p align=\"left\"><font face=\"Comic Sans MS\">You have the following items \r\n          in your cart: </font></p>\r\n        <p align=\"left\"> \r\n          <select name=\"select\" size=\"10\">\r\n            ";
    private final static byte[]  _wl_block6Bytes = _getBytes( _wl_block6 );

    private final static String  _wl_block7 ="\r\n            <option> \r\n            ";
    private final static byte[]  _wl_block7Bytes = _getBytes( _wl_block7 );

    private final static String  _wl_block8 ="\r\n            </option>\r\n            ";
    private final static byte[]  _wl_block8Bytes = _getBytes( _wl_block8 );

    private final static String  _wl_block9 ="\r\n          </select>\r\n        </p>\r\n      </td>\r\n    </tr>\r\n  </table>\r\n</div>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p align=\"center\">&nbsp; </p>\r\n<hr>\r\n<hr>\r\n<p>&nbsp;</p>\r\n<p align=\"center\">&nbsp; </p>\r\n<br/>\r\n<br/>\r\n</html>\r\n";
    private final static byte[]  _wl_block9Bytes = _getBytes( _wl_block9 );

    static private weblogic.jsp.internal.jsp.JspFunctionMapper _jspx_fnmap = weblogic.jsp.internal.jsp.JspFunctionMapper.getInstance();

    public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) 
    throws javax.servlet.ServletException, java.io.IOException {

        javax.servlet.ServletConfig config = getServletConfig();
        javax.servlet.ServletContext application = config.getServletContext();
        javax.servlet.jsp.tagext.JspTag _activeTag = null;
        Object page = this;
        javax.servlet.jsp.PageContext pageContext = javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true , 8192 , true );
        response.setHeader("Content-Type", "text/html;charset=windows-1252");
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter)out;
        bw.setInitCharacterEncoding(_WL_ORIGINAL_ENCODING, _WL_ENCODED_BYTES_OK);
        javax.servlet.jsp.JspWriter _originalOut = out;
        javax.servlet.http.HttpSession session = request.getSession( true );
        try {;
            response.setContentType("text/html;charset=windows-1252");
            bw.write(_wl_block0Bytes, _wl_block0);
            bw.write(_wl_block1Bytes, _wl_block1);
            oracle.jsp.CartBean cart= null;synchronized(session){
                cart=(oracle.jsp.CartBean)pageContext.getAttribute("cart",PageContext.SESSION_SCOPE);
                if(cart==null){
                    cart=new oracle.jsp.CartBean();
                    pageContext.setAttribute("cart",cart,PageContext.SESSION_SCOPE);

                }
            }
            bw.write(_wl_block2Bytes, _wl_block2);
            {String __value = request.getParameter("item");

                if ((__value != null) && !__value.equals((Object)""))cart.setItem(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType(__value, java.lang.String .class));
            }{String __value = request.getParameter("submit");

                if ((__value != null) && !__value.equals((Object)""))cart.setSubmit(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType(__value, java.lang.String .class));
            }bw.write(_wl_block3Bytes, _wl_block3);
     
       	cart.processRequest();

            bw.write(_wl_block2Bytes, _wl_block2);
            bw.write(_wl_block4Bytes, _wl_block4);
            bw.write(_wl_block5Bytes, _wl_block5);

String res = "";
res = request.getParameter("item");
out.print(cart.showDetails(res));

            bw.write(_wl_block6Bytes, _wl_block6);
 
	String[] items = cart.getItems();
	for (int i=0; i<items.length; i++) {

            bw.write(_wl_block7Bytes, _wl_block7);
 out.print((items[i])); 
            bw.write(_wl_block8Bytes, _wl_block8);

	}

            bw.write(_wl_block9Bytes, _wl_block9);
        } catch (Throwable __ee){
            if(!(__ee instanceof javax.servlet.jsp.SkipPageException)) {
                while ((out != null) && (out != _originalOut)) out = pageContext.popBody(); 
                _releaseTags(pageContext, _activeTag);
                pageContext.handlePageException(__ee);
            }
        }
    }
}
