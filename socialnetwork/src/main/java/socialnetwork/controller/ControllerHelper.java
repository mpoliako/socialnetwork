/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import socialnetwork.model.command.ICommand;
import socialnetwork.model.command.impl.LoginCommand;
import socialnetwork.model.command.impl.NoCommand;
import socialnetwork.model.command.impl.RegisterCommand;

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
