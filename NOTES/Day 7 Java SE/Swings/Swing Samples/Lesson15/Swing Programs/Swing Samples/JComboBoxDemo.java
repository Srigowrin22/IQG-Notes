//JComboBox demo
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.border.*;
class JComboBoxDemo extends JFrame implements ItemListener
{
	JComboBox  box;
	JLabel lbl ;  //label is use set some stringon frame
	JComboBoxDemo()
	{
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		//create the combo box
		box = new JComboBox();
		box.addItem("India");
		box.addItem("America");
		box.addItem("Germany");
		box.addItem("Japan");
		//add it to the container
		c.add("South",box);
		//create an empty label and add it to the container
		lbl = new JLabel();   //lbl = new JLabel(" ",JJabel Entity)
		c.add("Center",lbl);    //JList is we visible multiple items
		//attach listener
		box.addItemListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void itemStateChanged(ItemEvent ie)
    {
		String str = (String)box.getSelectedItem();
		lbl.setText(str);
	}
	public static void main(String[] args) 
	{
		JComboBoxDemo demo = new JComboBoxDemo();
		demo.setTitle("Test");
		demo.setSize(500,400);
		demo.show();
	}
}
