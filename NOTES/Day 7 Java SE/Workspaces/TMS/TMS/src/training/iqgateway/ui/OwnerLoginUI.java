package training.iqgateway.ui;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.operations.impl.AdminOperationImpl;
import training.iqgateway.operations.impl.OwnerOperationImpl;

public class OwnerLoginUI extends JFrame {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JButton signup = new JButton();
    private JTextField aadhar = new JTextField();
    private JTextField name = new JTextField();
    private JPasswordField pwd = new JPasswordField();
    private JButton login = new JButton();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel2 = new JLabel();

    public OwnerLoginUI() {
        try {
            jbInit();
            this.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(602, 434));
        this.setTitle("USER LOGIN");
        jLabel1.setText("TRAFFIC OFFENCE MANAGEMENT SYSTEM");
        jLabel1.setBounds(new Rectangle(50, 30, 505, 40));
        jLabel1.setFont(new Font("Tahoma", 0, 25));
        jLabel5.setText("Don't have an account?");
        jLabel5.setBounds(new Rectangle(115, 310, 200, 40));
        jLabel5.setFont(new Font("Tahoma", 0, 18));
        signup.setText("SIGNUP");
        signup.setBounds(new Rectangle(335, 305, 105, 35));
        signup.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    signup_actionPerformed(e);
                }
            });
        aadhar.setBounds(new Rectangle(320, 145, 195, 35));
        name.setBounds(new Rectangle(320, 105, 195, 35));
        pwd.setBounds(new Rectangle(320, 185, 195, 35));
        login.setText("LOGIN");
        login.setBounds(new Rectangle(245, 245, 85, 35));
        login.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    login_actionPerformed(e);
                }
            });
        jLabel4.setText("Password: ");
        jLabel4.setBounds(new Rectangle(90, 190, 160, 35));
        jLabel4.setFont(new Font("Tahoma", 0, 18));
        jLabel3.setText("Aadhar: ");
        jLabel3.setBounds(new Rectangle(90, 145, 160, 35));
        jLabel3.setFont(new Font("Tahoma", 0, 18));
        jLabel2.setText("Username:");
        jLabel2.setBounds(new Rectangle(90, 100, 160, 35));
        jLabel2.setFont(new Font("Tahoma", 0, 18));
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(login, null);
        this.getContentPane().add(pwd, null);
        this.getContentPane().add(name, null);
        this.getContentPane().add(aadhar, null);
        this.getContentPane().add(signup, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel1, null);
    }


    private void login_actionPerformed(ActionEvent e) {
        String ownName = name.getText();
        String ownAadhar = aadhar.getText();
        String pass = new String(pwd.getPassword());
        
        OwnerOperationImpl ownerOpImpl = new OwnerOperationImpl();
        OwnerEO ownerEO = new OwnerEO();
        ownerEO.setOwnerName(ownName);
        ownerEO.setOwnerAadhar(ownAadhar);
        ownerEO.setPassword(pass); 

        if (ownerOpImpl.login(ownerEO)) {
            OwnerEO ownerEOLogin = new OwnerEO();
            ownerEOLogin = ownerOpImpl.findOwnerByAadhar(ownerEO.getOwnerAadhar());
            OwnerDashboardUI dashboard = new OwnerDashboardUI(ownerEOLogin);
            this.dispose();
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID or Password",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed To connect");
        }
        clearFields();
    }

    private void signup_actionPerformed(ActionEvent e) {
        OwnerSignupUI signup = new OwnerSignupUI();
        signup.setVisible(true);
    }

    private void clearFields() {
        name.setText("");
        aadhar.setText("");
        pwd.setText("");
    }
}
