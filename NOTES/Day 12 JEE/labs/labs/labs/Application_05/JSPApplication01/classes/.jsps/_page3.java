
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import oracle.jsp.el.*;
import javax.el.*;


public class _page3 extends com.orionserver.http.OrionHttpJspPage {


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
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _page3 page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {
      // compile time tag reuse - begin
      JspTag[] __ojsp_classicTags = new JspTag[1];
      // usage : org.apache.taglibs.standard.tag.rt.core.OutTag value
      org.apache.taglibs.standard.tag.rt.core.OutTag _ctru0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) OracleJspRuntime.getTagHandler(pageContext, org.apache.taglibs.standard.tag.rt.core.OutTag.class, "compiletime");
      // compile tag reuse - end


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      if (_ctru0_helper_1(pageContext, __ojsp_classicTags, _ctru0))
        return;
      out.write(__oracle_jsp_text[3]);
      if (_ctru0_helper_2(pageContext, __ojsp_classicTags, _ctru0))
        return;
      out.write(__oracle_jsp_text[4]);
      if (_ctru0_helper_3(pageContext, __ojsp_classicTags, _ctru0))
        return;
      out.write(__oracle_jsp_text[5]);
      if (_ctru0_helper_4(pageContext, __ojsp_classicTags, _ctru0))
        return;
      out.write(__oracle_jsp_text[6]);

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
    
  private boolean _ctru0_helper_1(PageContext pageContext, JspTag[] __ojsp_classicTags, Object... tags  ) throws  java.lang.Throwable {
    
    org.apache.taglibs.standard.tag.rt.core.OutTag _ctru0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) tags[0];
    
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    int __jsp_tag_starteval;

    {
      _ctru0.setPageContext(pageContext);
      _ctru0.setParent(null);
      _ctru0.setValue((java.lang.Object)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${sessionScope.firstName}",java.lang.Object.class, pageContext,null));
      __jsp_tag_starteval=_ctru0.doStartTag();
      if (_ctru0.doEndTag()==Tag.SKIP_PAGE)
        return true;
    }
    return false;
  }
  
  private boolean _ctru0_helper_2(PageContext pageContext, JspTag[] __ojsp_classicTags, Object... tags  ) throws  java.lang.Throwable {
    
    org.apache.taglibs.standard.tag.rt.core.OutTag _ctru0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) tags[0];
    
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    int __jsp_tag_starteval;

    {
      _ctru0.setPageContext(pageContext);
      _ctru0.setParent(null);
      _ctru0.setValue((java.lang.Object) ( (java.lang.String)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${sessionScope.lastName}",java.lang.String.class, pageContext, null)+" "));
      __jsp_tag_starteval=_ctru0.doStartTag();
      if (_ctru0.doEndTag()==Tag.SKIP_PAGE)
        return true;
    }
    return false;
  }
  
  private boolean _ctru0_helper_3(PageContext pageContext, JspTag[] __ojsp_classicTags, Object... tags  ) throws  java.lang.Throwable {
    
    org.apache.taglibs.standard.tag.rt.core.OutTag _ctru0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) tags[0];
    
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    int __jsp_tag_starteval;

    {
      _ctru0.setPageContext(pageContext);
      _ctru0.setParent(null);
      _ctru0.setValue((java.lang.Object)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${param.streetAddress}",java.lang.Object.class, pageContext,null));
      __jsp_tag_starteval=_ctru0.doStartTag();
      if (_ctru0.doEndTag()==Tag.SKIP_PAGE)
        return true;
    }
    return false;
  }
  
  private boolean _ctru0_helper_4(PageContext pageContext, JspTag[] __ojsp_classicTags, Object... tags  ) throws  java.lang.Throwable {
    
    org.apache.taglibs.standard.tag.rt.core.OutTag _ctru0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) tags[0];
    
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    JspWriter out = pageContext.getOut();
    int __jsp_tag_starteval;

    {
      _ctru0.setPageContext(pageContext);
      _ctru0.setParent(null);
      _ctru0.setValue((java.lang.Object)oracle.jsp.runtime.OracleUnifiedELRuntime.evaluateValueExpression("${param.city}",java.lang.Object.class, pageContext,null));
      __jsp_tag_starteval=_ctru0.doStartTag();
      if (_ctru0.doEndTag()==Tag.SKIP_PAGE)
        return true;
    }
    return false;
  }

  
  private static final char __oracle_jsp_text[][]=new char[7][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n\"http://www.w3.org/TR/html4/loose.dtd\">\r\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\r\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\r\n<html>\r\n  <head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\r\n    <title>page3</title>\r\n  </head>\r\n  <body><p>\r\n      First Name: ".toCharArray();
    __oracle_jsp_text[3] = 
    "\r\n    </p><p>\r\n      Last Name: \r\n      ".toCharArray();
    __oracle_jsp_text[4] = 
    "\r\n    </p><p>\r\n      Street Address: \r\n      ".toCharArray();
    __oracle_jsp_text[5] = 
    "\r\n    </p><p>\r\n      City: \r\n      ".toCharArray();
    __oracle_jsp_text[6] = 
    "\r\n    </p></body>\r\n</html>".toCharArray();
    }
    catch (java.lang.Throwable th) {
      java.lang.System.err.println(th);
    }
  }
}
