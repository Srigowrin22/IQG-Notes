package training.iqgateway.ui;

import java.awt.*;
import java.awt.event.*;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.table.DefaultTableModel;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import training.iqgateway.dao.RoleDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.factory.GenericDAOFactory;
import training.iqgateway.operations.impl.AdminOperationImpl;
import training.iqgateway.utils.Validation;


public class AdminDashboardUI extends JFrame {
    private JTabbedPane mainTabbedPane = new JTabbedPane();
    private JLabel helloAdm =
        new JLabel("Welcome, Admin!", SwingConstants.LEFT);
    private JButton logoutBtn = new JButton("Logout");

    public AdminDashboardUI() {
        this(null);
    }

    public AdminDashboardUI(AdminEO adminEO) {
        setTitle("ADMIN DASHBOARD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setMinimumSize(new Dimension(1200, 700));
        setLocationRelativeTo(null);

        // Top Panel (Header)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
                                                                                             2),
                                                              BorderFactory.createEmptyBorder(18,
                                                                                              40,
                                                                                              18,
                                                                                              40)));
        topPanel.setBackground(new Color(240, 240, 240));

        helloAdm.setFont(new Font("Tahoma", Font.BOLD, 32));
        if (adminEO != null && adminEO.getName() != null) {
            helloAdm.setText("Welcome, " + adminEO.getName() + "!");
        }
        topPanel.add(helloAdm, BorderLayout.WEST);

        logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 22));
        logoutBtn.setPreferredSize(new Dimension(140, 48));
        topPanel.add(logoutBtn, BorderLayout.EAST);

        // Main Tabbed Pane
        mainTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 22)); // Big, bold tab labels

        mainTabbedPane.addTab("     Admin Management        ",
                              new AdminManagementTabbedPanel());
        mainTabbedPane.addTab("     Role Management         ",
                              new RoleManagementTabbedPanel());

        // Layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(mainTabbedPane, BorderLayout.CENTER);

        // Logout action
        logoutBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AdminDashboardUI.this.dispose();
                    JOptionPane.showMessageDialog(AdminDashboardUI.this,
                                                  "Logged OUT");

                    // Show the login UI
                    AdminLoginUI loginUI = new AdminLoginUI();
                    loginUI.setVisible(true);
                }
            });
    }
}


