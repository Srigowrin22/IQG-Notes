import java.awt.event.*;
import javax.swing.*;

public class TimerTest 
{
  public TimerTest() {
    ActionListener act = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Swing is powerful!!");
      }
    };
    Timer tim = new Timer(1000, act);
    tim.start();

    while(true)
	{
		
	}
  }

  public static void main( String args[] ) {
    new TimerTest();
  }
}
