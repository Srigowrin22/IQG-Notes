package training.iqgateway.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;

import training.iqgateway.entities.*;
import training.iqgateway.operations.impl.RTOOperationImpl;

public class RTODashboardUI extends JFrame {
    private RTOOperationImpl rtoOpImpl = new RTOOperationImpl();
    private AdminEO currentOwner;

    // Top bar components
    private JTextField regisField;
    private JButton searchBtn;
    private JLabel helloUser;
    private JButton logoutBtn;

    // Panels
    private JTabbedPane tabbedPane;
    private DefaultTableModel vehicleOffenceModel;
    private JTable vehicleOffenceTable;
    private DefaultTableModel clearOffenceModel;
    private JTable clearOffenceTable;
    private JLabel totalLabel;
    private DefaultTableModel vehicleModel;
    private JTable vehicleTable;
    private DefaultTableModel offenceTypeModel;
    private JTable offenceTypeTable;
    private JPanel registrationPanel;
    private JTextField regIDField, regVehicleField, regDateField, regLocationField, regRegistrarField;
    private JTextField ownerNameField, ownerAadharField, ownerPhoneField, ownerAddressField, ownerPanField, ownerGenderField;
    private JTextField newOwnerNameField, newOwnerAadharField, newOwnerPhoneField, newOwnerAddressField, newOwnerPanField, newOwnerGenderField;
    private JButton transferBtn;

    public RTODashboardUI(AdminEO adminEO) {
        this.currentOwner = adminEO;
        setTitle("RTO Management Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(buildTopBar(), BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 18));
        tabbedPane.addTab("   Vehicle Offence   ", buildVehicleOffencePanel());
        tabbedPane.addTab("   Clear Vehicle Offence     ", buildClearOffencePanel());
        tabbedPane.addTab("   Vehicle Operation     ", buildVehicleOperationPanel());
        tabbedPane.addTab("   OffenceTypes Operation    ", buildOffenceTypePanel());
        tabbedPane.addTab("   Registration Operation    ", buildRegistrationPanel());
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // 1. Top Bar
    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel(new BorderLayout(20, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Left: Hello message
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        helloUser = new JLabel("Hello, " + currentOwner.getDesignationID());
        helloUser.setFont(new Font("Tahoma", Font.BOLD, 20));
        leftPanel.add(helloUser);

        // Center: Registration ID label, text field, search button
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel regisLabel = new JLabel("Registration ID:");
        regisLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        regisField = new JTextField(18);
        regisField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        centerPanel.add(regisLabel);
        centerPanel.add(regisField);
        centerPanel.add(searchBtn);

        // Right: Logout button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        logoutBtn = new JButton("LOGOUT");
        logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        rightPanel.add(logoutBtn);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);

        // Listeners
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regisID = regisField.getText().trim();
                loadVehicleOffenceTable(regisID);
                loadRegistrationPanel(regisID);
                loadClearOffenceTable(regisID);
                loadVehicleTable(regisID);
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RTODashboardUI.this.dispose();
                JOptionPane.showMessageDialog(RTODashboardUI.this, "Logged OUT");

                // Show the login UI
                AdminLoginUI loginUI = new AdminLoginUI();
                loginUI.setVisible(true);
            }
        });

