
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import oracle.jsp.el.*;
import javax.el.*;


public class _BookDetails extends com.orionserver.http.OrionHttpJspPage {


  // Begin global declarations

  private static OracleUnifiedFunctionMapperImpl[] __ojsp_fnmappers = OracleUnifiedELRuntime.__init_ojsp_unifiedfMappers(2);
  // End global declarations

  // begin functions
  static {
    __ojsp_fnmappers[0].mapFunction("ns","caps",bookdetails.oracle.el.Functions.class,"caps",new Class[] {java.lang.String.class});
    __ojsp_fnmappers[1].mapFunction("ns","reverse",bookdetails.oracle.el.Functions.class,"reverse",new Class[] {java.lang.String.class});
  }
  // end functions

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
    _BookDetails page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      if (_handler_1_helper_1(pageContext))
        return;
      out.write(__oracle_jsp_text[3]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${book.title}",java.lang.String.class, pageContext, null));
      out.write(__oracle_jsp_text[4]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:caps(book.title)}",java.lang.String.class, pageContext, __ojsp_fnmappers[0]));
      out.write(__oracle_jsp_text[5]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:reverse(book.title)}",java.lang.String.class, pageContext, __ojsp_fnmappers[1]));
      out.write(__oracle_jsp_text[6]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${book.author}",java.lang.String.class, pageContext, null));
      out.write(__oracle_jsp_text[7]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:caps(book.author)}",java.lang.String.class, pageContext, __ojsp_fnmappers[0]));
      out.write(__oracle_jsp_text[8]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:reverse(book.author)}",java.lang.String.class, pageContext, __ojsp_fnmappers[1]));
      out.write(__oracle_jsp_text[9]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${book.description}",java.lang.String.class, pageContext, null));
      out.write(__oracle_jsp_text[10]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:caps(book.description)}",java.lang.String.class, pageContext, __ojsp_fnmappers[0]));
      out.write(__oracle_jsp_text[11]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:reverse(book.description)}",java.lang.String.class, pageContext, __ojsp_fnmappers[1]));
      out.write(__oracle_jsp_text[12]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${book.isbn}",java.lang.String.class, pageContext, null));
      out.write(__oracle_jsp_text[13]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:caps(book.isbn)}",java.lang.String.class, pageContext, __ojsp_fnmappers[0]));
      out.write(__oracle_jsp_text[14]);
      out.write( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${ns:reverse(book.isbn)}",java.lang.String.class, pageContext, __ojsp_fnmappers[1]));
      out.write(__oracle_jsp_text[15]);

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
    
  private boolean _handler_1_helper_1(PageContext pageContext, Object... tags  ) throws  java.lang.Throwable {
    
    
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    {
      bookdetails.oracle.tag.SearchBookTag _handler_1= (bookdetails.oracle.tag.SearchBookTag) OracleJspRuntime.getSimpleTagHandler(bookdetails.oracle.tag.SearchBookTag.class, pageContext);
      _handler_1.setJspContext(pageContext);
      _handler_1.setVar("book");
      _handler_1.doTag();
      OracleJspRuntime.simpleReleaseTagHandler(_handler_1, pageContext);
    }
    return false;
  }

  
  private static final char __oracle_jsp_text[][]=new char[16][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n<html>\n    <head>\n        <title>Book Details</title>\n    </head>\n    <body style=\"font-family:'Comic Sans MS';\">\n        <h1 align=\"center\">My JSP Book Details Application</h1>\n         \n        <hr></hr>\n         \n        <p>The application shows the usage of Custom Tag and the Expression Language.</p>\n         <ul>\n            <li>\n                A \n                <em>&lt;ns:searchBook&gt;</em>\n                 tag is invoked to populate the JSP page context with book\n                details from the &quot;BookBean&quot; Java bean</li>\n            <li>The books fields are all printed in capital letters.</li>\n            <li>The details are printed in reverse.</li>\n        </ul>\n         \n        <br></br>  \n        <b><u>Result:</u></b>\n         \n        <br></br>\n         \n        ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n         \n        <table border=\"1\">\n            <thead>\n                <td>\n                    <b>Field</b>\n                </td>\n                <td>\n                    <b>Description</b>\n                </td>\n                <td>\n                    <b>Capitalized</b>\n                </td>\n                <td>\n                    <b>Reversed</b>\n                </td>\n            </thead>\n            <tr>\n                <td width=\"91\">Title</td>\n                <td width=\"182\">\n                    ".toCharArray();
    __oracle_jsp_text[4] = 
    "\n                </td>\n                <td width=\"248\">\n                    ".toCharArray();
    __oracle_jsp_text[5] = 
    "\n                </td>\n                <td width=\"271\">\n                    ".toCharArray();
    __oracle_jsp_text[6] = 
    "\n                </td>\n            </tr>\n            <tr>\n                <td width=\"91\">Author</td>\n                <td width=\"182\">\n                    ".toCharArray();
    __oracle_jsp_text[7] = 
    "\n                </td>\n                <td width=\"248\">\n                    ".toCharArray();
    __oracle_jsp_text[8] = 
    "\n                </td>\n                  <td width=\"271\">\n                    ".toCharArray();
    __oracle_jsp_text[9] = 
    "\n                </td>\n            </tr>\n            <tr>\n                <td width=\"91\">Description</td>\n                <td width=\"182\">\n                    ".toCharArray();
    __oracle_jsp_text[10] = 
    "\n                </td>\n                <td width=\"248\">\n                    ".toCharArray();
    __oracle_jsp_text[11] = 
    "\n                </td>\n                  <td width=\"271\">\n                    ".toCharArray();
    __oracle_jsp_text[12] = 
    "\n                </td>\n            </tr>\n            <tr>\n                <td width=\"91\">ISBN</td>\n                <td width=\"182\">\n                    ".toCharArray();
    __oracle_jsp_text[13] = 
    "\n                </td>\n                <td width=\"248\">\n                    ".toCharArray();
    __oracle_jsp_text[14] = 
    "\n                </td>\n                  <td width=\"271\">\n                    ".toCharArray();
    __oracle_jsp_text[15] = 
    "\n                </td>\n            </tr>\n        </table>\n         \n    </body>\n</html>".toCharArray();
    }
    catch (java.lang.Throwable th) {
      java.lang.System.err.println(th);
    }
  }
}
