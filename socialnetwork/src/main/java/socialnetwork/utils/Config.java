/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork.utils;

import java.util.ResourceBundle;

public class Config {

    private static Config instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "socialnetwork.utils.config";
    public static final String DRIVER = "DRIVER";
    public static final String URL = "URL";
    public static final String MAIN = "MAIN";
    public static final String ERROR = "ERROR";
    public static final String LOGIN = "LOGIN";
    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_DRIVER = "jdbc.driver";
    public static final String JDBC_USER = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";
    public static final String JDBC_ACQUIRE_INCREMENT = "jdbc.acquireIncrement";
    public static final String JDBC_INITIAL_POOL_SIZE= "jdbc.initialPoolSize";
    public static final String JDBC_MAX_POOL_SIZE = "jdbc.maxPoolSize";
    public static final String JDBC_MIN_POOL_SIZE = "jdbc.minPoolSize";
    public static final String JDBC_MAX_STATEMENTS = "jdbc.maxStatements";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
