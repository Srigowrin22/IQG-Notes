//create a Tabbed Pane
import java.awt.*;
import javax.swing.*;
class JTabbedDemo extends JFrame
{
	JTabbedDemo()
	{
	//create container
	Container c = getContentPane();
	//create Tabbed pane
	JTabbedPane jtp = new JTabbedPane();
	//add the tabs to this pane
	jtp.addTab("Capitals",new CapitalsPanel());
	//JPanel specifies the group of components
	jtp.addTab("Countries",new CountriesPanel());
	//add the tabbed pane to container 'c'
	c.add(jtp);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public static void main(String[] args) 
	{
		JTabbedDemo demo = new JTabbedDemo();
		demo.setTitle("My Tabbed Pane");
		demo.setSize(500,400);
		demo.show();
	}
}
class CapitalsPanel extends JPanel
{
	CapitalsPanel()
	{
	JButton b1 = new JButton("New Delhi");
	JButton b2 = new JButton("Washington");
	JButton b3 = new JButton("Tokyo");
	add(b1);
	add(b2);
	add(b3);
	}
}
class CountriesPanel extends JPanel
{
	CountriesPanel()
	{
		JCheckBox c1 = new JCheckBox(" India");
		JCheckBox c2 = new JCheckBox(" America");
		JCheckBox c3 = new JCheckBox(" Japan");
		add(c1);
		add(c2);
		add(c3);
	}
}