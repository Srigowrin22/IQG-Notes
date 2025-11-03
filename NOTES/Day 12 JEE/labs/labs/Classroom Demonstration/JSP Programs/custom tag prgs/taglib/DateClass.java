import java.util.Date;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class DateClass extends TagSupport
{
	public int doEndTag() throws JspException
	{
		try{
			Date dt = new Date();
			String dt_str = "" + dt;
			pageContext.getOut().write( dt_str );

		}catch( Exception e)	{	System.out.println( e );	}

		return EVAL_PAGE;
	}
}