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
import socialnetwork.utils.Config;
import socialnetwork.utils.Message;
import socialnetwork.model.facade.UserFacadeLocal;

public class LoginCommand implements ICommand {
	
	private final static Logger LOG = Logger.getLogger(RecoverPasswordCommand.class);

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";

	private UserFacadeLocal userEJB = lookupUsersFacadeLocal();
	

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		String page = null;
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		
		//userEJB = lookupUsersFacadeLocal();
		User user = userEJB.findUserByNameAndPassword(login, password);
		//User user = DaoUtils.getDaoFactory().getUserDao().findUserByNameAndPassword(login, password);
		//User user = DaoUtils.getDaoFactory().getMockDao().findUserByNameAndPassword(login, password);

		if (user != null) {
			request.getSession().setAttribute("user", user);
			page = Config.getInstance().getProperty(Config.MAIN);
		} else {
			request.getSession().setAttribute("error",
					Message.getInstance().getProperty(Message.LOGIN_ERROR));
			page = Config.getInstance().getProperty(Config.ERROR);
		}

		return page;
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