class AdminManagementTabbedPanel extends JPanel {
    public AdminManagementTabbedPanel() {
        try {
            UIManager.put("TabbedPane.font",
                          new Font("Tahoma", Font.BOLD, 18));
            UIManager.put("TabbedPane.tabInsets", new Insets(10, 30, 10, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        JTabbedPane adminTabs = new JTabbedPane();

        adminTabs.addTab("Add Admin", new AddAdminPanel());
        adminTabs.addTab("Modify Admin", new ModifyAdminPanel());
        adminTabs.addTab("Remove Admin", new RemoveAdminPanel());
        adminTabs.addTab("View Admins", new ViewAdminPanel());

        adminTabs.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(adminTabs, BorderLayout.CENTER);
    }
}

class AddAdminPanel extends JPanel {
    private JTextField Name = new JTextField(20);
    private JTextField Aadhar = new JTextField(20);
    private JTextField Dates = new JTextField(20);
    private JTextField phone = new JTextField(20);
    private JComboBox gender = new JComboBox(new String[] { "M", "F", "NA" });
    private JComboBox newCombo = new JComboBox();
    private JButton addBtn = new JButton("Add Admin");

    public AddAdminPanel() {
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                                                               2),
                                                                                                "Add Admin",
                                                                                                TitledBorder.LEFT,
                                                                                                TitledBorder.TOP,
                                                                                                new Font("Tahoma",
                                                                                                         Font.BOLD,
                                                                                                         20)),
                                                               BorderFactory.createEmptyBorder(30,
                                                                                               60,
                                                                                               30,
                                                                                               60)));

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 16);

        // Name Panel
        JPanel namePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        Name.setFont(fieldFont);
        Name.setPreferredSize(new Dimension(220, 36));
        namePanel.add(nameLabel);
        namePanel.add(Name);

        // Aadhar Panel
        JPanel aadharPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel aadharLabel = new JLabel("Aadhar:");
        aadharLabel.setFont(labelFont);
        Aadhar.setFont(fieldFont);
        Aadhar.setPreferredSize(new Dimension(220, 36));
        aadharPanel.add(aadharLabel);
        aadharPanel.add(Aadhar);

        // Date Panel
        JPanel datePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel dateLabel = new JLabel("Hire Date (yyyy-MM-dd):");
        dateLabel.setFont(labelFont);
        Dates.setFont(fieldFont);
        Dates.setPreferredSize(new Dimension(220, 36));
        datePanel.add(dateLabel);
        datePanel.add(Dates);

        // Phone Panel
        JPanel phonePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(labelFont);
        phone.setFont(fieldFont);
        phone.setPreferredSize(new Dimension(220, 36));
        phonePanel.add(phoneLabel);
        phonePanel.add(phone);

        // Gender Panel
        JPanel genderPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        gender.setFont(fieldFont);
        gender.setPreferredSize(new Dimension(220, 36));
        genderPanel.add(genderLabel);
        genderPanel.add(gender);

        // Role Combo Panel
        JPanel rolePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(labelFont);
        newCombo.setFont(fieldFont);
        newCombo.setPreferredSize(new Dimension(220, 36));
        rolePanel.add(roleLabel);
        rolePanel.add(newCombo);

        // Button Panel
        JPanel btnPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        addBtn.setPreferredSize(new Dimension(180, 40));
        btnPanel.add(addBtn);

        // Add all panels to mainPanel
        mainPanel.add(namePanel);
        mainPanel.add(aadharPanel);
        mainPanel.add(datePanel);
        mainPanel.add(phonePanel);
        mainPanel.add(genderPanel);
        mainPanel.add(rolePanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(btnPanel);

        add(mainPanel);

        // Populate roles in combo initially
        populateRoleCombo();

        // Refresh roles every time panel is shown (e.g., when tab is selected)
        this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    populateRoleCombo();
                }
            });

        // Button action
        addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AddButton_actionPerformed(e);
                }
            });
    }

    private void populateRoleCombo() {
        Object selected = newCombo.getSelectedItem();
        newCombo.removeAllItems();
        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roleEOList = adminOpImpl.findAllRoles();
        int selIndex = -1;
        for (int i = 0; i < roleEOList.size(); i++) {
            RoleEO role = roleEOList.get(i);
            newCombo.addItem(role.getRoleName());
            if (selected != null && selected.equals(role.getRoleName())) {
                selIndex = i;
            }
        }
        if (selIndex >= 0) {
            newCombo.setSelectedIndex(selIndex);
        } else if (newCombo.getItemCount() > 0) {
            newCombo.setSelectedIndex(0);
        }
    }

    private void AddButton_actionPerformed(ActionEvent e) {
        String name = Name.getText();
        String aadhar = Aadhar.getText();
        String date = Dates.getText();
        String phoneStr = phone.getText();
        String genderStr = (String)gender.getSelectedItem();

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        AdminEO adminEO = new AdminEO();
        List<RoleEO> roleEOList =
            adminOpImpl.findRoleByRoleName((String)newCombo.getSelectedItem());

        for (RoleEO role : roleEOList) {
            adminEO.setRoleID(role);
            adminEO.setName(name);
            adminEO.setAadhar(aadhar);
            adminEO.setPassword("adm123");
            adminEO.setSignup(0);
            adminEO.setGender(genderStr);
            if (phoneStr != null && !phoneStr.trim().isEmpty()) {
                try {
                    adminEO.setPhone(Long.parseLong(phoneStr.trim()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                                                  "Invalid phone number!",
                                                  "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            break;
        }

        if (!date.equals("")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date utilDate = sdf.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                adminEO.setHireDate(sqlDate);
            } catch (Exception es) {
                es.printStackTrace();
            }
        } else {
            adminEO.setHireDate(null);
        }
        Validation validate = new Validation();
        if (validate.isValidName(adminEO.getName())) {
            if (validate.isValidAadhaar(adminEO.getAadhar())) {
                if (validate.isValidPhoneNumber(adminEO.getPhone())) {
                    adminOpImpl.addAdmin(adminEO);

                    if (adminOpImpl.findAdminByAadhar(adminEO.getAadhar()) !=
                        null) {
                        String adminDetails =
                            "Name: " + adminEO.getName() + "\n" +
                            "Aadhar: " + adminEO.getAadhar() + "\n" +
                            "Phone: " + adminEO.getPhone() + "\n" +
                            "Gender: " + adminEO.getGender() + "\n" +
                            "Role ID: " + adminEO.getRoleID().getRoleID() +
                            "\n" +
                            "Designation ID: " + adminEO.getDesignationID() +
                            "\n" +
                            "Hire Date: " + adminEO.getHireDate();

                        JOptionPane.showMessageDialog(this,
                                                      "Admin added successfully!\t\t\n\n" +
                                adminDetails, "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(this,
                                                      "Failed to add admin. Please try again.",
                                                      "Warning",
                                                      JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                                                  "Invalid Phone number.",
                                                  "Warning",
                                                  JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Aadhar number.",
                                              "Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                                          "Invalid! Name should only contain letters.",
                                          "Warning",
                                          JOptionPane.WARNING_MESSAGE);
        }


        clearFields();

    }

    private void clearFields() {
        Name.setText("");
        Aadhar.setText("");
        Dates.setText("");
        phone.setText("");
        gender.setSelectedIndex(0);
        newCombo.setSelectedIndex(0);
    }
}


class ModifyAdminPanel extends JPanel {
    private JTextField desigIDFld =
        new JTextField(20); // For searching by Designation ID
    private JTextField RoleName = new JTextField(20);
    private JTextField DesigID = new JTextField(20);
    private JTextField Name = new JTextField(20);
    private JTextField Aadhar = new JTextField(20);
    private JTextField Dates = new JTextField(20);
    private JTextField Phone = new JTextField(20);
    private JComboBox Gender = new JComboBox(new String[] { "M", "F", "NA" });
    private JButton searchBtn = new JButton("Search");
    private JButton updateBtn = new JButton("Update Admin");

    // If you have a RoleDAO or similar for role lookups
    private static RoleDAO roleDAORef = GenericDAOFactory.createRoleDAO();

    public ModifyAdminPanel() {
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                                                               2),
                                                                                                "Modify Admin",
                                                                                                TitledBorder.LEFT,
                                                                                                TitledBorder.TOP,
                                                                                                new Font("Tahoma",
                                                                                                         Font.BOLD,
                                                                                                         20)),
                                                               BorderFactory.createEmptyBorder(30,
                                                                                               60,
                                                                                               30,
                                                                                               60)));

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 16);

        // Search Panel
        JPanel searchPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel searchLabel = new JLabel("Designation ID:");
        searchLabel.setFont(labelFont);
        desigIDFld.setFont(fieldFont);
        desigIDFld.setPreferredSize(new Dimension(220, 36));
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        searchBtn.setPreferredSize(new Dimension(140, 36));
        searchPanel.add(searchLabel);
        searchPanel.add(desigIDFld);
        searchPanel.add(searchBtn);

        // Role Panel
        JPanel rolePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel roleLabel = new JLabel("Role Name:");
        roleLabel.setFont(labelFont);
        RoleName.setFont(fieldFont);
        RoleName.setEditable(false);
        RoleName.setPreferredSize(new Dimension(220, 36));
        rolePanel.add(roleLabel);
        rolePanel.add(RoleName);

        // DesigID Panel
        JPanel desigPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel desigLabel = new JLabel("Designation ID:");
        desigLabel.setFont(labelFont);
        DesigID.setFont(fieldFont);
        DesigID.setEditable(false);
        DesigID.setPreferredSize(new Dimension(220, 36));
        desigPanel.add(desigLabel);
        desigPanel.add(DesigID);

        // Name Panel
        JPanel namePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        Name.setFont(fieldFont);
        Name.setPreferredSize(new Dimension(220, 36));
        namePanel.add(nameLabel);
        namePanel.add(Name);

        // Aadhar Panel
        JPanel aadharPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel aadharLabel = new JLabel("Aadhar:");
        aadharLabel.setFont(labelFont);
        Aadhar.setFont(fieldFont);
        Aadhar.setEditable(false);
        Aadhar.setPreferredSize(new Dimension(220, 36));
        aadharPanel.add(aadharLabel);
        aadharPanel.add(Aadhar);

        // Date Panel
        JPanel datePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel dateLabel = new JLabel("Hire Date (yyyy-MM-dd):");
        dateLabel.setFont(labelFont);
        Dates.setFont(fieldFont);
        Dates.setPreferredSize(new Dimension(220, 36));
        datePanel.add(dateLabel);
        datePanel.add(Dates);

        // Phone Panel
        JPanel phonePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(labelFont);
        Phone.setFont(fieldFont);
        Phone.setPreferredSize(new Dimension(220, 36));
        phonePanel.add(phoneLabel);
        phonePanel.add(Phone);

        // Gender Panel
        JPanel genderPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        Gender.setFont(fieldFont);
        Gender.setPreferredSize(new Dimension(220, 36));
        genderPanel.add(genderLabel);
        genderPanel.add(Gender);

        // Button Panel
        JPanel btnPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateBtn.setPreferredSize(new Dimension(180, 40));
        btnPanel.add(updateBtn);

        // Add all panels to mainPanel
        mainPanel.add(searchPanel);
        mainPanel.add(rolePanel);
        mainPanel.add(desigPanel);
        mainPanel.add(namePanel);
        mainPanel.add(aadharPanel);
        mainPanel.add(datePanel);
        mainPanel.add(phonePanel);
        mainPanel.add(genderPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(btnPanel);

        add(mainPanel);

        // Action listeners
        searchBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    search_actionPerformed(e);
                }
            });
        updateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    UpdateButton_actionPerformed(e);
                }
            });
    }

    private void search_actionPerformed(ActionEvent e) {
        String id = desigIDFld.getText();

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        AdminEO adminEO = adminOpImpl.findAdminByDesigID(id);

        if (id != null && adminEO != null) {
            RoleEO role =
                roleDAORef.findRoleByRoleID(adminEO.getRoleID().getRoleID());
            RoleName.setText(role.getRoleName());
            RoleName.setEditable(false);
            DesigID.setText(adminEO.getDesignationID());
            DesigID.setEditable(false);
            Name.setText(adminEO.getName());
            Aadhar.setText(adminEO.getAadhar());
            Aadhar.setEditable(false);
            Dates.setText(adminEO.getHireDate() == null ? "" :
                          adminEO.getHireDate().toString());
            Phone.setText(adminEO.getPhone() == null ? "" :
                          adminEO.getPhone().toString());
            if (adminEO.getGender() != null) {
                Gender.setSelectedItem(adminEO.getGender());
            } else {
                Gender.setSelectedIndex(2);
            }

        } else {
            clearFields();
            JOptionPane.showMessageDialog(this, "Invalid ID", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to fetch!");
        }
    }

    private void UpdateButton_actionPerformed(ActionEvent e) {
        String name = Name.getText();
        String dates = Dates.getText();
        String genderStr = (String)Gender.getSelectedItem();
        Long phone = Long.parseLong(Phone.getText());

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        AdminEO adminEO = adminOpImpl.findAdminByAadhar(Aadhar.getText());
        adminEO.setName(name);
        adminEO.setGender(genderStr);
        adminEO.setPhone(phone);

        // Date logic
        if (!dates.equals("")) {
            try {
                SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date utilDate = sdf.parse(dates);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                adminEO.setHireDate(sqlDate);
            } catch (Exception es) {
                es.printStackTrace();
            }
        } else {
            adminEO.setHireDate(null);
        }

        System.out.println(adminOpImpl.modifyAdmin(adminEO));

        if (adminOpImpl.findAdminByAadhar(Aadhar.getText()) != null) {
            String adminDetails = "Name: " + adminEO.getName() + "\n" +
                "Aadhar: " + adminEO.getAadhar() + "\n" +
                "Phone: " + adminEO.getPhone() + "\n" +
                "Gender: " + adminEO.getGender() + "\n" +
                "Role ID: " + adminEO.getRoleID().getRoleID() + "\n" +
                "Designation ID: " + adminEO.getDesignationID() + "\n" +
                "Hire Date: " + adminEO.getHireDate();

            JOptionPane.showMessageDialog(this,
                                          "Admin updated successfully!\t\t\n\n" +
                    adminDetails, "Success", JOptionPane.INFORMATION_MESSAGE);

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this,
                                          "Failed to update admin. Please try again.",
                                          "Warning",
                                          JOptionPane.WARNING_MESSAGE);

            clearFields();
        }
    }

    private void clearFields() {
        desigIDFld.setText("");
        RoleName.setText("");
        DesigID.setText("");
        Name.setText("");
        Aadhar.setText("");
        Dates.setText("");
        Phone.setText("");
        Gender.setSelectedIndex(0);
        desigIDFld.requestFocus();
    }
}
class RemoveAdminPanel extends JPanel {
    private JTextField desigID = new JTextField(28); // Wider field
    private JTextPane adminTextPane = new JTextPane(); // Centered content
    private JButton searchBtn = new JButton("Search");
    private JButton deleteBtn = new JButton("Delete Admin");

