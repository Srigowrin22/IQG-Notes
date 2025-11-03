
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class slide extends JFrame
{
	JSlider sp;
	Container c;

	public slide()
	{
		c= getContentPane();
		sp = new JSlider(0,0,20,5);
		sp.setPaintLabels(true);
		sp.setLabelTable(sp.createStandardLabels(5));
		c.add(sp);
		setSize(400,400);
		show();
	}


	public static void main(String arg[])
	{
		new slide();
	}
}


//getValue()

//changeListener stateChanged