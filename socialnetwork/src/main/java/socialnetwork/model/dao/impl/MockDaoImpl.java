package socialnetwork.model.dao.impl;

import java.util.Date;

import socialnetwork.model.dao.MockDao;
import socialnetwork.model.dao.bean.User;

public class MockDaoImpl implements MockDao {

	@Override
	public User findUserByNameAndPassword(String user, String password) {
		
		if (user.equals("root") && password.equals("root")) {
			return new User(1l, "root", "root@test.com", "Misha", "male",
					"Hello All", "Poliakov", "root", "/photo/123", new Date(),
					"admin");
		}
		
		return null;

	}

}