    // If you have a RoleDAO or similar for role lookups
    private static RoleDAO roleDAORef = GenericDAOFactory.createRoleDAO();

    public RemoveAdminPanel() {
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                                                               2),
                                                                                                "Remove Admin",
                                                                                                TitledBorder.LEFT,
                                                                                                TitledBorder.TOP,
                                                                                                new Font("Tahoma",
                                                                                                         Font.BOLD,
                                                                                                         24)),
                    // Larger title font
                    BorderFactory.createEmptyBorder(40, 100, 40,
                                                    100))); // More padding

        Font labelFont = new Font("Tahoma", Font.BOLD, 18);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 18);

        // Search Panel
        JPanel searchPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 18));
        JLabel searchLabel = new JLabel("Designation ID:");
        searchLabel.setFont(labelFont);
        desigID.setFont(fieldFont);
        desigID.setPreferredSize(new Dimension(320, 44));
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        searchBtn.setPreferredSize(new Dimension(160, 44));
        searchPanel.add(searchLabel);
        searchPanel.add(desigID);
        searchPanel.add(searchBtn);

        // Admin Details Area - Centered Content
        JPanel detailsPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 18));
        adminTextPane.setFont(fieldFont);
        adminTextPane.setEditable(false);
        adminTextPane.setPreferredSize(new Dimension(600, 260));
        setPaneCenterAlignment(); // Set initial alignment
        JScrollPane scrollPane = new JScrollPane(adminTextPane);
        scrollPane.setPreferredSize(new Dimension(600, 260));
        detailsPanel.add(scrollPane);

        // Button Panel
        JPanel btnPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 18));
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        deleteBtn.setPreferredSize(new Dimension(210, 50));
        btnPanel.add(deleteBtn);

        // Add all panels to mainPanel
        mainPanel.add(searchPanel);
        mainPanel.add(detailsPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(btnPanel);

        add(mainPanel);

        // Action listeners
        searchBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        deleteBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    delete_actionPerformed(e);
                }
            });
    }

    // Utility to center-align all text in the pane

    private void setPaneCenterAlignment() {
        StyledDocument doc = adminTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    // Use this to set text and keep it centered

    private void setAdminDetails(String text) {
        adminTextPane.setText(text);
        setPaneCenterAlignment();
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        String id = desigID.getText();

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        AdminEO adminEO = adminOpImpl.findAdminByDesigID(id);

        RoleEO role = null;
        String roleName = "";
        if (adminEO != null && adminEO.getRoleID() != null) {
            role =
roleDAORef.findRoleByRoleID(adminEO.getRoleID().getRoleID());
            roleName = (role != null) ? role.getRoleName() : "";
        }

        if (id != null && adminEO != null) {
            String adminDetails = "Name: " + adminEO.getName() + "\n" +
                "\nAadhar: " + adminEO.getAadhar() + "\n" +
                "\nRole ID: " + roleName + "\n" +
                "\nDesignation ID: " + adminEO.getDesignationID() + "\n" +
                "\nHire Date: " + adminEO.getHireDate();
            setAdminDetails(adminDetails);
        } else {
            clearFields();
            JOptionPane.showMessageDialog(this, "Invalid ID", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to fetch!");
        }
    }

    private void delete_actionPerformed(ActionEvent e) {
        String id = desigID.getText();

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        AdminEO adminEO = adminOpImpl.findAdminByDesigID(id);

        if (id != null && adminEO != null &&
            adminOpImpl.removeAdmin(adminEO) != null) {
            JOptionPane.showMessageDialog(this,
                                          "Designation ID " + id + " deleted successfully.",
                                          "Deleted",
                                          JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            clearFields();
            JOptionPane.showMessageDialog(this, "Invalid ID", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to Delete!");
        }
    }

    private void clearFields() {
        adminTextPane.setText("");
        setPaneCenterAlignment();
        desigID.setText("");
    }
}

class ViewAdminPanel extends JPanel {
    private JTable table;
    private JScrollPane scroll;

    private static RoleDAO roleDAORef = GenericDAOFactory.createRoleDAO();

    public ViewAdminPanel() {
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                "View Admins", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 22)
            ),
            // Reduce or remove empty border to minimize space around table
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Table setup
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        table.setFillsViewportHeight(true);

        scroll = new JScrollPane(table);

        // Let scroll pane expand to fill mainPanel
        mainPanel.add(scroll, BorderLayout.CENTER);

        // Add mainPanel to this panel with constraints to expand
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        int marginPx = (int) (1.5 * dpi / 2.54); // 2.54 cm per inch

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        // Set insets for 1.5 cm margin on all sides
        gbc.insets = new Insets(marginPx, marginPx, marginPx, marginPx);

        add(mainPanel, gbc);

        // Auto-refresh table whenever the panel becomes visible
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                viewAdminList();
            }
        });

        // Initial load
        viewAdminList();
    }


    private void viewAdminList() {
        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<AdminEO> adminEOList = adminOpImpl.listAllAdmin();

        SimpleDateFormat sdf =
            new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);

        String[] columnNames =
        { "ROLE", "DESGNT. ID", "NAME", "AADHAR", "HIRE DATE" };
        Object[][] data = new Object[adminEOList.size()][columnNames.length];

        for (int i = 0; i < adminEOList.size(); i++) {
            AdminEO adminEO = adminEOList.get(i);
            RoleEO role =
                roleDAORef.findRoleByRoleID(adminEO.getRoleID().getRoleID());
            String roleName = (role != null) ? role.getRoleName() : "";
            data[i][0] = roleName;
            data[i][1] = adminEO.getDesignationID();
            data[i][2] = adminEO.getName();
            data[i][3] = adminEO.getAadhar();
            data[i][4] =
                    (adminEO.getHireDate() != null) ? sdf.format(adminEO.getHireDate()) :
                    "";
        }

        table.setModel(new DefaultTableModel(data, columnNames) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
    }
}


