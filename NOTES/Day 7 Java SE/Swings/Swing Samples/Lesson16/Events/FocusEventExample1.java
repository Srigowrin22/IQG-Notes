import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FocusEventExample1 extends JFrame implements ActionListener, FocusListener 
{
	public  JButton b1,b2;
	public FocusEventExample1()
	{
			JFrame f=new JFrame(" Event Demo ");
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(300,300);

			f.setLayout(new FlowLayout());
			 b1=new JButton(" Click Me ");
			 b2=new JButton(" Button 2 ");


			

			b1.addFocusListener(this);
		
			b1.addActionListener(this);


			b2.addActionListener(this);
			f.add(b1);
			f.add(b2);
	}

	public void actionPerformed(ActionEvent AE)
	{
		if(AE.getSource()==b1)
		{
				System.out.println(" Buttton 1 Clicked ");
				System.exit(1);
		}

		if(AE.getSource()==b2)
		{
								System.out.println(" Buttton 2 Clicked ");
		}
	}
	public void focusGained(FocusEvent FE)
	{
		System.out.println(" Focus is Gained ");

	}

	
	public void focusLost(FocusEvent FE)
	{
		System.out.println(" Focus is Lost");

	}


	public static void main(String[] args) 
	{
		new FocusEventExample1();
	}
}
