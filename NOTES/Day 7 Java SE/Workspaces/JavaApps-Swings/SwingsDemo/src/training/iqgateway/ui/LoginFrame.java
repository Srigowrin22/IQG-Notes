package training.iqgateway.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class LoginFrame extends JFrame {
    private BorderLayout layoutMain = new BorderLayout();
    private JPanel panelCenter = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JToolBar toolBar = new JToolBar();
    private JButton buttonOpen = new JButton();
    private JButton buttonClose = new JButton();
    private JButton buttonHelp = new JButton();
    private ImageIcon imageOpen = new ImageIcon(LoginFrame.class.getResource("openfile.gif"));
    private ImageIcon imageClose = new ImageIcon(LoginFrame.class.getResource("closefile.gif"));
    private ImageIcon imageHelp = new ImageIcon(LoginFrame.class.getResource("help.gif"));
    private JLabel Username = new JLabel();
    private JLabel UserPassword = new JLabel();
    private JButton LoginButton = new JButton();
    private JPasswordField PasswordTextField = new JPasswordField();
    private JTextField UsernameTextField = new JTextField();

    public LoginFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.getContentPane().setLayout( layoutMain );
        panelCenter.setLayout( null );
        this.setSize(new Dimension(523, 304));
        this.setTitle( "Login" );
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        buttonOpen.setToolTipText( "Open File" );
        buttonOpen.setIcon( imageOpen );
        buttonClose.setToolTipText( "Close File" );
        buttonClose.setIcon( imageClose );
        buttonHelp.setToolTipText( "About" );
        buttonHelp.setIcon( imageHelp );
        Username.setText("Username");
        Username.setBounds(new Rectangle(105, 50, 120, 35));
        Username.setFont(new Font("Tahoma", 0, 20));
        UserPassword.setText("Password");
        UserPassword.setBounds(new Rectangle(105, 105, 110, 35));
        UserPassword.setFont(new Font("Tahoma", 0, 20));
        LoginButton.setText("LOGIN");
        LoginButton.setBounds(new Rectangle(190, 170, 75, 21));
        LoginButton.setFont(new Font("Tahoma", 0, 13));
        PasswordTextField.setBounds(new Rectangle(270, 105, 205, 45));
        UsernameTextField.setBounds(new Rectangle(270, 50, 205, 45));
        menuFile.add( menuFileExit );
        menuBar.add( menuFile );
        toolBar.add( buttonOpen );
        toolBar.add( buttonClose );
        toolBar.add( buttonHelp );
        this.getContentPane().add( toolBar, BorderLayout.NORTH );
        this.getContentPane().add( panelCenter, BorderLayout.CENTER);
        panelCenter.add(UsernameTextField, null);
        panelCenter.add(PasswordTextField, null);
        panelCenter.add(LoginButton, null);
        panelCenter.add(UserPassword, null);
        panelCenter.add(Username, null);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
