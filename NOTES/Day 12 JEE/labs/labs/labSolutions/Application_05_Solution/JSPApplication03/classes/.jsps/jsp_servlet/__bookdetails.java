package jsp_servlet;

import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class __bookdetails extends  weblogic.servlet.jsp.JspBase  implements weblogic.servlet.jsp.StaleIndicator {

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
        if (sci.isResourceStale("/BookDetails.jsp", 1252054657250L ,"10.3.0.0","Europe/Prague")) return true;
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

    private final static String  _wl_block1 ="\r\n\r\n";
    private final static byte[]  _wl_block1Bytes = _getBytes( _wl_block1 );

    private final static String  _wl_block2 ="\r\n\r\n<html>\r\n    <head>\r\n        <title>Book Details</title>\r\n    </head>\r\n    <body style=\"font-family:\'Comic Sans MS\';\">\r\n        <h1 align=\"center\">My JSP Book Details Application</h1>\r\n         \r\n        <hr></hr>\r\n         \r\n        <p>The application shows the usage of Custom Tag and the Expression Language.</p>\r\n         <ul>\r\n            <li>\r\n                A \r\n                <em>&lt;ns:searchBook&gt;</em>\r\n                 tag is invoked to populate the JSP page context with book\r\n                details from the &quot;BookBean&quot; Java bean</li>\r\n            <li>The books fields are all printed in capital letters.</li>\r\n            <li>The details are printed in reverse.</li>\r\n        </ul>\r\n         \r\n        <br></br>  \r\n        <b><u>Result:</u></b>\r\n            \r\n        <br></br>\r\n        \r\n         ";
    private final static byte[]  _wl_block2Bytes = _getBytes( _wl_block2 );

    private final static String  _wl_block3 ="\r\n\r\n         \r\n        <table border=\"1\">\r\n            <thead>\r\n            <tr>\r\n                <td>\r\n                    <b>Field</b>\r\n                </td>\r\n                <td>\r\n                    <b>Description</b>\r\n                </td>\r\n                <td>\r\n                    <b>Capitalized</b>\r\n                </td>\r\n                <td>\r\n                    <b>Reversed</b>\r\n                </td>\r\n                </tr>\r\n            </thead>\r\n            <tr>\r\n                <td width=\"91\">Title</td>\r\n                <td width=\"182\">\r\n                    ";
    private final static byte[]  _wl_block3Bytes = _getBytes( _wl_block3 );

    private final static String  _wl_block4 ="  \r\n                </td>\r\n                <td width=\"248\">\r\n                   ";
    private final static byte[]  _wl_block4Bytes = _getBytes( _wl_block4 );

    private final static String  _wl_block5 ="\r\n                </td>\r\n                <td width=\"271\">\r\n      \t\t ";
    private final static byte[]  _wl_block5Bytes = _getBytes( _wl_block5 );

    private final static String  _wl_block6 ="\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n                <td width=\"91\">Author</td>\r\n                <td width=\"182\">\r\n                    ";
    private final static byte[]  _wl_block6Bytes = _getBytes( _wl_block6 );

    private final static String  _wl_block7 ="\r\n                </td>\r\n                <td width=\"248\">\r\n                    ";
    private final static byte[]  _wl_block7Bytes = _getBytes( _wl_block7 );

    private final static String  _wl_block8 ="\r\n                </td>\r\n                  <td width=\"271\">\r\n                    ";
    private final static byte[]  _wl_block8Bytes = _getBytes( _wl_block8 );

    private final static String  _wl_block9 ="\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n                <td width=\"91\">Description</td>\r\n                <td width=\"182\">\r\n                    ";
    private final static byte[]  _wl_block9Bytes = _getBytes( _wl_block9 );

    private final static String  _wl_block10 ="\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n                <td width=\"91\">ISBN</td>\r\n                <td width=\"182\">\r\n                    ";
    private final static byte[]  _wl_block10Bytes = _getBytes( _wl_block10 );

    private final static String  _wl_block11 ="\r\n                </td>\r\n            </tr>\r\n        </table>\r\n         \r\n    </body>\r\n</html>";
    private final static byte[]  _wl_block11Bytes = _getBytes( _wl_block11 );

    static private weblogic.jsp.internal.jsp.JspFunctionMapper _jspx_fnmap = weblogic.jsp.internal.jsp.JspFunctionMapper.getInstance();
     static {_jspx_fnmap.mapFunction("ns:reverse", bookdetails.oracle.el.Functions.class, "reverse", new Class[]{ java.lang.String .class });
        _jspx_fnmap.mapFunction("ns:caps", bookdetails.oracle.el.Functions.class, "caps", new Class[]{ java.lang.String .class });
    }

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
            bw.write(_wl_block2Bytes, _wl_block2);
             bookdetails.oracle.tag.SearchBookTag __tag0 = null ;
            int __result__tag0 = 0 ;

            if (__tag0 == null ){
                __tag0 = new  bookdetails.oracle.tag.SearchBookTag ();
                weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag0);
            }
            __tag0.setJspContext(pageContext);

            __tag0.setVar(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("book", java.lang.String .class));
            _activeTag=__tag0;
            __tag0.doTag();
            weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag0);
            bw.write(_wl_block3Bytes, _wl_block3);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${book.title}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block4Bytes, _wl_block4);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:caps(book.title)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block5Bytes, _wl_block5);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:reverse(book.title)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block6Bytes, _wl_block6);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${book.author}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block7Bytes, _wl_block7);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:caps(book.author)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block8Bytes, _wl_block8);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:reverse(book.author)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block9Bytes, _wl_block9);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${book.description}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block7Bytes, _wl_block7);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:caps(book.description)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block8Bytes, _wl_block8);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:reverse(book.description)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block10Bytes, _wl_block10);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${book.isbn}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block7Bytes, _wl_block7);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:caps(book.isbn)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block8Bytes, _wl_block8);
            out.write(( java.lang.String ) weblogic.servlet.jsp.ELHelper.evaluate("${ns:reverse(book.isbn)}",java.lang.String.class,pageContext,_jspx_fnmap));
            bw.write(_wl_block11Bytes, _wl_block11);
        } catch (Throwable __ee){
            if(!(__ee instanceof javax.servlet.jsp.SkipPageException)) {
                while ((out != null) && (out != _originalOut)) out = pageContext.popBody(); 
                _releaseTags(pageContext, _activeTag);
                pageContext.handlePageException(__ee);
            }
        }
    }
}
