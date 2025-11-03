package examples.jms.topic;

import java.io.*;
import java.util.*;
import javax.transaction.*;
import javax.naming.*;
import javax.jms.*;
import javax.rmi.PortableRemoteObject;

public class TopicSend
{
  // Defines the JNDI context factory.
  public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
  // Defines the JMS connection factory.
  public final static String JMS_FACTORY="BibleJMSFactory";
  // Defines the topic.
  public final static String TOPIC="BibleJMSTopic";

  protected TopicConnectionFactory tconFactory;
  protected TopicConnection tcon;
  protected TopicSession tsession;
  protected TopicPublisher tpublisher;
  protected Topic topic;
  protected TextMessage msg;

  public void init(Context ctx, String topicName)
    throws NamingException, JMSException
  {
    tconFactory = (TopicConnectionFactory) 
      PortableRemoteObject.narrow(ctx.lookup(JMS_FACTORY),
                                  TopicConnectionFactory.class);
    tcon = tconFactory.createTopicConnection();
    tsession = tcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    topic = (Topic) PortableRemoteObject.narrow(ctx.lookup(topicName), Topic.class);
    tpublisher = tsession.createPublisher(topic);
    msg = tsession.createTextMessage();
    tcon.start();
  }

  /**
   * Sends a message to a JMS topic.
   *
   * @param message message to be sent
   * @exception JMSException if JMS fails to send message due to internal error
   *
   */
  public void send(String message) throws JMSException {
    msg.setText(message);
    tpublisher.publish(msg);
  }

  /**
   * Closes JMS objects.
   *
   * @exception JMSException if JMS fails to close objects due to internal error
   */
  public void close() throws JMSException {
    tpublisher.close();
    tsession.close();
    tcon.close();
  }

/**
  * main() method.
  *
  * @param args WebLogic Server URL
  * @exception Exception if operation fails
  */
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: java examples.jms.topic.TopicSend WebLogicURL");
      return;
    }
    InitialContext ic = getInitialContext(args[0]);
    TopicSend ts = new TopicSend();
    ts.init(ic, TOPIC);
    readAndSend(ts);
    ts.close();
  }

 /**
  * Prompts, reads, and sends a message.
  *
  * @param ts TopicSend
  * @exception IOException if problem occurs during read/write operation
  * @exception JMSException if JMS fails due to internal error
  */
  protected static void readAndSend(TopicSend ts)
    throws IOException, JMSException
  {
    BufferedReader msgStream = new BufferedReader (new InputStreamReader(System.in));
    String line=null;
    do {
      System.out.print("Enter message (\"quit\" to quit): ");
      line = msgStream.readLine();
      if (line != null && line.trim().length() != 0) {
        ts.send(line);
        System.out.println("JMS Message Sent: "+line+"\n");
      }
    } while (line != null && ! line.equalsIgnoreCase("quit"));
  }

/**
  * Get initial JNDI context.
  *
  * @param url Weblogic URL.
  * @exception  NamingException if problem occurs with JNDI context interface
  */
  protected static InitialContext getInitialContext(String url)
    throws NamingException
  {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, url);
    env.put("weblogic.jndi.createIntermediateContexts", "true");
    return new InitialContext(env);
  }

}

