package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.TagDao;
import socialnetwork.model.dao.bean.Tag;
import socialnetwork.model.datasource.JDBCDataSource;

public class TagDaoImpl extends AbstractDao implements TagDao {

	private static final String GET_ALL_TAGS = "SELECT ID, NAME FROM TAGS WHERE IS_DELETED = 'N'";
	private static final String GET_TAG_BY_ID = "SELECT ID, NAME FROM TAGS WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_TAG = "INSERT INTO TAGS(NAME) VALUES (?)";
	private static final String UPDATE_TAG = "UPDATE TAGS SET NAME = ? WHERE ID = ?";
	private static final String DELETE_TAG = "UPDATE TAGS SET IS_DELETED = 'Y' WHERE ID = ?";

	public TagDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Tag> getAllTags() {
		return queryList(GET_ALL_TAGS, getResultSetMapper());
	}

	@Override
	public Tag getTagById(final Long id) {
		return querySingleResult(GET_TAG_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public void addTag(final Tag tag) {
		executeUpdate(INSERT_TAG, tag.getName());

	}

	@Override
	public void updateTag(final Tag tag) {
		executeUpdate(UPDATE_TAG, tag.getName(), tag.getId());
	}

	@Override
	public void addOrUpdateTag(final Tag tag) {
		if (tag.getId() == null) {
			addTag(tag);
		} else {
			addTag(tag);
		}
	}

	@Override
	public void deleteTag(Long id) {
		executeUpdate(DELETE_TAG, id);
	}

	private ResultSetMapper<Tag> getResultSetMapper() {
		return new ResultSetMapper<Tag>() {
			@Override
			public Tag map(ResultSet rs) throws SQLException {
				return new Tag(rs.getLong(1), rs.getString(2));
			}

		};
	}

}
