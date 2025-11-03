
import javax.swing.*;
import java.awt.event.*;

public class FileChooserDemoSan
{
	public void jbInit() 
	{
		final JFrame frameRef = new JFrame();
		frameRef.setSize(300,400);
		frameRef.setVisible(true);
		frameRef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frameRef.setLayout(new java.awt.FlowLayout());
		final JFileChooser fc = new JFileChooser();

		final JTextField ProofTxtFld = new JTextField(25);
		JButton chooseBtn = new JButton("...");
		chooseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(frameRef);
			
				if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fc.getSelectedFile();
                    //this is where a real application would open the file.
                    ProofTxtFld.setText(file.getPath());
            
				}
			}
		});
		frameRef.add(ProofTxtFld);
		frameRef.add(chooseBtn);

	}

	public static void main(String args[])
	{
		FileChooserDemoSan demo = new FileChooserDemoSan();

		demo.jbInit();
	}
	

}
