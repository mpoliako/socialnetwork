package socialnetwork.model.daofactory;

import socialnetwork.model.dao.CommentDao;
import socialnetwork.model.dao.GroupDao;
import socialnetwork.model.dao.GroupImageDao;
import socialnetwork.model.dao.GroupPostDao;
import socialnetwork.model.dao.GroupUserDao;
import socialnetwork.model.dao.MessageDao;
import socialnetwork.model.dao.MockDao;
import socialnetwork.model.dao.PostDao;
import socialnetwork.model.dao.PostTagDao;
import socialnetwork.model.dao.RatingDao;
import socialnetwork.model.dao.TagDao;
import socialnetwork.model.dao.UserDao;
import socialnetwork.model.dao.UserFollowDao;
import socialnetwork.model.dao.UserImageDao;
import socialnetwork.model.datasource.JDBCDataSource;

public abstract class DaoFactory {

	protected final JDBCDataSource datasource;

	public DaoFactory(final JDBCDataSource datasource) {
		super();
		this.datasource = datasource;
	}

	public abstract UserDao getUserDao();

	public abstract MessageDao getMessageDao();

	public abstract PostDao getPostDao();

	public abstract UserFollowDao getUserFollowDao();

	public abstract CommentDao getCommentDao();

	public abstract RatingDao getRatingDao();

	public abstract TagDao getTagDao();

	public abstract PostTagDao getPostTagDao();

	public abstract UserImageDao getUserImageDao();

	public abstract GroupDao getGroupDao();

	public abstract GroupUserDao getGroupUserDao();
	
	public abstract GroupPostDao getGroupPostDao();
	
	public abstract GroupImageDao getGroupImageDao();
	
	public abstract MockDao getMockDao();

	public JDBCDataSource getDatasource() {
		return datasource;
	}

}
