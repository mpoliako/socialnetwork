package socialnetwork.model.daofactory.impl;

import socialnetwork.model.dao.CommentDao;
import socialnetwork.model.dao.GroupDao;
import socialnetwork.model.dao.GroupImageDao;
import socialnetwork.model.dao.GroupPostDao;
import socialnetwork.model.dao.GroupUserDao;
import socialnetwork.model.dao.MessageDao;
import socialnetwork.model.dao.PostDao;
import socialnetwork.model.dao.PostTagDao;
import socialnetwork.model.dao.RatingDao;
import socialnetwork.model.dao.TagDao;
import socialnetwork.model.dao.UserDao;
import socialnetwork.model.dao.UserFollowDao;
import socialnetwork.model.dao.UserImageDao;
import socialnetwork.model.dao.impl.CommentDaoImpl;
import socialnetwork.model.dao.impl.GroupDaoImpl;
import socialnetwork.model.dao.impl.GroupImageDaoImpl;
import socialnetwork.model.dao.impl.GroupPostDaoImpl;
import socialnetwork.model.dao.impl.GroupUserDaoImpl;
import socialnetwork.model.dao.impl.MessageDaoImpl;
import socialnetwork.model.dao.impl.PostDaoImpl;
import socialnetwork.model.dao.impl.PostTagDaoImpl;
import socialnetwork.model.dao.impl.RatingDaoImpl;
import socialnetwork.model.dao.impl.TagDaoImpl;
import socialnetwork.model.dao.impl.UserDaoImpl;
import socialnetwork.model.dao.impl.UserFollowDaoImpl;
import socialnetwork.model.dao.impl.UserImageDaoImpl;
import socialnetwork.model.daofactory.DaoFactory;
import socialnetwork.model.datasource.JDBCDataSource;

public class OracleDaoFactory extends DaoFactory {

	public OracleDaoFactory(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl(datasource);
	}

	@Override
	public MessageDao getMessageDao() {
		return new MessageDaoImpl(datasource);
	}

	@Override
	public PostDao getPostDao() {
		return new PostDaoImpl(datasource);
	}

	@Override
	public UserFollowDao getUserFollowDao() {
		return new UserFollowDaoImpl(datasource);
	}

	@Override
	public CommentDao getCommentDao() {
		return new CommentDaoImpl(datasource);
	}

	@Override
	public RatingDao getRatingDao() {
		return new RatingDaoImpl(datasource);
	}

	@Override
	public TagDao getTagDao() {
		return new TagDaoImpl(datasource);
	}

	@Override
	public PostTagDao getPostTagDao() {
		return new PostTagDaoImpl(datasource);
	}

	@Override
	public UserImageDao getUserImageDao() {
		return new UserImageDaoImpl(datasource);
	}

	@Override
	public GroupDao getGroupDao() {
		return new GroupDaoImpl(datasource);
	}

	@Override
	public GroupUserDao getGroupUserDao() {
		return new GroupUserDaoImpl(datasource);
	}

	@Override
	public GroupPostDao getGroupPostDao() {
		return new GroupPostDaoImpl(datasource);
	}

	@Override
	public GroupImageDao getGroupImageDao() {
		return new GroupImageDaoImpl(datasource);
	}

}
