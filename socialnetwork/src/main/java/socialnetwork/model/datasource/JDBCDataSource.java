package socialnetwork.model.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import socialnetwork.utils.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {

	private Properties props;
	private ComboPooledDataSource cpds;
	private static JDBCDataSource datasource;
	private final static Logger LOG = Logger.getLogger(JDBCDataSource.class);

	private JDBCDataSource() throws IOException, SQLException {

		props = Utils.readProperties("datasource.properties");

		LOG.info("Prepare new DataSource with parameters: jdbc.driver = "
				+ props.getProperty("jdbc.driver") + ", jdbcUrl = "
				+ props.getProperty("jdbcUrl") + ", username = "
				+ props.getProperty("username") + ", password = "
				+ props.getProperty("password") + ", initialPoolSize = "
				+ props.getProperty("initialPoolSize")
				+ ", acquireIncrement = "
				+ props.getProperty("acquireIncrement") + ", maxPoolSize = "
				+ props.getProperty("maxPoolSize") + ", minPoolSize = "
				+ props.getProperty("minPoolSize") + ", maxStatements = "
				+ props.getProperty("maxStatements"));

		try {
			Class.forName(props.getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}

		cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
		cpds.setUser(props.getProperty("username"));
		cpds.setPassword(props.getProperty("password"));

		cpds.setInitialPoolSize(new Integer((String) props
				.getProperty("initialPoolSize")));
		cpds.setAcquireIncrement(new Integer((String) props
				.getProperty("acquireIncrement")));
		cpds.setMaxPoolSize(new Integer((String) props
				.getProperty("maxPoolSize")));
		cpds.setMinPoolSize(new Integer((String) props
				.getProperty("minPoolSize")));
		cpds.setMaxStatements(new Integer((String) props
				.getProperty("maxStatements")));

		LOG.info("DataSource successfully initialized");

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
