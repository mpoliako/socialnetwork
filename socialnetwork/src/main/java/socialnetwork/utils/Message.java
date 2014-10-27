/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork.utils;

import java.util.ResourceBundle;

public class Message {

    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "socialnetwork.utils.messages";
    public static final String SERVLET_EXECPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String RECOVER_PASSWORD = "RECOVER_PASSWORD";
    public static final String RECOVER_PASSWORD_TITLE = "RECOVER_PASSWORD_TITLE";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
