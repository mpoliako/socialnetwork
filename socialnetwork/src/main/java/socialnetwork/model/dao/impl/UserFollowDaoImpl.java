package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.UserFollowDao;
import socialnetwork.model.dao.bean.UserFollow;
import socialnetwork.model.datasource.JDBCDataSource;

public class UserFollowDaoImpl extends AbstractDao implements UserFollowDao {
	
	private static final String GET_USER_FOLLOWS_BY_USER_ID = "SELECT USER_ID, FOLLOWER_ID FROM USER_FOLLOWS WHERE USER_ID = ? WHERE IS_DELETED = 'N'";
	private static final String GET_USER_FOLLOWS_BY_FOLLOWER_ID = "SELECT USER_ID, FOLLOWER_ID FROM USER_FOLLOWS WHERE FOLLOWER_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_USER_FOLLOWS = "INSERT INTO USER_FOLLOWS(USER_ID, FOLLOWER_ID) values (?,?)";
	private static final String DELETE_USER_FOLLOW = "UPDATE USER_FOLLOWS SET IS_DELETED = 'Y' WHERE USER_ID = ? AND FOLLOWER_ID = ?";
	
	public UserFollowDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<UserFollow> getUserFollowsByUserId(final Long userId) {
		return queryList(GET_USER_FOLLOWS_BY_USER_ID, getResultSetMapper(), userId);
	}

	@Override
	public List<UserFollow> getUserFollowsByFollowerId(final Long followerId) {
		return queryList(GET_USER_FOLLOWS_BY_FOLLOWER_ID, getResultSetMapper(), followerId);
	}

	@Override
	public void addUserFollow(final UserFollow userFollow) {
		executeUpdate(INSERT_USER_FOLLOWS, userFollow.getUserId(), userFollow.getFollowerId());
	}

	@Override
	public void deleteUserFollow(Long userId, Long followerId) {
		executeUpdate(DELETE_USER_FOLLOW, userId, followerId);
	}
	
	private ResultSetMapper<UserFollow> getResultSetMapper() {
		return new ResultSetMapper<UserFollow>() {
			@Override
			public UserFollow map(ResultSet rs) throws SQLException {
				return new UserFollow(rs.getLong(1), rs.getLong(2));
			}

		};
	}
	

}
