import java.awt.event.*;
import javax.swing.*;

public class EventExample extends JFrame  implements ActionListener,FocusListener
{
	JButton b1,b2;
	public EventExample() 
	{
			JFrame f=new JFrame(" Event Demo ");
			f.setVisible(true);
			f.setSize(300,300);
			f.setLayout(new java.awt.FlowLayout());
			b1=new JButton(" Click Me ");
			b2=new JButton("  Exit ");

			b1.addActionListener(this);
			b1.addFocusListener(this);
			b2.addActionListener(this);
			b2.addFocusListener(this);

			f.add(b1);
			f.add(b2);
	}

	public void actionPerformed(ActionEvent AE)
	{

			if(AE.getSource()==b1)
			{

					System.out.println(" Button is Clicked ");

					JOptionPane.showMessageDialog(this," You have Clicked the Button 1 ");

			}

			else if (AE.getSource()==b2)
			{
				 System.out.println(" Exit Button Clicked ");
			}


	}

	public void focusGained(FocusEvent FE)
	{
		System.out.println(" Button Got the Focus ");
	}


	public void focusLost(FocusEvent FE)
	{
		System.out.println(" Button Lost the Focus ");
	}

	public static void main(String args[])
	{
		new EventExample();
	}

}
