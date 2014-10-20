package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.GroupUserDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.GroupUser;
import socialnetwork.model.datasource.JDBCDataSource;

public class GroupUserDaoImpl extends AbstractDao implements GroupUserDao {
	
	private static final String GET_GROUP_USERS_BY_USER_ID = "SELECT GROUP_ID, USER_ID FROM GROUP_USERS WHERE USER_ID = ? WHERE IS_DELETED = 'N'";
	private static final String GET_GROUP_USERS_BY_GROUP_ID = "SELECT GROUP_ID, USER_ID FROM GROUP_USERS WHERE GROUP_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_GROUP_USER_BY_USER_ID_AND_GROUP_ID = "SELECT GROUP_ID, USER_ID FROM GROUP_USERS WHERE USER_ID = ? AND GROUP_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_GROUP_USER = "INSERT INTO GROUP_USERS(GROUP_ID, USER_ID) VALUES (?,?)";
	private static final String DELETE_GROUP_POST = "UPDATE GROUP_USERS SET IS_DELETED = 'Y' WHERE GROUP_ID = ? AND USER_ID = ?";

	public GroupUserDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<GroupUser> getGroupUsersbyUserId(final Long userId) {
		return queryList(GET_GROUP_USERS_BY_USER_ID, getResultSetMapper(), userId);
	}

	@Override
	public List<GroupUser> getGroupUsersbyGroupId(final Long groupId) {
		return queryList(GET_GROUP_USERS_BY_GROUP_ID, getResultSetMapper(), groupId);
	}

	@Override
	public GroupUser getGroupUserbyUserIdAndGroupId(final Long userId, final Long groupId) {
		return querySingleResult(GET_GROUP_USER_BY_USER_ID_AND_GROUP_ID, getResultSetMapper(), userId, groupId);
	}

	@Override
	public void addGroupUser(final GroupUser groupUser) {
		executeUpdate(INSERT_GROUP_USER, groupUser.getGroupId(), groupUser.getUserId());		
	}

	@Override
	public void deleteGroupUser(Long groupId, Long userId) {
		executeUpdate(DELETE_GROUP_POST, groupId, userId);
	}
	
	private ResultSetMapper<GroupUser> getResultSetMapper() {
		return new ResultSetMapper<GroupUser>() {
			@Override
			public GroupUser map(ResultSet rs) throws SQLException {
				return new GroupUser(rs.getLong(1), rs.getLong(2));
			}

		};
	}
	
	

}