        return topPanel;
    }

    // 2. Vehicle Offence Panel
    private int editableRow = -1; // Only this row is editable

    class PaidStatusRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int statusCol = 6; // "Status" column index
            Object statusValue = table.getValueAt(row, statusCol);

            if ("Paid".equalsIgnoreCase(String.valueOf(statusValue))) {
                c.setBackground(new Color(204, 255, 204)); // light green
            } else {
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                } else {
                    c.setBackground(Color.WHITE);
                }
            }
            return c;
        }
    }

    private JPanel buildVehicleOffencePanel() {
        final JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            "Vehicle Offences",
            0, 0, new Font("Tahoma", Font.BOLD, 20)
        ));

        // Columns: ID, RegisID, Offence Type (name), Place, Date, Time, Status, Reporter
        final String[] columns = {
            "VehicleOffence ID", "RegisID", "Offence Type", "Place", "Date", "Time", "Status", "Reporter"
        };

        vehicleOffenceModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                // Only the editable row's Registration ID (col 1) is editable
                return row == editableRow && col == 1;
            }
        };
        vehicleOffenceTable = new JTable(vehicleOffenceModel);
        vehicleOffenceTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleOffenceTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        vehicleOffenceTable.setRowHeight(28);

        // Set custom renderer for coloring "Paid" rows
        vehicleOffenceTable.setDefaultRenderer(Object.class, new PaidStatusRowRenderer());

        JScrollPane scrollPane = new JScrollPane(vehicleOffenceTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Update Button
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        JButton updateBtn = new JButton("Update");
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnPanel.add(updateBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        // Double-click to make a row editable (only Registration ID)
        vehicleOffenceTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editableRow = vehicleOffenceTable.getSelectedRow();
                    vehicleOffenceModel.fireTableRowsUpdated(editableRow, editableRow);
                }
            }
        });

        // Update button logic
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editableRow >= 0) {
                    try {
                        // Validate Registration ID is filled
                        Object regObj = vehicleOffenceModel.getValueAt(editableRow, 1);
                        String regisId = (regObj != null) ? regObj.toString().trim() : "";
                        if (regisId.isEmpty()) {
                            JOptionPane.showMessageDialog(panel, "Registration ID must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        // Validate Registration ID exists in Registration table
                        RegistrationEO reg = rtoOpImpl.findRegistrationByID(regisId);
                        if (reg == null) {
                            JOptionPane.showMessageDialog(panel, "Registration ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Fetch existing VehicleOffenceEO by ID
                        int vehOffId = Integer.parseInt(vehicleOffenceModel.getValueAt(editableRow, 0).toString());
                        VehicleOffenceEO vehOff = rtoOpImpl.findVehicleOffenceByID(vehOffId);
                        if (vehOff == null) {
                            JOptionPane.showMessageDialog(panel, "Vehicle Offence not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Set only the new Registration ID
                        vehOff.setRegistrationID(reg);

                        // Update
                        String result = rtoOpImpl.modifyVehicleOffence(vehOff);
                        JOptionPane.showMessageDialog(panel, result, "Success", JOptionPane.INFORMATION_MESSAGE);

                        editableRow = -1;
                        loadVehicleOffenceTable(regisField.getText().trim());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Invalid data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Double-click a row to edit Registration ID.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        loadVehicleOffenceTable(""); // load all by default
        return panel;
    }

    // Helper method to load offences
    private void loadVehicleOffenceTable(String regisID) {
        vehicleOffenceModel.setRowCount(0);
        List<VehicleOffenceEO> list;
        if (regisID == null || regisID.isEmpty()) {
            list = rtoOpImpl.findAllVehicleOffence();
        } else {
            list = rtoOpImpl.findVehicleOffenceByRegisID(regisID);
        }
        for (VehicleOffenceEO v : list) {
            String offenceType = "";
            if (v.getOffenceID() != null) {
                OffenceTypesEO offence = rtoOpImpl.findOffenceTypeByID(v.getOffenceID().getOffenceID());
                if (offence != null) {
                    offenceType = offence.getOffenceType();
                }
            }
            vehicleOffenceModel.addRow(new Object[]{
                v.getVehicleOffenceID(),
                v.getRegistrationID() != null ? v.getRegistrationID().getRegistrationID() : "",
                offenceType,
                v.getLocation(),
                v.getDate() != null ? v.getDate().toString() : new java.sql.Date(System.currentTimeMillis()).toString(),
                v.getTime() != null ? v.getTime().toString() : new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()),
                v.getStatus() == 1 ? "Paid" : "Not Paid",
                v.getReporter() != null ? v.getReporter() : ""
            });
        }
    }

    // 3. Clear Vehicle Offence Panel
    private JPanel buildClearOffencePanel() {
        final JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            "Clear Vehicle Offences",
            0, 0, new Font("Tahoma", Font.BOLD, 20)
        ));

        // Optional: "Select All" button above the table
        JButton selectAllBtn = new JButton("Select All");
        selectAllBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        selectAllBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < clearOffenceModel.getRowCount(); i++) {
                    clearOffenceModel.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        });
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(selectAllBtn);
        panel.add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Check To Pay", "VehicleOffence ID", "Offence Made", "RegisID", "Penalty"};
        clearOffenceModel = new DefaultTableModel(columns, 0) {
            public Class<?> getColumnClass(int col) {
                return col == 0 ? Boolean.class : String.class;
            }
            public boolean isCellEditable(int row, int col) {
                return col == 0;
            }
        };

        clearOffenceTable = new JTable(clearOffenceModel);
        clearOffenceTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clearOffenceTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        clearOffenceTable.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(clearOffenceTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        totalLabel = new JLabel("Total: \u20B9 0");
        totalLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        JButton payBtn = new JButton("PAY");
        payBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        bottomPanel.add(totalLabel);
        bottomPanel.add(payBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Update total on checkbox change
        clearOffenceModel.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int total = 0;
                for (int i = 0; i < clearOffenceModel.getRowCount(); i++) {
                    Boolean checked = (Boolean) clearOffenceModel.getValueAt(i, 0);
                    if (checked != null && checked) {
                        int penalty = 0;
                        try {
                            penalty = Integer.parseInt(clearOffenceModel.getValueAt(i, 4).toString());
                        } catch (Exception ex) { penalty = 0; }
                        total += penalty;
                    }
                }
                totalLabel.setText("Total: \u20B9" + total);
            }
        });

        payBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean paidAny = false;
                for (int i = 0; i < clearOffenceModel.getRowCount(); i++) {
                    Boolean checked = (Boolean) clearOffenceModel.getValueAt(i, 0);
                    if (checked != null && checked) {
                        int vehOffenceID = Integer.parseInt(clearOffenceModel.getValueAt(i, 1).toString());
                        VehicleOffenceEO vehOff = rtoOpImpl.findVehicleOffenceByID(vehOffenceID);
                        if (vehOff != null) {
                            rtoOpImpl.clearVehicleOffence(vehOff);
                            paidAny = true;
                        }
                    }
                }
                if (paidAny) {
                    JOptionPane.showMessageDialog(panel, "Selected offences cleared!");
                } else {
                    JOptionPane.showMessageDialog(panel, "No offences selected.");
                }
                loadClearOffenceTable(regisField.getText().trim());
                loadVehicleOffenceTable(regisField.getText().trim());
                totalLabel.setText("Total: \u20B9 0");
            }
        });

        loadClearOffenceTable(""); // load all by default
        return panel;
    }

    // Example implementation for loading table rows
    private void loadClearOffenceTable(String regisID) {
        clearOffenceModel.setRowCount(0);
        List<VehicleOffenceEO> list;
        if (regisID == null || regisID.isEmpty()) {
            list = rtoOpImpl.findAllVehicleOffence();
        } else {
            list = rtoOpImpl.findVehicleOffenceByRegisID(regisID);
        }
        for (VehicleOffenceEO v : list) {
            if (v.getStatus() == 0) { // Only not paid
                int penalty = 0;
                OffenceTypesEO offence = null;
                String offenceType = "";
                if (v.getOffenceID() != null) {
                    offence = rtoOpImpl.findOffenceTypeByID(v.getOffenceID().getOffenceID());
                    if (offence != null) {
                        penalty = offence.getPenaltyAmt();
                        offenceType = offence.getOffenceType();
                    }
                }
                clearOffenceModel.addRow(new Object[]{
                    Boolean.FALSE,
                    v.getVehicleOffenceID(),
                    offenceType,
                    v.getRegistrationID() != null ? v.getRegistrationID().getRegistrationID() : "",
                    penalty
                });
            }
        }
    }


