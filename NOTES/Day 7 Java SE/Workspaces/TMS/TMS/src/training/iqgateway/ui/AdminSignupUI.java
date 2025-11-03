package training.iqgateway.ui;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.operations.impl.AdminOperationImpl;

public class AdminSignupUI extends JFrame {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JPasswordField password = new JPasswordField();
    private JPasswordField repassword = new JPasswordField();
    private JTextField userID = new JTextField();
    private JTextField username = new JTextField();
    private JTextField aadhar = new JTextField();
    private JLabel jLabel7 = new JLabel();
    private JButton signup = new JButton();
    private JButton login = new JButton();
    private JPasswordField oldpwd = new JPasswordField();
    private JLabel jLabel8 = new JLabel();

    public AdminSignupUI() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(600, 560));
        this.setTitle("ADMIN AND AUTHORITY SIGNUP");

        // Heading
        jLabel1.setText("TRAFFIC OFFENCE MANAGEMENT SYSTEM");
        jLabel1.setBounds(30, 22, 540, 38);
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 22));

        // User ID
        jLabel2.setText("User ID:");
        jLabel2.setBounds(70, 90, 140, 30);
        jLabel2.setFont(new Font("Tahoma", Font.BOLD, 16));
        userID.setBounds(230, 90, 240, 32);
        userID.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Username
        jLabel3.setText("Username:");
        jLabel3.setBounds(70, 135, 140, 30);
        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 16));
        username.setBounds(230, 135, 240, 32);
        username.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Aadhar
        jLabel4.setText("Aadhar:");
        jLabel4.setBounds(70, 180, 140, 30);
        jLabel4.setFont(new Font("Tahoma", Font.BOLD, 16));
        aadhar.setBounds(230, 180, 240, 32);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Old Password
        jLabel8.setText("Old Password:");
        jLabel8.setBounds(70, 225, 140, 30);
        jLabel8.setFont(new Font("Tahoma", Font.BOLD, 16));
        oldpwd.setBounds(230, 225, 240, 32);
        oldpwd.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Password
        jLabel5.setText("Password:");
        jLabel5.setBounds(70, 270, 140, 30);
        jLabel5.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setBounds(230, 270, 240, 32);
        password.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Re-type Password
        jLabel6.setText("Re-type Password:");
        jLabel6.setBounds(70, 315, 160, 30);
        jLabel6.setFont(new Font("Tahoma", Font.BOLD, 16));
        repassword.setBounds(230, 315, 240, 32);
        repassword.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Signup Button
        signup.setText("SIGNUP");
        signup.setBounds(170, 380, 120, 36);
        signup.setFont(new Font("Tahoma", Font.BOLD, 16));
        signup.setBounds(new Rectangle(225, 370, 120, 40));
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signup_actionPerformed(e);
            }
        });

        // Login Button
        login.setText("LOGIN");
        login.setBounds(310, 380, 120, 36);
        login.setFont(new Font("Tahoma", Font.BOLD, 16));
        login.setBounds(new Rectangle(300, 425, 120, 40));
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login_actionPerformed(e);
            }
        });

        // Have an account?
        jLabel7.setText("Have an account?");
        jLabel7.setBounds(180, 435, 200, 30);
        jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 15));

        // Add components to content pane
        jLabel7.setBounds(new Rectangle(145, 430, 135, 30));
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(userID, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(username, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(aadhar, null);
        this.getContentPane().add(jLabel8, null);
        this.getContentPane().add(oldpwd, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(password, null);
        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(repassword, null);

        // Center the window on the screen
        this.getContentPane().add(signup, null);
        this.getContentPane().add(login, null);
        this.getContentPane().add(jLabel7, null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void signup_actionPerformed(ActionEvent e) {

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        if (adminOpImpl.findAdminByDesigID(userID.getText()) == null) {
            JOptionPane.showMessageDialog(this, "UserID Does not exist!",
                                          "Please wait until Admin creates your UserID",
                                          JOptionPane.YES_OPTION);
            System.out.println("Failed To SignUp");
            clearFields();
        } else {
            String npwd = new String(password.getPassword());
            String rpwd = new String(repassword.getPassword());

            if (npwd.equals(rpwd)) {
                AdminEO adminEO = new AdminEO();
                adminEO.setDesignationID(userID.getText());
                adminEO.setName(username.getText());
                adminEO.setAadhar(aadhar.getText());
                adminEO.setPassword(oldpwd.getText());

                if (adminOpImpl.authorize(adminEO).startsWith("Succesfully")) {
                    JOptionPane.showMessageDialog(this, "Account Authorized!",
                                                  "Try to login",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    adminEO = new AdminEO();
                    adminEO.setDesignationID(userID.getText());
                    adminEO.setName(username.getText());
                    adminEO.setAadhar(aadhar.getText());
                    adminEO.setPassword(password.getText());
                    if (adminOpImpl.modifyAdmin(adminEO).startsWith("Succesfully")) {
                        JOptionPane.showMessageDialog(this,
                                                      "Password changed!  Login now!",
                                                      "Try to login",
                                                      JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                                                  "Wrong Admin Password! Contact Admin!",
                                                  "Failed",
                                                  JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                                              "Password mismatch! Try again!",
                                              "Try to login",
                                              JOptionPane.WARNING_MESSAGE);
            }
        }
        clearFields();
    }

    private void login_actionPerformed(ActionEvent e) {
        new AdminLoginUI().setVisible(true);
    }

    private void clearFields() {
        userID.setText("");
        username.setText("");
        password.setText("");
    }
}
