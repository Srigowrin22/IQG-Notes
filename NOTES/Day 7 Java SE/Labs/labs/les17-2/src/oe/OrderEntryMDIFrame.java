package oe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class OrderEntryMDIFrame extends JFrame {
    private BorderLayout layoutMain = new BorderLayout();
    private JDesktopPane desktopPane = new JDesktopPane();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JLabel statusBar = new JLabel();
    private JMenu jMenu1 = new JMenu();
    private JMenuItem jMenuItem1 = new JMenuItem();
    private JMenuItem jMenuItem2 = new JMenuItem();
    private JMenuItem jMenuItem3 = new JMenuItem();
    private JMenuItem jMenuItem4 = new JMenuItem();
    private JMenuItem jMenuItem5 = new JMenuItem();

    public OrderEntryMDIFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderEntryFrame iFrame = new OrderEntryFrame();
                        iFrame.setVisible(true);
                        desktopPane.add(iFrame);

    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.getContentPane().setLayout( layoutMain );
        desktopPane.setLayout( null );
        this.setSize( new Dimension(700, 500) );
        this.setTitle( "Order Entry Application" );
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        statusBar.setText( "" );
        jMenu1.setText("Order");
        jMenuItem1.setText("New");
        jMenuItem2.setText("Open");
        jMenuItem3.setText("Save");
        jMenuItem4.setText("Close");
        jMenuItem5.setText("Print");
        menuFile.add( menuFileExit );
        menuBar.add( menuFile );
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenu1.add(jMenuItem4);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem5);
        menuBar.add(jMenu1);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        this.getContentPane().add(desktopPane, BorderLayout.CENTER);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
