package training.iqgateway;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SayHelloTag extends TagSupport {
    
    @Override
    public int doStartTag() throws JspException{
        return SKIP_BODY;
    }
    
    @Override
    public int doEndTag() throws JspException{
        try{
            pageContext.getOut().write("Welcome to the new era of development " + 
                "Application using JSP Custome tags");
        }catch(Exception e){
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
