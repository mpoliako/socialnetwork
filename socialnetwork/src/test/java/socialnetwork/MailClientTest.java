package socialnetwork;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import socialnetwork.mail.MailClient;

public class MailClientTest {

	@Test
	public void testSendMail() {
		try {
			MailClient.getInstance().send("mishapolyakov.ts73@gmail.com", "df", "sdafasdfds");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
