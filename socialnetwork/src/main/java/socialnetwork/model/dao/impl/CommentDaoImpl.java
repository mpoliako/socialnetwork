package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.CommentDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.Comment;
import socialnetwork.model.datasource.JDBCDataSource;

public class CommentDaoImpl extends AbstractDao implements CommentDao {

	private static final String GET_ALL_COMMENTS = "SELECT ID, BODY, CREATE_DATE, POST_ID, TYPE, USER_ID  FROM COMMENTS WHERE IS_DELETED = 'N'";
	private static final String GET_COMMENT_BY_ID = "SELECT ID, BODY, CREATE_DATE, POST_ID, TYPE, USER_ID  FROM COMMENTS WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String GET_COMMENT_BY_USER_ID = "SELECT ID, BODY, CREATE_DATE, POST_ID, TYPE, USER_ID  FROM COMMENTS WHERE USER_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_COMMENT_BY_POST_ID = "SELECT ID, BODY, CREATE_DATE, POST_ID, TYPE, USER_ID  FROM COMMENTS WHERE POST_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_COMMENT = "INSERT INTO COMMENTS(ID, BODY, CREATE_DATE, POST_ID, TYPE, USER_ID) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_COMMENT = "UPDATE COMMENTS SET BODY= ?, POST_ID= ?, TYPE= ?, USER_ID= ? WHERE ID = ?";
	private static final String DELETE_COMMENT = "UPDATE COMMENTS SET IS_DELETED = 'Y' WHERE ID = ?";

	public CommentDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Comment> getAllComments() {
		return queryList(GET_ALL_COMMENTS, getResultSetMapper());
	}

	@Override
	public Comment getCommentById(final Long id) {
		return querySingleResult(GET_COMMENT_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public List<Comment> getCommentsByUserId(final Long userId) {
		return queryList(GET_COMMENT_BY_USER_ID, getResultSetMapper(), userId);
	}

	@Override
	public List<Comment> getCommentsByPostId(final Long postId) {
		return queryList(GET_COMMENT_BY_POST_ID, getResultSetMapper(), postId);
	}

	@Override
	public void addComment(final Comment comment) {
		executeUpdate(INSERT_COMMENT, comment.getId(), comment.getBody(),
				new java.sql.Timestamp(System.currentTimeMillis()), comment.getPostId(),
				comment.getType(), comment.getUserId());
	}

	@Override
	public void updateComment(final Comment comment) {
		executeUpdate(UPDATE_COMMENT, comment.getBody(), comment.getPostId(), comment.getType(), comment.getUserId(), comment.getId());
	}

	@Override
	public void addOrUpdateComment(final Comment comment) {
		if (comment.getId() == null) {
			addComment(comment);
		} else {
			updateComment(comment);
		}
	}

	@Override
	public void deleteComment(Long id) {
		executeUpdate(DELETE_COMMENT, id);		
	}

	private ResultSetMapper<Comment> getResultSetMapper() {
		return new ResultSetMapper<Comment>() {
			@Override
			public Comment map(ResultSet rs) throws SQLException {
				return new Comment(rs.getLong(1), rs.getString(2),
						rs.getDate(3), rs.getLong(4), rs.getString(5),
						rs.getLong(6));
			}

		};
	}

}
