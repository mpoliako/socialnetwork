package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import socialnetwork.mail.MailClient;
import socialnetwork.model.command.ICommand;
import socialnetwork.model.dao.bean.User;
import socialnetwork.model.facade.UserFacadeLocal;
import socialnetwork.utils.Config;
import socialnetwork.utils.Message;

public class RecoverPasswordCommand implements ICommand {

	private final static Logger LOG = Logger.getLogger(RecoverPasswordCommand.class);
	private static final String EMAIL = "email";
	private UserFacadeLocal userEJB = lookupUsersFacadeLocal();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {		

		String email = request.getParameter(EMAIL);
		
		LOG.info("Recover password for email = " + email);

		if (email == null) {
			LOG.warn("User email is null, return to login page");
			return Config.getInstance().getProperty(Config.LOGIN);
		}

		final User user = userEJB.findUserByEmail(email);
		
		
		if (user == null) {
			LOG.warn("No user was found by email: " + email + ". Return to login page");
			return Config.getInstance().getProperty(Config.LOGIN);
		}
		
		final String message = Message.getInstance().getProperty(Message.RECOVER_PASSWORD).replaceAll(":1", user.getDisplayName()).replaceAll(":2", user.getPasswordHash());
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					MailClient.getInstance().send(user.getEmail(), Message.getInstance().getProperty(Message.RECOVER_PASSWORD_TITLE), message);
					LOG.info("Mail successfully sent to email "+ user.getEmail());
				} catch (MessagingException e) {
					LOG.error(e.getMessage(), e);
				}	
			}}).start();
		
			
		LOG.info("Return to login page");
		return Config.getInstance().getProperty(Config.LOGIN);

	}
	
	 private UserFacadeLocal lookupUsersFacadeLocal() {
	        try {
	            Context c =new InitialContext();
	            return (UserFacadeLocal)c.lookup("java:module/UserFacade!socialnetwork.model.facade.UserFacadeLocal");
	        } catch (NamingException ex) {
	        	LOG.error(ex.getMessage(), ex);
	            throw new RuntimeException(ex);
	        }
	    }

}
