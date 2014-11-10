package socialnetwork.model.facade;

import javax.ejb.Remote;

import socialnetwork.model.dao.bean.User;

@Remote
public interface UserFacadeRemote {
	
	public User findUserByNameAndPassword(String login, String password);
	public User findUserByEmail(String email);
	public void addUser(User user);

}
