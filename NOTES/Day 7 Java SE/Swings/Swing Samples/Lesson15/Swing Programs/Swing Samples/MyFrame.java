
package com.gui.jdbc;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	private JTextField t1 , t2 , t3;
	private JButton jb = new JButton("SAVE");
	private Connection conn;
	public MyFrame()
	{
		super("JDBC DEMO");
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/trainer",
							"trainer", "trainer");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
		Container cont = getContentPane();
		cont.setLayout(new GridLayout(0,2));
		cont.add(new JLabel("ENTER NAME"));
		cont.add(t1);
		cont.add(new JLabel("ENTER EMAIL"));
		cont.add(t2);
		cont.add(new JLabel("ENTER AGE"));
		cont.add(t3);
		cont.add(jb);
		jb.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		
	}
	public static void main(String[] args) throws Exception {
		SplashScreen sp = new SplashScreen();
		sp.setVisible(true);
		Thread.sleep(3000);
		sp.setVisible(false);
		sp.dispose();
		MyFrame my = new MyFrame();
		my.setVisible(true);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		//insert into user1 values('','',)
		String sql = "insert into user2 values('"+t1.getText()+"','"+t2.getText()+"',"+t3.getText()+")";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
