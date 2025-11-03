package org.demo.business;

import javax.ejb.Remote;

@Remote
public interface ServiceRequestSession {
     void sendMailMessage(String from, String to, String subject,  String content);
}
