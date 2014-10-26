package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socialnetwork.model.command.ICommand;
import socialnetwork.model.dao.bean.User;
import socialnetwork.utils.Config;
import socialnetwork.utils.DaoUtils;

public class RegisterCommand implements ICommand {
	
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		
		
		User user = new User(login, email, password);
		user.setRole("user");		
		
		DaoUtils.getDaoFactory().getUserDao().addUser(user);
		
		request.getSession().setAttribute("user", user);
		
		return Config.getInstance().getProperty(Config.MAIN);
	}

}
