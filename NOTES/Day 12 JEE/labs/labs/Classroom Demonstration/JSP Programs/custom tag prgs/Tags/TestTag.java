//Code for the tag handler:TestTag.java
import java.io.IOException;
import java.util.Date;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

//Implementing the tag generates the HTML
public class TestTag extends TagSupport
{

	//This is the start of the tag
	public int doStartTag() throws JspTagException
	{
		return EVAL_BODY_INCLUDE;
	}
	//This is the end of the tag
	public int doEndTag() throws JspTagException
	{

		try
		{
JspWriter out=pageContext.getOut();
		String dateString=new Date().toString();
				pageContext.getOut().write("Hello everybody</br>");
				pageContext.getOut().write("Date: " + dateString + "<p/>");
		}
		catch(IOException ee)
		{
			throw new JspTagException("Error encountered");
		}
		//This return value evaluates the rest of the page
		return EVAL_PAGE;
	}
}
