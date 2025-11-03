import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MessagePane extends JPanel implements ActionListener
{
	public MessagePane()
	{
		JButton b1=new JButton(" Click Me");
		b1.addActionListener(this);
		add(b1);
	}

	public void actionPerformed(ActionEvent AE)
	{
		JOptionPane.showMessageDialog(this,"Hai, Look at this Message dialog", "Informational Message Pane",JOptionPane.QUESTION_MESSAGE);
	}
	public static void main(String[] args) 
	{
		JFrame f=new JFrame(" Message Demo");
		MessagePane pan=new MessagePane();
		f.getContentPane().add(pan);
		f.setSize(300,300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
