package socialnetwork.mail;

import com.sun.mail.smtp.SMTPTransport;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import socialnetwork.utils.Config;


public class MailClient {
	
	private static MailClient mailclient;
	private static final Properties props = System.getProperties(); 
	private static final Logger LOG = Logger.getLogger(MailClient.class);
	
	private String mailUserName;
	private String mailPassword;
	
    private MailClient() {
        props.setProperty(Config.SMTP_HOST, Config.getInstance().getProperty(Config.SMTP_HOST));
        props.setProperty(Config.SMTP_SOCKET_FACTORY,  Config.getInstance().getProperty(Config.SMTP_SOCKET_FACTORY));
        props.setProperty(Config.SMTP_SOCKET_FACTORY_FALLBACK, Config.getInstance().getProperty(Config.SMTP_SOCKET_FACTORY_FALLBACK));
        props.setProperty(Config.SMTP_PORT, Config.getInstance().getProperty(Config.SMTP_PORT));
        props.setProperty(Config.SMTP_SOCKET_FACTORY_PORT, Config.getInstance().getProperty(Config.SMTP_SOCKET_FACTORY_PORT));
        props.setProperty(Config.SMTPS_AUTH, Config.getInstance().getProperty(Config.SMTPS_AUTH));
        props.put(Config.SMTPS_QUITWAIT, Config.getInstance().getProperty(Config.SMTPS_QUITWAIT));
        
        if(Config.getInstance().getProperty(Config.SMTP_USER_NAME) != null && Config.getInstance().getProperty(Config.SMTP_PASSWORD) != null) {
        	mailUserName = Config.getInstance().getProperty(Config.SMTP_USER_NAME);
        	mailPassword = Config.getInstance().getProperty(Config.SMTP_PASSWORD);
        }        
        
    }
    
    /**
     * send email using SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send(String recipientEmail, String title, String message) throws AddressException, MessagingException {
    	send(mailUserName, mailPassword, recipientEmail, "", title, message);
    }

    /**
     * send email using SMTP server.
     *
     * @param username username
     * @param password password
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
    	send(username, password, recipientEmail, "", title, message);
    }
    
    /**
     * Send email using GMail SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send(String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
    	send(mailUserName, mailPassword, recipientEmail, ccEmail, title, message);
    }	

    /**
     * Send email using GMail SMTP server.
     *
     * @param username username
     * @param password password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        
        LOG.debug("Prepare to send email with parameters: username = " + username + ", password=" +password+ ", recipientEmail=" +recipientEmail+ ", ccEmail=" +ccEmail+ ", title=" +title+ ", message=" + message);
        
        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, Config.getInstance().getProperty(Config.SMTP_ENCODING));
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport(Config.getInstance().getProperty(Config.SMTPS_TRANSPORT));

        t.connect(Config.getInstance().getProperty(Config.SMTP_HOST), username, password);
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
        
        LOG.debug("Message successfully sent");
    }
    
    public static MailClient getInstance() {
		if (mailclient == null) {
			mailclient = new MailClient();
			return mailclient;
		} else {
			return mailclient;
		}
	}
}