class RoleManagementTabbedPanel extends JPanel {
    public RoleManagementTabbedPanel() {
        setLayout(new BorderLayout());
        JTabbedPane roleTabs = new JTabbedPane();

        roleTabs.addTab("Add Role", new AddRolePanel());
        roleTabs.addTab("Modify Role", new ModifyRolePanel());
        roleTabs.addTab("Remove Role", new RemoveRolePanel());
        roleTabs.addTab("View Roles", new ViewRolePanel());

        roleTabs.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(roleTabs, BorderLayout.CENTER);
    }
}

// --- Add Role Panel ---
class AddRolePanel extends JPanel {
    private JTextField roleNameField = new JTextField(20);
    private JButton addBtn = new JButton("Add Role");
    private JComboBox roleCombo = new JComboBox();

    public AddRolePanel() {
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
                                                                                                                               2),
                                                                                                "Add Role",
                                                                                                TitledBorder.LEFT,
                                                                                                TitledBorder.TOP,
                                                                                                new Font("Tahoma",
                                                                                                         Font.BOLD,
                                                                                                         20)),
                                                               BorderFactory.createEmptyBorder(30,
                                                                                               60,
                                                                                               30,
                                                                                               60)));

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 16);

        // Input Panel
        JPanel inputPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel nameLabel = new JLabel("Role Name:");
        nameLabel.setFont(labelFont);
        roleNameField.setFont(fieldFont);
        roleNameField.setPreferredSize(new Dimension(220, 36));
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        addBtn.setPreferredSize(new Dimension(140, 36));
        inputPanel.add(nameLabel);
        inputPanel.add(roleNameField);
        inputPanel.add(addBtn);

        // Combo Panel
        JPanel comboPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel comboLabel = new JLabel("Existing Roles:");
        comboLabel.setFont(labelFont);
        roleCombo.setFont(fieldFont);
        roleCombo.setPreferredSize(new Dimension(220, 36));
        comboPanel.add(comboLabel);
        comboPanel.add(roleCombo);

        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(comboPanel);

        add(mainPanel);

        // Populate combo
        populateRoleCombo();

        addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String roleName = roleNameField.getText().trim();
                    if (roleName.isEmpty()) {
                        JOptionPane.showMessageDialog(AddRolePanel.this,
                                                      "Role name cannot be empty.",
                                                      "Warning",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    AdminOperationImpl adminOpImpl = new AdminOperationImpl();
                    RoleEO roleEO = new RoleEO();
                    roleEO.setRoleID(countRoles());
                    roleEO.setRoleName(roleName);
                    String result = adminOpImpl.addRole(roleEO);
                    if (result != null) {
                        JOptionPane.showMessageDialog(AddRolePanel.this,
                                                      "Role added successfully!\nID: " +
                                                      roleEO.getRoleID() +
                                                      "\nRole Name: " +
                                                      roleEO.getRoleName(),
                                                      "Success",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        roleNameField.setText("");
                        populateRoleCombo(); // Always refresh the combo after add!
                    } else {
                        JOptionPane.showMessageDialog(AddRolePanel.this,
                                                      "Failed to add Role. Please try again.",
                                                      "Warning",
                                                      JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
    }

    private void populateRoleCombo() {
        roleCombo.removeAllItems();
        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roleEOList = adminOpImpl.findAllRoles();
        for (RoleEO role : roleEOList) {
            roleCombo.addItem(role.getRoleName());
        }
    }

    private int countRoles() {
        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roleEOList = adminOpImpl.findAllRoles();
        return roleEOList.size() + 1;
    }
}

class ModifyRolePanel extends JPanel {
    private JComboBox roleCombo = new JComboBox();
    private JTextField roleIdField = new JTextField(10);
    private JTextField roleNameField = new JTextField(20);
    private JButton updateBtn = new JButton("UPDATE ROLE");

    public ModifyRolePanel() {
        setLayout(new GridBagLayout()); // For perfect centering
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Modern font configuration
        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 16);

        // Main container panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                            2),
                                                             "Modify Roles",
                                                             TitledBorder.LEFT,
                                                             TitledBorder.TOP,
                                                             new Font("Tahoma",
                                                                      Font.BOLD,
                                                                      20)));

        // Combo Panel
        JPanel comboPanel =
            new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        comboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel comboLabel = new JLabel("Select Role:");
        comboLabel.setFont(labelFont);
        roleCombo.setFont(fieldFont);
        roleCombo.setPreferredSize(new Dimension(250, 35));
        comboPanel.add(comboLabel);
        comboPanel.add(roleCombo);

        // ID Panel
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        idPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel idLabel = new JLabel("Role ID:");
        idLabel.setFont(labelFont);
        roleIdField.setEditable(false);
        roleIdField.setFont(fieldFont);
        roleIdField.setPreferredSize(new Dimension(100, 35));
        idPanel.add(idLabel);
        idPanel.add(roleIdField);

        // Name Panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel nameLabel = new JLabel("Role Name:");
        nameLabel.setFont(labelFont);
        roleNameField.setFont(fieldFont);
        roleNameField.setPreferredSize(new Dimension(250, 35));
        namePanel.add(nameLabel);
        namePanel.add(roleNameField);

        // Button Panel
        JPanel btnPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateBtn.setPreferredSize(new Dimension(180, 40));
        btnPanel.add(updateBtn);

        // Add all components to main panel
        mainPanel.add(comboPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(idPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(btnPanel);

        // Add main panel to center of GridBagLayout
        add(mainPanel);

        // Auto-refresh when panel becomes visible
        this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    refreshRoleCombo();
                }
            });

        // Initial population
        refreshRoleCombo();

        roleCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateFields();
                }
            });

        updateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateRole();
                }
            });
    }

    // Public method to force refresh from external classes

    public void refreshRoleCombo() {
        String previouslySelected = (String)roleCombo.getSelectedItem();
        roleCombo.removeAllItems();

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roles = adminOpImpl.findAllRoles();
        for (RoleEO role : roles) {
            roleCombo.addItem(role.getRoleName());
        }

        // Restore selection if still valid
        if (previouslySelected != null && roleCombo.getItemCount() > 0) {
            roleCombo.setSelectedItem(previouslySelected);
        } else if (roleCombo.getItemCount() > 0) {
            roleCombo.setSelectedIndex(0);
        }
    }

    private void updateFields() {
        String selectedRole = (String)roleCombo.getSelectedItem();
        if (selectedRole == null)
            return;

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roles = adminOpImpl.findRoleByRoleName(selectedRole);
        if (!roles.isEmpty()) {
            RoleEO role = roles.get(0);
            roleIdField.setText(String.valueOf(role.getRoleID()));
            roleNameField.setText(role.getRoleName());
        }
    }

    private void updateRole() {
        if (roleIdField.getText().isEmpty() ||
            roleNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        RoleEO updatedRole = new RoleEO();
        updatedRole.setRoleID(Integer.parseInt(roleIdField.getText()));
        updatedRole.setRoleName(roleNameField.getText().trim());

        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        String result = adminOpImpl.modifyRole(updatedRole);

        if (result != null) {
            JOptionPane.showMessageDialog(this, "Role updated successfully!",
                                          "Success",
                                          JOptionPane.INFORMATION_MESSAGE);
            refreshRoleCombo(); // Refresh combo after update
            roleCombo.setSelectedItem(updatedRole.getRoleName()); // Select updated item
        } else {
            JOptionPane.showMessageDialog(this, "Update failed!", "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
}


// --- Remove Role Panel ---
class RemoveRolePanel extends JPanel {
    private JComboBox roleCombo = new JComboBox();
    private JTextField roleIdField = new JTextField(10);
    private JTextField roleNameField = new JTextField(20);
    private JButton deleteBtn = new JButton("Delete Role");

    public RemoveRolePanel() {
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                                                               2),
                                                                                                "Remove Role",
                                                                                                TitledBorder.LEFT,
                                                                                                TitledBorder.TOP,
                                                                                                new Font("Tahoma",
                                                                                                         Font.BOLD,
                                                                                                         20)),
                                                               BorderFactory.createEmptyBorder(30,
                                                                                               60,
                                                                                               30,
                                                                                               60)));

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 16);

        // Combo Panel
        JPanel comboPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel comboLabel = new JLabel("Select Role:");
        comboLabel.setFont(labelFont);
        roleCombo.setFont(fieldFont);
        roleCombo.setPreferredSize(new Dimension(220, 36));
        comboPanel.add(comboLabel);
        comboPanel.add(roleCombo);

        // ID Panel
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel idLabel = new JLabel("Role ID:");
        idLabel.setFont(labelFont);
        roleIdField.setEditable(false);
        roleIdField.setFont(fieldFont);
        roleIdField.setPreferredSize(new Dimension(100, 36));
        idPanel.add(idLabel);
        idPanel.add(roleIdField);

        // Name Panel
        JPanel namePanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel nameLabel = new JLabel("Role Name:");
        nameLabel.setFont(labelFont);
        roleNameField.setEditable(false);
        roleNameField.setFont(fieldFont);
        roleNameField.setPreferredSize(new Dimension(220, 36));
        namePanel.add(nameLabel);
        namePanel.add(roleNameField);

        // Button Panel
        JPanel btnPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteBtn.setPreferredSize(new Dimension(180, 40));
        btnPanel.add(deleteBtn);

        mainPanel.add(comboPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(idPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(btnPanel);

        add(mainPanel);

        // Refresh combo every time the panel is shown
        this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    populateRoleCombo(null);
                }
            });

        // Initial population
        populateRoleCombo(null);

        // Combo box selection listener
        roleCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateFields();
                }
            });

        // Delete button listener
        deleteBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String idText = roleIdField.getText();
                    String name = roleNameField.getText();
                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(RemoveRolePanel.this,
                                                      "Select a role to delete.",
                                                      "Warning",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    int roleId = Integer.parseInt(idText);
                    AdminOperationImpl adminOpImpl = new AdminOperationImpl();
                    String result = adminOpImpl.removeRole(roleId);
                    if (result != null) {
                        JOptionPane.showMessageDialog(RemoveRolePanel.this,
                                                      "ID: " + idText +
                                                      "\nROLE: " + name,
                                                      "Successfully Deleted",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        int index = roleCombo.getSelectedIndex();
                        populateRoleCombo(null);
                        if (roleCombo.getItemCount() > 0) {
                            if (index >= roleCombo.getItemCount())
                                index = roleCombo.getItemCount() - 1;
                            roleCombo.setSelectedIndex(index);
                        } else {
                            roleIdField.setText("");
                            roleNameField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(RemoveRolePanel.this,
                                                      "Failed to delete role.",
                                                      "Error",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        // Initialize fields for first item
        if (roleCombo.getItemCount() > 0) {
            roleCombo.setSelectedIndex(0);
        }
    }

    public void populateRoleCombo(String selectRoleName) {
        roleCombo.removeAllItems();
        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roleEOList = adminOpImpl.findAllRoles();
        for (RoleEO role : roleEOList) {
            roleCombo.addItem(role.getRoleName());
        }
        if (selectRoleName != null) {
            roleCombo.setSelectedItem(selectRoleName);
        } else if (roleCombo.getItemCount() > 0) {
            roleCombo.setSelectedIndex(0);
        }
        if (roleCombo.getItemCount() == 0) {
            roleIdField.setText("");
            roleNameField.setText("");
        }
    }

    // Helper to update fields when combo selection changes

    private void updateFields() {
        String selectedRoleName = (String)roleCombo.getSelectedItem();
        if (selectedRoleName != null) {
            AdminOperationImpl adminOpImpl = new AdminOperationImpl();
            List<RoleEO> roleEOList =
                adminOpImpl.findRoleByRoleName(selectedRoleName);
            if (roleEOList.size() != 0) {
                roleIdField.setText(String.valueOf(roleEOList.get(0).getRoleID()));
                roleNameField.setText(roleEOList.get(0).getRoleName());
            } else {
                roleIdField.setText("");
                roleNameField.setText("");
            }
        } else {
            roleIdField.setText("");
            roleNameField.setText("");
        }
    }
}

// --- View Role Panel ---
class ViewRolePanel extends JPanel {
    private JTable table;
    private JScrollPane scroll;

    public ViewRolePanel() {
        setLayout(new GridBagLayout()); // Center everything

        // Main panel with titled border and padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                                                               2),
                                                                                                "View Roles",
                                                                                                TitledBorder.LEFT,
                                                                                                TitledBorder.TOP,
                                                                                                new Font("Tahoma",
                                                                                                         Font.BOLD,
                                                                                                         20)),
                                                               new EmptyBorder(30,
                                                                               40,
                                                                               30,
                                                                               40)));

        // Table setup
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 18));
        table.setRowHeight(32);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
        table.setFillsViewportHeight(true);

        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(700, 350));

        mainPanel.add(scroll, BorderLayout.CENTER);

        add(mainPanel, new GridBagConstraints());

        // Refresh table whenever the panel becomes visible (like in a tab)
        this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    refreshTable(null);
                }
            });

        refreshTable(null); // Initial load
    }

    public void refreshTable(String highlightRoleName) {
        AdminOperationImpl adminOpImpl = new AdminOperationImpl();
        List<RoleEO> roleEOlist = adminOpImpl.findAllRoles();

        String[] columnNames = { "ROLE ID", "ROLE NAME" };
        Object[][] data = new Object[roleEOlist.size()][columnNames.length];

        int highlightRow = -1;
        for (int i = 0; i < roleEOlist.size(); i++) {
            RoleEO roleEO = roleEOlist.get(i);
            data[i][0] = roleEO.getRoleID();
            data[i][1] = roleEO.getRoleName();
            if (highlightRoleName != null &&
                roleEO.getRoleName().equalsIgnoreCase(highlightRoleName)) {
                highlightRow = i;
            }
        }

        table.setModel(new DefaultTableModel(data, columnNames) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

        // Optionally scroll to and select the highlighted row
        if (highlightRow >= 0) {
            table.setRowSelectionInterval(highlightRow, highlightRow);
            table.scrollRectToVisible(table.getCellRect(highlightRow, 0,
                                                        true));
        } else {
            table.clearSelection();
        }
    }
}
