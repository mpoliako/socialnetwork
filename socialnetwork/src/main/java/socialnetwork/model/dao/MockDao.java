package socialnetwork.model.dao;

import socialnetwork.model.dao.bean.User;

public interface MockDao {

	public User findUserByNameAndPassword(String user, String password);
	
}
