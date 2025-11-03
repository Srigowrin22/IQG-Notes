package training.iqgateway.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.entities.RegistrationEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.operations.impl.OwnerOperationImpl;

public class OwnerDashboardUI extends JFrame {

    // Components
    private JButton profileBtn = new JButton("Profile");
    private JButton logoutbtn = new JButton("LOGOUT");
    private JLabel helloUser = new JLabel();
    private JComboBox regisCombo = new JComboBox();
    private JTabbedPane tabbedPane = new JTabbedPane();

    // Panels
    private JPanel vehicleDetailsPanel = new JPanel(new GridBagLayout());
    private JPanel offenceHistoryPanel = new JPanel(new BorderLayout());
    private JPanel pendingOffencesPanel = new JPanel(new BorderLayout());
    private JPanel clearOffencesPanel = new JPanel(new BorderLayout());

    // Owner object (to be set on login or fetch)
    private OwnerEO currentOwner;
    private RegistrationEO selectedRegistration;
    private VehicleEO selectedVehicle;

    // User operations implementation (data fetch)
    private OwnerOperationImpl ownerOpImpl = new OwnerOperationImpl();

    // Tables and models
    private DefaultTableModel offenceHistoryTableModel =
        new DefaultTableModel();
    private JTable offenceHistoryTable;

    private DefaultTableModel clearOffenceTableModel = new DefaultTableModel();
    private JTable clearOffenceTable;
    private JLabel totalAmountLabel = new JLabel("Total: 0");

    // Text fields references for Vehicle Details panel
    private JTextField tfRegID, tfLocation, tfRegistrar, tfRegDate;
    private JTextField tfVehicleID, tfBrand, tfModel, tfType, tfFuel, tfExhaust, tfColor, tfDate;

    // Pending offences container panel
    private JPanel pendingOffencesContainer;


    public OwnerDashboardUI() {
        this(new OwnerEO());
    }

    public OwnerDashboardUI(OwnerEO ownerEO) {
        setTitle("USER DASHBOARD");
        this.currentOwner = ownerEO; // Set owner first
        jbInit(); // Initialize UI after setting owner
        loadRegistrationIDs();  
    }

