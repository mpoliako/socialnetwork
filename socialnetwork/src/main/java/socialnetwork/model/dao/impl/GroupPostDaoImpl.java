package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.GroupPostDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.GroupPost;
import socialnetwork.model.datasource.JDBCDataSource;

public class GroupPostDaoImpl extends AbstractDao implements GroupPostDao {
	
	private static final String GET_GROUP_POSTS_BY_GROUP_ID = "SELECT GROUP_ID, POST_ID FROM GROUP_POSTS WHERE GROUP_ID = ? WHERE IS_DELETED = 'N'";
	private static final String GET_GROUP_POSTS_BY_POST_ID = "SELECT GROUP_ID, POST_ID FROM GROUP_POSTS WHERE POST_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_GROUP_POST_BY_GROUP_ID_AND_POST_ID = "SELECT GROUP_ID, POST_ID FROM GROUP_POSTS WHERE GROUP_ID = ? AND POST_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_GROUP_POST = "INSERT INTO GROUP_POSTS(GROUP_ID, POST_ID) VALUES (?,?)";
	private static final String DELETE_GROUP_POST = "UPDATE GROUP_POSTS SET IS_DELETED = 'Y' WHERE GROUP_ID = ? AND POST_ID = ?";

	public GroupPostDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<GroupPost> getGroupPostsByGroupId(final Long groupId) {
		return queryList(GET_GROUP_POSTS_BY_GROUP_ID, getResultSetMapper(), groupId);
	}

	@Override
	public List<GroupPost> getGroupPostsByPostId(final Long postId) {
		return queryList(GET_GROUP_POSTS_BY_POST_ID, getResultSetMapper(), postId);
	}

	@Override
	public GroupPost getGroupPostByGroupIdAndPostId(final Long groupId, final Long postId) {
		return querySingleResult(GET_GROUP_POST_BY_GROUP_ID_AND_POST_ID, getResultSetMapper(), groupId, postId);
	}

	@Override
	public void addGroupPost(final GroupPost groupPost) {
		executeUpdate(INSERT_GROUP_POST, groupPost.getGroupId(), groupPost.getPostId());	
	}

	@Override
	public void deleteGroupPost(Long groupId, Long postId) {
		executeUpdate(DELETE_GROUP_POST, groupId, postId);
	}
	
	private ResultSetMapper<GroupPost> getResultSetMapper() {
		return new ResultSetMapper<GroupPost>() {
			@Override
			public GroupPost map(ResultSet rs) throws SQLException {
				return new GroupPost(rs.getLong(1), rs.getLong(2));
			}

		};
	}

}
