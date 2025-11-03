import javax.swing.*;
import java.awt.*;

public class toolbar extends JFrame
{
	toolbar()
	{
		super( "Toolbar test" );
		JButton b1 = new JButton( "ok" );		b1.setToolTipText( "Ok man" );
		JButton b2 = new JButton( "cancel" );	b2.setToolTipText( "Cancel it chick" );

		JButton b3 = new JButton( "ok2" );		b3.setToolTipText( "man" );
		JButton b4 = new JButton( "cancel2" );	b4.setToolTipText( "chick" );

		JToolBar tb = new JToolBar();
		tb.add( b1 );
		tb.add( b2 );
		tb.addSeparator();
		tb.add( b3 );
		tb.add( b4 );

		getContentPane().add( "North", tb );
		
	}
	public static void  main( String a[] )
	{
		toolbar tb =  new toolbar();
		tb.setSize( 300, 300 );
		tb.setVisible( true );
	}
}