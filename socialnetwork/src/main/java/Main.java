import java.io.IOException;
import java.sql.SQLException;

import socialnetwork.model.dao.GroupDao;
import socialnetwork.model.dao.MessageDao;
import socialnetwork.model.dao.PostDao;
import socialnetwork.model.dao.UserDao;
import socialnetwork.model.dao.bean.Message;
import socialnetwork.model.dao.bean.User;
import socialnetwork.model.daofactory.DaoFactory;
import socialnetwork.model.daofactory.impl.OracleDaoFactory;
import socialnetwork.model.datasource.JDBCDataSource;


public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		
		DaoFactory daoFactory = new OracleDaoFactory(JDBCDataSource.getInstance());
		// UserDao userDao = daoFactory.getUserDao();
		
		//User user = new User("petro", "pet@gmail.com", "Petro", "male", "Hello all, I want ro make friends!", "Petrov", "asdf4f-+32sd", "/asdf/gse/df.gif", "user");

		//System.out.println(userDao.findAllUsers());
		
		//userDao.addUser(user);
		
		//System.out.println(userDao.findUserById(3));
		
		//User user = userDao.findUserById(3);
		//user.setRole("admin");
		//user.setId(10l);
		//userDao.addOrUpdateUser(user);
		
		//System.out.println(userDao.findUserById(3)); 
		
		//MessageDao messageDao = daoFactory.getMessageDao();
		
		//Message message = new Message(3l, "Test message to admin", "test", 1l);
		
		//Message message = messageDao.findMessageById(2l);
		
		//message.setTitle("test test");
		
		//messageDao.addOrUpdateMessage(message);
		
		//System.out.println(messageDao.getAllMessages());
		//System.out.println(messageDao.findMessageById(1l));
		/*System.out.println(messageDao.getAllMessages());
		System.out.println(messageDao.getMessagesBySender(1l));
		System.out.println(messageDao.getMessagesByReceiver(1l));*/
		//System.out.println(messageDao.getMessagesBySenderAndReceiver(2l, 2l));
		
		PostDao postDao = daoFactory.getPostDao();
		//System.out.println(postDao.getAllPosts());
		System.out.println(postDao.getPostById(1l));
		System.out.println(postDao.getPostsByGroupId(1l));
		

	}

}
