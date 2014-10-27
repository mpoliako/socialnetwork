package socialnetwork.model.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import socialnetwork.utils.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {

	private ComboPooledDataSource cpds;
	private static JDBCDataSource datasource;
	private final static Logger LOG = Logger.getLogger(JDBCDataSource.class);

	private JDBCDataSource() throws IOException, SQLException {

		LOG.info("Prepare new DataSource with parameters: jdbc.driver = "
				+ Config.getInstance().getProperty(Config.JDBC_DRIVER)
				+ ", jdbcUrl = "
				+ Config.getInstance().getProperty(Config.JDBC_URL)
				+ ", username = "
				+ Config.getInstance().getProperty(Config.JDBC_USER)
				+ ", password = "
				+ Config.getInstance().getProperty(Config.JDBC_PASSWORD)
				+ ", initialPoolSize = "
				+ Config.getInstance().getProperty(
						Config.JDBC_INITIAL_POOL_SIZE)
				+ ", acquireIncrement = "
				+ Config.getInstance().getProperty(
						Config.JDBC_ACQUIRE_INCREMENT) + ", maxPoolSize = "
				+ Config.getInstance().getProperty(Config.JDBC_MAX_POOL_SIZE)
				+ ", minPoolSize = "
				+ Config.getInstance().getProperty(Config.JDBC_MIN_POOL_SIZE)
				+ ", maxStatements = "
				+ Config.getInstance().getProperty(Config.JDBC_MAX_STATEMENTS));

		try {
			Class.forName(Config.getInstance().getProperty(Config.JDBC_DRIVER));
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}

		cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(Config.getInstance().getProperty(Config.JDBC_URL));
		cpds.setUser(Config.getInstance().getProperty(Config.JDBC_USER));
		cpds.setPassword(Config.getInstance().getProperty(Config.JDBC_PASSWORD));

		cpds.setInitialPoolSize(new Integer(Config.getInstance().getProperty(
				Config.JDBC_INITIAL_POOL_SIZE)));
		cpds.setAcquireIncrement(new Integer(Config.getInstance().getProperty(
				Config.JDBC_ACQUIRE_INCREMENT)));
		cpds.setMaxPoolSize(new Integer(Config.getInstance().getProperty(
				Config.JDBC_MAX_POOL_SIZE)));
		cpds.setMinPoolSize(new Integer(Config.getInstance().getProperty(
				Config.JDBC_MIN_POOL_SIZE)));
		cpds.setMaxStatements(new Integer(Config.getInstance().getProperty(
				Config.JDBC_MAX_STATEMENTS)));

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
