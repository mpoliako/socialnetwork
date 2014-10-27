package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.User;

public interface UserDao {
	
	public List<User> getAllUsers();
	public User findUserById(final Long id);
	public User findUserByNameAndPassword(final String login, final String password);
	public User findUserByEmail(String email);
	public void addUser(final User user);
	public void updateUser(final User user);
	public void addOrUpdateUser(final User user);
	public void deleteUser(final Long id);

}
