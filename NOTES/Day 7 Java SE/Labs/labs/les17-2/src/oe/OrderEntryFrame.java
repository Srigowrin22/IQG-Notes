package oe;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class OrderEntryFrame extends JInternalFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jPanel1 = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanel jPanel2 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JLabel jLabel1 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JTextField jTextField2 = new JTextField();
    private JTextField jTextField3 = new JTextField();
    private JTextField jTextField4 = new JTextField();
    private JTextField jTextField5 = new JTextField();
    private JButton jButton1 = new JButton();
    private JList jList1 = new JList();
    private JLabel jLabel6 = new JLabel();
    private JTextField jTextField6 = new JTextField();
    private JButton jButton2 = new JButton();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();

    public OrderEntryFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setBounds(0, 0, 400, 300);
        this.getContentPane().setLayout(borderLayout1);
        jPanel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(2);
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.setLayout(null);
        jLabel1.setText("Order Id");
        jLabel1.setBounds(new Rectangle(10, 10, 50, 25));
        jTextField1.setBounds(new Rectangle(60, 15, 85, 20));
        jLabel2.setText("Customer Id");
        jLabel2.setBounds(new Rectangle(10, 55, 75, 15));
        jLabel3.setText("Address");
        jLabel3.setBounds(new Rectangle(10, 90, 50, 15));
        jLabel4.setText("Name");
        jLabel4.setBounds(new Rectangle(10, 70, 50, 15));
        jLabel5.setText("Phone ");
        jLabel5.setBounds(new Rectangle(10, 110, 45, 15));
        jTextField2.setBounds(new Rectangle(90, 110, 120, 20));
        jTextField3.setBounds(new Rectangle(90, 90, 120, 20));
        jTextField4.setBounds(new Rectangle(90, 70, 120, 20));
        jTextField5.setBounds(new Rectangle(90, 50, 60, 20));
        jButton1.setText("Find");
        jButton1.setBounds(new Rectangle(160, 45, 73, 22));
        jLabel6.setText("Product Id");
        jLabel6.setBounds(new Rectangle(220, 85, 75, 15));
        jTextField6.setBounds(new Rectangle(295, 85, 95, 20));
        jButton2.setText("Add");
        jButton2.setBounds(new Rectangle(225, 105, 73, 22));
        jLabel7.setText("Order Date");
        jLabel7.setBounds(new Rectangle(240, 10, 65, 15));
        jLabel8.setBounds(new Rectangle(310, 10, 70, 15));
        jLabel8.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jLabel9.setText("Order Total");
        jLabel9.setBounds(new Rectangle(240, 35, 65, 15));
        jLabel10.setBounds(new Rectangle(310, 35, 70, 15));
        jLabel10.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.add(jLabel10, null);
        jPanel2.add(jLabel9, null);
        jPanel2.add(jLabel8, null);
        jPanel2.add(jLabel7, null);
        jPanel2.add(jButton2, null);
        jPanel2.add(jTextField6, null);
        jPanel2.add(jLabel6, null);
        jPanel2.add(jButton1, null);
        jPanel2.add(jTextField5, null);
        jPanel2.add(jTextField4, null);
        jPanel2.add(jTextField3, null);
        jPanel2.add(jTextField2, null);
        jPanel2.add(jLabel5, null);
        jPanel2.add(jLabel4, null);
        jPanel2.add(jLabel3, null);
        jPanel2.add(jLabel2, null);
        jPanel2.add(jTextField1, null);
        jPanel2.add(jLabel1, null);
        jScrollPane1.getViewport().add(jList1, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jScrollPane1, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);

    }
}
