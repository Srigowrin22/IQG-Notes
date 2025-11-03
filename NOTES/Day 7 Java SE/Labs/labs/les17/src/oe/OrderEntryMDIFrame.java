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
        menuFile.add( menuFileExit );
        menuBar.add( menuFile );
        this.getContentPane().add( statusBar, BorderLayout.SOUTH );
        this.getContentPane().add( desktopPane, BorderLayout.CENTER );
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
