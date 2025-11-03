//Crating a Menu
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyMenu extends JFrame implements ActionListener
{
	JMenuBar mb;
	JMenu file,edit,font;
	JMenuItem nw,cl,cp,pt;
	JCheckBoxMenuItem pr;
	Container c ;
	MyMenu()
	{
		//create a  container and set a layout
		c = getContentPane();
		//c.locate(500,400);
		c.setLayout(new FlowLayout());
		//create the menubar and add to c
		mb = new JMenuBar();
		c.add(mb);
		setJMenuBar(mb);
		//c.add("North",mb);
		//create file,edit menus and add to mb
		file = new JMenu("File");
		edit = new JMenu("Edit");
		mb.add(file);
		mb.add(edit);
		//create menu items and add them to file and edit
		nw = new JMenuItem("New");
		cl = new JMenuItem("Close");
		cp = new JMenuItem("Copy");
		pt = new JMenuItem("Paste");
		file.add(nw);
		file.add(cl);
		file.add(cp);
		file.add(pt);
		//disable close item of file menu
		//cl.setEnabled(false);
		//create a check box menu item and add to file menu
		pr = new JCheckBoxMenuItem("print");
		file.add(pr);
		//add a line or seperator to file menu
		//file.addSeparator();
		//create font submenu in file menu
		font = new JMenu("font");
		file.add(font);
		//create items in font submenu
		font.add("Arial");
		font.add("Times New Roman");
		//add listener
		nw.addActionListener(this);
		cl.addActionListener(this);
		cp.addActionListener(this);
		pt.addActionListener(this);
        pr.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
        public void actionPerformed(ActionEvent ae) 
        {
        	String str = ae.getActionCommand();
        	if(str.equals("New")){        		
        		Test t = new Test();
        		t.setSize(500,400);
        		t.setVisible(true);
        	}
        	
        	/*
			if(nw.isArmed())     //isArmed only in JMenu items
                System.out.println("New Selected");
			if(cl.isArmed())
		        System.out.println("Close Selected");
			if(cp.isArmed())
				System.out.println("Copy Selected");
			if(pt.isArmed())
				System.out.println("Past Selected");
			if(pr.getModel().isSelected())
				System.out.println("Print is on");
			else System.out.println("Print is off");*/
		}

	public static void main(String[] args) 
	{
		MyMenu mm = new MyMenu();
		mm.setSize(500,400);
		mm.setTitle("My Swing Menu");
		mm.show();
	}
}
