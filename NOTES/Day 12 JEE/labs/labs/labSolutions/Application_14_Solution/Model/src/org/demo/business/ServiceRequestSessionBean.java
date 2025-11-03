package org.demo.business;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jms.*;

import javax.naming.*;

@Stateless(name = "ServiceRequestSession",
           mappedName = "ServiceRequestSessionEJB_Solution")
@Remote
@Local
public class ServiceRequestSessionBean implements ServiceRequestSession,
                                                  ServiceRequestSessionLocal {
    public ServiceRequestSessionBean() {
    }



    public void sendMailMessage(String from, String to,
    String subject, String content) {
            try {
            System.out.println("Looking up CF");
            ConnectionFactory connectionFactory =
              (ConnectionFactory) new InitialContext()
                                    .lookup("weblogic.jms.demoConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            System.out.println("Starting Queue Session");
            Session queueSession = connection.createSession(false,
                                                    Session.AUTO_ACKNOWLEDGE);
            Destination queue = (Queue) new InitialContext()
                                                 .lookup("weblogic.jms.demoQueue");
            MessageProducer publisher =
                                        queueSession.createProducer(queue);
            Message message = queueSession.createMessage();
            message.setJMSType("MailMessage");
            message.setStringProperty("from", from);
            message.setStringProperty("to", to);
            message.setStringProperty("subject", subject);
            message.setStringProperty("content", content);
            publisher.send(message);
            System.out.println("Message Sent to JMS Queue");
            publisher.close();
            queueSession.close();
            connection.close();
            }
            catch (Throwable ex) { 
               ex.printStackTrace(); 
            }
      }

    

}

