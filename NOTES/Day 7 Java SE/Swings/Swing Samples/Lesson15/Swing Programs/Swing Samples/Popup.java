import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Popup extends JFrame
{
	JPopupMenu jpm;
	
	public Popup()
	{
		setTitle(" Poup Menu Example ");
		JPanel pan=(JPanel)getContentPane();
		pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
		JButton jb=new JButton(" Press This Button to Bring up the PopupMenu");
		JButton ex=new JButton(" Exit ");
		pan.add(jb);
		pan.add(ex);
		
		jb.addMouseListener (new MyListener());
		
		jpm=new JPopupMenu();
		JMenuItem one =new JMenuItem(" JMenuItem");
		jpm.add(one);
		
		JCheckBoxMenuItem cbm1=new JCheckBoxMenuItem(" JCheck Box Menu Item");
		JCheckBoxMenuItem cbm2=new JCheckBoxMenuItem(" JCheck Box Menu Item");
		
		
		jpm.add(cbm1);
		jpm.add(cbm2);
		
		jpm.addSeparator();
		jpm.setBackground(Color.lightGray);
		
		JRadioButtonMenuItem rbm1=new JRadioButtonMenuItem(" JCheck Box Menu Item");
		JRadioButtonMenuItem rbm2=new JRadioButtonMenuItem(" JCheck Box Menu Item");
		
		rbm1.setSelected(true);
		ButtonGroup BG=new ButtonGroup();
		BG.add(rbm1);
		BG.add(rbm2);
		
		jpm.add(rbm1);
		jpm.add(rbm2);
		jpm.addSeparator();
		
	}
	
	class MyListener extends MouseAdapter
	{
		public void mouseReleased(MouseEvent e)
		{
			jpm.show((JComponent)e.getSource(),e.getX(),e.getY());
		}
	}

	public static void main(String[] args)
	{
		Popup p=new Popup();
		p.setForeground(Color.black);
		p.setBackground(Color.lightGray);
		p.setVisible(true);
		
		

	}

}
