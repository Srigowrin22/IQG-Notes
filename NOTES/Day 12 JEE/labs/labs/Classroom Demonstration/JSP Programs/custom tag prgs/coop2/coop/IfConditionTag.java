package coop;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class IfConditionTag extends BodyTagSupport 
{
	public int doStartTag() throws JspTagException 
	{
		IfTag parent = (IfTag)findAncestorWithClass(this, IfTag.class);

		if (parent == null) 
			throw new JspTagException("condition not inside if");

		return(EVAL_BODY_BUFFERED);
	}

	public int doAfterBody() 
	{
		IfTag parent = (IfTag)findAncestorWithClass(this, IfTag.class);
		String bodyString = getBodyContent().getString();

		if (bodyString.trim().equals("true")) 
			parent.setCondition(true);
		else
			parent.setCondition(false);

		return(SKIP_BODY);
	}
}