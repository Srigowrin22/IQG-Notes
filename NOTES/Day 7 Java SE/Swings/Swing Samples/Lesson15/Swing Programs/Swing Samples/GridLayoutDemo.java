//Grid Layout demo
import java.awt.*;
import javax.swing.*;
class GridLayoutDemo extends JFrame
{
	GridLayoutDemo()
	{
		Container c = getContentPane();
		JButton b1,b2,b3,b4,b5,b6;
		GridLayout grid = new GridLayout(3,2);
		c.setLayout(grid);
		b1 = new JButton("Button1");
		b2 = new JButton("Button2");
		b3 = new JButton("Button3");
		b4 = new JButton("Button4");
		b5 = new JButton("Button5");
		b6 = new JButton("Button6");
		c.add(b1);
		c.add(b3);
		c.add(b2);
		c.add(b4);
		c.add(b5);
		c.add(b6);
     }
	public static void main(String[] args) 
	{
		GridLayoutDemo demo = new GridLayoutDemo();
		demo.setSize(500,400);
		demo.show();
	}
}
