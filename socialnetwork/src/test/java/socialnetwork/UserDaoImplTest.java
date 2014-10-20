package socialnetwork;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;
import socialnetwork.model.dao.UserDao;
import socialnetwork.model.dao.bean.User;
import socialnetwork.model.daofactory.DaoFactory;
import socialnetwork.model.daofactory.impl.OracleDaoFactory;
import socialnetwork.model.datasource.JDBCDataSource;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserDaoImplTest {

	private static UserDao userDao;

	@BeforeClass
	public static void setUp() {
		try {
			DaoFactory factory = new OracleDaoFactory(
					JDBCDataSource.getInstance());
			userDao = factory.getUserDao();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectUser() {
		
		User user =  new User("petro", "pet@gmail.com", "Petro", "male", "Hello all, I want ro make friends!", "Petrov", "asdf4f-+32sd", "/asdf/gse/df.gif", "user");
		userDao.addUser(user);		
		
		System.out.println(userDao.getAllUsers());
		assertNotNull(userDao.getAllUsers());
		
		System.out.println(userDao.findUserById(1l));
		assertNotNull(userDao.findUserById(1l));
		
		userDao.deleteUser(3l);
		
	}

}
