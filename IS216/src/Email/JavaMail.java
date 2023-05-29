package Email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	private String to;
	private String subject;
	private String msg;
	private final String from = "ptrinh931@gmail.com";
	private final String password = "qoubneyjfmznrhpm";
	
	public JavaMail(String to, String subject, String msg) {
		this.to = to;
		this.subject = subject;
		this.msg = msg;
	}
	
	public void sendEmail() {
	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    
	    Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {
		       protected PasswordAuthentication getPasswordAuthentication() {  
		       return new PasswordAuthentication(from,password);  
		   }  
		   });  
	    
	    try {
		   Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setText(msg);;  
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
		   
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
