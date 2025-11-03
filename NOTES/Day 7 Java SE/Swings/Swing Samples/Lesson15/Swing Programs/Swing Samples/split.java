import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class split extends JFrame
{
	JSplitPane sp;
	Container c;

	public split()
	{
		sp = new JSplitPane(1,false,new JTextArea(),new JTextArea());
		c = getContentPane();
		sp.setDividerSize(10);
		sp.setDividerLocation(150);
		c.add(sp);
		setSize(400,400);
		show();
	}


	public static void main(String arg[])
	{
		new split();
	}
}