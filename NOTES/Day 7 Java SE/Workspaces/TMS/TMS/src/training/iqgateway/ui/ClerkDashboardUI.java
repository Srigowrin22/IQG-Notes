package training.iqgateway.ui;

import java.awt.*;
import java.awt.event.*;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableCellRenderer;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.operations.impl.ClerkOperationImpl;
import training.iqgateway.report.ExecuteReport;

public class ClerkDashboardUI extends JFrame {

    private JPanel VehicleOffence = new JPanel(new GridBagLayout());
    private JPanel ClearVehicleOff = new JPanel(new GridBagLayout());
    private JPanel OwnerDetails = new JPanel();
    private JPanel ViewOffence = new JPanel(new BorderLayout());
    private JTextField regisID = new JTextField();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();

    private JTextField dateField = new JTextField(20);
    private JTextField timeField = new JTextField(20);
    private JTextField reporterField = new JTextField(20);
    private JLabel imagePreview = new JLabel();
    private JButton logout = new JButton("LOGOUT");
    private JComboBox offenceTypeCombo = new JComboBox();
    private JTextField location = new JTextField(15);

    // --- Clear Vehicle Off panel components ---
    private JTextField clearRegisIDField = new JTextField(15);
    private DefaultTableModel clearTableModel;
    private JTable clearOffenceTable;
    private JTextArea clearPaymentArea = new JTextArea(4, 30);
    private JButton clearPayButton = new JButton("PAY and Generate Reports");

    // --- Owner Details ---

    private JTextField ownerRegisIDField = new JTextField(15);
    private JButton fetchOwnerBtn = new JButton("Fetch");

    // Owner Details fields
    private JTextField ownerNameField = new JTextField(20);
    private JTextField ownerGenderField = new JTextField(5);
    private JTextField ownerAadharField = new JTextField(20);
    private JTextField ownerPanField = new JTextField(20);
    private JTextField ownerPhoneField = new JTextField(15);
    private JTextField ownerAddressField = new JTextField(30);

    // Registration Details fields
    private JTextField regRegistrationIDField = new JTextField(20);
    private JTextField regLocationField = new JTextField(20);
    private JTextField regRegistrationDateField = new JTextField(20);
    private JTextField regRegistrarField = new JTextField(20);
    private JTextField regDateField = new JTextField(20);

    // Vehicle Details fields
    private JTextField vehicleIDField = new JTextField(15);
    private JTextField vehicleBrandField = new JTextField(20);
    private JTextField vehicleModelField = new JTextField(20);
    private JTextField vehicleTypeField = new JTextField(20);
    private JTextField vehicleFuelTypeField = new JTextField(15);
    private JTextField vehicleNoOfExhaustField = new JTextField(5);
    private JTextField vehicleColorField = new JTextField(15);
    private JTextField vehicleManufactureDateField = new JTextField(20);


    // View Vehicle Offence
    private JTable viewOffenceTable;
    private DefaultTableModel viewOffenceTableModel;

    // To store selected image file path (optional)
    private String imageFilePath = null;
    private String videoFilePath = null;
    private JLabel imageFileNameLabel = new JLabel("No image chosen");
    private JLabel videoFileNameLabel = new JLabel("No video chosen");
    private byte[] imageBytes = null;
    private byte[] videoBytes = null;

    private JLabel helloAdm = new JLabel();
    private AdminEO currentAdmin = new AdminEO();
    private ClerkOperationImpl clkOpImpl = new ClerkOperationImpl();

    public ClerkDashboardUI() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ClerkDashboardUI(AdminEO adminEO) {
        this();
        this.currentAdmin = adminEO;
        helloAdm.setText("Hello! " + currentAdmin.getDesignationID());
        reporterField.setText(currentAdmin.getDesignationID());
    }

