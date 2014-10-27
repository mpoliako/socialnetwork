package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import socialnetwork.mail.MailClient;
import socialnetwork.model.command.ICommand;
import socialnetwork.model.dao.bean.User;
import socialnetwork.utils.Config;
import socialnetwork.utils.DaoUtils;
import socialnetwork.utils.Message;

public class RecoverPasswordCommand implements ICommand {

	private final static Logger LOG = Logger.getLogger(RecoverPasswordCommand.class);
	private static final String EMAIL = "email";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {		

		String email = request.getParameter(EMAIL);
		
		LOG.info("Recover password for email = " + email);

		if (email == null) {
			LOG.warn("User email is null, return to login page");
			return Config.getInstance().getProperty(Config.LOGIN);
		}

		User user = DaoUtils.getDaoFactory().getUserDao()
				.findUserByEmail(email);
		
		
		if (user == null) {
			LOG.warn("No user was found by email: " + email + ". Return to login page");
			return Config.getInstance().getProperty(Config.LOGIN);
		}
		
		String message = Message.getInstance().getProperty(Message.RECOVER_PASSWORD).replaceAll("?1", user.getDisplayName()).replaceAll("?2", user.getPasswordHash());
		
		try {
			MailClient.getInstance().send(user.getEmail(), Message.getInstance().getProperty(Message.RECOVER_PASSWORD_TITLE), message);
			LOG.info("Mail successfully sent to email "+ user.getEmail() +". Return to login page");
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
		}		
		
		return Config.getInstance().getProperty(Config.LOGIN);

	}

}
