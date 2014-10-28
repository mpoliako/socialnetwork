/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import socialnetwork.model.command.ICommand;
import socialnetwork.model.command.impl.FriendsCommand;
import socialnetwork.model.command.impl.GroupsCommand;
import socialnetwork.model.command.impl.HomeCommand;
import socialnetwork.model.command.impl.LoginCommand;
import socialnetwork.model.command.impl.MessagesCommand;
import socialnetwork.model.command.impl.NoCommand;
import socialnetwork.model.command.impl.RecoverPasswordCommand;
import socialnetwork.model.command.impl.RegisterCommand;
import socialnetwork.model.command.impl.SupportCommand;

/**
 *
 * @author Artem
 */
public class ControllerHelper {

    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private ControllerHelper() {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("recoverpassword", new RecoverPasswordCommand());
        commands.put("home", new HomeCommand());
        commands.put("friends", new FriendsCommand());
        commands.put("messages", new MessagesCommand());
        commands.put("groups", new GroupsCommand());
        commands.put("support", new SupportCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