    private void jbInit() throws Exception {
        setTitle("CLERK OPERATIONS");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setContentPane(mainPanel);

        // Top hello panel
        JPanel topPanel = new JPanel(new BorderLayout());
        helloAdm.setFont(new Font("Tahoma", Font.BOLD, 28));
        topPanel.add(helloAdm, BorderLayout.WEST);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 18));
        topPanel.add(logout, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12,
                                                           0)); // 0.5cm spacing below
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Build panels (make sure each uses TitledBorder and proper fonts)
        buildVehicleOffencePanel();
        buildClearVehicleOffPanel();
        buildOwnerDetailsPanel();
        buildViewOffencePanel();

        // Add tabs
        jTabbedPane1.setFont(new Font("Tahoma", Font.BOLD, 18));
        jTabbedPane1.addTab("   Vehicle Offence ", VehicleOffence);
        jTabbedPane1.addTab("   Clear Offence   ", ClearVehicleOff);
        jTabbedPane1.addTab("   Owner Details   ", OwnerDetails);
        jTabbedPane1.addTab("   View All Offence    ", ViewOffence);

        // Tab change listener
        jTabbedPane1.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    Component selectedComponent =
                        jTabbedPane1.getSelectedComponent();
                    if (selectedComponent == VehicleOffence) {
                        resetVehicleOffencePanel();
                    } else if (selectedComponent == ClearVehicleOff) {
                        loadClearVehicleOffPanel(); // just clear, do not load data
                    } else if (selectedComponent == OwnerDetails) {
                        fetchOwnerDetailsByRegisID(); // just clear, do not load data
                    } else if (selectedComponent == ViewOffence) {
                        loadAllVehicleOffences();
                    }
                }
            });

        logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close the current dashboard window
                    // (this refers to the current JFrame, so use ClerkDashboardUI.this)
                    ClerkDashboardUI.this.dispose();
                    JOptionPane.showMessageDialog(ClerkDashboardUI.this,
                                                  "Logged OUT");

                    // Show the login UI
                    AdminLoginUI loginUI = new AdminLoginUI();
                    loginUI.setVisible(true);
                }
            });

        mainPanel.add(jTabbedPane1, BorderLayout.CENTER);
    }

    private void buildVehicleOffencePanel() {
        VehicleOffence.removeAll();

        // Add a bold titled border to the panel
        TitledBorder border =
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                            2),
                                             "Vehicle Offence Entry",
                                             TitledBorder.LEFT,
                                             TitledBorder.TOP,
                                             new Font("Tahoma", Font.BOLD,
                                                      22));
        VehicleOffence.setBorder(border);

        VehicleOffence.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 16, 16, 16); // More spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // Helper for boxed fields
        Border fieldBorder =
            BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
                                                                              1),
                                               BorderFactory.createEmptyBorder(5,
                                                                               8,
                                                                               5,
                                                                               8));

        // Registration ID
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel regisIDLabel = new JLabel("Registration ID:");
        regisIDLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(regisIDLabel, gbc);

        gbc.gridx = 1;
        regisID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        regisID.setPreferredSize(new Dimension(220, 32));
        regisID.setBorder(fieldBorder);
        VehicleOffence.add(regisID, gbc);

        // Offence Type
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel offenceTypeLabel = new JLabel("Offence Type:");
        offenceTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(offenceTypeLabel, gbc);

        gbc.gridx = 1;
        offenceTypeCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        offenceTypeCombo.setPreferredSize(new Dimension(220, 32));
        VehicleOffence.add(offenceTypeCombo, gbc);

        // Place
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel placeLabel = new JLabel("Place:");
        placeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(placeLabel, gbc);

        gbc.gridx = 1;
        location.setFont(new Font("Tahoma", Font.PLAIN, 16));
        location.setPreferredSize(new Dimension(220, 32));
        location.setBorder(fieldBorder);
        VehicleOffence.add(location, gbc);

        // Date
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel dateLabel = new JLabel("Offence Date (yyyy-MM-dd):");
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(dateLabel, gbc);

        gbc.gridx = 1;
        dateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dateField.setPreferredSize(new Dimension(220, 32));
        dateField.setToolTipText("yyyy-MM-dd");
        dateField.setBorder(fieldBorder);
        VehicleOffence.add(dateField, gbc);

        // Time
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel timeLabel = new JLabel("Offence Time (HH:mm):");
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(timeLabel, gbc);

        gbc.gridx = 1;
        timeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        timeField.setPreferredSize(new Dimension(220, 32));
        timeField.setToolTipText("HH:mm");
        timeField.setBorder(fieldBorder);
        VehicleOffence.add(timeField, gbc);

        // Reporter
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel reporterLabel = new JLabel("Reporter:");
        reporterLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(reporterLabel, gbc);

        gbc.gridx = 1;
        reporterField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        reporterField.setPreferredSize(new Dimension(220, 32));
        reporterField.setEditable(false);
        reporterField.setBorder(fieldBorder);
        VehicleOffence.add(reporterField, gbc);

        // --- Add vertical space before evidence section ---
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        VehicleOffence.add(Box.createVerticalStrut(18), gbc);
        gbc.gridwidth = 1;

        // --- Image Evidence ---
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel imageLabel = new JLabel("Upload Image Evidence:");
        imageLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(imageLabel, gbc);

        gbc.gridx = 1;
        JPanel imageFilePanel =
            new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton chooseImageBtn = new JButton("Choose Image");
        chooseImageBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        imageFileNameLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
        imageFilePanel.add(chooseImageBtn);
        imageFilePanel.add(Box.createHorizontalStrut(10));
        imageFilePanel.add(imageFileNameLabel);
        imageFilePanel.setOpaque(false);
        VehicleOffence.add(imageFilePanel, gbc);

        // --- Video Evidence ---
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel videoLabel = new JLabel("Upload Video Evidence:");
        videoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        VehicleOffence.add(videoLabel, gbc);

        gbc.gridx = 1;
        JPanel videoFilePanel =
            new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton chooseVideoBtn = new JButton("Choose Video");
        chooseVideoBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        videoFileNameLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
        videoFilePanel.add(chooseVideoBtn);
        videoFilePanel.add(Box.createHorizontalStrut(10));
        videoFilePanel.add(videoFileNameLabel);
        videoFilePanel.setOpaque(false);
        VehicleOffence.add(videoFilePanel, gbc);

        // --- Add vertical space before button ---
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        VehicleOffence.add(Box.createVerticalStrut(18), gbc);
        gbc.gridwidth = 1;

        // Upload Button
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton uploadBtn = new JButton("Upload Offence");
        uploadBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        uploadBtn.setPreferredSize(new Dimension(220, 40));
        VehicleOffence.add(uploadBtn, gbc);

        // Restore anchor for future use
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        // Populate offenceTypeCombo
        populateOffenceTypeCombo();

        // --- Image File Chooser Logic ---
        chooseImageBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select Image Evidence");
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files",
                                                                                                  "jpg",
                                                                                                  "jpeg",
                                                                                                  "png",
                                                                                                  "bmp",
                                                                                                  "gif"));
                    int option = fileChooser.showOpenDialog(VehicleOffence);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        imageFilePath = file.getAbsolutePath();
                        imageFileNameLabel.setText(file.getName());
                        try {
                            FileInputStream fis = new FileInputStream(file);
                            imageBytes = new byte[(int)file.length()];
                            fis.read(imageBytes);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(VehicleOffence,
                                                          "Error reading image file: " +
                                                          ex.getMessage(),
                                                          "File Error",
                                                          JOptionPane.ERROR_MESSAGE);
                            imageBytes = null;
                        }
                    }
                }
            });

        // --- Video File Chooser Logic ---
        chooseVideoBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select Video Evidence");
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Video Files",
                                                                                                  "mp4",
                                                                                                  "avi",
                                                                                                  "mov",
                                                                                                  "mkv"));
                    int option = fileChooser.showOpenDialog(VehicleOffence);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        videoFilePath = file.getAbsolutePath();
                        videoFileNameLabel.setText(file.getName());
                        try {
                            FileInputStream fis = new FileInputStream(file);
                            videoBytes = new byte[(int)file.length()];
                            fis.read(videoBytes);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(VehicleOffence,
                                                          "Error reading video file: " +
                                                          ex.getMessage(),
                                                          "File Error",
                                                          JOptionPane.ERROR_MESSAGE);
                            videoBytes = null;
                        }
                    }
                }
            });

        // Upload button logic
        uploadBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String registrationId = regisID.getText().trim();
                    String offenceTypeStr =
                        (String)offenceTypeCombo.getSelectedItem();
                    String offencePlace = location.getText().trim();
                    String dateStr = dateField.getText().trim();
                    String timeStr = timeField.getText().trim();
                    String reporter = reporterField.getText().trim();

                    if (registrationId.isEmpty() || offenceTypeStr == null ||
                        offencePlace.isEmpty() || imageFilePath == null) {
                        JOptionPane.showMessageDialog(VehicleOffence,
                                                      "Please fill all fields and select an evidence file!",
                                                      "Validation Error",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (imageBytes == null) {
                        JOptionPane.showMessageDialog(VehicleOffence,
                                                      "Please select image evidence files!",
                                                      "Validation Error",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Parse Offence ID from Combo
                    String[] offenceParts = offenceTypeStr.split(" ");
                    int offenceID = Integer.parseInt(offenceParts[0]);

                    // Date
                    java.sql.Date sqlDate = null;
                    if (!dateStr.isEmpty()) {
                        try {
                            sqlDate = java.sql.Date.valueOf(dateStr);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(VehicleOffence,
                                                          "Invalid date format! Use yyyy-MM-dd.",
                                                          "Date Error",
                                                          JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        // Fetch current system date
                        sqlDate =
                                new java.sql.Date(System.currentTimeMillis());
                    }

                    // Time
                    String sqlTime = null;
                    if (!timeStr.isEmpty()) {
                        if (!timeStr.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                            JOptionPane.showMessageDialog(VehicleOffence,
                                                          "Invalid time format! Use HH:mm.",
                                                          "Time Error",
                                                          JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        sqlTime = timeStr + ":00";
                    } else {
                        // Get current system time in HH:mm:ss
                        SimpleDateFormat sdf =
                            new SimpleDateFormat("HH:mm:ss");
                        sqlTime = sdf.format(new Date());
                    }

                    // Now you can safely use:

                    // Prepare evidence as byte[] (for BLOB)
                    File file = new File(imageFilePath);
                    byte[] fileBytes = new byte[(int)file.length()];
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        fis.read(fileBytes);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(VehicleOffence,
                                                      "Error reading file: " +
                                                      ex.getMessage(),
                                                      "File Error",
                                                      JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Prepare entity
                    VehicleOffenceEO vehOffEO = new VehicleOffenceEO();
                    RegistrationEO regisEO =
                        clkOpImpl.findRegistrationByID(registrationId);
                    if (regisEO == null) {
                        JOptionPane.showMessageDialog(VehicleOffence,
                                                      "Registration ID not found!",
                                                      "Validation Error",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    vehOffEO.setRegistrationID(regisEO);

                    OffenceTypesEO offEO =
                        clkOpImpl.findOffenceTypeByID(offenceID);
                    if (offEO == null) {
                        JOptionPane.showMessageDialog(VehicleOffence,
                                                      "Offence Type not found!",
                                                      "Validation Error",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    vehOffEO.setOffenceID(offEO);
                    vehOffEO.setLocation(offencePlace);
                    vehOffEO.setDate(sqlDate); // Can be null
                    vehOffEO.setTime(java.sql.Time.valueOf(sqlTime));
                    vehOffEO.setReporter(reporter);
                    vehOffEO.setProof1(imageBytes); // image
                    if (videoBytes != null) {
                        vehOffEO.setProof2(videoBytes); // video (optional)
                    } else {
                        vehOffEO.setProof2(null); // or just skip this line if your setter allows null
                    }

                    // Call DB logic
                    String result = clkOpImpl.addVehicleOffence(vehOffEO);

                    if (result.startsWith("Succesfully")) {
                        JOptionPane.showMessageDialog(VehicleOffence,
                                                      "Offence uploaded successfully!",
                                                      "Success",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        resetVehicleOffencePanel();
                    } else {
                        JOptionPane.showMessageDialog(VehicleOffence, result,
                                                      "Error",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
    }

    private void populateOffenceTypeCombo() {
        offenceTypeCombo.removeAllItems();
        ClerkOperationImpl clerkOpImplCombo = new ClerkOperationImpl();
        List<OffenceTypesEO> offTypeList =
            clerkOpImplCombo.findAllOffenceType();
        for (OffenceTypesEO off : offTypeList) {
            offenceTypeCombo.addItem(off.getOffenceID() + " " +
                                     off.getOffenceType() + " -> " +
                                     off.getVehicleType());
        }
        if (offenceTypeCombo.getItemCount() > 0) {
            offenceTypeCombo.setSelectedIndex(0);
        }
    }

    private void resetVehicleOffencePanel() {
        regisID.setText("");
        location.setText("");
        imagePreview.setIcon(null);
        imageFilePath = null;
        populateOffenceTypeCombo();
    }

    private void buildClearVehicleOffPanel() {
        ClearVehicleOff.removeAll();

        // Add a bold titled border to the panel
        TitledBorder border =
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                            2),
                                             "Clear Vehicle Offence",
                                             TitledBorder.LEFT,
                                             TitledBorder.TOP,
                                             new Font("Tahoma", Font.BOLD,
                                                      22));
        ClearVehicleOff.setBorder(border);

        ClearVehicleOff.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 16, 16, 16); // More spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        // Government statement and info
        JLabel infoLabel =
            new JLabel("<html><b>Note:</b> Select the offences to clear. " +
                       "All payments are non-refundable. By paying, you acknowledge the offence and agree to the terms set by the Government of India.</html>");
        infoLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
        gbc.gridwidth = 2;
        ClearVehicleOff.add(infoLabel, gbc);

        // Add vertical space
        gbc.gridy++;
        gbc.gridwidth = 2;
        ClearVehicleOff.add(Box.createVerticalStrut(10), gbc);

        // Registration ID label
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel regisIDLabel = new JLabel("Registration ID:");
        regisIDLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        ClearVehicleOff.add(regisIDLabel, gbc);

        // Registration ID text field
        gbc.gridx = 1;
        clearRegisIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clearRegisIDField.setPreferredSize(new Dimension(220, 32));
        clearRegisIDField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
                                                                                                      1),
                                                                       BorderFactory.createEmptyBorder(5,
                                                                                                       8,
                                                                                                       5,
                                                                                                       8)));
        ClearVehicleOff.add(clearRegisIDField, gbc);

        // Table with offences and checkbox
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;

        String[] columnNames =
        { "Select", "Offence ID", "Offence Type", "Location", "Date", "Time",
          "Amount" };
        clearTableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 0)
                        return Boolean.class; // Checkbox
                    if (columnIndex == 6)
                        return Integer.class; // Amount
                    return String.class;
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 0; // Only checkbox editable
                }
            };

        clearOffenceTable = new JTable(clearTableModel);
        clearOffenceTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        clearOffenceTable.setRowHeight(28);
        clearOffenceTable.getTableHeader().setFont(new Font("Tahoma",
                                                            Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(clearOffenceTable);
        scrollPane.setPreferredSize(new Dimension(750, 220));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        ClearVehicleOff.add(scrollPane, gbc);

        // Payment gateway text area
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0;
        clearPaymentArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clearPaymentArea.setEditable(false);
        clearPaymentArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                                   1),
                                                                    "Payment Gateway",
                                                                    TitledBorder.LEFT,
                                                                    TitledBorder.TOP,
                                                                    new Font("Tahoma",
                                                                             Font.BOLD,
                                                                             16)));
        clearPaymentArea.setText("Total Amount: 0\n\n" +
                "Please select offences above to see the amount to pay.\n" +
                "Offence committed under the Motor Vehicles Act, 1988.\n" +
                "All payments go to the Government of India.");
        ClearVehicleOff.add(clearPaymentArea, gbc);

        // Pay button
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        clearPayButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        clearPayButton.setPreferredSize(new Dimension(260, 40));
        ClearVehicleOff.add(clearPayButton, gbc);

        // Listen for checkbox changes to update amount
        clearTableModel.addTableModelListener(new javax.swing.event.TableModelListener() {
                public void tableChanged(javax.swing.event.TableModelEvent e) {
                    updatePaymentArea();
                }
            });

        // Pay button action
        clearPayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int rowCount = clearTableModel.getRowCount();
                    int totalAmount = 0;
                    List<Integer> selectedVehicleOffenceIDs =
                        new ArrayList<Integer>();
                    for (int i = 0; i < rowCount; i++) {
                        Boolean selected =
                            (Boolean)clearTableModel.getValueAt(i, 0);
                        if (selected != null && selected) {
                            // Column 1 should be VehicleOffenceEO ID
                            Integer vehicleOffenceID =
                                Integer.parseInt(clearTableModel.getValueAt(i,
                                                                            1).toString());
                            selectedVehicleOffenceIDs.add(vehicleOffenceID);
                            Integer amount =
                                (Integer)clearTableModel.getValueAt(i, 6);
                            totalAmount += amount;
                        }
                    }
                    if (selectedVehicleOffenceIDs.isEmpty()) {
                        JOptionPane.showMessageDialog(ClearVehicleOff,
                                                      "Please select at least one offence to pay.",
                                                      "Warning",
                                                      JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // Confirm payment
                    int option =
                        JOptionPane.showConfirmDialog(ClearVehicleOff, "You are about to pay ?" +
                                                      totalAmount + " for " +
                                                      selectedVehicleOffenceIDs.size() +
                                                      " offence(s).\nProceed?",
                                                      "Confirm Payment",
                                                      JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        ClerkOperationImpl clerkOpImpl =
                            new ClerkOperationImpl();
                        for (Integer vehicleOffenceID :
                             selectedVehicleOffenceIDs) {
                            VehicleOffenceEO vehOffEO =
                                clkOpImpl.findVehicleOffenceByID(vehicleOffenceID);
                            clerkOpImpl.clearVehicleOffence(vehOffEO);
                        }
                        JOptionPane.showMessageDialog(ClearVehicleOff,
                                                      "Amount paid and offence(s) cleared!",
                                                      "Payment Success",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        loadClearVehicleOffPanel(); // Refresh table
                    }
                }
            });

        // Load offences when user presses Enter in regisIDField
        clearRegisIDField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    loadClearVehicleOffPanel();
                }
            });
    }

    private void updatePaymentArea() {
        int rowCount = clearTableModel.getRowCount();
        int totalAmount = 0;
        int selectedCount = 0;
        for (int i = 0; i < rowCount; i++) {
            Boolean selected = (Boolean)clearTableModel.getValueAt(i, 0);
            if (selected != null && selected) {
                Integer amount = (Integer)clearTableModel.getValueAt(i, 6);
                totalAmount += amount;
                selectedCount++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Total Amount: ?").append(totalAmount).append("\n\n");
        if (selectedCount == 0) {
            sb.append("Please select offences above to see the amount to pay.\n");
        } else {
            sb.append("You have selected ").append(selectedCount).append(" offence(s).\n");
        }
        sb.append("Offence committed under the Motor Vehicles Act, 1988.\n");
        sb.append("All payments go to the Government of India.");
        clearPaymentArea.setText(sb.toString());
    }

    private void loadClearVehicleOffPanel() {
        String regisID = clearRegisIDField.getText().trim();
        clearTableModel.setRowCount(0);
        clearPaymentArea.setText("Total Amount: 0\n\n" +
                "Please select offences above to see the amount to pay.\n" +
                "Offence committed under the Motor Vehicles Act, 1988.\n" +
                "All payments go to the Government of India.");

        if (regisID.isEmpty()) {
            return;
        }

        ClerkOperationImpl clerkOpImpl = new ClerkOperationImpl();
        List<VehicleOffenceEO> offences =
            clerkOpImpl.findVehicleOffenceByStatus(regisID, 0);

        if (offences == null || offences.isEmpty()) {
            JOptionPane.showMessageDialog(ClearVehicleOff,
                                          "No offences found for this vehicle.",
                                          "Information",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (VehicleOffenceEO offence : offences) {
            int offID = offence.getOffenceID().getOffenceID();
            OffenceTypesEO offenceType =
                clerkOpImpl.findOffenceTypeByID(offID);
            String offenceTypeName =
                offenceType != null ? offenceType.getOffenceType() : "Unknown";
            int amount = offenceType != null ? offenceType.getPenaltyAmt() : 0;
            String location =
                offence.getLocation() != null ? offence.getLocation() : "";
            String date =
                offence.getDate() != null ? offence.getDate().toString() : "";
            String time =
                offence.getTime() != null ? offence.getTime().toString() : "";

            Object[] rowData = new Object[] { Boolean.FALSE, // Checkbox
                    offence.getVehicleOffenceID(),
                    // VehicleOffenceID (for clearing)
                    offenceTypeName, // Offence Type
                    location, // Location
                    date, // Date
                    time, // Time
                    amount // Amount
                    } ;
            clearTableModel.addRow(rowData);
        }
    }

    private void buildOwnerDetailsPanel() {
        OwnerDetails.removeAll();

        // Add a bold titled border to the panel
        TitledBorder border =
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                            2),
                                             "Owner, Registration & Vehicle Details",
                                             TitledBorder.LEFT,
                                             TitledBorder.TOP,
                                             new Font("Tahoma", Font.BOLD,
                                                      22));
        OwnerDetails.setBorder(border);

        OwnerDetails.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(14, 14, 14, 14); // More spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Helper for boxed fields
        Border fieldBorder =
            BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
                                                                              1),
                                               BorderFactory.createEmptyBorder(5,
                                                                               8,
                                                                               5,
                                                                               8));

        // Row 0: Registration ID label, text field, fetch button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        JLabel regisIDLabel = new JLabel("Registration ID:");
        regisIDLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        OwnerDetails.add(regisIDLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.5;
        ownerRegisIDField.setText(""); // clear on panel load
        ownerRegisIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerRegisIDField.setPreferredSize(new Dimension(220, 32));
        ownerRegisIDField.setBorder(fieldBorder);
        OwnerDetails.add(ownerRegisIDField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        fetchOwnerBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        fetchOwnerBtn.setPreferredSize(new Dimension(120, 32));
        OwnerDetails.add(fetchOwnerBtn, gbc);

        int row = 1;
        gbc.weightx = 0.1;

        // --- Owner Details Title ---
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 4;
        JLabel ownerTitle = new JLabel("Owner Details");
        ownerTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        OwnerDetails.add(ownerTitle, gbc);
        gbc.gridwidth = 1;

        // Owner Name & Gender
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(nameLabel, gbc);
        gbc.gridx = 1;
        ownerNameField.setEditable(false);
        ownerNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerNameField.setBorder(fieldBorder);
        OwnerDetails.add(ownerNameField, gbc);
        gbc.gridx = 2;
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(genderLabel, gbc);
        gbc.gridx = 3;
        ownerGenderField.setEditable(false);
        ownerGenderField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerGenderField.setBorder(fieldBorder);
        OwnerDetails.add(ownerGenderField, gbc);

        // Owner Aadhar & PAN
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel aadharLabel = new JLabel("Aadhar:");
        aadharLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(aadharLabel, gbc);
        gbc.gridx = 1;
        ownerAadharField.setEditable(false);
        ownerAadharField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerAadharField.setBorder(fieldBorder);
        OwnerDetails.add(ownerAadharField, gbc);
        gbc.gridx = 2;
        JLabel panLabel = new JLabel("PAN Card:");
        panLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(panLabel, gbc);
        gbc.gridx = 3;
        ownerPanField.setEditable(false);
        ownerPanField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerPanField.setBorder(fieldBorder);
        OwnerDetails.add(ownerPanField, gbc);

        // Owner Phone & Address
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(phoneLabel, gbc);
        gbc.gridx = 1;
        ownerPhoneField.setEditable(false);
        ownerPhoneField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerPhoneField.setBorder(fieldBorder);
        OwnerDetails.add(ownerPhoneField, gbc);
        gbc.gridx = 2;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(addressLabel, gbc);
        gbc.gridx = 3;
        ownerAddressField.setEditable(false);
        ownerAddressField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerAddressField.setBorder(fieldBorder);
        OwnerDetails.add(ownerAddressField, gbc);

        // --- Section Spacing ---
        gbc.gridy = row++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        OwnerDetails.add(Box.createVerticalStrut(8), gbc);
        gbc.gridwidth = 1;

        // --- Registration Details Title ---
        gbc.gridy = row++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        JLabel regTitle = new JLabel("Registration Details");
        regTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        OwnerDetails.add(regTitle, gbc);
        gbc.gridwidth = 1;

        // Registration ID & Location
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel regIDLabel = new JLabel("Registration ID:");
        regIDLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(regIDLabel, gbc);
        gbc.gridx = 1;
        regRegistrationIDField.setEditable(false);
        regRegistrationIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        regRegistrationIDField.setBorder(fieldBorder);
        OwnerDetails.add(regRegistrationIDField, gbc);
        gbc.gridx = 2;
        JLabel regLocLabel = new JLabel("Location:");
        regLocLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(regLocLabel, gbc);
        gbc.gridx = 3;
        regLocationField.setEditable(false);
        regLocationField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        regLocationField.setBorder(fieldBorder);
        OwnerDetails.add(regLocationField, gbc);

        // Registration Date & Registrar
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel regDateLabel = new JLabel("Registration Date:");
        regDateLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(regDateLabel, gbc);
        gbc.gridx = 1;
        regRegistrationDateField.setEditable(false);
        regRegistrationDateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        regRegistrationDateField.setBorder(fieldBorder);
        OwnerDetails.add(regRegistrationDateField, gbc);
        gbc.gridx = 2;
        JLabel regRegistrarLabel = new JLabel("Registrar:");
        regRegistrarLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(regRegistrarLabel, gbc);
        gbc.gridx = 3;
        regRegistrarField.setEditable(false);
        regRegistrarField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        regRegistrarField.setBorder(fieldBorder);
        OwnerDetails.add(regRegistrarField, gbc);

        // Expiry Date (single field spanning two columns)
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(expiryLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        regDateField.setEditable(false);
        regDateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        regDateField.setBorder(fieldBorder);
        OwnerDetails.add(regDateField, gbc);
        gbc.gridwidth = 1;

        // --- Section Spacing ---
        gbc.gridy = row++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        OwnerDetails.add(Box.createVerticalStrut(8), gbc);
        gbc.gridwidth = 1;

        // --- Vehicle Details Title ---
        gbc.gridy = row++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        JLabel vehicleTitle = new JLabel("Vehicle Details");
        vehicleTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        OwnerDetails.add(vehicleTitle, gbc);
        gbc.gridwidth = 1;

        // Vehicle ID & Brand
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel vehicleIDLabel = new JLabel("Vehicle ID:");
        vehicleIDLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(vehicleIDLabel, gbc);
        gbc.gridx = 1;
        vehicleIDField.setEditable(false);
        vehicleIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleIDField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleIDField, gbc);
        gbc.gridx = 2;
        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(brandLabel, gbc);
        gbc.gridx = 3;
        vehicleBrandField.setEditable(false);
        vehicleBrandField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleBrandField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleBrandField, gbc);

        // Model & Type
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(modelLabel, gbc);
        gbc.gridx = 1;
        vehicleModelField.setEditable(false);
        vehicleModelField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleModelField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleModelField, gbc);
        gbc.gridx = 2;
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(typeLabel, gbc);
        gbc.gridx = 3;
        vehicleTypeField.setEditable(false);
        vehicleTypeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleTypeField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleTypeField, gbc);

        // Fuel Type & No. of Exhausts
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel fuelLabel = new JLabel("Fuel Type:");
        fuelLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(fuelLabel, gbc);
        gbc.gridx = 1;
        vehicleFuelTypeField.setEditable(false);
        vehicleFuelTypeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleFuelTypeField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleFuelTypeField, gbc);
        gbc.gridx = 2;
        JLabel exhaustLabel = new JLabel("No. of Exhausts:");
        exhaustLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(exhaustLabel, gbc);
        gbc.gridx = 3;
        vehicleNoOfExhaustField.setEditable(false);
        vehicleNoOfExhaustField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleNoOfExhaustField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleNoOfExhaustField, gbc);

        // Color & Manufacture Date
        gbc.gridy = row++;
        gbc.gridx = 0;
        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(colorLabel, gbc);
        gbc.gridx = 1;
        vehicleColorField.setEditable(false);
        vehicleColorField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleColorField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleColorField, gbc);
        gbc.gridx = 2;
        JLabel manuDateLabel = new JLabel("Manufacture Date:");
        manuDateLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        OwnerDetails.add(manuDateLabel, gbc);
        gbc.gridx = 3;
        vehicleManufactureDateField.setEditable(false);
        vehicleManufactureDateField.setFont(new Font("Tahoma", Font.PLAIN,
                                                     16));
        vehicleManufactureDateField.setBorder(fieldBorder);
        OwnerDetails.add(vehicleManufactureDateField, gbc);

        // --- Action Listeners ---
        fetchOwnerBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fetchOwnerDetailsByRegisID();
                }
            });

        ownerRegisIDField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fetchOwnerDetailsByRegisID();
                }
            });

        // On panel load, clear all fields and do not show any dialog!
        clearOwnerVehicleFields();

        OwnerDetails.revalidate();
        OwnerDetails.repaint();
    }


    // Helper to clear all fields

    private void clearOwnerVehicleFields() {
        ownerNameField.setText("");
        ownerGenderField.setText("");
        ownerAadharField.setText("");
        ownerPanField.setText("");
        ownerPhoneField.setText("");
        ownerAddressField.setText("");
        regRegistrationIDField.setText("");
        regLocationField.setText("");
        regRegistrationDateField.setText("");
        regRegistrarField.setText("");
        regDateField.setText("");
        vehicleIDField.setText("");
        vehicleBrandField.setText("");
        vehicleModelField.setText("");
        vehicleTypeField.setText("");
        vehicleFuelTypeField.setText("");
        vehicleNoOfExhaustField.setText("");
        vehicleColorField.setText("");
        vehicleManufactureDateField.setText("");
    }

    // Fetch and fill all details

    private void fetchOwnerDetailsByRegisID() {
        String regisID = ownerRegisIDField.getText().trim();

        if (regisID.isEmpty()) {
            JOptionPane.showMessageDialog(OwnerDetails,
                                          "Please enter a Registration ID.",
                                          "Input Required",
                                          JOptionPane.WARNING_MESSAGE);
            return;
        }

        clearOwnerVehicleFields();

        ClerkOperationImpl clkOpImpl = new ClerkOperationImpl();

        // 1. Fetch registration
        RegistrationEO registration = clkOpImpl.findRegistrationByID(regisID);
        if (registration == null) {
            JOptionPane.showMessageDialog(OwnerDetails,
                                          "No registration found for this Registration ID.",
                                          "Not Found",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Fetch owner and vehicle using registration
        String ownerAadhar = registration.getOwnerAadhar().getOwnerAadhar();
        int vehicleID = registration.getVehicleID().getVehicleID();

        OwnerEO owner = clkOpImpl.findOwnerByAadhar(ownerAadhar);
        VehicleEO vehicle = clkOpImpl.findVehicleByID(vehicleID);

        // Fill registration details
        regRegistrationIDField.setText(registration.getRegistrationID());
        regLocationField.setText(registration.getLocation());
        regRegistrationDateField.setText(registration.getRegistrationDate() !=
                                         null ?
                                         registration.getRegistrationDate().toString() :
                                         "");
        regRegistrarField.setText(registration.getRegistrar());
        regDateField.setText(registration.getRegistrationDate() != null ?
                             registration.getRegistrationDate().toString() :
                             "");

        // Fill owner details
        if (owner != null) {
            ownerNameField.setText(owner.getOwnerName());
            ownerGenderField.setText(owner.getGender());
            ownerAadharField.setText(owner.getOwnerAadhar());
            ownerPanField.setText(owner.getPancard());
            ownerPhoneField.setText(owner.getPhone().toString());
            ownerAddressField.setText(owner.getAddress());
        }

        // Fill vehicle details
        if (vehicle != null) {
            vehicleIDField.setText(String.valueOf(vehicle.getVehicleID()));
            vehicleBrandField.setText(vehicle.getVehicleBrand());
            vehicleModelField.setText(vehicle.getVehicleModel());
            vehicleTypeField.setText(vehicle.getVehicleType());
            vehicleFuelTypeField.setText(vehicle.getFuelType());
            vehicleNoOfExhaustField.setText(String.valueOf(vehicle.getNoOfExhaust()));
            vehicleColorField.setText(vehicle.getColor());
            vehicleManufactureDateField.setText(vehicle.getManufactureDate() !=
                                                null ?
                                                vehicle.getManufactureDate().toString() :
                                                "");
        }
    }

    private void buildViewOffencePanel() {
        ViewOffence.removeAll();

        // Add a bold titled border to the panel
        TitledBorder border =
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                            2),
                                             "View All Vehicle Offences",
                                             TitledBorder.LEFT,
                                             TitledBorder.TOP,
                                             new Font("Tahoma", Font.BOLD,
                                                      22));
        ViewOffence.setBorder(border);

        ViewOffence.setLayout(new BorderLayout(18, 18)); // More spacing

        // Column names for the offence table
        String[] columns =
        { "Vehicle Offence ID", "Registration ID", "Offence Type", "Place",
          "Date", "Time", "Reporter", "Status" };

        // Initialize table model
        viewOffenceTableModel = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make table read-only
                }
            };

        // Initialize table with custom renderer for status
        viewOffenceTable = new JTable(viewOffenceTableModel) {
                @Override
                public Component prepareRenderer(TableCellRenderer renderer,
                                                 int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);
                    // Get status value from model (last column)
                    Object statusObj = getModel().getValueAt(row, 7);
                    if (statusObj != null) {
                        String status = statusObj.toString();
                        if ("Not Paid".equals(status)) {
                            c.setBackground(new Color(255, 204,
                                                      204)); // Light red
                        } else if ("Paid".equals(status)) {
                            c.setBackground(new Color(204, 255,
                                                      204)); // Pale green
                        } else {
                            c.setBackground(Color.WHITE);
                        }
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                    return c;
                }
            };

        viewOffenceTable.setRowHeight(30);
        viewOffenceTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        viewOffenceTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD,
                                                           18));
        viewOffenceTable.setFillsViewportHeight(true);

        // Add scroll pane with border and padding
        JScrollPane scrollPane = new JScrollPane(viewOffenceTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,
                                                                                             1),
                                                              "All Vehicle Offences",
                                                              TitledBorder.LEFT,
                                                              TitledBorder.TOP,
                                                              new Font("Tahoma",
                                                                       Font.BOLD,
                                                                       16)));
        scrollPane.setPreferredSize(new Dimension(1000, 420));

        // Button panel
        JPanel buttonPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 16));
        JButton generateReportBtn = new JButton("Generate Reports");
        JButton reportOnTypeBtn = new JButton("My Report");
        generateReportBtn.setPreferredSize(new Dimension(220, 40));
        reportOnTypeBtn.setPreferredSize(new Dimension(270, 40));
        generateReportBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        reportOnTypeBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        buttonPanel.add(generateReportBtn);
        buttonPanel.add(reportOnTypeBtn);

        // Add to main panel
        ViewOffence.add(scrollPane, BorderLayout.CENTER);
        ViewOffence.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions (provide your own implementations)
        generateReportBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO: Implement your report generation logic
                    showReportPanel(ViewOffence);
                    JOptionPane.showMessageDialog(ViewOffence,
                                                  "Report generated!");
                }
            });
        reportOnTypeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String designationId = currentAdmin.getDesignationID();
                    // Adjust these paths and parameter name as needed
                    String rptDesignPath = "D:\\Training\\Birt2 Workspace\\TMSReports\\MY_CLK_REPORT.rptdesign";
                    String outputPath = "D:\\Training\\Birt2 Workspace\\TMSReportsOutput\\designation.pdf";
                    String paramName = "reporter"; // Use the actual parameter name expected by your BIRT report
                    
                    ExecuteReport ex = new ExecuteReport();
                    ex.executeReport1(rptDesignPath, outputPath, designationId, paramName);

                    // Optionally open the generated report
                    Runtime rTime = Runtime.getRuntime();
                    String command = "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe " + outputPath;
                    rTime.exec(command);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ViewOffence, "Failed to generate report: " + ex.getMessage());
                }
                JOptionPane.showMessageDialog(ViewOffence, "Report for specified type generated!");

            }
        });

    }

    // Method to show the panel with 5 toggle buttons
    public void showReportPanel(Component parent) {
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        reportPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        Dimension btnSize = new Dimension(220, 35);

        JToggleButton btn1 = new JToggleButton("Reports by Registration ID");
        JToggleButton btn2 = new JToggleButton("Reports by Offence Type");
        JToggleButton btn3 = new JToggleButton("Reports by Date Range");
        JToggleButton btn4 = new JToggleButton("Reports of Police Officer");
        JToggleButton btn5 = new JToggleButton("All Vehicle Offences Reports");

        for (JToggleButton btn : new JToggleButton[]{btn1, btn2, btn3, btn4, btn5}) {
                btn.setMaximumSize(btnSize); btn.setAlignmentX(Component.CENTER_ALIGNMENT); 
                btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18)); 
                reportPanel.add(btn); reportPanel.add(Box.createVerticalStrut(18));
            }
            reportPanel.remove(reportPanel.getComponentCount() - 1);

        // Attach ActionListeners to each button for BIRT report generation
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showParamDialogAndGenerate(ViewOffence,
                    "Enter Registration ID:",
                    "registration_id",
                    "D:\\Training\\Birt2 Workspace\\TMSReports\\REGISTRATION_REPORT.rptdesign",
                    "D:\\Training\\Birt2 Workspace\\TMSReportsOutput\\registration.pdf"
                );
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showParamDialogAndGenerate(ViewOffence,
                    "Enter Offence Type:",
                    "offence_type",
                    "D:\\Training\\Birt2 Workspace\\TMSReports\\OFFENCE_TYPE_REPORT.rptdesign",
                    "D:\\Training\\Birt2 Workspace\\TMSReportsOutput\\offencetype.pdf"
                );
            }
        });
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDateRangeDialogAndGenerate(ViewOffence,
                    "D:\\Training\\Birt2 Workspace\\TMSReports\\DATE_RANGE_REPORT.rptdesign",
                    "D:\\Training\\Birt2 Workspace\\TMSReportsOutput\\daterange.pdf"
                );
            }
        });
        btn4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    generateBirtReport("D:\\Training\\Birt2 Workspace\\TMSReports\\POLICE_REPORT.rptdesign",
                                       "D:\\Training\\Birt2 Workspace\\TMSReportsOutput\\police.pdf");
                }
            });
        btn5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    generateBirtReport("D:\\Training\\Birt2 Workspace\\TMSReports\\ALL_OFFENCES_REPORT.rptdesign",
                                       "D:\\Training\\Birt2 Workspace\\TMSReportsOutput\\alloffences.pdf");
                }
            });

        JOptionPane.showMessageDialog(parent, reportPanel,
                                      "REPORT SELECTION MENU",
                                      JOptionPane.PLAIN_MESSAGE);
    }
    
    // For single parameter (registration_id or offence_type)
    private void showParamDialogAndGenerate(Component parent, String label, String paramName, String rptDesignPath, String outputPath) {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        panel.add(new JLabel(label));
        JTextField textField = new JTextField(15);
        panel.add(textField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Input Required", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String paramValue = textField.getText().trim();
            if (!paramValue.isEmpty()) {
                generateBirtReportWithParam(rptDesignPath, outputPath, paramValue, paramName);
            } else {
                JOptionPane.showMessageDialog(parent, "Input cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // For date range (from_date and to_date)
    private void showDateRangeDialogAndGenerate(Component parent, String rptDesignPath, String outputPath) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("From Date (yyyy-MM-dd):"));
        JTextField fromDateField = new JTextField(10);
        panel.add(fromDateField);

        panel.add(new JLabel("To Date (yyyy-MM-dd):"));
        JTextField toDateField = new JTextField(10);
        panel.add(toDateField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Enter Date Range", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String fromDate = fromDateField.getText().trim();
            String toDate = toDateField.getText().trim();
            if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                generateBirtReportWithParam(rptDesignPath, outputPath, fromDate + "," + toDate, "from_date,to_date");
            } else {
                JOptionPane.showMessageDialog(parent, "Both dates are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void generateBirtReportWithParam(String rptDesignPath, String outputPath, String paramValue, String paramName) {
        try {
            ExecuteReport ex = new ExecuteReport();
            if ("from_date,to_date".equals(paramName)) {
                String[] dates = paramValue.split(",");
                ex.executeReport1(rptDesignPath, outputPath, dates[0], "from_date");
                ex.executeReport1(rptDesignPath, outputPath, dates[1], "to_date");
            } else {
                ex.executeReport1(rptDesignPath, outputPath, paramValue, paramName);
            }
            Runtime rTime = Runtime.getRuntime();
            String command = "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe " + outputPath;
            rTime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage());
        }
    }

    // Stub for BIRT report generation logic
    private void generateBirtReport(String rptDesignPath, String outputPath) {
        try {
            ExecuteReport ex = new ExecuteReport();
            ex.executeReport(rptDesignPath, outputPath);
            Runtime rTime = Runtime.getRuntime();
            String command = "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe " + outputPath;
            rTime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate report: " + e.getMessage());
        }
    }


    private void loadAllVehicleOffences() {
        viewOffenceTableModel.setRowCount(0); // Clear existing rows

        ClerkOperationImpl clerkOpImpl = new ClerkOperationImpl();
        List<VehicleOffenceEO> offences = clerkOpImpl.findAllVehicleOffence();

        if (offences == null || offences.isEmpty()) {
            JOptionPane.showMessageDialog(ViewOffence,
                                          "No vehicle offences found.",
                                          "Information",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (VehicleOffenceEO offence : offences) {
            // Defensive null checks
            String regisID =
                (offence.getRegistrationID() != null && offence.getRegistrationID().getRegistrationID() !=
                 null) ? offence.getRegistrationID().getRegistrationID() :
                "N/A";
            int vehicleOffenceID = offence.getVehicleOffenceID();
            int offenceTypeID =
                (offence.getOffenceID() != null) ? offence.getOffenceID().getOffenceID() :
                0;
            String offenceType = "N/A";
            try {
                OffenceTypesEO typeEO =
                    clerkOpImpl.findOffenceTypeByID(offenceTypeID);
                if (typeEO != null && typeEO.getOffenceType() != null) {
                    offenceType = typeEO.getOffenceType();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ViewOffence,
                                              "Invalid Offence ID.",
                                              "Information",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String place =
                offence.getLocation() != null ? offence.getLocation() : "N/A";
            String date =
                (offence.getDate() != null) ? offence.getDate().toString() :
                "N/A";
            String time =
                (offence.getTime() != null) ? offence.getTime().toString() :
                "N/A";
            String reporter =
                (offence.getReporter() != null) ? offence.getReporter() :
                "N/A";
            int statusInt = offence.getStatus();
            String statusText = (statusInt == 1) ? "Paid" : "Not Paid";

            Object[] row =
            { vehicleOffenceID, regisID, offenceType, place, date, time,
              reporter, statusText };
            viewOffenceTableModel.addRow(row);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new ClerkDashboardUI().setVisible(true);
                }
            });
    }
}