//    private JPanel buildClearOffencePanel() {
//        final JPanel panel = new JPanel(new BorderLayout(15, 15));
//        panel.setBorder(BorderFactory.createTitledBorder(
//            BorderFactory.createLineBorder(Color.GRAY, 2),
//            "Clear Vehicle Offences",
//            0, 0, new Font("Tahoma", Font.BOLD, 20)
//        ));
//
//        String[] columns = {"Check To Pay", "VehicleOffence ID", "Offence Made", "RegisID", "Penalty"};
//        clearOffenceModel = new DefaultTableModel(columns, 0) {
//            public Class<?> getColumnClass(int col) { return col == 0 ? Boolean.class : String.class; }
//            public boolean isCellEditable(int row, int col) { return col == 0; }
//        };
//        clearOffenceTable = new JTable(clearOffenceModel);
//        clearOffenceTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        clearOffenceTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
//        clearOffenceTable.setRowHeight(28);
//
//        // "Select All" checkbox in header
//        final JCheckBox selectAll = new JCheckBox();
//        selectAll.setOpaque(false);
//
//        TableColumn tc = clearOffenceTable.getColumnModel().getColumn(0);
//        tc.setHeaderRenderer(new CheckBoxHeader(selectAll));
//
//        selectAll.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                boolean checked = selectAll.isSelected();
//                for (int i = 0; i < clearOffenceModel.getRowCount(); i++) {
//                    clearOffenceModel.setValueAt(checked, i, 0);
//                }
//            }
//        });
//
//        JScrollPane scrollPane = new JScrollPane(clearOffenceTable);
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
//        totalLabel = new JLabel("Total: \u20B9 0");
//        totalLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
//        JButton payBtn = new JButton("PAY");
//        payBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
//        bottomPanel.add(totalLabel);
//        bottomPanel.add(payBtn);
//        panel.add(bottomPanel, BorderLayout.SOUTH);
//
//        // Update total on checkbox change
//        clearOffenceModel.addTableModelListener(new TableModelListener() {
//            public void tableChanged(TableModelEvent e) {
//                int total = 0;
//                for (int i = 0; i < clearOffenceModel.getRowCount(); i++) {
//                    Boolean checked = (Boolean) clearOffenceModel.getValueAt(i, 0);
//                    if (checked != null && checked) {
//                        int penalty = 0;
//                        try {
//                            penalty = Integer.parseInt(clearOffenceModel.getValueAt(i, 4).toString());
//                        } catch (Exception ex) { penalty = 0; }
//                        total += penalty;
//                    }
//                }
//                totalLabel.setText("Total: \u20B9" + total);
//            }
//        });
//
//        payBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                boolean paidAny = false;
//                for (int i = 0; i < clearOffenceModel.getRowCount(); i++) {
//                    Boolean checked = (Boolean) clearOffenceModel.getValueAt(i, 0);
//                    if (checked != null && checked) {
//                        int vehOffenceID = Integer.parseInt(clearOffenceModel.getValueAt(i, 1).toString());
//                        VehicleOffenceEO vehOff = rtoOpImpl.findVehicleOffenceByID(vehOffenceID);
//                        if (vehOff != null) {
//                            rtoOpImpl.clearVehicleOffence(vehOff);
//                            paidAny = true;
//                        }
//                    }
//                }
//                if (paidAny) {
//                    JOptionPane.showMessageDialog(panel, "Selected offences cleared!");
//                } else {
//                    JOptionPane.showMessageDialog(panel, "No offences selected.");
//                }
//                loadClearOffenceTable(regisField.getText().trim());
//                loadVehicleOffenceTable(regisField.getText().trim());
//                totalLabel.setText("Total: ?0");
//                selectAll.setSelected(false);
//            }
//        });
//
//        loadClearOffenceTable(""); // load all by default
//        return panel;
//    }
//
//    // Custom header renderer for checkbox
//    class CheckBoxHeader extends JPanel implements TableCellRenderer {
//        JCheckBox check;
//        public CheckBoxHeader(JCheckBox check) {
//            this.check = check;
//            setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//            add(check);
//            setOpaque(false);
//        }
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            return this;
//        }
//    }
//
//    private void loadClearOffenceTable(String regisID) {
//        clearOffenceModel.setRowCount(0);
//        List<VehicleOffenceEO> list;
//        if (regisID == null || regisID.isEmpty()) {
//            list = rtoOpImpl.findAllVehicleOffence();
//        } else {
//            list = rtoOpImpl.findVehicleOffenceByRegisID(regisID);
//        }
//        for (VehicleOffenceEO v : list) {
//            if (v.getStatus() == 0) { // Only not paid
//                int penalty = 0;
//                OffenceTypesEO offence = null;
//                String offenceType = "";
//                if (v.getOffenceID() != null) {
//                    offence = rtoOpImpl.findOffenceTypeByID(v.getOffenceID().getOffenceID());
//                    if (offence != null) {
//                        penalty = offence.getPenaltyAmt();
//                        offenceType = offence.getOffenceType();
//                    }
//                }
//                clearOffenceModel.addRow(new Object[]{
//                    Boolean.FALSE,
//                    v.getVehicleOffenceID(),
//                    offenceType,
//                    v.getRegistrationID() != null ? v.getRegistrationID().getRegistrationID() : "",
//                    penalty
//                });
//            }
//        }
//    }
    
    // 4. Vehicle Operation Panel
    private JPanel buildVehicleOperationPanel() {
        final JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            "Vehicle Operations",
            0, 0, new Font("Tahoma", Font.BOLD, 20)
        ));
    
        // All columns as per your Vehicle table
        final String[] columns = {
            "Vehicle ID", "Brand", "Model", "Type", "Fuel", "No Of Exhaust", "Color", "Manufacture Date"
        };
    
        vehicleModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return row == editableRow;
            }
        };
    
        vehicleTable = new JTable(vehicleModel);
        vehicleTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        vehicleTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        vehicleTable.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        JButton addBtn = new JButton("ADD");
        JButton updateBtn = new JButton("UPDATE");
        JButton showBtn = new JButton("SHOW");
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        showBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(showBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);
    
        // ADD button: add a blank editable row with defaults
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String today = new java.sql.Date(System.currentTimeMillis()).toString();
                vehicleModel.addRow(new Object[]{"", "", "", "", "Petrol", "1", "White", today});
                editableRow = vehicleModel.getRowCount() - 1;
                vehicleModel.fireTableRowsInserted(editableRow, editableRow);
            }
        });
    
        // UPDATE button: validate and insert/update
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editableRow >= 0) {
                    try {
                        // Validate all fields
                        for (int col = 0; col < vehicleModel.getColumnCount(); col++) {
                            String value = vehicleModel.getValueAt(editableRow, col).toString().trim();
                            if (value.isEmpty()) {
                                JOptionPane.showMessageDialog(panel, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        }
    
                        int vehicleID = Integer.parseInt(vehicleModel.getValueAt(editableRow, 0).toString().trim());
                        String brand = vehicleModel.getValueAt(editableRow, 1).toString().trim();
                        String model = vehicleModel.getValueAt(editableRow, 2).toString().trim();
                        String type = vehicleModel.getValueAt(editableRow, 3).toString().trim();
                        String fuel = vehicleModel.getValueAt(editableRow, 4).toString().trim();
                        int noOfExhaust = Integer.parseInt(vehicleModel.getValueAt(editableRow, 5).toString().trim());
                        String color = vehicleModel.getValueAt(editableRow, 6).toString().trim();
                        java.sql.Date manufactureDate = java.sql.Date.valueOf(vehicleModel.getValueAt(editableRow, 7).toString().trim());
    
                        VehicleEO vehicle = new VehicleEO();
                        vehicle.setVehicleID(vehicleID);
                        vehicle.setVehicleBrand(brand);
                        vehicle.setVehicleModel(model);
                        vehicle.setVehicleType(type);
                        vehicle.setFuelType(fuel);
                        vehicle.setNoOfExhaust(noOfExhaust);
                        vehicle.setColor(color);
                        vehicle.setManufactureDate(manufactureDate);
    
                        // Check if vehicle already exists
                        String result;
                        if (vehicleID > 0 && rtoOpImpl.findVehicleByID(vehicleID) != null) {
                            result = rtoOpImpl.modifyVehicle(vehicle); // Update existing
                        } else {
                            result = rtoOpImpl.addVehicle(vehicle); // Add new
                        }
    
                        JOptionPane.showMessageDialog(panel, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                        editableRow = -1;
                        // Reload with current filter
                        String regisID = regisField.getText().trim();
                        loadVehicleTable(regisID);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Vehicle ID and No Of Exhaust must be integers!\nManufacture Date must be yyyy-MM-dd", "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(panel, "Manufacture Date must be in yyyy-MM-dd format!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please click ADD to insert a new vehicle.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    
        // SHOW button: reload all vehicles, disable editing
        showBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editableRow = -1;
                String regisID = regisField.getText().trim();
                loadVehicleTable(regisID);
            }
        });
    
        // Initial load: show all vehicles
        loadVehicleTable("");
        return panel;
    }
    
    // Overloaded loadVehicleTable: with regisID
    private void loadVehicleTable(String regisID) {
        vehicleModel.setRowCount(0);
        if (regisID == null || regisID.isEmpty()) {
            // Show all vehicles
            java.util.List<VehicleEO> list = rtoOpImpl.findAllVehicles();
            for (VehicleEO v : list) {
                vehicleModel.addRow(new Object[]{
                    v.getVehicleID(),
                    v.getVehicleBrand(),
                    v.getVehicleModel(),
                    v.getVehicleType(),
                    v.getFuelType(),
                    v.getNoOfExhaust(),
                    v.getColor(),
                    v.getManufactureDate() != null ? v.getManufactureDate().toString() : ""
                });
            }
        } else {
            // Show vehicle(s) for this registration ID
            RegistrationEO reg = rtoOpImpl.findRegistrationByID(regisID);
            if (reg == null) {
                JOptionPane.showMessageDialog(null, "Registration ID not found!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int vehicleID = reg.getVehicleID().getVehicleID();
            VehicleEO v = rtoOpImpl.findVehicleByID(vehicleID);
            if (v != null) {
                vehicleModel.addRow(new Object[]{
                    v.getVehicleID(),
                    v.getVehicleBrand(),
                    v.getVehicleModel(),
                    v.getVehicleType(),
                    v.getFuelType(),
                    v.getNoOfExhaust(),
                    v.getColor(),
                    v.getManufactureDate() != null ? v.getManufactureDate().toString() : ""
                });
            }
        }
    }

    // 5. Offence Type Operation Panel
    private int editableRowOffenceType = -1; // Only this row is editable

    private JPanel buildOffenceTypePanel() {
        final JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            "Offence Type Operations",
            0, 0, new Font("Tahoma", Font.BOLD, 20)
        ));

        String[] columns = {"Offence ID", "Type", "Vehicle Type", "Penalty"};
        offenceTypeModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return row == editableRowOffenceType;
            }
        };
        offenceTypeTable = new JTable(offenceTypeModel);
        offenceTypeTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        offenceTypeTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        offenceTypeTable.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(offenceTypeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        JButton addBtn = new JButton("ADD");
        JButton updateBtn = new JButton("UPDATE");
        JButton deleteBtn = new JButton("DELETE");
        JButton showBtn = new JButton("SHOW");
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        showBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(showBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        // ADD button: add a blank editable row with defaults
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                offenceTypeModel.addRow(new Object[]{"", "", "", "100"});
                editableRowOffenceType = offenceTypeModel.getRowCount() - 1;
                offenceTypeModel.fireTableRowsInserted(editableRowOffenceType, editableRowOffenceType);
            }
        });

        // Double-click to make a row editable
        offenceTypeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editableRowOffenceType = offenceTypeTable.getSelectedRow();
                    offenceTypeModel.fireTableRowsUpdated(editableRowOffenceType, editableRowOffenceType);
                }
            }
        });

        // UPDATE button: validate and insert/update
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean anyUpdated = false;
                for (int row = 0; row < offenceTypeModel.getRowCount(); row++) {
                    try {
                        // Validate all fields in this row
                        boolean skipRow = false;
                        for (int col = 0; col < offenceTypeModel.getColumnCount(); col++) {
                            Object valObj = offenceTypeModel.getValueAt(row, col);
                            String value = (valObj != null) ? valObj.toString().trim() : "";
                            if (value.isEmpty()) {
                                skipRow = true;
                                break;
                            }
                        }
                        if (skipRow) continue; // Skip incomplete rows

                        int offenceID = Integer.parseInt(offenceTypeModel.getValueAt(row, 0).toString());
                        String type = offenceTypeModel.getValueAt(row, 1).toString();
                        String vehicleType = offenceTypeModel.getValueAt(row, 2).toString();
                        int penalty = Integer.parseInt(offenceTypeModel.getValueAt(row, 3).toString());

                        OffenceTypesEO offence = new OffenceTypesEO();
                        offence.setOffenceID(offenceID);
                        offence.setOffenceType(type);
                        offence.setVehicleType(vehicleType);
                        offence.setPenaltyAmt(penalty);

                        // Check if offence already exists
                        String result;
                        if (offenceID > 0 && rtoOpImpl.findOffenceTypeByID(offenceID) != null) {
                            result = rtoOpImpl.modifyOffenceType(offence); // Update existing
                        } else {
                            result = rtoOpImpl.addOffenceType(offence); // Add new
                        }
                        anyUpdated = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Offence ID and Penalty must be integers in row " + (row + 1), "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Error updating row " + (row + 1) + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (anyUpdated) {
                    JOptionPane.showMessageDialog(panel, "Offence types updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    editableRowOffenceType = -1;
                    loadOffenceTypeTable();
                } else {
                    JOptionPane.showMessageDialog(panel, "No complete rows to update.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = offenceTypeTable.getSelectedRow();
                if (row >= 0) {
                    int offenceID = Integer.parseInt(offenceTypeModel.getValueAt(row, 0).toString());
                    rtoOpImpl.removeOffenceType(offenceID);
                    JOptionPane.showMessageDialog(panel, "Offence type deleted!");
                    loadOffenceTypeTable();
                }
            }
        });

        showBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editableRowOffenceType = -1;
                loadOffenceTypeTable();
            }
        });

        loadOffenceTypeTable();
        return panel;
    }

    private void loadOffenceTypeTable() {
        offenceTypeModel.setRowCount(0);
        List<OffenceTypesEO> list = rtoOpImpl.findAllOffenceType();
        for (OffenceTypesEO o : list) {
            offenceTypeModel.addRow(new Object[]{
                o.getOffenceID(),
                o.getOffenceType(),
                o.getVehicleType(),
                o.getPenaltyAmt()
            });
        }
    }

    // 6. Registration Operation Panel (basic, you can expand as needed)
    
    private JButton addBtn, modifyBtn, makeTransferBtn;
    private boolean isInAddMode = false;
    private JPanel ownerPanel, regPanel, newOwnerPanel;
    private JPanel buildRegistrationPanel() {
        registrationPanel = new JPanel();
        registrationPanel.setLayout(new BoxLayout(registrationPanel, BoxLayout.Y_AXIS));
        registrationPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            "Registration Operations",
            0, 0, new Font("Tahoma", Font.BOLD, 20)
        ));

        // --- Owner Panel ---
        ownerPanel = new JPanel(new GridBagLayout());
        ownerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            "Owner Details",
            0, 0, new Font("Tahoma", Font.BOLD, 16)
        ));

        Font boldFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 17);
        Border fieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(7, 12, 7, 12)
        );
        Dimension fieldSize = new Dimension(240, 36);

        // Initialize all fields before use!
        ownerNameField = new JTextField(20);
        ownerNameField.setFont(fieldFont);
        ownerNameField.setBorder(fieldBorder);
        ownerNameField.setPreferredSize(fieldSize);

        ownerGenderField = new JTextField(20);
        ownerGenderField.setFont(fieldFont);
        ownerGenderField.setBorder(fieldBorder);
        ownerGenderField.setPreferredSize(fieldSize);

        ownerAadharField = new JTextField(20);
        ownerAadharField.setFont(fieldFont);
        ownerAadharField.setBorder(fieldBorder);
        ownerAadharField.setPreferredSize(fieldSize);
        ownerAadharField.setEditable(false);

        ownerPanField = new JTextField(20);
        ownerPanField.setFont(fieldFont);
        ownerPanField.setBorder(fieldBorder);
        ownerPanField.setPreferredSize(fieldSize);
        ownerPanField.setEditable(false);

        ownerAddressField = new JTextField(20);
        ownerAddressField.setFont(fieldFont);
        ownerAddressField.setBorder(fieldBorder);
        ownerAddressField.setPreferredSize(fieldSize);

        ownerPhoneField = new JTextField(20);
        ownerPhoneField.setFont(fieldFont);
        ownerPhoneField.setBorder(fieldBorder);
        ownerPhoneField.setPreferredSize(fieldSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 24, 12, 24); // More space around components
        gbc.anchor = GridBagConstraints.WEST;

        // Left column (Name, Gender, Aadhar)
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:"); nameLabel.setFont(boldFont);
        ownerPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        ownerPanel.add(ownerNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel genderLabel = new JLabel("Gender:"); genderLabel.setFont(boldFont);
        ownerPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        ownerPanel.add(ownerGenderField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel aadharLabel = new JLabel("Aadhar:"); aadharLabel.setFont(boldFont);
        ownerPanel.add(aadharLabel, gbc);
        gbc.gridx = 1;
        ownerPanel.add(ownerAadharField, gbc);

        // Right column (PAN, Address, Phone)
        gbc.gridx = 2; gbc.gridy = 0;
        JLabel panLabel = new JLabel("PAN Card:"); panLabel.setFont(boldFont);
        ownerPanel.add(panLabel, gbc);
        gbc.gridx = 3;
        ownerPanel.add(ownerPanField, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        JLabel addressLabel = new JLabel("Address:"); addressLabel.setFont(boldFont);
        ownerPanel.add(addressLabel, gbc);
        gbc.gridx = 3;
        ownerPanel.add(ownerAddressField, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("Phone:"); phoneLabel.setFont(boldFont);
        ownerPanel.add(phoneLabel, gbc);
        gbc.gridx = 3;
        ownerPanel.add(ownerPhoneField, gbc);

        registrationPanel.add(ownerPanel);

        // --- Registration Panel ---
        regPanel = new JPanel(new GridBagLayout());
        regPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            "Registration Details",
            0, 0, new Font("Tahoma", Font.BOLD, 16)
        ));

        // Initialize fields
        regIDField = new JTextField(20); regIDField.setFont(fieldFont); regIDField.setBorder(fieldBorder); regIDField.setPreferredSize(fieldSize);
        regVehicleField = new JTextField(20); regVehicleField.setFont(fieldFont); regVehicleField.setBorder(fieldBorder); regVehicleField.setPreferredSize(fieldSize);
        regDateField = new JTextField(20); regDateField.setFont(fieldFont); regDateField.setBorder(fieldBorder); regDateField.setPreferredSize(fieldSize);
        regLocationField = new JTextField(20); regLocationField.setFont(fieldFont); regLocationField.setBorder(fieldBorder); regLocationField.setPreferredSize(fieldSize);
        regRegistrarField = new JTextField(20); regRegistrarField.setFont(fieldFont); regRegistrarField.setBorder(fieldBorder); regRegistrarField.setPreferredSize(fieldSize);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(12, 24, 12, 24); // More space around components
        gbc2.anchor = GridBagConstraints.WEST;

        // Left column (Registration ID, Vehicle ID, Registered Date)
        gbc2.gridx = 0; gbc2.gridy = 0;
        JLabel regIDLabel = new JLabel("Registration ID:"); regIDLabel.setFont(boldFont);
        regPanel.add(regIDLabel, gbc2);
        gbc2.gridx = 1;
        regPanel.add(regIDField, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 1;
        JLabel vehicleIDLabel = new JLabel("Vehicle ID:"); vehicleIDLabel.setFont(boldFont);
        regPanel.add(vehicleIDLabel, gbc2);
        gbc2.gridx = 1;
        regPanel.add(regVehicleField, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 2;
        JLabel regDateLabel = new JLabel("Registered Date:"); regDateLabel.setFont(boldFont);
        regPanel.add(regDateLabel, gbc2);
        gbc2.gridx = 1;
        regPanel.add(regDateField, gbc2);

        // Right column (Location, Registrar)
        gbc2.gridx = 2; gbc2.gridy = 0;
        JLabel locationLabel = new JLabel("Location:"); locationLabel.setFont(boldFont);
        regPanel.add(locationLabel, gbc2);
        gbc2.gridx = 3;
        regPanel.add(regLocationField, gbc2);

        gbc2.gridx = 2; gbc2.gridy = 1;
        JLabel registrarLabel = new JLabel("Registrar:"); registrarLabel.setFont(boldFont);
        regPanel.add(registrarLabel, gbc2);
        gbc2.gridx = 3;
        regPanel.add(regRegistrarField, gbc2);



        // --- Button panel ---
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        addBtn = new JButton("ADD");
        modifyBtn = new JButton("Modify");
        transferBtn = new JButton("Transfer");
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        modifyBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        transferBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnPanel.add(addBtn);
        btnPanel.add(modifyBtn);
        btnPanel.add(transferBtn);

        registrationPanel.add(ownerPanel);
        registrationPanel.add(regPanel);
        registrationPanel.add(btnPanel);

        setRegistrationFieldsEditable(false);

        // Double-click anywhere to enable editing
        MouseAdapter enableEditListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setRegistrationFieldsEditable(true);
                regVehicleField.setEditable(false);
                regIDField.setEditable(false);
            }
        };
        ownerPanel.addMouseListener(enableEditListener);
        regPanel.addMouseListener(enableEditListener);

        // --- Button Logic ---

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (newOwnerPanel != null) {
                    registrationPanel.remove(newOwnerPanel);
                }
                registrationPanel.revalidate();
                registrationPanel.repaint();
                handleAdd();
                
            }
        });

        modifyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (newOwnerPanel != null) {
                    registrationPanel.remove(newOwnerPanel);
                }
                registrationPanel.revalidate();
                registrationPanel.repaint();
                setRegistrationFieldsEditable(false);
                ownerAadharField.setEditable(false);
                ownerPanField.setEditable(false);
                addBtn.setText("Add");
                isInAddMode = false;
                handleModify();
            }
        });

        transferBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (newOwnerPanel != null) {
                    registrationPanel.remove(newOwnerPanel);
                }
                setRegistrationFieldsEditable(false);
                ownerAadharField.setEditable(false);
                ownerPanField.setEditable(false);
                addBtn.setText("Add");
                isInAddMode = false;
                registrationPanel.revalidate();
                registrationPanel.repaint();
                handleTransfer();
            }
        });
        return registrationPanel;
    }
    
    private void loadRegistrationPanel(String regisID) {
        clearRegistrationFields();
        setRegistrationFieldsEditable(false);

        if (regisID == null || regisID.trim().isEmpty()) return;

        RegistrationEO reg = rtoOpImpl.findRegistrationByID(regisID);
        if (reg != null) {
            regDateField.setText("yyyy-MM-dd");
            regIDField.setText(reg.getRegistrationID());
            regVehicleField.setText(reg.getVehicleID() != null ? reg.getVehicleID().getVehicleID().toString() : "");
            regDateField.setText(reg.getRegistrationDate() != null ? reg.getRegistrationDate().toString() : "");
            regLocationField.setText(reg.getLocation() != null ? reg.getLocation() : "");
            regRegistrarField.setText(reg.getRegistrar() != null ? reg.getRegistrar() : "");
            OwnerEO ownerEO = rtoOpImpl.findOwnerByAadhar(reg.getOwnerAadhar().getOwnerAadhar());
            if (ownerEO != null) {
                ownerNameField.setText(ownerEO.getOwnerName());
                ownerAadharField.setText(ownerEO.getOwnerAadhar());
                ownerPhoneField.setText(ownerEO.getPhone() != null ? ownerEO.getPhone().toString() : "");
                ownerGenderField.setText(ownerEO.getGender());
                ownerAddressField.setText(ownerEO.getAddress());
                ownerPanField.setText(ownerEO.getPancard());
            }
        }
    }
    
    private void setRegistrationFieldsEditable(boolean editable) {
        ownerNameField.setEditable(editable);
        ownerGenderField.setEditable(editable);
        ownerPhoneField.setEditable(editable);
        ownerAddressField.setEditable(editable);
        regIDField.setEditable(editable);
        regVehicleField.setEditable(editable);
        regDateField.setEditable(editable);
        regLocationField.setEditable(editable);
        regRegistrarField.setEditable(!editable);
        // ownerAadharField and ownerPanField remain non-editable
    }

    private void clearRegistrationFields() {
        ownerNameField.setText("");
        ownerAadharField.setText("");
        ownerPhoneField.setText("");
        ownerAddressField.setText("");
        ownerPanField.setText("");
        ownerGenderField.setText("");
        regIDField.setText("");
        regVehicleField.setText("");
        regDateField.setText("");
        regLocationField.setText("");
        regRegistrarField.setText("");
    }

    private boolean areRegistrationFieldsComplete() {
        return !ownerNameField.getText().trim().isEmpty()
            && !ownerGenderField.getText().trim().isEmpty()
            && !ownerPhoneField.getText().trim().isEmpty()
            && !ownerAddressField.getText().trim().isEmpty()
            && !regIDField.getText().trim().isEmpty()
            && !regVehicleField.getText().trim().isEmpty()
            && !regDateField.getText().trim().isEmpty()
            && !regLocationField.getText().trim().isEmpty()
            && !regRegistrarField.getText().trim().isEmpty();
    }

    private void handleAdd() {
        String result = null;
        if (!isInAddMode) {
            clearRegistrationFields();
            regRegistrarField.setText(currentOwner.getDesignationID());
            setRegistrationFieldsEditable(true);
            ownerAadharField.setEditable(true);
            ownerPanField.setEditable(true);

            addBtn.setText("Save");
            isInAddMode = true;
            regVehicleField.requestFocus();
            
        } else {
            try {
                String vehicleIdStr = regVehicleField.getText().trim();
                String regisID = regIDField.getText().trim();

                System.out.println(vehicleIdStr);
                if (vehicleIdStr.isEmpty() || regisID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please feed all the data.", "Missing Data", JOptionPane.WARNING_MESSAGE);
                    regVehicleField.requestFocus();
                    return;
                }
                Integer vehicleId = null;
                try {
                    vehicleId = Integer.parseInt(vehicleIdStr);
                    if (vehicleId <= 0 || regisID.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter valid ID.", "Invalid Data", JOptionPane.WARNING_MESSAGE);
                        regVehicleField.requestFocus();
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Vehicle ID must be a number.", "Invalid Data", JOptionPane.WARNING_MESSAGE);
                    regVehicleField.requestFocus();
                    return;
                }
                VehicleEO vehicleEO = rtoOpImpl.findVehicleByID(vehicleId);
                if (vehicleEO == null) {
                    JOptionPane.showMessageDialog(null, "Vehicle Not Present!", "Error", JOptionPane.ERROR_MESSAGE);
                    regVehicleField.requestFocus();
                    return;
                }
        
                RegistrationEO regisEO = rtoOpImpl.findRegistrationByVehID(vehicleId);
                if (regisEO != null) {
                    JOptionPane.showMessageDialog(null, "Vehicle ID already registered!", "Error", JOptionPane.ERROR_MESSAGE);
                    regVehicleField.requestFocus();
                    return;
                }
                
                regisEO = rtoOpImpl.findRegistrationByID(regisID);
                if (regisEO != null) {
                    JOptionPane.showMessageDialog(null, "Registration ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    regVehicleField.requestFocus();
                    return;
                }

                // Fetch and validate owner details
                String ownerName = ownerNameField.getText().trim();
                String gender = ownerGenderField.getText().trim();
                String aadhar = ownerAadharField.getText().trim();
                String pan = ownerPanField.getText().trim();
                String address = ownerAddressField.getText().trim();
                String phoneStr = ownerPhoneField.getText().trim();
                if (aadhar.isEmpty() || pan.isEmpty() || ownerName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Owner's Aadhar, PAN, and Name.", "Missing Data", JOptionPane.WARNING_MESSAGE);
                    ownerAadharField.requestFocus();
                    return;
                }
                Long phone = null;
                try {
                    phone = Long.parseLong(phoneStr);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Owner's phone must be a number.", "Invalid Data", JOptionPane.WARNING_MESSAGE);
                    ownerPhoneField.requestFocus();
                    return;
                }

                OwnerEO ownerEO = rtoOpImpl.findOwnerByAadhar(aadhar);

                if (ownerEO != null) {
                    // Owner exists, modify details
                    ownerEO.setOwnerName(ownerName);
                    ownerEO.setGender(gender);
                    ownerEO.setAddress(address);
                    ownerEO.setPhone(phone);
                    rtoOpImpl.modifyOwner(ownerEO);
                } else {
                    // Owner not present, add new
                    ownerEO = new OwnerEO();
                    ownerEO.setOwnerAadhar(aadhar);
                    ownerEO.setPancard(pan);
                    ownerEO.setOwnerName(ownerName);
                    ownerEO.setGender(gender);
                    ownerEO.setAddress(address);
                    ownerEO.setPhone(phone);
                    String password = ownerName + (aadhar.length() >= 2 ? aadhar.substring(aadhar.length() - 2) : aadhar);
                    ownerEO.setPassword(password);
                    rtoOpImpl.addOwner(ownerEO);
                }

                // Fetch and validate registration details
                String regisDate = regDateField.getText().trim();
                if (regisDate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter todays Date.", "Missing Data", JOptionPane.WARNING_MESSAGE);
                    regIDField.requestFocus();
                    return;
                }
                // Validate date format
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                try {
                    java.util.Date parsed = sdf.parse(regisDate);
                } catch (java.text.ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter Registration Date in yyyy-MM-dd format.", "Invalid Date Format", JOptionPane.ERROR_MESSAGE);
                    regDateField.requestFocus();
                    return;
                }
                
                regisEO = new RegistrationEO();
                regisEO.setRegistrationID(regisID);
                regisEO.setLocation(regLocationField.getText().trim());
                regisEO.setRegistrationDate(java.sql.Date.valueOf(regisDate));
                regisEO.setVehicleID(vehicleEO);
                regisEO.setOwnerAadhar(ownerEO);
                regisEO.setRegistrar(regRegistrarField.getText());
                // Set other registration fields as needed

                result = rtoOpImpl.addRegistration(regisEO);

                JOptionPane.showMessageDialog(null, "Registration added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Reset fields and button
                setRegistrationFieldsEditable(false);
                ownerAadharField.setEditable(false);
                ownerPanField.setEditable(false);
                addBtn.setText("Add");
                isInAddMode = false;
                loadRegistrationPanel("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, result + "Error: " + ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleModify() {
        String regisField = regIDField.getText().trim();
        if (regisField.isEmpty()) {
            JOptionPane.showMessageDialog(registrationPanel, "Set Registration ID in top panel!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!areRegistrationFieldsComplete()) {
            JOptionPane.showMessageDialog(registrationPanel, "Incomplete fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String regisID = regIDField.getText().trim();
        List<VehicleOffenceEO> vehOffEOList = new ArrayList<VehicleOffenceEO>();
        vehOffEOList = rtoOpImpl.findVehicleOffenceByStatus(regisID, 0);
        
        RegistrationEO regis = new RegistrationEO();
        regis = rtoOpImpl.findRegistrationByID(regisID);
        
        if (!vehOffEOList.isEmpty()) {
            JOptionPane.showMessageDialog(registrationPanel, "Clear offence before modification!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(regis != null && regisID.equals(regis.getRegistrationID()) && !regis.getOwnerAadhar().getOwnerAadhar().equals(ownerAadharField.getText())){
            JOptionPane.showMessageDialog(registrationPanel, "Registration ID belongs to other!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;         
        }
        try {
            String aadhar = ownerAadharField.getText();
            OwnerEO ownerEO = new OwnerEO();
            ownerEO = rtoOpImpl.findOwnerByAadhar(aadhar);
            ownerEO.setOwnerName(ownerNameField.getText().trim());
            ownerEO.setGender(ownerGenderField.getText().trim());
            ownerEO.setOwnerAadhar(ownerAadharField.getText().trim());
            ownerEO.setPancard(ownerPanField.getText().trim());
            ownerEO.setPhone(Long.parseLong(ownerPhoneField.getText().trim()));
            ownerEO.setAddress(ownerAddressField.getText().trim());

            RegistrationEO regisEO = new RegistrationEO();
            regisEO.setRegistrationID(regIDField.getText().trim());
            VehicleEO vehicle = rtoOpImpl.findVehicleByID(Integer.parseInt(regVehicleField.getText().trim()));
            if (vehicle == null) throw new Exception("Vehicle not present!");
            regisEO.setVehicleID(vehicle);
            regisEO.setRegistrationDate(java.sql.Date.valueOf(regDateField.getText().trim()));
            regisEO.setLocation(regLocationField.getText().trim());
            regisEO.setRegistrar(currentOwner.getDesignationID());
            regisEO.setOwnerAadhar(ownerEO);

            // Update or add owner
            OwnerEO owner = new OwnerEO();
            owner = rtoOpImpl.findOwnerByAadhar(ownerEO.getOwnerAadhar());
                        
            if (owner != null) {
                rtoOpImpl.modifyOwner(ownerEO);
            } else {
                rtoOpImpl.addOwner(ownerEO);
            }

            // Update or add registration    
            if (regis != null) {
                System.out.println(rtoOpImpl.modifyRegistration(regisEO));
            } else {
                System.out.println(rtoOpImpl.addRegistration(regisEO));
            }
            JOptionPane.showMessageDialog(registrationPanel, "Registration modified!", "Success", JOptionPane.INFORMATION_MESSAGE);
            setRegistrationFieldsEditable(false);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(registrationPanel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return;
        }
    }
    
    private void handleTransfer() {
        String regisID = regIDField.getText().trim();
        if (regisID.isEmpty()) {
            JOptionPane.showMessageDialog(registrationPanel, "Set Registration ID in top panel!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<VehicleOffenceEO> vehOffEOList = new ArrayList<VehicleOffenceEO>();
        vehOffEOList = rtoOpImpl.findVehicleOffenceByStatus(regisID, 0);
        if (!vehOffEOList.isEmpty()) {
            JOptionPane.showMessageDialog(registrationPanel, "Clear offence before transfer!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        showNewOwnerTransferPanel(registrationPanel, regisID);
    }
    
    private void showNewOwnerTransferPanel(JPanel parentPanel, final String regisID) {
        newOwnerPanel = new JPanel(new GridBagLayout());
        newOwnerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            "New Owner Details",
            0, 0, new Font("Tahoma", Font.BOLD, 16)
        ));

        Font fieldFont = new Font("Tahoma", Font.PLAIN, 17);
        Font boldFont = new Font("Tahoma", Font.BOLD, 16);
        Border fieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(7, 12, 7, 12)
        );
        Dimension fieldSize = new Dimension(240, 36);

        newOwnerNameField = new JTextField(20); newOwnerNameField.setFont(fieldFont); newOwnerNameField.setBorder(fieldBorder); newOwnerNameField.setPreferredSize(fieldSize);
        newOwnerGenderField = new JTextField(20); newOwnerGenderField.setFont(fieldFont); newOwnerGenderField.setBorder(fieldBorder); newOwnerGenderField.setPreferredSize(fieldSize);
        newOwnerAadharField = new JTextField(20); newOwnerAadharField.setFont(fieldFont); newOwnerAadharField.setBorder(fieldBorder); newOwnerAadharField.setPreferredSize(fieldSize);
        newOwnerPanField = new JTextField(20); newOwnerPanField.setFont(fieldFont); newOwnerPanField.setBorder(fieldBorder); newOwnerPanField.setPreferredSize(fieldSize);
        newOwnerAddressField = new JTextField(20); newOwnerAddressField.setFont(fieldFont); newOwnerAddressField.setBorder(fieldBorder); newOwnerAddressField.setPreferredSize(fieldSize);
        newOwnerPhoneField = new JTextField(20); newOwnerPhoneField.setFont(fieldFont); newOwnerPhoneField.setBorder(fieldBorder); newOwnerPhoneField.setPreferredSize(fieldSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 24, 12, 24);
        gbc.anchor = GridBagConstraints.WEST;

        // Left column (Name, Gender, Aadhar)
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:"); nameLabel.setFont(boldFont);
        newOwnerPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        newOwnerPanel.add(newOwnerNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel genderLabel = new JLabel("Gender:"); genderLabel.setFont(boldFont);
        newOwnerPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        newOwnerPanel.add(newOwnerGenderField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel aadharLabel = new JLabel("Aadhar:"); aadharLabel.setFont(boldFont);
        newOwnerPanel.add(aadharLabel, gbc);
        gbc.gridx = 1;
        newOwnerPanel.add(newOwnerAadharField, gbc);

        // Right column (PAN, Address, Phone)
        gbc.gridx = 2; gbc.gridy = 0;
        JLabel panLabel = new JLabel("PAN Card:"); panLabel.setFont(boldFont);
        newOwnerPanel.add(panLabel, gbc);
        gbc.gridx = 3;
        newOwnerPanel.add(newOwnerPanField, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        JLabel addressLabel = new JLabel("Address:"); addressLabel.setFont(boldFont);
        newOwnerPanel.add(addressLabel, gbc);
        gbc.gridx = 3;
        newOwnerPanel.add(newOwnerAddressField, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("Phone:"); phoneLabel.setFont(boldFont);
        newOwnerPanel.add(phoneLabel, gbc);
        gbc.gridx = 3;
        newOwnerPanel.add(newOwnerPhoneField, gbc);

        // Button spans all columns
        makeTransferBtn = new JButton("Make Transfer Complete");
        makeTransferBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.CENTER;
        newOwnerPanel.add(makeTransferBtn, gbc);

        registrationPanel.add(newOwnerPanel);
        registrationPanel.revalidate();
        registrationPanel.repaint();

        makeTransferBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validate all fields
                if (newOwnerNameField.getText().trim().isEmpty() || newOwnerGenderField.getText().trim().isEmpty()
                    || newOwnerAadharField.getText().trim().isEmpty() || newOwnerPanField.getText().trim().isEmpty()
                    || newOwnerAddressField.getText().trim().isEmpty() || newOwnerPhoneField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(registrationPanel, "Incomplete fields!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                List<VehicleOffenceEO> vehOffEOList = new ArrayList<VehicleOffenceEO>();
                vehOffEOList = rtoOpImpl.findVehicleOffenceByStatus(regisID, 0);
                if (!vehOffEOList.isEmpty()) {
                    JOptionPane.showMessageDialog(registrationPanel, "Clear offence before transfer!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    String newAadhar = newOwnerAadharField.getText().trim();
                    OwnerEO newOwner = rtoOpImpl.findOwnerByAadhar(newAadhar);
                    if (newOwner == null) {
                        // Add new owner
                        newOwner = new OwnerEO();
                        newOwner.setOwnerName(newOwnerNameField.getText().trim());
                        newOwner.setOwnerAadhar(newAadhar);
                        newOwner.setGender(newOwnerGenderField.getText().trim());
                        newOwner.setPhone(Long.parseLong(newOwnerPhoneField.getText().trim()));
                        newOwner.setAddress(newOwnerAddressField.getText().trim());
                        newOwner.setPancard(newOwnerPanField.getText().trim());
                        String password = newOwner.getOwnerName() + newAadhar.substring(newAadhar.length() - 2);
                        newOwner.setPassword(password);
                        rtoOpImpl.addOwner(newOwner);
                        System.out.println(newOwner);
                        System.out.println("Add");
                    } else {
                        // Owner exists, update details
                        newOwner.setOwnerName(newOwnerNameField.getText().trim());
                        newOwner.setGender(newOwnerGenderField.getText().trim());
                        newOwner.setPhone(Long.parseLong(newOwnerPhoneField.getText().trim()));
                        newOwner.setAddress(newOwnerAddressField.getText().trim());
                        newOwner.setPancard(newOwnerPanField.getText().trim());
                        rtoOpImpl.modifyOwner(newOwner);
                        System.out.println(newOwner);
                        System.out.println("mod");
                    }
                    // Update registration to new owner
                    RegistrationEO regis = rtoOpImpl.findRegistrationByID(regisID);
                    regis.setOwnerAadhar(newOwner);
                    System.out.println(regis);
                    rtoOpImpl.transferRegistration(regis);

                    JOptionPane.showMessageDialog(registrationPanel, "Ownership transferred!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearRegistrationFields();
                    registrationPanel.remove(newOwnerPanel);
                    registrationPanel.revalidate();
                    registrationPanel.repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registrationPanel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
