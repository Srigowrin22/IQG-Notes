import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FocusEventExample extends JFrame 
{
	public static void main(String[] args) 
	{
			JFrame f=new JFrame(" Event Demo ");
			f.setVisible(true);
			f.setSize(300,300);

			f.setLayout(new FlowLayout());
			JButton b1=new JButton(" Click Me ");
			JButton b2=new JButton(" Button 2 ");


			ButtonListener BL=new ButtonListener();

			b1.addFocusListener(BL);

			f.add(b1);
			f.add(b2);
	}
}

class ButtonListener implements FocusListener
{
	public void focusGained(FocusEvent FE)
	{
		System.out.println(" Focus is Gained ");

	}

	
	public void focusLost(FocusEvent FE)
	{
		System.out.println(" Focus is Lost");

	}

}
