package socialnetwork.model.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


import socialnetwork.utils.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {

    private Properties props;
    private ComboPooledDataSource cpds;
    private static JDBCDataSource datasource;

    private JDBCDataSource() throws IOException, SQLException {
        // load datasource properties
    	
        props = Utils.readProperties("datasource.properties");
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
        cpds.setUser(props.getProperty("username"));
        cpds.setPassword(props.getProperty("password"));

        cpds.setInitialPoolSize(new Integer((String) props.getProperty("initialPoolSize")));
        cpds.setAcquireIncrement(new Integer((String) props.getProperty("acquireIncrement")));
        cpds.setMaxPoolSize(new Integer((String) props.getProperty("maxPoolSize")));
        cpds.setMinPoolSize(new Integer((String) props.getProperty("minPoolSize")));
        cpds.setMaxStatements(new Integer((String) props.getProperty("maxStatements")));

    }

    public static JDBCDataSource getInstance() throws IOException, SQLException {
        if (datasource == null) {
              datasource = new JDBCDataSource();
              return datasource;
            } else {
              return datasource;
            }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
