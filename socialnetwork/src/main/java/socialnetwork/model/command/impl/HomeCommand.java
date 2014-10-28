package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socialnetwork.model.command.ICommand;
import socialnetwork.utils.Config;

public class HomeCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		return Config.getInstance().getProperty(Config.HOME);
	}

}