    private void jbInit() {
        // Full screen and main panel with padding
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 800));
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setContentPane(mainPanel);

        // Top panel with Profile, Registration ComboBox, and Logout button at top right
        JPanel topPanel = new JPanel(new BorderLayout());

        // Left panel: Profile button and helloUser label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        profileBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        profileBtn.setPreferredSize(new Dimension(110, 40));
        leftPanel.add(profileBtn);

        helloUser.setFont(new Font("Tahoma", Font.BOLD, 24));
        helloUser.setText("Hello, " + currentOwner.getOwnerName() + "!");
        leftPanel.add(helloUser);

        // Add 0.5cm (~12px) spacing below hello message
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        // Center panel: Registration ID label and combo box centered
        JPanel centerPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel regisLabel = new JLabel("Registration ID:");
        regisLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        centerPanel.add(regisLabel);

        regisCombo.setFont(new Font("Tahoma", Font.BOLD, 18));
        centerPanel.add(regisCombo);

        // Right panel: Logout button
        JPanel rightPanel =
            new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        logoutbtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        logoutbtn.setPreferredSize(new Dimension(120, 40));
        rightPanel.add(logoutbtn);

        // Add sub-panels to top panel
        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Initialize all panels (with new design)
        buildVehicleDetailsPanel();
        buildOffenceHistoryPanel();
        buildPendingOffencesPanel();
        buildClearOffencesPanel();

        // Add tabs with bold, large font
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 18));
        tabbedPane.addTab("    Vehicle Details      ", vehicleDetailsPanel);
        tabbedPane.addTab("    Offence History      ", offenceHistoryPanel);
        tabbedPane.addTab("    Pending Offences     ", pendingOffencesPanel);
        tabbedPane.addTab("    Clear Offences   ", clearOffencesPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Listeners - using anonymous inner classes
        profileBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openProfileUI();
                }
            });

        

        regisCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (regisCombo.getSelectedItem() != null) {
                        String regisID = (String)regisCombo.getSelectedItem();
                        loadRegistrationDetails(regisID);
                        loadVehicleDetails(regisID);
                        loadOffenceHistory(regisID);
                        loadPendingOffences(regisID);
                        loadClearOffences(regisID);
                    }
                }
            });
        
        logoutbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    OwnerDashboardUI.this.dispose();
                    JOptionPane.showMessageDialog(OwnerDashboardUI.this,
                                                  "Logged OUT");
                    OwnerLoginUI loginUI = new OwnerLoginUI();
                    loginUI.setVisible(true);
                }
            });
    }
    
    // 2) Populate registration combo box with regisIDs for currentOwner's aadhar

    private void loadRegistrationIDs() {
        regisCombo.removeAllItems();
        if (currentOwner == null) {
            return;
        }
        List<RegistrationEO> regisEOList = new ArrayList<RegistrationEO>();
        regisEOList =
                ownerOpImpl.findRegistrationByAadhar(currentOwner.getOwnerAadhar());
        for (RegistrationEO regisEO : regisEOList) {
            regisCombo.addItem(regisEO.getRegistrationID());
        }
    }
    
    // 3) Vehicle Details Panel: stacked Registration Details and Vehicle Details
    private void buildVehicleDetailsPanel() {
    vehicleDetailsPanel.removeAll();
    vehicleDetailsPanel.setLayout(new GridBagLayout());

    // Main panel border/title
    TitledBorder mainBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        "Vehicle & Registration Details",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Tahoma", Font.BOLD, 22)
    );
    vehicleDetailsPanel.setBorder(mainBorder);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(20, 20, 20, 20); // More spacing
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = 1.0;

    // --- Registration Details panel ---
    JPanel regDetailsPanel = new JPanel(new GridBagLayout());
    TitledBorder regBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
        "Registration Details",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Tahoma", Font.BOLD, 18)
    );
    regDetailsPanel.setBorder(regBorder);

    GridBagConstraints regGbc = new GridBagConstraints();
    regGbc.insets = new Insets(12, 12, 12, 12);
    regGbc.fill = GridBagConstraints.HORIZONTAL;
    regGbc.anchor = GridBagConstraints.WEST;

    Font labelFont = new Font("Tahoma", Font.BOLD, 16);
    Font fieldFont = new Font("Tahoma", Font.PLAIN, 16);
    Border fieldBorder = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
        BorderFactory.createEmptyBorder(5, 8, 5, 8)
    );

    JLabel lblRegID = new JLabel("Registration ID:");
    lblRegID.setFont(labelFont);
    JTextField tfRegIDLocal = new JTextField(20);
    tfRegIDLocal.setEditable(false);
    tfRegIDLocal.setFont(fieldFont);
    tfRegIDLocal.setBorder(fieldBorder);

    JLabel lblRegLoc = new JLabel("Location:");
    lblRegLoc.setFont(labelFont);
    JTextField tfRegLocLocal = new JTextField(20);
    tfRegLocLocal.setEditable(false);
    tfRegLocLocal.setFont(fieldFont);
    tfRegLocLocal.setBorder(fieldBorder);

    JLabel lblRegt = new JLabel("Registrar:");
    lblRegt.setFont(labelFont);
    JTextField tfRegt = new JTextField(20);
    tfRegt.setEditable(false);
    tfRegt.setFont(fieldFont);
    tfRegt.setBorder(fieldBorder);

    JLabel lblRegDate = new JLabel("Registration Date:");
    lblRegDate.setFont(labelFont);
    JTextField tfRegDateLocal = new JTextField(20);
    tfRegDateLocal.setEditable(false);
    tfRegDateLocal.setFont(fieldFont);
    tfRegDateLocal.setBorder(fieldBorder);

    int y = 0;
    regGbc.gridx = 0; regGbc.gridy = y;
    regDetailsPanel.add(lblRegID, regGbc);
    regGbc.gridx = 1;
    regDetailsPanel.add(tfRegIDLocal, regGbc);

    regGbc.gridx = 0; regGbc.gridy = ++y;
    regDetailsPanel.add(lblRegLoc, regGbc);
    regGbc.gridx = 1;
    regDetailsPanel.add(tfRegLocLocal, regGbc);

    regGbc.gridx = 0; regGbc.gridy = ++y;
    regDetailsPanel.add(lblRegt, regGbc);
    regGbc.gridx = 1;
    regDetailsPanel.add(tfRegt, regGbc);

    regGbc.gridx = 0; regGbc.gridy = ++y;
    regDetailsPanel.add(lblRegDate, regGbc);
    regGbc.gridx = 1;
    regDetailsPanel.add(tfRegDateLocal, regGbc);

    // --- Vehicle Details panel ---
    JPanel vehicleDetailsSubPanel = new JPanel(new GridBagLayout());
    TitledBorder vehBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
        "Vehicle Details",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Tahoma", Font.BOLD, 18)
    );
    vehicleDetailsSubPanel.setBorder(vehBorder);

    GridBagConstraints vehGbc = new GridBagConstraints();
    vehGbc.insets = new Insets(12, 12, 12, 12);
    vehGbc.fill = GridBagConstraints.HORIZONTAL;
    vehGbc.anchor = GridBagConstraints.WEST;

    JLabel lblVehicleID = new JLabel("Vehicle ID:");
    lblVehicleID.setFont(labelFont);
    JTextField tfVehicleIDLocal = new JTextField(20);
    tfVehicleIDLocal.setEditable(false);
    tfVehicleIDLocal.setFont(fieldFont);
    tfVehicleIDLocal.setBorder(fieldBorder);

    JLabel lblMake = new JLabel("Make:");
    lblMake.setFont(labelFont);
    JTextField tfMakeLocal = new JTextField(20);
    tfMakeLocal.setEditable(false);
    tfMakeLocal.setFont(fieldFont);
    tfMakeLocal.setBorder(fieldBorder);

    JLabel lblModel = new JLabel("Model:");
    lblModel.setFont(labelFont);
    JTextField tfModelLocal = new JTextField(20);
    tfModelLocal.setEditable(false);
    tfModelLocal.setFont(fieldFont);
    tfModelLocal.setBorder(fieldBorder);

    JLabel lblType = new JLabel("Type:");
    lblType.setFont(labelFont);
    JTextField tfTypeLocal = new JTextField(20);
    tfTypeLocal.setEditable(false);
    tfTypeLocal.setFont(fieldFont);
    tfTypeLocal.setBorder(fieldBorder);

    JLabel lblFuelType = new JLabel("Fuel Type:");
    lblFuelType.setFont(labelFont);
    JTextField tfFuelTypeLocal = new JTextField(20);
    tfFuelTypeLocal.setEditable(false);
    tfFuelTypeLocal.setFont(fieldFont);
    tfFuelTypeLocal.setBorder(fieldBorder);

    JLabel lblExhaust = new JLabel("Exhaust:");
    lblExhaust.setFont(labelFont);
    JTextField tfExhaustLocal = new JTextField(20);
    tfExhaustLocal.setEditable(false);
    tfExhaustLocal.setFont(fieldFont);
    tfExhaustLocal.setBorder(fieldBorder);

    JLabel lblColor = new JLabel("Color:");
    lblColor.setFont(labelFont);
    JTextField tfColorLocal = new JTextField(20);
    tfColorLocal.setEditable(false);
    tfColorLocal.setFont(fieldFont);
    tfColorLocal.setBorder(fieldBorder);

    JLabel lblMDate = new JLabel("Manufactured Date:");
    lblMDate.setFont(labelFont);
    JTextField tfMDateLocal = new JTextField(20);
    tfMDateLocal.setEditable(false);
    tfMDateLocal.setFont(fieldFont);
    tfMDateLocal.setBorder(fieldBorder);

    y = 0;
    vehGbc.gridx = 0; vehGbc.gridy = y;
    vehicleDetailsSubPanel.add(lblVehicleID, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfVehicleIDLocal, vehGbc);

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblMake, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfMakeLocal, vehGbc);

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblModel, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfModelLocal, vehGbc);

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblType, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfTypeLocal, vehGbc);

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblFuelType, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfFuelTypeLocal, vehGbc);        

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblExhaust, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfExhaustLocal, vehGbc);        

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblColor, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfColorLocal, vehGbc);

    vehGbc.gridx = 0; vehGbc.gridy = ++y;
    vehicleDetailsSubPanel.add(lblMDate, vehGbc);
    vehGbc.gridx = 1;
    vehicleDetailsSubPanel.add(tfMDateLocal, vehGbc);

    // Add panels vertically in vehicleDetailsPanel with spacing
    GridBagConstraints mainGbc = new GridBagConstraints();
    mainGbc.gridx = 0;
    mainGbc.gridy = 0;
    mainGbc.weightx = 1.0;
    mainGbc.fill = GridBagConstraints.HORIZONTAL;
    mainGbc.anchor = GridBagConstraints.NORTHWEST;
    vehicleDetailsPanel.add(regDetailsPanel, mainGbc);

    mainGbc.gridy = 1;
    mainGbc.insets = new Insets(24, 0, 0, 0); // Extra space between panels
    vehicleDetailsPanel.add(vehicleDetailsSubPanel, mainGbc);

        // Store text fields for updating on selection
        this.tfRegID = tfRegIDLocal;     
        this.tfLocation = tfRegLocLocal;
        this.tfRegistrar = tfRegt;
        this.tfRegDate = tfRegDateLocal;
        
        this.tfVehicleID = tfVehicleIDLocal;
        this.tfBrand = tfMakeLocal;
        this.tfModel = tfModelLocal;
        this.tfType = tfTypeLocal;
        this.tfFuel = tfFuelTypeLocal;
        this.tfExhaust = tfExhaustLocal;
        this.tfColor = tfColorLocal;
        this.tfDate = tfMDateLocal;
    }
    
    // Load registration details into Vehicle Details panel

    private void loadRegistrationDetails(String regisID) {
        
        selectedRegistration = new RegistrationEO();
        selectedRegistration = ownerOpImpl.findRegistrationByID(regisID);
        
        tfRegID.setText(selectedRegistration.getRegistrationID());
        tfLocation.setText(selectedRegistration.getLocation());
        tfRegistrar.setText(selectedRegistration.getRegistrar());
        tfRegDate.setText(selectedRegistration.getRegistrationDate()+"");
    }

    // Load vehicle details into Vehicle Details panel

    private void loadVehicleDetails(String regisID) {
        
        Integer vehicleID = selectedRegistration.getVehicleID().getVehicleID();
        selectedVehicle = new VehicleEO();
        selectedVehicle = ownerOpImpl.findVehicleByID(vehicleID);
        
        tfVehicleID.setText(selectedVehicle.getVehicleID()+"");
        tfBrand.setText(selectedVehicle.getVehicleBrand());
        tfModel.setText(selectedVehicle.getVehicleModel());
        tfType.setText(selectedVehicle.getVehicleType());
        tfFuel.setText(selectedVehicle.getFuelType());
        tfExhaust.setText(selectedVehicle.getNoOfExhaust()+"");
        tfColor.setText(selectedVehicle.getColor());
        tfDate.setText(selectedVehicle.getManufactureDate()+"");

    }
    
    private void buildOffenceHistoryPanel() {
    offenceHistoryPanel.removeAll();

    // Add a bold titled border to the panel
    TitledBorder border = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        "Offence History",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Tahoma", Font.BOLD, 22)
    );
    offenceHistoryPanel.setBorder(border);

    // Table columns
    String[] columns = { "Offence Type", "Place", "Date", "Time", "Status" };
    offenceHistoryTableModel = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    offenceHistoryTable = new JTable(offenceHistoryTableModel);
    offenceHistoryTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
    offenceHistoryTable.setRowHeight(28);
    offenceHistoryTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
    offenceHistoryTable.setFillsViewportHeight(true);

    JScrollPane scrollPane = new JScrollPane(offenceHistoryTable);
    scrollPane.setPreferredSize(new Dimension(1000, 400));
    scrollPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12)); // Padding inside panel

    offenceHistoryPanel.setLayout(new BorderLayout(10, 10));
    offenceHistoryPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadOffenceHistory(String regisID) {
        offenceHistoryTableModel.setRowCount(0);
        List<VehicleOffenceEO> VehOffList = ownerOpImpl.findVehicleOffenceByRegisID(regisID);

       for (VehicleOffenceEO vehOff : VehOffList) {
           if (vehOff.getStatus() == 1) {
               int offenceID = vehOff.getOffenceID().getOffenceID();
               
               OffenceTypesEO offTypesEO = new OffenceTypesEO();
               offTypesEO = ownerOpImpl.findOffenceTypeByID(offenceID);
               
               String offenceType = offTypesEO.getOffenceType(); 
               String place = vehOff.getLocation();
               java.sql.Date date = vehOff.getDate();
               java.sql.Time time = vehOff.getTime();
               String status = (vehOff.getStatus() == 1? "Paid" : "Not Paid");

               String dateStr = new java.text.SimpleDateFormat("dd-MMM-yyyy").format(date);

               Object[] row = {offenceType, place, dateStr, time, status };
               offenceHistoryTableModel.addRow(row);
           }
       }
        offenceHistoryPanel.revalidate();
        offenceHistoryPanel.repaint();
    }


    // 5) Pending Offences panel: show offences with status=0 including images

    private void buildPendingOffencesPanel() {
        pendingOffencesPanel.removeAll();
        pendingOffencesPanel.setLayout(new BoxLayout(pendingOffencesPanel,
                                                     BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane();
        pendingOffencesPanel.add(scrollPane);
        JPanel offencesContainer = new JPanel();
        offencesContainer.setLayout(new BoxLayout(offencesContainer,
                                                  BoxLayout.Y_AXIS));
        scrollPane.setViewportView(offencesContainer);
        this.pendingOffencesContainer = offencesContainer;
    }
    
    private void loadPendingOffences(String regisID) {
    if (pendingOffencesContainer == null)
        return;
    pendingOffencesContainer.removeAll();
    pendingOffencesContainer.setLayout(new BoxLayout(pendingOffencesContainer, BoxLayout.Y_AXIS));
    pendingOffencesContainer.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

    List<VehicleOffenceEO> VehOffList = ownerOpImpl.findVehicleOffenceByRegisID(regisID);

    Font titleFont = new Font("Tahoma", Font.BOLD, 18);
    Font labelFont = new Font("Tahoma", Font.BOLD, 15);
    Font detailFont = new Font("Tahoma", Font.PLAIN, 15);

    for (VehicleOffenceEO vehOff : VehOffList) {
        if (vehOff.getStatus() == 0) {
            int offenceID = vehOff.getOffenceID().getOffenceID();
            OffenceTypesEO offTypesEO = ownerOpImpl.findOffenceTypeByID(offenceID);

            JPanel offencePanel = new JPanel(new BorderLayout(15, 15));
            TitledBorder offenceBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                "Offence Type: " + offTypesEO.getOffenceType(),
                TitledBorder.LEFT,
                TitledBorder.TOP,
                titleFont
            );
            offencePanel.setBorder(offenceBorder);
            offencePanel.setBackground(Color.WHITE);

            JLabel offenceDetails = new JLabel(
                "<html><b>Place:</b> " + vehOff.getLocation() + "<br>" +
                "<b>Date:</b> " + vehOff.getDate() + "<br>" +
                "<b>Time:</b> " + vehOff.getTime() + "<br>" +
                "<b>Penalty Amount:</b> <span style='color:#B22222;'> &#8377;" + offTypesEO.getPenaltyAmt() + "</span></html>"
            );
            offenceDetails.setFont(detailFont);
            offencePanel.add(offenceDetails, BorderLayout.CENTER);

            JPanel proofsPanel = new JPanel();
            proofsPanel.setLayout(new BoxLayout(proofsPanel, BoxLayout.X_AXIS));
            proofsPanel.setBackground(Color.WHITE);

            // Proof 1
            JLabel proof1Label = new JLabel();
            proof1Label.setPreferredSize(new Dimension(150, 100));
            proof1Label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            proof1Label.setHorizontalAlignment(SwingConstants.CENTER);
            proof1Label.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon icon1 = getImageIconFromBytes(vehOff.getProof1(), 150, 100);
            if (icon1 != null) {
                proof1Label.setIcon(icon1);
                proof1Label.setText("");
            } else {
                proof1Label.setIcon(getDefaultIcon(150, 100));
                proof1Label.setText("No Image");
                proof1Label.setFont(labelFont);
            }
            proofsPanel.add(proof1Label);
            proofsPanel.add(Box.createHorizontalStrut(14)); // Space between images

            // Proof 2
            JLabel proof2Label = new JLabel();
            proof2Label.setPreferredSize(new Dimension(150, 100));
            proof2Label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            proof2Label.setHorizontalAlignment(SwingConstants.CENTER);
            proof2Label.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon icon2 = getImageIconFromBytes(vehOff.getProof2(), 150, 100);
            if (icon2 != null) {
                proof2Label.setIcon(icon2);
                proof2Label.setText("");
            } else {
                proof2Label.setIcon(getDefaultIcon(150, 100));
                proof2Label.setText("No Image");
                proof2Label.setFont(labelFont);
            }
            proofsPanel.add(proof2Label);

            offencePanel.add(proofsPanel, BorderLayout.EAST);

            // Add some vertical spacing between offence panels
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setOpaque(false);
            wrapper.add(offencePanel, BorderLayout.CENTER);
            wrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

            pendingOffencesContainer.add(wrapper);
        }
    }
    pendingOffencesContainer.revalidate();
    pendingOffencesContainer.repaint();
    }
    
    private void buildClearOffencesPanel() {
    clearOffencesPanel.removeAll();
    clearOffencesPanel.setLayout(new BorderLayout(15, 15));

    // Panel border/title
    TitledBorder border = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        "Clear Offences",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Tahoma", Font.BOLD, 22)
    );
    clearOffencesPanel.setBorder(border);

    // Table columns
    String[] columns = {
        "Select", "VehicleOffence ID", "Offence Type", "Location",
        "Date", "Time", "Status", "Penalty"
    };
    clearOffenceTableModel = new DefaultTableModel(columns, 0) {
        @Override
        public Class<?> getColumnClass(int col) {
            return col == 0 ? Boolean.class : String.class;
        }
        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 0;
        }
    };

    clearOffenceTable = new JTable(clearOffenceTableModel);
    clearOffenceTable.setRowHeight(28);
    clearOffenceTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
    clearOffenceTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
    clearOffenceTable.setFillsViewportHeight(true);

    JScrollPane scrollPane = new JScrollPane(clearOffenceTable);
    scrollPane.setPreferredSize(new Dimension(1000, 400));
    scrollPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12)); // Padding inside panel

    clearOffencesPanel.add(scrollPane, BorderLayout.CENTER);

    // Bottom panel with total and pay button
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 16));
    totalAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
    JButton payBtn = new JButton("PAY");
    payBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
    payBtn.setPreferredSize(new Dimension(120, 40));
    bottomPanel.add(totalAmountLabel);
    bottomPanel.add(payBtn);
    clearOffencesPanel.add(bottomPanel, BorderLayout.SOUTH);

    clearOffenceTableModel.addTableModelListener(new javax.swing.event.TableModelListener() {
        public void tableChanged(javax.swing.event.TableModelEvent e) {
            updateTotalAmount();
        }
    });

    payBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            openPaymentUI(selectedRegistration.getRegistrationID());
        }
    });
    }
    
    private void loadClearOffences(String regisID) {
        clearOffenceTableModel.setRowCount(0);
        List<VehicleOffenceEO> VehOffList = ownerOpImpl.findVehicleOffenceByRegisID(regisID);

        for (VehicleOffenceEO vehOff : VehOffList) {
           if (vehOff.getStatus() == 0) {
               int offenceID = vehOff.getOffenceID().getOffenceID();
               
               OffenceTypesEO offTypesEO = new OffenceTypesEO();
               offTypesEO = ownerOpImpl.findOffenceTypeByID(offenceID);
               
               Integer VehOffenceID = vehOff.getVehicleOffenceID();
               String offenceType = offTypesEO.getOffenceType(); 
               String place = vehOff.getLocation();
               java.sql.Date date = vehOff.getDate();
               String dateStr = new java.text.SimpleDateFormat("dd-MMM-yyyy").format(date);
               java.sql.Time time = vehOff.getTime();
               String status = (vehOff.getStatus() == 1? "Paid" : "Not Paid");
               Integer penalty = offTypesEO.getPenaltyAmt();

               Object[] row = {false, VehOffenceID, offenceType, place, dateStr, time, status, penalty };
               clearOffenceTableModel.addRow(row);
           }
        }
        updateTotalAmount();
        clearOffencesPanel.revalidate();
        clearOffencesPanel.repaint();
    }
    
    private void updateTotalAmount() {
        int total = 0;
        for (int i = 0; i < clearOffenceTableModel.getRowCount(); i++) {
            Boolean selected = (Boolean) clearOffenceTableModel.getValueAt(i, 0);
            if (selected != null && selected) {
                Integer penalty = (Integer) clearOffenceTableModel.getValueAt(i, 7);
                total += penalty;
            }
        }
        totalAmountLabel.setText("Total: " + "\u20B9" + total);
    }

    private void openProfileUI() {
        final JDialog profileDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Profile", true);
        profileDialog.setSize(800, 600); // Increased dialog size
        profileDialog.setLocationRelativeTo(this);
        profileDialog.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font labelFont = new Font("Tahoma", Font.BOLD, 18);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 18);
        
        // Use columns instead of fixed width for better responsiveness
        final int TEXT_FIELD_COLUMNS = 30;
        int FIELD_HEIGHT = 34;

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        mainPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow field to expand
        final JTextField nameField = new JTextField(currentOwner.getOwnerName(), TEXT_FIELD_COLUMNS);
        nameField.setFont(fieldFont);
        mainPanel.add(nameField, gbc);

        // Gender
        gbc.gridx = 0; 
        gbc.gridy++;
        gbc.weightx = 0; // Reset for label
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        mainPanel.add(genderLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        final JComboBox genderCombo = new JComboBox(new String[]{"M", "F", "NA"});
        genderCombo.setFont(fieldFont);
        genderCombo.setSelectedItem(currentOwner.getGender());
        genderCombo.setPreferredSize(new Dimension(200, FIELD_HEIGHT));
        mainPanel.add(genderCombo, gbc);

        // Aadhar (non-editable)
        gbc.gridx = 0; 
        gbc.gridy++;
        gbc.weightx = 0;
        JLabel aadharLabel = new JLabel("Aadhar:");
        aadharLabel.setFont(labelFont);
        mainPanel.add(aadharLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        final JTextField aadharField = new JTextField(currentOwner.getOwnerAadhar(), TEXT_FIELD_COLUMNS);
        aadharField.setFont(fieldFont);
        aadharField.setEditable(false); // Non-editable
        mainPanel.add(aadharField, gbc);

        // Pancard (non-editable if not empty)
        gbc.gridx = 0; 
        gbc.gridy++;
        gbc.weightx = 0;
        JLabel pancardLabel = new JLabel("Pancard:");
        pancardLabel.setFont(labelFont);
        mainPanel.add(pancardLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        String pancard = currentOwner.getPancard();
        final JTextField pancardField = new JTextField(pancard != null ? pancard : "", TEXT_FIELD_COLUMNS);
        pancardField.setFont(fieldFont);
        if (pancard == null || pancard.isEmpty()) {
            pancardField.setEditable(true);
        } else {
            pancardField.setEditable(false);
        }
        mainPanel.add(pancardField, gbc);

        // Phone
        gbc.gridx = 0; 
        gbc.gridy++;
        gbc.weightx = 0;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(labelFont);
        mainPanel.add(phoneLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        final JTextField phoneField = new JTextField(String.valueOf(currentOwner.getPhone()), TEXT_FIELD_COLUMNS);
        phoneField.setFont(fieldFont);
        mainPanel.add(phoneField, gbc);

        // Address (multi-line support)
        gbc.gridx = 0; 
        gbc.gridy++;
        gbc.weightx = 0;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        mainPanel.add(addressLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        final JTextField addressField = new JTextField(currentOwner.getAddress(), TEXT_FIELD_COLUMNS);
        addressField.setFont(fieldFont);
        mainPanel.add(addressField, gbc);

        // Add vertical space
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(Box.createVerticalStrut(20), gbc);

        // Buttons panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        JButton updateBtn = new JButton("Update");
        JButton changePassBtn = new JButton("Change Password");
        updateBtn.setFont(labelFont);
        changePassBtn.setFont(labelFont);
        btnPanel.add(updateBtn);
        btnPanel.add(changePassBtn);

        profileDialog.add(mainPanel, BorderLayout.CENTER);
        profileDialog.add(btnPanel, BorderLayout.SOUTH);
        profileDialog.pack(); // Auto-adjust size based on components

        // Update button action
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = nameField.getText().trim();
                String newAddr = addressField.getText().trim();
                String newPhone = phoneField.getText().trim();
                String newGender = (String) genderCombo.getSelectedItem();
                String pancard = pancardField.getText().trim();

                // Simple validation
                if (newName.isEmpty() || newAddr.isEmpty() || newPhone.isEmpty()) {
                    JOptionPane.showMessageDialog(profileDialog, "All fields must be filled.");
                    return;
                }

                // Update owner object
                currentOwner.setOwnerName(newName);
                currentOwner.setAddress(newAddr);
                currentOwner.setPhone(Long.parseLong(newPhone));
                currentOwner.setGender(newGender);
                currentOwner.setPancard(pancard);

                // Call your update method (implement in your DAO as needed)
                if (ownerOpImpl.modifyOwner(currentOwner) != null) {
                    JOptionPane.showMessageDialog(profileDialog, "Profile updated successfully!");
                    profileDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(profileDialog, "Update failed.");
                }
            }
        });

        // Change Password button action
        changePassBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JDialog passDialog = new JDialog(profileDialog, "Change Password", true);
                passDialog.setSize(500, 320);
                passDialog.setLocationRelativeTo(profileDialog);

                JPanel passPanel = new JPanel(new GridBagLayout());
                passPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(12, 12, 12, 12);
                gbc.anchor = GridBagConstraints.WEST;

                Font labelFont = new Font("Tahoma", Font.BOLD, 18);
                Font fieldFont = new Font("Tahoma", Font.PLAIN, 18);
                int FIELD_COLUMNS = 18;

                // Old Password
                gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
                JLabel oldPassLabel = new JLabel("Old Password:");
                oldPassLabel.setFont(labelFont);
                passPanel.add(oldPassLabel, gbc);

                gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
                final JPasswordField oldPassField = new JPasswordField(FIELD_COLUMNS);
                oldPassField.setFont(fieldFont);
                passPanel.add(oldPassField, gbc);

                // New Password
                gbc.gridx = 0; gbc.gridy++; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
                JLabel newPassLabel = new JLabel("New Password:");
                newPassLabel.setFont(labelFont);
                passPanel.add(newPassLabel, gbc);

                gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
                final JPasswordField newPassField = new JPasswordField(FIELD_COLUMNS);
                newPassField.setFont(fieldFont);
                passPanel.add(newPassField, gbc);

                // Retype New Password
                gbc.gridx = 0; gbc.gridy++; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
                JLabel retypePassLabel = new JLabel("Retype New Password:");
                retypePassLabel.setFont(labelFont);
                passPanel.add(retypePassLabel, gbc);

                gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
                final JPasswordField retypePassField = new JPasswordField(FIELD_COLUMNS);
                retypePassField.setFont(fieldFont);
                passPanel.add(retypePassField, gbc);

                // Update Button
                gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
                JButton passUpdateBtn = new JButton("Update Password");
                passUpdateBtn.setFont(labelFont);
                passPanel.add(passUpdateBtn, gbc);

                passUpdateBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String oldPass = new String(oldPassField.getPassword());
                        String newPass = new String(newPassField.getPassword());
                        String retypePass = new String(retypePassField.getPassword());

                        if (!oldPass.equals(currentOwner.getPassword())) {
                            JOptionPane.showMessageDialog(passDialog, "Old password is incorrect.");
                            return;
                        }
                        if (newPass.isEmpty() || !newPass.equals(retypePass)) {
                            JOptionPane.showMessageDialog(passDialog, "New passwords do not match or are empty.");
                            return;
                        }
                        // Update password
                        currentOwner.setPassword(newPass);
                        if (ownerOpImpl.modifyOwner(currentOwner) != null) {
                            // Masked password: show only last 2 chars
                            String masked = "";
                            int len = newPass.length();
                            if (len <= 2) {
                                masked = newPass;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < len - 2; i++) sb.append("*");
                                sb.append(newPass.substring(len - 2));
                                masked = sb.toString();
                            }
                            JOptionPane.showMessageDialog(passDialog, "Password updated: " + masked);
                            passDialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(passDialog, "Password update failed.");
                        }
                    }
                });

                passDialog.setContentPane(passPanel);
                passDialog.pack(); // Automatically size dialog to fit content
                passDialog.setVisible(true);
            }
        });


        profileDialog.setVisible(true);
    }
    
    private void openPaymentUI(final String regis) {
        int total = 0;
        final List<Integer> selectedRows = new ArrayList<Integer>();
        for (int i = 0; i < clearOffenceTableModel.getRowCount(); i++) {
            Boolean selected = (Boolean) clearOffenceTableModel.getValueAt(i, 0);
            if (selected != null && selected) {
                Integer penalty = (Integer) clearOffenceTableModel.getValueAt(i, 7);
                total += penalty;
                Integer ID = (Integer)clearOffenceTableModel.getValueAt(i, 1);
                selectedRows.add(ID);
            }
        }
        if (selectedRows.isEmpty()) {
            JOptionPane.showMessageDialog(clearOffencesPanel, "Please select at least one offence to pay.");
            return;
        }

        final JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(clearOffencesPanel), "Payment", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(clearOffencesPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel helloLabel = new JLabel("Hello," + currentOwner.getOwnerName());
        helloLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        mainPanel.add(helloLabel);

        JLabel chooseLabel = new JLabel("Choose the mode of payment:");
        chooseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(chooseLabel);

        final String[] modes = {"GPay", "PhonePe", "Bank Transfer"};
        final ButtonGroup bg = new ButtonGroup();
        JPanel modePanel = new JPanel(new GridLayout(3, 1, 5, 5));
        final JRadioButton[] modeButtons = new JRadioButton[modes.length];
        for (int i = 0; i < modes.length; i++) {
            modeButtons[i] = new JRadioButton(modes[i]);
            bg.add(modeButtons[i]);
            modePanel.add(modeButtons[i]);
        }
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(modePanel);

        final JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Payment Details"));
        detailsPanel.setVisible(false);

        // Details fields
        final JLabel ownerNameLabel = new JLabel();
        final JLabel upiLabel = new JLabel();
        final JLabel bankLabel = new JLabel();
        final JLabel phoneLabel = new JLabel();
        final JLabel tmLabel = new JLabel(" Traffic Management ");
        tmLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        final JTextField amountField = new JTextField("\u20B9" + total);
        amountField.setEditable(false);

        final JButton payBtn = new JButton("Pay");
        payBtn.setEnabled(false);

        // Add details fields
        detailsPanel.add(ownerNameLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(upiLabel);
        detailsPanel.add(bankLabel);
        detailsPanel.add(phoneLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(tmLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(amountField);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(payBtn);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(detailsPanel);

        // Mode selection logic (no lambdas)
        final String ownerName = "Owner: " + currentOwner.getOwnerName();
        final String phone = "Phone: " + currentOwner.getPhone();
        final String amount = "\u20B9" + total;

        for (int i = 0; i < modes.length; i++) {
            final int idx = i;
            modeButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    detailsPanel.setVisible(true);
                    payBtn.setEnabled(true);
                    if (modes[idx].equals("GPay")) {
                        ownerNameLabel.setText(ownerName);
                        upiLabel.setText("UPI: randomgpayupi@okicici");
                        bankLabel.setText("Bank: gpayuser");
                        phoneLabel.setText(phone);
                    } else if (modes[idx].equals("PhonePe")) {
                        ownerNameLabel.setText(ownerName);
                        upiLabel.setText("UPI: randomphonepe@okhdfc");
                        bankLabel.setText("Bank: phonepeuser");
                        phoneLabel.setText(phone);
                    } else if (modes[idx].equals("Bank Transfer")) {
                        ownerNameLabel.setText(ownerName);
                        upiLabel.setText("UPI: N/A");
                        bankLabel.setText("Bank: Govt");
                        phoneLabel.setText(phone);
                    }
                    amountField.setText(amount);
                    dialog.pack();
                }
            });
        }

        payBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Find selected payment mode
                String selectedMode = null;
                for (int i = 0; i < modeButtons.length; i++) {
                    if (modeButtons[i].isSelected()) {
                        selectedMode = modes[i];
                        break;
                    }
                }
                if (selectedMode == null) {
                    JOptionPane.showMessageDialog(dialog, "Please select a payment mode.");
                    return;
                }
                if (selectedRows.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please select at least one offence to pay.");
                    return;
                }

                int allSuccess = 0;
                for (int i = 0; i < selectedRows.size(); i++) {
                    int row = selectedRows.get(i);
                    VehicleOffenceEO vehOffEO = new VehicleOffenceEO();
                    vehOffEO = ownerOpImpl.findVehicleOffenceByID(row);
                    String clearVehicleOffence = ownerOpImpl.clearVehicleOffence(vehOffEO);
                    allSuccess++;
                    JOptionPane.showMessageDialog(dialog, clearVehicleOffence);
                }

                if (allSuccess == selectedRows.size()) {
                    JOptionPane.showMessageDialog(dialog, "Payment successful!");
                    dialog.dispose();
                    // Reload and refresh both panels
                    loadClearOffences(regis);
                    clearOffencesPanel.revalidate();
                    clearOffencesPanel.repaint();

                    loadPendingOffences(regis);
                    pendingOffencesPanel.revalidate();
                    pendingOffencesPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Some payments failed. Please try again.");
                }
            }
        });

        dialog.setContentPane(mainPanel);
        dialog.pack();
        dialog.setVisible(true);
    }
    
    private ImageIcon getImageIconFromBytes(byte[] bytes, int width, int height) {
        if (bytes == null) return null;
        ImageIcon icon = new ImageIcon(bytes);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private ImageIcon getDefaultIcon(int width, int height) {
        Icon defaultIcon = UIManager.getIcon("OptionPane.informationIcon");
        if (defaultIcon instanceof ImageIcon) {
            Image img = ((ImageIcon) defaultIcon).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
    
}
