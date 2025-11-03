import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FocusEventExample2 extends FocusAdapter
{
	public  JButton b1,b2;
	public FocusEventExample2()
	{
			JFrame f=new JFrame(" Event Demo ");
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(300,300);

			f.setLayout(new FlowLayout());
			 b1=new JButton(" Click Me ");
			 b2=new JButton(" Button 2 ");


			

			b1.addFocusListener(this);
		
			f.add(b1);
			f.add(b2);
	}

	public void focusGained(FocusEvent FE)
	{
		System.out.println(" Focus is Gained ");

	}
	

	public static void main(String[] args) 
	{
		new FocusEventExample2();
	}
}
