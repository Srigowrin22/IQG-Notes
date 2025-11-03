package first;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.*;

public class SimpleRandomTag extends TagSupport 
{
	protected int len = 50;

	public int doStartTag() 
	{
		try {
			JspWriter out = pageContext.getOut();
			int num = new Random( 10 ).nextInt();
			out.print( num );
		} catch(Exception e) {
			System.out.println("Error generating number : " + e);
		}
		return(SKIP_BODY);
	}
}