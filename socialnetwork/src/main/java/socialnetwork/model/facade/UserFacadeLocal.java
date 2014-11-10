package socialnetwork.model.facade;

import javax.ejb.Local;

import socialnetwork.model.dao.bean.User;

@Local
public interface UserFacadeLocal {

	public User findUserByNameAndPassword(String login, String password);
	public User findUserByEmail(String email);
	public void addUser(User user);
}
