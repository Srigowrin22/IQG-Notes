package org.demo.business;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.Session;
import java.util.Properties;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@MessageDriven(mappedName = "weblogic.jms.demoQueue",
               name = "SendMailMessageBean",
     activationConfig = {
       @ActivationConfigProperty(
         propertyName = "connectionFactoryJndiName",
          propertyValue = "weblogic.jms.demoConnectionFactory"),

       @ActivationConfigProperty(
         propertyName = "destinationName",
         propertyValue = "demoQueue"),
 
       @ActivationConfigProperty(
         propertyName = "destinationType",
         propertyValue = "javax.jms.Queue")

     })
public class SendMailMessageBean implements MessageListener {
    public void onMessage(Message message) {
        Properties props = new Properties();
                  props.put("mail.smtp.host", "srdemo.org");
                  Session session = Session.getInstance(props);
        try {
              String from = message.getStringProperty("from");
              String to = message.getStringProperty("to");
              String subject = message.getStringProperty("subject");
              String content = message.getStringProperty("content");
              javax.mail.Message msg = new MimeMessage(session);
              msg.setFrom(new InternetAddress(from));
              InternetAddress[] address = {new InternetAddress(to)};
              msg.setRecipients(
                      javax.mail.Message.RecipientType.TO, address);
              msg.setSubject(subject);
              msg.setSentDate(new java.util.Date());
              msg.setContent(content, "text/html");
              Transport.send(msg);
            }
            catch (Throwable ex) { ex.printStackTrace(); }

    }
}
