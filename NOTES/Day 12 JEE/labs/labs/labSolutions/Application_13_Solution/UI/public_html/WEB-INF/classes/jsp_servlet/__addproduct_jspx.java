package jsp_servlet;

import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class __addproduct_jspx extends  weblogic.servlet.jsp.JspBase  implements weblogic.servlet.jsp.StaleIndicator {

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
        if (sci.isResourceStale("/AddProduct.jspx", 1227207044000L ,"10.3.0.0","Europe/Prague")) return true;
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

    private final static String  _wl_block0 ="<p>";
    private final static byte[]  _wl_block0Bytes = _getBytes( _wl_block0 );

    private final static String  _wl_block1 ="</p>";
    private final static byte[]  _wl_block1Bytes = _getBytes( _wl_block1 );

    private final static String  _wl_block2 ="\r\n                 \r\n              ";
    private final static byte[]  _wl_block2Bytes = _getBytes( _wl_block2 );

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
             com.sun.faces.taglib.jsf_core.ViewTag __tag0 = null ;
            int __result__tag0 = 0 ;

            if (__tag0 == null ){
                __tag0 = new  com.sun.faces.taglib.jsf_core.ViewTag ();
                weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag0);
            }
            __tag0.setPageContext(pageContext);
            __tag0.setParent(null);
            __tag0.setJspId("id0");
            _activeTag=__tag0;
            __result__tag0 = __tag0.doStartTag();

            if (__result__tag0!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
                try {
                    if (__result__tag0== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                        out = pageContext.pushBody();
                        bw = (weblogic.servlet.jsp.ByteWriter)out;
                        __tag0.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                        __tag0.doInitBody();
                    }
                    do {
                         oracle.adfinternal.view.faces.unified.taglib.UnifiedDocumentTag __tag1 = null ;
                        int __result__tag1 = 0 ;

                        if (__tag1 == null ){
                            __tag1 = new  oracle.adfinternal.view.faces.unified.taglib.UnifiedDocumentTag ();
                            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag1);
                        }
                        __tag1.setPageContext(pageContext);
                        __tag1.setParent(__tag0);
                        __tag1.setJspId("id1");
                        _activeTag=__tag1;
                        __result__tag1 = __tag1.doStartTag();

                        if (__result__tag1!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
                            try {
                                if (__result__tag1== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                    out = pageContext.pushBody();
                                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                                    __tag1.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                                    __tag1.doInitBody();
                                }
                                do {
                                     oracle.adfinternal.view.faces.unified.taglib.UnifiedFormTag __tag2 = null ;
                                    int __result__tag2 = 0 ;

                                    if (__tag2 == null ){
                                        __tag2 = new  oracle.adfinternal.view.faces.unified.taglib.UnifiedFormTag ();
                                        weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag2);
                                    }
                                    __tag2.setPageContext(pageContext);
                                    __tag2.setParent(__tag1);
                                    __tag2.setJspId("id2");
                                    _activeTag=__tag2;
                                    __result__tag2 = __tag2.doStartTag();

                                    if (__result__tag2!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
                                        try {
                                            if (__result__tag2== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                out = pageContext.pushBody();
                                                bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                __tag2.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                                                __tag2.doInitBody();
                                            }
                                            do {
                                                 oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag __tag3 = null ;
                                                int __result__tag3 = 0 ;

                                                if (__tag3 == null ){
                                                    __tag3 = new  oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag ();
                                                    weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag3);
                                                }
                                                __tag3.setPageContext(pageContext);
                                                __tag3.setParent(__tag2);
                                                __tag3.setJspId("id3");
                                                __tag3.setText( weblogic.servlet.jsp.ELHelper.createValueExpression("Add Product",java.lang.Object.class,pageContext, null ));
                                                __tag3.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.panelHeader2}",javax.faces.component.UIComponent.class,pageContext, null ));
                                                __tag3.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("panelHeader2", java.lang.String .class));
                                                _activeTag=__tag3;
                                                __result__tag3 = __tag3.doStartTag();

                                                if (__result__tag3!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
                                                    try {
                                                        if (__result__tag3== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                            out = pageContext.pushBody();
                                                            bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                            __tag3.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                                                            __tag3.doInitBody();
                                                        }
                                                        do {

                                                            if (_jspx___tag4(request, response, pageContext, _activeTag, __tag3))
                                                             return;

                                                            if (_jspx___tag5(request, response, pageContext, _activeTag, __tag3))
                                                             return;

                                                            if (_jspx___tag6(request, response, pageContext, _activeTag, __tag3))
                                                             return;

                                                            if (_jspx___tag7(request, response, pageContext, _activeTag, __tag3))
                                                             return;

                                                            if (_jspx___tag8(request, response, pageContext, _activeTag, __tag3))
                                                             return;
                                                             oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag __tag9 = null ;
                                                            int __result__tag9 = 0 ;

                                                            if (__tag9 == null ){
                                                                __tag9 = new  oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag ();
                                                                weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag9);
                                                            }
                                                            __tag9.setPageContext(pageContext);
                                                            __tag9.setParent(__tag3);
                                                            __tag9.setJspId("id9");
                                                            __tag9.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.panelFormLayout1}",javax.faces.component.UIComponent.class,pageContext, null ));
                                                            __tag9.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("panelFormLayout1", java.lang.String .class));
                                                            _activeTag=__tag9;
                                                            __result__tag9 = __tag9.doStartTag();

                                                            if (__result__tag9!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
                                                                try {
                                                                    if (__result__tag9== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                                        out = pageContext.pushBody();
                                                                        bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                                        __tag9.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                                                                        __tag9.doInitBody();
                                                                    }
                                                                    do {

                                                                        if (_jspx___tag10(request, response, pageContext, _activeTag, __tag9))
                                                                         return;

                                                                        if (_jspx___tag15(request, response, pageContext, _activeTag, __tag9))
                                                                         return;
                                                                         oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag __tag17 = null ;
                                                                        int __result__tag17 = 0 ;

                                                                        if (__tag17 == null ){
                                                                            __tag17 = new  oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag ();
                                                                            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag17);
                                                                        }
                                                                        __tag17.setPageContext(pageContext);
                                                                        __tag17.setParent(__tag9);
                                                                        __tag17.setJspId("id17");
                                                                        __tag17.setLabel( weblogic.servlet.jsp.ELHelper.createValueExpression("Description",java.lang.Object.class,pageContext, null ));
                                                                        __tag17.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.npDescription}",javax.faces.component.UIComponent.class,pageContext, null ));
                                                                        __tag17.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("npDescription", java.lang.String .class));
                                                                        __tag17.setRequired( weblogic.servlet.jsp.ELHelper.createValueExpression("true",boolean.class,pageContext, null ));
                                                                        _activeTag=__tag17;
                                                                        __result__tag17 = __tag17.doStartTag();

                                                                        if (__result__tag17!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
                                                                            try {
                                                                                if (__result__tag17== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                                                    out = pageContext.pushBody();
                                                                                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                                                    __tag17.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                                                                                    __tag17.doInitBody();
                                                                                }
                                                                                do {

                                                                                    if (_jspx___tag18(request, response, pageContext, _activeTag, __tag17))
                                                                                     return;
                                                                                    bw.write(_wl_block0Bytes, _wl_block0);
                                                                                    bw.write(_wl_block2Bytes, _wl_block2);
                                                                                    bw.write(_wl_block1Bytes, _wl_block1);
                                                                                } while (__tag17.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
                                                                            } finally {
                                                                                if (__result__tag17== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                                                    out = pageContext.popBody();
                                                                                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                                                }
                                                                            }
                                                                        }
                                                                        if (__tag17.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
                                                                            _activeTag = null;
                                                                            _releaseTags(pageContext, __tag17);
                                                                            return;
                                                                        }
                                                                        _activeTag=__tag17.getParent();
                                                                        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag17);
                                                                        __tag17.release();

                                                                        if (_jspx___tag19(request, response, pageContext, _activeTag, __tag9))
                                                                         return;

                                                                        if (_jspx___tag21(request, response, pageContext, _activeTag, __tag9))
                                                                         return;

                                                                        if (_jspx___tag22(request, response, pageContext, _activeTag, __tag9))
                                                                         return;

                                                                        if (_jspx___tag23(request, response, pageContext, _activeTag, __tag9))
                                                                         return;
                                                                    } while (__tag9.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
                                                                } finally {
                                                                    if (__result__tag9== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                                        out = pageContext.popBody();
                                                                        bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                                    }
                                                                }
                                                            }
                                                            if (__tag9.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
                                                                _activeTag = null;
                                                                _releaseTags(pageContext, __tag9);
                                                                return;
                                                            }
                                                            _activeTag=__tag9.getParent();
                                                            weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag9);
                                                            __tag9.release();
                                                        } while (__tag3.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
                                                    } finally {
                                                        if (__result__tag3== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                            out = pageContext.popBody();
                                                            bw = (weblogic.servlet.jsp.ByteWriter)out;
                                                        }
                                                    }
                                                }
                                                if (__tag3.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
                                                    _activeTag = null;
                                                    _releaseTags(pageContext, __tag3);
                                                    return;
                                                }
                                                _activeTag=__tag3.getParent();
                                                weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag3);
                                                __tag3.release();
                                            } while (__tag2.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
                                        } finally {
                                            if (__result__tag2== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                                out = pageContext.popBody();
                                                bw = (weblogic.servlet.jsp.ByteWriter)out;
                                            }
                                        }
                                    }
                                    if (__tag2.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
                                        _activeTag = null;
                                        _releaseTags(pageContext, __tag2);
                                        return;
                                    }
                                    _activeTag=__tag2.getParent();
                                    weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag2);
                                    __tag2.release();
                                } while (__tag1.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
                            } finally {
                                if (__result__tag1== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                                    out = pageContext.popBody();
                                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                                }
                            }
                        }
                        if (__tag1.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
                            _activeTag = null;
                            _releaseTags(pageContext, __tag1);
                            return;
                        }
                        _activeTag=__tag1.getParent();
                        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag1);
                        __tag1.release();
                    } while (__tag0.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
                } finally {
                    if (__result__tag0== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                        out = pageContext.popBody();
                        bw = (weblogic.servlet.jsp.ByteWriter)out;
                    }
                }
            }
            if (__tag0.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
                _activeTag = null;
                _releaseTags(pageContext, __tag0);
                return;
            }
            _activeTag=__tag0.getParent();
            weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag0);
            __tag0.release();
        } catch (Throwable __ee){
            if(!(__ee instanceof javax.servlet.jsp.SkipPageException)) {
                while ((out != null) && (out != _originalOut)) out = pageContext.popBody(); 
                _releaseTags(pageContext, _activeTag);
                pageContext.handlePageException(__ee);
            }
        }
    }

    private boolean _jspx___tag4(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         javax.faces.webapp.FacetTag __tag4 = null ;
        int __result__tag4 = 0 ;

        if (__tag4 == null ){
            __tag4 = new  javax.faces.webapp.FacetTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag4);
        }
        __tag4.setPageContext(pageContext);
        __tag4.setParent(parent);
        __tag4.setName(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("context", java.lang.String .class));
        _activeTag=__tag4;
        __result__tag4 = __tag4.doStartTag();

        if (__result__tag4!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag4== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class javax.faces.webapp.FacetTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag4.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag4);
            return true;
        }
        _activeTag=__tag4.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag4);
        __tag4.release();
        return false;
    }

    private boolean _jspx___tag5(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         javax.faces.webapp.FacetTag __tag5 = null ;
        int __result__tag5 = 0 ;

        if (__tag5 == null ){
            __tag5 = new  javax.faces.webapp.FacetTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag5);
        }
        __tag5.setPageContext(pageContext);
        __tag5.setParent(parent);
        __tag5.setName(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("menuBar", java.lang.String .class));
        _activeTag=__tag5;
        __result__tag5 = __tag5.doStartTag();

        if (__result__tag5!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag5== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class javax.faces.webapp.FacetTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag5.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag5);
            return true;
        }
        _activeTag=__tag5.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag5);
        __tag5.release();
        return false;
    }

    private boolean _jspx___tag6(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         javax.faces.webapp.FacetTag __tag6 = null ;
        int __result__tag6 = 0 ;

        if (__tag6 == null ){
            __tag6 = new  javax.faces.webapp.FacetTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag6);
        }
        __tag6.setPageContext(pageContext);
        __tag6.setParent(parent);
        __tag6.setName(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("toolbar", java.lang.String .class));
        _activeTag=__tag6;
        __result__tag6 = __tag6.doStartTag();

        if (__result__tag6!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag6== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class javax.faces.webapp.FacetTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag6.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag6);
            return true;
        }
        _activeTag=__tag6.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag6);
        __tag6.release();
        return false;
    }

    private boolean _jspx___tag7(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         javax.faces.webapp.FacetTag __tag7 = null ;
        int __result__tag7 = 0 ;

        if (__tag7 == null ){
            __tag7 = new  javax.faces.webapp.FacetTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag7);
        }
        __tag7.setPageContext(pageContext);
        __tag7.setParent(parent);
        __tag7.setName(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("legend", java.lang.String .class));
        _activeTag=__tag7;
        __result__tag7 = __tag7.doStartTag();

        if (__result__tag7!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag7== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class javax.faces.webapp.FacetTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag7.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag7);
            return true;
        }
        _activeTag=__tag7.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag7);
        __tag7.release();
        return false;
    }

    private boolean _jspx___tag8(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelHeaderTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         javax.faces.webapp.FacetTag __tag8 = null ;
        int __result__tag8 = 0 ;

        if (__tag8 == null ){
            __tag8 = new  javax.faces.webapp.FacetTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag8);
        }
        __tag8.setPageContext(pageContext);
        __tag8.setParent(parent);
        __tag8.setName(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("info", java.lang.String .class));
        _activeTag=__tag8;
        __result__tag8 = __tag8.doStartTag();

        if (__result__tag8!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag8== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class javax.faces.webapp.FacetTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag8.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag8);
            return true;
        }
        _activeTag=__tag8.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag8);
        __tag8.release();
        return false;
    }

    private boolean _jspx___tag10(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         javax.faces.webapp.FacetTag __tag10 = null ;
        int __result__tag10 = 0 ;

        if (__tag10 == null ){
            __tag10 = new  javax.faces.webapp.FacetTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag10);
        }
        __tag10.setPageContext(pageContext);
        __tag10.setParent(parent);
        __tag10.setName(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("footer", java.lang.String .class));
        _activeTag=__tag10;
        __result__tag10 = __tag10.doStartTag();

        if (__result__tag10!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag10== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class javax.faces.webapp.FacetTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
            do {

                if (_jspx___tag11(request, response, pageContext, _activeTag, __tag10))
                 return true;
            } while (__tag10.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
        }
        if (__tag10.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag10);
            return true;
        }
        _activeTag=__tag10.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag10);
        __tag10.release();
        return false;
    }

    private boolean _jspx___tag11(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, javax.faces.webapp.FacetTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         org.apache.myfaces.trinidadinternal.taglib.UIXGroupTag __tag11 = null ;
        int __result__tag11 = 0 ;

        if (__tag11 == null ){
            __tag11 = new  org.apache.myfaces.trinidadinternal.taglib.UIXGroupTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag11);
        }
        __tag11.setPageContext(pageContext);
        __tag11.setParent(parent);
        __tag11.setJspId("id11");
        __tag11.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.group1}",java.lang.Object.class,pageContext, null ));
        __tag11.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("group1", java.lang.String .class));
        _activeTag=__tag11;
        __result__tag11 = __tag11.doStartTag();

        if (__result__tag11!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            try {
                if (__result__tag11== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.pushBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                    __tag11.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                    __tag11.doInitBody();
                }
                do {

                    if (_jspx___tag12(request, response, pageContext, _activeTag, __tag11))
                     return true;
                } while (__tag11.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
            } finally {
                if (__result__tag11== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.popBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                }
            }
        }
        if (__tag11.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag11);
            return true;
        }
        _activeTag=__tag11.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag11);
        __tag11.release();
        return false;
    }

    private boolean _jspx___tag12(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, org.apache.myfaces.trinidadinternal.taglib.UIXGroupTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedToolbarTag __tag12 = null ;
        int __result__tag12 = 0 ;

        if (__tag12 == null ){
            __tag12 = new  oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedToolbarTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag12);
        }
        __tag12.setPageContext(pageContext);
        __tag12.setParent(parent);
        __tag12.setJspId("id12");
        __tag12.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.toolbar1}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag12.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("toolbar1", java.lang.String .class));
        _activeTag=__tag12;
        __result__tag12 = __tag12.doStartTag();

        if (__result__tag12!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            try {
                if (__result__tag12== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.pushBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                    __tag12.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                    __tag12.doInitBody();
                }
                do {

                    if (_jspx___tag13(request, response, pageContext, _activeTag, __tag12))
                     return true;

                    if (_jspx___tag14(request, response, pageContext, _activeTag, __tag12))
                     return true;
                } while (__tag12.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
            } finally {
                if (__result__tag12== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.popBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                }
            }
        }
        if (__tag12.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag12);
            return true;
        }
        _activeTag=__tag12.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag12);
        __tag12.release();
        return false;
    }

    private boolean _jspx___tag13(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedToolbarTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.nav.UnifiedCommandToolbarButtonTag __tag13 = null ;
        int __result__tag13 = 0 ;

        if (__tag13 == null ){
            __tag13 = new  oracle.adfinternal.view.faces.unified.taglib.nav.UnifiedCommandToolbarButtonTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag13);
        }
        __tag13.setPageContext(pageContext);
        __tag13.setParent(parent);
        __tag13.setJspId("id13");
        __tag13.setText( weblogic.servlet.jsp.ELHelper.createValueExpression("Save",java.lang.Object.class,pageContext, null ));
        __tag13.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.addProduct}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag13.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("addProduct", java.lang.String .class));
        __tag13.setAction( weblogic.servlet.jsp.ELHelper.createMethodExpression("#{productBrowsingBean.addProduct}",java.lang.String.class,new Class[]{},pageContext, null ));
        _activeTag=__tag13;
        __result__tag13 = __tag13.doStartTag();

        if (__result__tag13!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag13== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag13.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag13);
            return true;
        }
        _activeTag=__tag13.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag13);
        __tag13.release();
        return false;
    }

    private boolean _jspx___tag14(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedToolbarTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.nav.UnifiedCommandToolbarButtonTag __tag14 = null ;
        int __result__tag14 = 0 ;

        if (__tag14 == null ){
            __tag14 = new  oracle.adfinternal.view.faces.unified.taglib.nav.UnifiedCommandToolbarButtonTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag14);
        }
        __tag14.setPageContext(pageContext);
        __tag14.setParent(parent);
        __tag14.setJspId("id14");
        __tag14.setText( weblogic.servlet.jsp.ELHelper.createValueExpression("Cancel",java.lang.Object.class,pageContext, null ));
        __tag14.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.cancelButton}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag14.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("cancelButton", java.lang.String .class));
        __tag14.setAction( weblogic.servlet.jsp.ELHelper.createMethodExpression("#{productBrowsingBean.cancelButton_action}",java.lang.String.class,new Class[]{},pageContext, null ));
        _activeTag=__tag14;
        __result__tag14 = __tag14.doStartTag();

        if (__result__tag14!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag14== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag14.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag14);
            return true;
        }
        _activeTag=__tag14.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag14);
        __tag14.release();
        return false;
    }

    private boolean _jspx___tag15(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag __tag15 = null ;
        int __result__tag15 = 0 ;

        if (__tag15 == null ){
            __tag15 = new  oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag15);
        }
        __tag15.setPageContext(pageContext);
        __tag15.setParent(parent);
        __tag15.setJspId("id15");
        __tag15.setLabel( weblogic.servlet.jsp.ELHelper.createValueExpression("Name",java.lang.Object.class,pageContext, null ));
        __tag15.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.npName}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag15.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("npName", java.lang.String .class));
        __tag15.setRequired( weblogic.servlet.jsp.ELHelper.createValueExpression("true",boolean.class,pageContext, null ));
        _activeTag=__tag15;
        __result__tag15 = __tag15.doStartTag();

        if (__result__tag15!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            try {
                if (__result__tag15== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.pushBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                    __tag15.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                    __tag15.doInitBody();
                }
                do {

                    if (_jspx___tag16(request, response, pageContext, _activeTag, __tag15))
                     return true;
                } while (__tag15.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
            } finally {
                if (__result__tag15== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.popBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                }
            }
        }
        if (__tag15.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag15);
            return true;
        }
        _activeTag=__tag15.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag15);
        __tag15.release();
        return false;
    }

    private boolean _jspx___tag16(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag __tag16 = null ;
        int __result__tag16 = 0 ;

        if (__tag16 == null ){
            __tag16 = new  oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag16);
        }
        __tag16.setPageContext(pageContext);
        __tag16.setParent(parent);
        __tag16.setMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("5",int.class,pageContext, null ));
        __tag16.setMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("30",int.class,pageContext, null ));
        __tag16.setHintMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("Name is required",java.lang.Object.class,pageContext, null ));
        __tag16.setHintMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("Must be less than 30",java.lang.Object.class,pageContext, null ));
        __tag16.setMessageDetailMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("Name is requiredand must be at least 5 characters",java.lang.Object.class,pageContext, null ));
        __tag16.setMessageDetailMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("Name must be 30 characters or less",java.lang.Object.class,pageContext, null ));
        _activeTag=__tag16;
        __result__tag16 = __tag16.doStartTag();

        if (__result__tag16!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag16== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag16.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag16);
            return true;
        }
        _activeTag=__tag16.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag16);
        __tag16.release();
        return false;
    }

    private boolean _jspx___tag18(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag __tag18 = null ;
        int __result__tag18 = 0 ;

        if (__tag18 == null ){
            __tag18 = new  oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag18);
        }
        __tag18.setPageContext(pageContext);
        __tag18.setParent(parent);
        __tag18.setMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("5",int.class,pageContext, null ));
        __tag18.setMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("3000",int.class,pageContext, null ));
        __tag18.setHintMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("Description is required",java.lang.Object.class,pageContext, null ));
        __tag18.setHintMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("Description must be 3000 characters or less",java.lang.Object.class,pageContext, null ));
        __tag18.setMessageDetailMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("A Description is required",java.lang.Object.class,pageContext, null ));
        __tag18.setMessageDetailMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("A Description must be 3000 characters or less",java.lang.Object.class,pageContext, null ));
        _activeTag=__tag18;
        __result__tag18 = __tag18.doStartTag();

        if (__result__tag18!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag18== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag18.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag18);
            return true;
        }
        _activeTag=__tag18.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag18);
        __tag18.release();
        return false;
    }

    private boolean _jspx___tag19(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag __tag19 = null ;
        int __result__tag19 = 0 ;

        if (__tag19 == null ){
            __tag19 = new  oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag19);
        }
        __tag19.setPageContext(pageContext);
        __tag19.setParent(parent);
        __tag19.setJspId("id19");
        __tag19.setLabel( weblogic.servlet.jsp.ELHelper.createValueExpression("Additional Information",java.lang.Object.class,pageContext, null ));
        __tag19.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.npAdditionalInfo}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag19.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("npAdditionalInfo", java.lang.String .class));
        __tag19.setRequired( weblogic.servlet.jsp.ELHelper.createValueExpression("true",boolean.class,pageContext, null ));
        _activeTag=__tag19;
        __result__tag19 = __tag19.doStartTag();

        if (__result__tag19!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            try {
                if (__result__tag19== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.pushBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                    __tag19.setBodyContent(( javax.servlet.jsp.tagext.BodyContent)out);
                    __tag19.doInitBody();
                }
                do {

                    if (_jspx___tag20(request, response, pageContext, _activeTag, __tag19))
                     return true;
                } while (__tag19.doAfterBody()== javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN);
            } finally {
                if (__result__tag19== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                    out = pageContext.popBody();
                    bw = (weblogic.servlet.jsp.ByteWriter)out;
                }
            }
        }
        if (__tag19.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag19);
            return true;
        }
        _activeTag=__tag19.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag19);
        __tag19.release();
        return false;
    }

    private boolean _jspx___tag20(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag __tag20 = null ;
        int __result__tag20 = 0 ;

        if (__tag20 == null ){
            __tag20 = new  oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag20);
        }
        __tag20.setPageContext(pageContext);
        __tag20.setParent(parent);
        __tag20.setMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("5",int.class,pageContext, null ));
        __tag20.setMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("3000",int.class,pageContext, null ));
        __tag20.setHintMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("Additional Information is required",java.lang.Object.class,pageContext, null ));
        __tag20.setHintMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("Addtional Information must be less than 3000 characters",java.lang.Object.class,pageContext, null ));
        __tag20.setMessageDetailMinimum( weblogic.servlet.jsp.ELHelper.createValueExpression("Additional Information is required",java.lang.Object.class,pageContext, null ));
        __tag20.setMessageDetailMaximum( weblogic.servlet.jsp.ELHelper.createValueExpression("Addtional Information must be less than 3000 characters",java.lang.Object.class,pageContext, null ));
        _activeTag=__tag20;
        __result__tag20 = __tag20.doStartTag();

        if (__result__tag20!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag20== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
                 throw  new  javax.servlet.jsp.JspTagException("Since tag class oracle.adfinternal.view.faces.unified.taglib.validator.ValidateLengthTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED");
            }
        }
        if (__tag20.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag20);
            return true;
        }
        _activeTag=__tag20.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag20);
        __tag20.release();
        return false;
    }

    private boolean _jspx___tag21(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag __tag21 = null ;
        int __result__tag21 = 0 ;

        if (__tag21 == null ){
            __tag21 = new  oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag21);
        }
        __tag21.setPageContext(pageContext);
        __tag21.setParent(parent);
        __tag21.setJspId("id21");
        __tag21.setLabel( weblogic.servlet.jsp.ELHelper.createValueExpression("List Price",java.lang.Object.class,pageContext, null ));
        __tag21.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.npListPrice}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag21.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("npListPrice", java.lang.String .class));
        __tag21.setRequired( weblogic.servlet.jsp.ELHelper.createValueExpression("true",boolean.class,pageContext, null ));
        _activeTag=__tag21;
        __result__tag21 = __tag21.doStartTag();

        if (__result__tag21!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag21== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag21.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag21);
            return true;
        }
        _activeTag=__tag21.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag21);
        __tag21.release();
        return false;
    }

    private boolean _jspx___tag22(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag __tag22 = null ;
        int __result__tag22 = 0 ;

        if (__tag22 == null ){
            __tag22 = new  oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag22);
        }
        __tag22.setPageContext(pageContext);
        __tag22.setParent(parent);
        __tag22.setJspId("id22");
        __tag22.setLabel( weblogic.servlet.jsp.ELHelper.createValueExpression("Minimum Price",java.lang.Object.class,pageContext, null ));
        __tag22.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.npMinPrice}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag22.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("npMinPrice", java.lang.String .class));
        __tag22.setRequired( weblogic.servlet.jsp.ELHelper.createValueExpression("true",boolean.class,pageContext, null ));
        _activeTag=__tag22;
        __result__tag22 = __tag22.doStartTag();

        if (__result__tag22!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag22== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag22.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag22);
            return true;
        }
        _activeTag=__tag22.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag22);
        __tag22.release();
        return false;
    }

    private boolean _jspx___tag23(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, oracle.adfinternal.view.faces.unified.taglib.layout.UnifiedPanelFormLayoutTag parent) throws Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter bw = (weblogic.servlet.jsp.ByteWriter) out;
         oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag __tag23 = null ;
        int __result__tag23 = 0 ;

        if (__tag23 == null ){
            __tag23 = new  oracle.adfinternal.view.faces.unified.taglib.input.UnifiedInputTextTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag23);
        }
        __tag23.setPageContext(pageContext);
        __tag23.setParent(parent);
        __tag23.setJspId("id23");
        __tag23.setLabel( weblogic.servlet.jsp.ELHelper.createValueExpression("Cost Price",java.lang.Object.class,pageContext, null ));
        __tag23.setBinding( weblogic.servlet.jsp.ELHelper.createValueExpression("#{productBrowsingBean.npCostPrice}",javax.faces.component.UIComponent.class,pageContext, null ));
        __tag23.setId(( java.lang.String ) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("npCostPrice", java.lang.String .class));
        __tag23.setRequired( weblogic.servlet.jsp.ELHelper.createValueExpression("true",boolean.class,pageContext, null ));
        _activeTag=__tag23;
        __result__tag23 = __tag23.doStartTag();

        if (__result__tag23!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag23== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag23.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag23);
            return true;
        }
        _activeTag=__tag23.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag23);
        __tag23.release();
        return false;
    }
}
