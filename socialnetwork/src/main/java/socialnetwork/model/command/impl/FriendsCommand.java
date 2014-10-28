package socialnetwork.model.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import socialnetwork.model.command.ICommand;
import socialnetwork.utils.Config;

public class FriendsCommand implements ICommand {
	
	private final static Logger LOG = Logger.getLogger(FriendsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		//LOG.info(Config.getInstance().getProperty(Config.FRIENDS));
		return Config.getInstance().getProperty(Config.FRIENDS);
	}

}
