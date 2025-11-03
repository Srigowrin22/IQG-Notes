package training.iqgateway;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CustomerRegistrationFrame extends JFrame {
    public CustomerRegistrationFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "New Customer Registration" );
    }
}
