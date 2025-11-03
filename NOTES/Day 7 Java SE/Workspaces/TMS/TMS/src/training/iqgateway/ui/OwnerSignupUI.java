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

import training.iqgateway.entities.OwnerEO;
import training.iqgateway.operations.impl.OwnerOperationImpl;

public class OwnerSignupUI extends JFrame {
    private JLabel jLabel1 = new JLabel();
    private JButton login = new JButton();
    private JButton signup = new JButton();
    private JLabel jLabel7 = new JLabel();
    private JTextField phone = new JTextField();
    private JTextField aadhar = new JTextField();
    private JTextField name = new JTextField();
    private JPasswordField rpwd = new JPasswordField();
    private JPasswordField pwd = new JPasswordField();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JTextField address = new JTextField();

    public OwnerSignupUI() {
        try {
            jbInit();
            this.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(613, 554));
        this.setTitle("USER SIGNUP");
        jLabel1.setText("TRAFFIC OFFENCE MANAGEMENT SYSTEM");
        jLabel1.setBounds(new Rectangle(55, 30, 500, 40));
        jLabel1.setFont(new Font("Tahoma", 0, 25));
        login.setText("LOGIN");
        login.setBounds(new Rectangle(325, 440, 100, 30));
        login.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    login_actionPerformed(e);
                }
            });
        signup.setText("SIGNUP");
        signup.setBounds(new Rectangle(220, 380, 115, 35));
        signup.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    signup_actionPerformed(e);
                }
            });
        jLabel7.setText("Have an account?");
        jLabel7.setBounds(new Rectangle(145, 435, 180, 40));
        jLabel7.setFont(new Font("Tahoma", 0, 18));
        phone.setBounds(new Rectangle(300, 190, 225, 30));
        aadhar.setBounds(new Rectangle(300, 145, 225, 30));
        name.setBounds(new Rectangle(300, 100, 225, 30));
        rpwd.setBounds(new Rectangle(300, 325, 225, 30));
        pwd.setBounds(new Rectangle(300, 280, 225, 30));
        jLabel6.setText("Re-type Password: ");
        jLabel6.setBounds(new Rectangle(80, 325, 175, 35));
        jLabel6.setFont(new Font("Tahoma", 0, 18));
        jLabel5.setText("Password: ");
        jLabel5.setBounds(new Rectangle(80, 280, 150, 35));
        jLabel5.setFont(new Font("Tahoma", 0, 18));
        jLabel4.setText("Phone: ");
        jLabel4.setBounds(new Rectangle(80, 190, 150, 35));
        jLabel4.setFont(new Font("Tahoma", 0, 18));
        jLabel3.setText("Aadhar: ");
        jLabel3.setBounds(new Rectangle(80, 145, 150, 35));
        jLabel3.setFont(new Font("Tahoma", 0, 18));
        jLabel2.setText("Username: ");
        jLabel2.setBounds(new Rectangle(80, 100, 150, 35));
        jLabel2.setFont(new Font("Tahoma", 0, 18));
        jLabel8.setText("Address: ");
        jLabel8.setBounds(new Rectangle(80, 235, 150, 35));
        jLabel8.setFont(new Font("Tahoma", 0, 18));
        address.setBounds(new Rectangle(300, 235, 225, 30));
        this.getContentPane().add(address, null);
        this.getContentPane().add(jLabel8, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(pwd, null);
        this.getContentPane().add(rpwd, null);
        this.getContentPane().add(name, null);
        this.getContentPane().add(aadhar, null);
        this.getContentPane().add(phone, null);
        this.getContentPane().add(jLabel7, null);
        this.getContentPane().add(signup, null);
        this.getContentPane().add(login, null);
        this.getContentPane().add(jLabel1, null);
    }

    private void signup_actionPerformed(ActionEvent e) {
        OwnerOperationImpl ownerOpImpl = new OwnerOperationImpl();
        if (ownerOpImpl.findOwnerByAadhar(aadhar.getText()) == null) {
           String pass = new String(pwd.getPassword());
            String rpass = new String(rpwd.getPassword());
            if (pass.equals(rpass)) {
                try {
                    OwnerEO ownerEO = new OwnerEO();
                    ownerEO.setOwnerName(name.getText());
                    ownerEO.setOwnerAadhar(aadhar.getText());
                    ownerEO.setAddress(address.getText());
                    ownerEO.setPhone(Long.parseLong(phone.getText()));
                    ownerEO.setPassword(pass);

                    String result = ownerOpImpl.addOwner(ownerEO);
                    System.out.println(result);

                    if (result.equals("Signup Successful")) {
                        String message = "Name: " + name.getText() + "\n" +
                            "Aadhar: " + aadhar.getText() + "\n" +
                            "Click on LOGIN to login !";

                        JOptionPane.showMessageDialog(this, message,
                                                      "Account created!",
                                                      JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                                      "Provide proper data!",
                                                      "Failed",
                                                      JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                                                  "Error during signup: " +
                                                  ex.getMessage(), "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match!",
                                              "Failed",
                                              JOptionPane.WARNING_MESSAGE);
            }
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Account Already exists!",
                                          "Try to login",
                                          JOptionPane.WARNING_MESSAGE);
            this.dispose();
            new OwnerLoginUI().setVisible(true);
        }
    }

    private void login_actionPerformed(ActionEvent e) {
        this.dispose();
        new OwnerLoginUI().setVisible(true);
    }

    private void clearFields() {
        name.setText("");
        aadhar.setText("");
        phone.setText("");
        address.setText("");
        pwd.setText("");
        rpwd.setText("");
    }
}
