package script;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ScriptTag extends TagSupport 
{
   	public int doStartTag() 
	{
		pageContext.setAttribute( "date", new java.util.Date() );  //**
	      	return EVAL_BODY_INCLUDE; 
  	}
   	public int doEndTag() throws JspException 
	{
      		return EVAL_PAGE;
   	}
}