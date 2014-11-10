package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import socialnetwork.model.command.ICommand;
import socialnetwork.model.dao.bean.User;
import socialnetwork.model.facade.UserFacadeLocal;
import socialnetwork.utils.Config;

public class RegisterCommand implements ICommand {
	
	private final static Logger LOG = Logger.getLogger(RegisterCommand.class);
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	
	private UserFacadeLocal userEJB = lookupUsersFacadeLocal();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		
		
		User user = new User(login, email, password);
		user.setRole("user");		
		
		userEJB.addUser(user);
		
		request.getSession().setAttribute("user", user);
		
		return Config.getInstance().getProperty(Config.MAIN);
	}
	
	private UserFacadeLocal lookupUsersFacadeLocal() {
		try {
			Context c = new InitialContext();
			return (UserFacadeLocal) c
					.lookup("java:module/UserFacade!socialnetwork.model.facade.UserFacadeLocal");
		} catch (NamingException ex) {
			LOG.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
	}

}
