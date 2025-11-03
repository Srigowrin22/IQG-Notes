package coop;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class MultiTag extends BodyTagSupport 
{
	private int reps;
	public void setReps(String repeats) 
	{
		try {
			reps = Integer.parseInt(repeats);
		} catch(NumberFormatException nfe) {	reps = 1;	}
	}

	public int doAfterBody() 
	{
		if (reps-- >= 1) 
		{
			BodyContent body = getBodyContent();
			try {
				JspWriter out = body.getEnclosingWriter();
				out.println( reps + " : " + body.getString() );
				body.clearBody();
			} catch(Exception e) 
			{
				System.out.println("Error in RepeatTag: " + e);
			}
			return EVAL_BODY_AGAIN;
		} else {	return(SKIP_BODY);	}
	}
}