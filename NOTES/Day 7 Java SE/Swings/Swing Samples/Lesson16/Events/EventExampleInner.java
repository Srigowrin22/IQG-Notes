import java.awt.event.*;
import javax.swing.*;

public class EventExampleInner extends JFrame  
{
	JButton b1,b2;
	JFrame f;

	public EventExampleInner() 
	{
			f=new JFrame(" Event Demo ");
			f.setVisible(true);
			f.setSize(300,300);

			f.setLayout(new java.awt.FlowLayout());
			b1=new JButton(" Click Me ");

			b2=new JButton("  Exit ");

			
			ButtonListener BL=new ButtonListener();

			b1.addActionListener(BL);
			
			b2.addActionListener(BL);

			f.add(b1);
			f.add(b2);
	}

	class ButtonListener implements ActionListener
	{
	
		public void actionPerformed(ActionEvent AE)
		{

			if(AE.getSource()==b1)
			{

					//System.out.println(" Button is Clicked ");

					JOptionPane.showMessageDialog(f," You have Clicked the Button 1 ");

			}

			else if (AE.getSource()==b2)
			{
				 System.out.println(" Exit Button Clicked ");
			}


	}

	}
	public static void main(String args[])
	{
		new EventExampleInner();
	}

}
