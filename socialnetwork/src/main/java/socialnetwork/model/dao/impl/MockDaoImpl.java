package socialnetwork.model.dao.impl;

import socialnetwork.model.dao.MockDao;

public class MockDaoImpl implements MockDao {

	@Override
	public boolean login(String user, String password) {
		if (user.equals("root") && password.equals("root"))
			return true;
		return false;
	}

}
