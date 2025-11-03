//Create a Table
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.util.*;
class JTableDemo extends JFrame
{
	JTableDemo()
	{
		//create data for the table
		Vector data = new Vector();
		//creat a row
		Vector row = new Vector();
		row.add("Chandra");
		row.add("Programmar");
		row.add("12,000.00");
		data.add(row);
		//create second row
        row = new Vector();
		row.add("Ranganath");
		row.add("Sys.Analist");
		row.add("15,000.00");
		data.add(row);
		//create third row
        row = new Vector();
		row.add("Surendra");
		row.add("Programmar");
		row.add("12,000.00");
		data.add(row);
		//create a Fourth row
        row = new Vector();
		row.add("Vijay");
		row.add("Team Lead");
		row.add("18,000.00");
		data.add(row);
		//create a row with column names
		Vector cols = new Vector();
		cols .add("Employee Name");
		cols .add("Designation");
		cols .add("Salary");
		//create the table
		JTable tab = new JTable(data,cols);
		//set a border for the table
		tab.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		//To display the column headings,create table header object seperatly.
		JTableHeader head = tab.getTableHeader();
		//create the container object
		Container c = getContentPane();
		//set a layout
		c.setLayout(new BorderLayout());
		//Border Layout
		c.add("North",head);
		c.add("Center",tab);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public static void main(String[] args) 
	{
		JTableDemo demo = new JTableDemo();
		demo.setSize(500,400);
		demo.setTitle("Employee Table");
		demo.show();
	}
}
