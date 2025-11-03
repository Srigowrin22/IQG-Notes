package training.iqgateway.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import training.iqgateway.entities.AdminEO;
import training.iqgateway.entities.OwnerEO;

public class RTODashboard {
    public RTODashboard() {
        AdminEO currentOwner = getCurrentOwner();
        JFrame frame = new RTODashboardUI(currentOwner);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }

    private AdminEO getCurrentOwner() {
            // TODO: Replace with actual logic to get logged-in owner
            AdminEO adminEO = new AdminEO();
            adminEO.setDesignationID("SAMPL123");
            // Set other owner properties as needed
            return adminEO;
        }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RTODashboard();
    }
}
