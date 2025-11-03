import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputPane extends JPanel implements ActionListener
{
	public InputPane()
	{
		JButton b1=new JButton(" Click Me");
		b1.addActionListener(this);
		add(b1);
	}

	public void actionPerformed(ActionEvent AE)
	{
		String output;
		output=JOptionPane.showInputDialog(this,"Enter Your Favorite Place ....");

		if((output==null)||(output.length()==0))
		{
			System.out.println(" Zero Data....");
			System.out.println(" Enter Data in the Text Field");
		}
		else
		{
			System.out.println(" Entered Data is : " + output);
		}

	}
	public static void main(String[] args) 
	{
		JFrame f=new JFrame(" Message Demo");
		InputPane pan=new InputPane();
		f.getContentPane().add(pan);
		f.setSize(300,300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
