import java.lang.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.awt.*;

public class ClockDemo extends Applet implements Runnable
{
	Thread T;
	public void start()
	{
		if(T==null)
		{
			T=new Thread();
			T.start();
		}
	}
	public void run()
	{
		while(T!=null)
		{
			repaint();
			try
			{
				T.sleep(5000);
			}
			catch(Exception e)
			{}
		}
	}

	public void paint(Graphics g)
	{
		String str;
		Date time=new Date();
		GregorianCalendar Calender =new GregorianCalendar();
		Calender.setTime(time);
		str= Calender.get(Calender.HOUR)+":"+
		Calender.get(Calender.MINUTE)+":"+
		Calender.get(Calender.SECOND);
		g.drawString(str,5,10);
}
}