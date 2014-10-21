package socialnetwork.listener;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import socialnetwork.model.daofactory.impl.OracleDaoFactory;
import socialnetwork.model.datasource.JDBCDataSource;
import socialnetwork.utils.DaoUtils;

@WebListener
public class ContextLoader implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			DaoUtils.setDaoFactory(new OracleDaoFactory(JDBCDataSource.getInstance()));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
