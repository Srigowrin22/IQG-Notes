package examples.jms.queue;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

public class QueueReceiveInTx
{
 // Defines the JNDI context factory.
  public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

  // Defines the JMS connection factory for the queue.
  public final static String JMS_FACTORY="BibleJMSFactory";

  // Defines the queue.
  public final static String QUEUE="BibleJMSQueue";

  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueReceiver qreceiver;
  private Queue queue;
  private UserTransaction utx;

 /**
  * Receives message interface.
  */
  public void receiveMessages() throws Exception {
    Message msg = null;
    String msgText = "";

    try {
      // Set transaction timeout to 30 minutes.
      utx.setTransactionTimeout(1800);
      utx.begin();
      System.out.println("TRANSACTION BEGUN");

	  int c=0;
      do {
		       
				msg = qreceiver.receive();
			if (msg != null) {
			if (msg instanceof TextMessage) 
			{
				msgText = ((TextMessage)msg).getText();
				c++;

				if(c==3)
				{
					 throw new javax.transaction.SystemException();
				}
		     }
		  
		  else {
            msgText = msg.toString();
          }
          System.out.println("Message Received: "+ msgText );
          if (msgText.equalsIgnoreCase("quit")) {
            utx.commit();
            System.out.println("TRANSACTION COMMITTED");
          }
        }
      } while(msg != null && ! msgText.equalsIgnoreCase("quit"));
    } catch (JMSException jmse) {
      System.out.println("Error receiving JMS message: "+jmse);
      jmse.printStackTrace();
      throw jmse;
    } catch (javax.transaction.NotSupportedException nse) {
      System.out.println("TRANSACTION COULD NOT BEGIN DUE TO: "+ nse);
      throw nse;
    } catch (javax.transaction.RollbackException rbe) {
      System.out.println("TRANSACTION ROLLED BACK DUE TO: "+rbe);
      throw rbe;
    } catch (javax.transaction.HeuristicRollbackException hre) {
      System.out.println("TRANSACTION ROLLED BACK DUE TO: "+hre);
      throw hre;
    } catch (javax.transaction.HeuristicMixedException hme) {
      System.out.println("TRANSACTION ROLLED BACK DUE TO: "+hme);
      throw hme;
    } catch (javax.transaction.SystemException se) {
      System.out.println("TRANSACTION EXCEPTION: "+se);

	  utx.rollback();

	  System.out.println(" Transaction Rollbacked..... ");

      throw se;
    }
  }

  public void init(Context ctx, String queueName)
    throws NamingException, JMSException
  {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    utx = (UserTransaction) ctx.lookup("javax.transaction.UserTransaction");
    queue = (Queue) ctx.lookup(queueName);
    qreceiver = qsession.createReceiver(queue);
    qcon.start();
  }

  /**
   * Closes JMS objects.
   * @exception JMSException if JMS fails to close objects due to internal error
   */
  public void close() throws JMSException {
    qreceiver.close();
    qsession.close();
    qcon.close();
  }

 /**
  * main() method.
  *
  * @param args  WebLogic Server URL
  * @exception  Exception if execution fails
  */
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: java examples.jms.queue.QueueReceiveInTx WebLogicURL");
      return;
    }
    InitialContext ic = getInitialContext(args[0]);
    QueueReceiveInTx qr = new QueueReceiveInTx();
    qr.init(ic, QUEUE);

    System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message).");

    qr.receiveMessages();
    qr.close();
  }

  private static InitialContext getInitialContext(String url)
    throws NamingException
  {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, url);
    return new InitialContext(env);
  }

}




