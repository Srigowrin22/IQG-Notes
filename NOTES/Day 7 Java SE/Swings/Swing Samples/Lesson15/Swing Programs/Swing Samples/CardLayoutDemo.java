//Card Layout demo
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class CardLayoutDemo extends JFrame implements ActionListener
{
	Container c;
	CardLayout card;
	CardLayoutDemo()
	{
		c = getContentPane();
		card = new CardLayout();
		c.setLayout(card);
		JButton b1,b2,b3;
		b1 = new JButton("Button1");
		b2 = new JButton("Button2");
		b3 = new JButton("Button3");
		c.add("First Card",b1);
		c.add("Second Card",b2);
		c.add("Third Card",b3);
		b1.addActionListener(this);
		b2.addActionListener(this);
        b3.addActionListener(this);
}
        public void actionPerformed(ActionEvent ae)
       {
			card.next(c);
	   }
	public static void main(String[] args) 
	{
		CardLayoutDemo demo =new CardLayoutDemo();
		demo.setSize(500,400);
		demo.show();
	}
}
