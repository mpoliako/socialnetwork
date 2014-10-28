package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socialnetwork.model.command.ICommand;
import socialnetwork.model.dao.bean.User;
import socialnetwork.utils.Config;
import socialnetwork.utils.DaoUtils;
import socialnetwork.utils.Message;

public class LoginCommand implements ICommand {

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		String page = null;
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);	
		
		//User user = DaoUtils.getDaoFactory().getUserDao().findUserByNameAndPassword(login, password);
		User user = DaoUtils.getDaoFactory().getMockDao().findUserByNameAndPassword(login, password);

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

}
