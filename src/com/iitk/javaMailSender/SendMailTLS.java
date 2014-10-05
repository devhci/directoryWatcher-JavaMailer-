package com.iitk.javaMailSender;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailTLS {
 
	public  void send (String mess) {
 
		final String username = "devendku";
		final String password = "krishna";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
	
		
		props.put("mail.smtp.host", "smtp.cc.iitk.ac.in");
		props.put("mail.smtp.port", "25");
		
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("devendku@iitk.ac.in"));
			message.setRecipients(Message.RecipientType.CC,
				InternetAddress.parse("devendku@iitk.ac.in,koriawas1@gmail.com"));
			
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ mess);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}