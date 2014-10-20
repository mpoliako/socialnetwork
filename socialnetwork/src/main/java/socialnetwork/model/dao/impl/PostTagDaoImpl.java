package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.PostTagDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.PostTag;
import socialnetwork.model.datasource.JDBCDataSource;

public class PostTagDaoImpl extends AbstractDao implements PostTagDao {
	
	private static final String GET_POST_TAGS_BY_POST_ID = "SELECT POST_ID, TAG_ID FROM POST_TAGS WHERE POST_ID = ? WHERE IS_DELETED = 'N'";
	private static final String GET_POST_TAGS_BY_TAG_ID = "SELECT POST_ID, TAG_ID FROM POST_TAGS WHERE TAG_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_POST_TAG_BY_POST_ID_AND_TAG_ID = "SELECT POST_ID, TAG_ID FROM POST_TAGS WHERE POST_ID = ? AND TAG_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_TAG = "INSERT INTO POST_TAGS(POST_ID, TAG_ID) VALUES (?,?)";
	private static final String DELETE_POST_TAG = "UPDATE POST_TAGS SET IS_DELETED = 'Y' WHERE POST_ID = ? AND TAG_ID = ?";

	public PostTagDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<PostTag> getPostTagsByPostId(final Long postId) {
		return queryList(GET_POST_TAGS_BY_POST_ID, getResultSetMapper(), postId);
	}

	@Override
	public List<PostTag> getPostTagsByTagId(final Long tagId) {
		return queryList(GET_POST_TAGS_BY_TAG_ID, getResultSetMapper(), tagId);
	}

	@Override
	public PostTag getPostTagByPostIdAndTagId(final Long postId, final Long tagId) {
		return querySingleResult(GET_POST_TAG_BY_POST_ID_AND_TAG_ID, getResultSetMapper(), postId, tagId);
	}

	@Override
	public void addPostTag(PostTag postTag) {
		executeUpdate(INSERT_TAG, postTag.getPostId(), postTag.getTagId());		
	}

	@Override
	public void deletePostTag(Long postId, Long tagId) {
		executeUpdate(DELETE_POST_TAG, postId, tagId);	
	}
	
	private ResultSetMapper<PostTag> getResultSetMapper() {
		return new ResultSetMapper<PostTag>() {
			@Override
			public PostTag map(ResultSet rs) throws SQLException {
				return new PostTag(rs.getLong(1), rs.getLong(2));
			}

		};
	}

}
