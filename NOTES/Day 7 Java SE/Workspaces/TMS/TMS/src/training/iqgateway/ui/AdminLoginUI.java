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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.operations.impl.AdminOperationImpl;
import training.iqgateway.operations.impl.ClerkOperationImpl;
import training.iqgateway.operations.impl.PoliceOperationImpl;
import training.iqgateway.operations.impl.RTOOperationImpl;

public class AdminLoginUI extends JFrame {
    private JLabel jLabel1 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JButton login = new JButton();
    private JPasswordField password = new JPasswordField();
    private JTextField userID = new JTextField();
    private JTextField username = new JTextField();
    private JButton signup = new JButton();
    private JLabel jLabel5 = new JLabel();

    public AdminLoginUI() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(540, 400)); // Slightly more compact and centered

        this.setTitle("ADMIN AND AUTHORITY LOGIN");

        // Heading
        jLabel1.setText("TRAFFIC OFFENCE MANAGEMENT SYSTEM");
        jLabel1.setBounds(25, 24, 490, 38);
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 22));

        // User ID
        jLabel2.setText("User ID:");
        jLabel2.setBounds(60, 90, 120, 30);
        jLabel2.setFont(new Font("Tahoma", Font.BOLD, 16));
        userID.setBounds(200, 90, 220, 32);
        userID.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Username
        jLabel3.setText("Username:");
        jLabel3.setBounds(60, 135, 120, 30);
        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 16));
        username.setBounds(200, 135, 220, 32);
        username.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Password
        jLabel4.setText("Password:");
        jLabel4.setBounds(60, 180, 120, 30);
        jLabel4.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setBounds(200, 180, 220, 32);
        password.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Login Button
        login.setText("LOGIN");
        login.setBounds(200, 235, 110, 36);
        login.setFont(new Font("Tahoma", Font.BOLD, 16));
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });

        // Signup
        jLabel5.setText("Don't have an account?");
        jLabel5.setBounds(60, 295, 200, 30);
        jLabel5.setFont(new Font("Tahoma", Font.PLAIN, 15));

        signup.setText("SIGNUP");
        signup.setBounds(270, 292, 120, 34);
        signup.setFont(new Font("Tahoma", Font.BOLD, 15));
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signup_actionPerformed(e);
            }
        });

        // Add components to content pane
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(userID, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(username, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(password, null);
        this.getContentPane().add(login, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(signup, null);

        // Center the window on the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    
    private void jButton1_actionPerformed(ActionEvent e) {
        String id = userID.getText();
        String name = username.getText();
        String pass = new String(password.getPassword());

        AdminEO adminEO = new AdminEO();
        adminEO.setDesignationID(id);
        adminEO.setName(name);
        adminEO.setPassword(pass);

        boolean loginSuccess = false;

        if (id.startsWith("AD")) {
            AdminOperationImpl adminOpImpl = new AdminOperationImpl();
            loginSuccess = adminOpImpl.login(adminEO);
            if (loginSuccess) {
                AdminDashboardUI dashboard = new AdminDashboardUI(adminEO);
                this.dispose();
                dashboard.setVisible(true);
            }
        } else if (id.startsWith("RTO")) {
            RTOOperationImpl rtoOpImpl = new RTOOperationImpl();
            loginSuccess = rtoOpImpl.login(adminEO);
            if (loginSuccess) {
                RTODashboardUI dashboard = new RTODashboardUI(adminEO);
                this.dispose();
                dashboard.setVisible(true);
            }
        } else if (id.startsWith("CLK")) {
            ClerkOperationImpl clkOpImpl = new ClerkOperationImpl();
            loginSuccess = clkOpImpl.login(adminEO);
            if (loginSuccess) {
                ClerkDashboardUI dashboard = new ClerkDashboardUI(adminEO);
                this.dispose();
                dashboard.setVisible(true);
            }
        } else if (id.startsWith("PLC")) {
            PoliceOperationImpl plcOpImpl = new PoliceOperationImpl();
            loginSuccess = plcOpImpl.login(adminEO);
            if (loginSuccess) {
                PoliceDashboardUI dashboard = new PoliceDashboardUI(adminEO);
                this.dispose();
                dashboard.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID prefix", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            clearFields();
            return;
        }

        if (!loginSuccess) {
            JOptionPane.showMessageDialog(this, "Failed to login! Try Signing IN...",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed To connect");
        }

        clearFields();
    }


    private void signup_actionPerformed(ActionEvent e) {
        AdminSignupUI signup = new AdminSignupUI();
        signup.setVisible(true);
    }

    private void clearFields() {
        userID.setText("");
        username.setText("");
        password.setText("");
    }
}
