package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.PostDao;
import socialnetwork.model.dao.bean.Post;
import socialnetwork.model.datasource.JDBCDataSource;

public class PostDaoImpl extends AbstractDao implements PostDao {

	private static final String GET_ALL_POSTS = "SELECT ID, BODY, CREATE_DATE, DELETE_DATE, LABEL, USER_ID, GROUP_ID FROM POSTS WHERE IS_DELETED = 'N'";
	private static final String GET_POST_BY_ID = "SELECT ID, BODY, CREATE_DATE, DELETE_DATE, LABEL, USER_ID, GROUP_ID FROM POSTS WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String GET_POSTS_BY_USER_ID = "SELECT ID, BODY, CREATE_DATE, DELETE_DATE, LABEL, USER_ID, GROUP_ID FROM POSTS WHERE USER_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_POSTS_BY_GROUP_ID = "SELECT ID, BODY, CREATE_DATE, DELETE_DATE, LABEL, USER_ID, GROUP_ID FROM POSTS WHERE GROUP_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_POSTS_BY_USER_LABEL = "SELECT ID, BODY, CREATE_DATE, DELETE_DATE, LABEL, USER_ID, GROUP_ID FROM POSTS WHERE LABEL = ? AND IS_DELETED = 'N'";
	private static final String INSERT_POST = "INSERT INTO POSTS(BODY, CREATE_DATE, LABEL, USER_ID) VALUES (?,?,?,?)";
	private static final String UPDATE_POST = "UPDATE POSTS SET BODY = ?, LABEL = ?, USER_ID = ? WHERE ID = ?";
	private static final String DELETE_POST = "UPDATE POSTS SET IS_DELETED = 'Y' WHERE ID = ?";

	public PostDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Post> getAllPosts() {
		return queryList(GET_ALL_POSTS, getResultSetMapper());
	}

	@Override
	public Post getPostById(final Long id) {
		return querySingleResult(GET_POST_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public List<Post> getPostsByUserId(final Long userId) {
		return queryList(GET_POSTS_BY_USER_ID, getResultSetMapper(), userId);
	}

	@Override
	public List<Post> getPostsByGroupId(Long groupId) {
		return queryList(GET_POSTS_BY_GROUP_ID, getResultSetMapper(), groupId);
	}

	@Override
	public List<Post> getPostsByUserLabel(final String label) {
		return queryList(GET_POSTS_BY_USER_LABEL, getResultSetMapper(), label);
	}

	@Override
	public void addPost(final Post post) {
		executeUpdate(INSERT_POST, post.getBody(), new java.sql.Timestamp(
				System.currentTimeMillis()), post.getLabel(), post.getUserId());
	}

	@Override
	public void updatePost(final Post post) {
		executeUpdate(UPDATE_POST, post.getBody(), post.getLabel(),
				post.getUserId(), post.getId());
	}

	@Override
	public void addOrUpdatePost(final Post post) {
		if (post.getId() == null) {
			addPost(post);
		} else {
			updatePost(post);
		}
	}

	@Override
	public void deletePost(Long id) {
		executeUpdate(DELETE_POST, id);
	}

	private ResultSetMapper<Post> getResultSetMapper() {
		return new ResultSetMapper<Post>() {
			@Override
			public Post map(ResultSet rs) throws SQLException {
				return new Post(rs.getLong(1), rs.getString(2), rs.getDate(3),
						rs.getDate(4), rs.getString(5), rs.getLong(6),
						rs.getLong(7));
			}

		};
	}

}
