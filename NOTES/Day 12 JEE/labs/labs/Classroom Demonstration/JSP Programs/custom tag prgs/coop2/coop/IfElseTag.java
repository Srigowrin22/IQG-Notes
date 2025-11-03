package coop;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class IfElseTag extends BodyTagSupport 
{
	public int doStartTag() throws JspTagException 
	{
		IfTag parent = (IfTag)findAncestorWithClass(this, IfTag.class);

		if (parent == null) 
			throw new JspTagException("else not inside if");
		else if (!parent.getHasCondition()) 
		{
			String warning = "condition tag must come before else tag";
			throw new JspTagException(warning);
		}
		return(EVAL_BODY_BUFFERED);
	}

	public int doAfterBody() 
	{
		IfTag parent =	(IfTag)findAncestorWithClass(this, IfTag.class);

		if (!parent.getCondition())
		{
			try {
				BodyContent body = getBodyContent();
				JspWriter out = body.getEnclosingWriter();
				out.print(body.getString());
			} catch(Exception e) 
			{	System.out.println("Error in IfElseTag: " + e);		}
		}
		return(SKIP_BODY);
	}
}