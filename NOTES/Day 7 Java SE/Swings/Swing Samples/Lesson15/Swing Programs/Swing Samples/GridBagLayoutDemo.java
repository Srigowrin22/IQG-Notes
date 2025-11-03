//GridBagLayout demo
import java.awt.*;
import javax.swing.*;
class GridBagLayoutDemo extends JFrame
{
	GridBagLayout gbag;
	GridBagConstraints cons;
	GridBagLayoutDemo()
	{
		Container c = getContentPane();
		gbag = new GridBagLayout();
		c.setLayout(gbag);
		cons = new GridBagConstraints();
		JButton b1 = new JButton("Button1");
		JButton b2 = new JButton("Button2");
		JButton b3 = new JButton("Button3");
		JButton b4 = new JButton("Button4");
	
		
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		cons.gridwidth = 1;
		cons.gridheight=3;
		cons.weightx = 1.0;
		gbag.setConstraints(b1,cons);
		c.add(b1);

		
		
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		gbag.setConstraints(b2,cons);
		c.add(b2);
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		gbag.setConstraints(b3,cons);
		c.add(b3);
		
		/*cons.weighty = 1.0;
		cons.gridheight = 2;
		cons.gridwidth = 1;
		gbag.setConstraints(b4,cons);
		c.add(b4);*/
}
	public static void main(String[] args) 
	{
		GridBagLayoutDemo demo = new GridBagLayoutDemo();
		demo.setSize(500,400);
		demo.show();
	}
}
