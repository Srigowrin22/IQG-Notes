
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
public class MenuTest extends JFrame implements ActionListener {

	JMenuBar jmb;
	JMenu fileMenu,editMenu;
	JMenuItem newItem,openItem;
	
	MenuTest(){
		jmb = new JMenuBar();
		
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		newItem.addActionListener(this);
		fileMenu.add(newItem);
		
		openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		jmb.add(fileMenu);
		
		editMenu = new JMenu("Edit");
		jmb.add(editMenu);
		setJMenuBar(jmb);
	}
	public void actionPerformed(ActionEvent ae) 
    {
		String str = ae.getActionCommand();
		if(str.equals("New")){
			But bt = new But();
			bt.setSize(500,400);
			bt.setTitle("My Button with text field");
			bt.setVisible(true);
		}
    }
	
	public static void main(String[] args) {
		MenuTest mune = new MenuTest();
		mune.setSize(800,600);
		mune.setVisible(true);
	}
}

	

