package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.GroupImageDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.GroupImage;
import socialnetwork.model.datasource.JDBCDataSource;

public class GroupImageDaoImpl extends AbstractDao implements GroupImageDao {

	private static final String GET_ALL_IMAGES = "SELECT ID, GROUP_ID, IMAGE_URL FROM GROUP_IMAGES WHERE IS_DELETED = 'N'";
	private static final String GET_IMAGE_BY_ID = "SELECT ID, GROUP_ID, IMAGE_URL FROM GROUP_IMAGES WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String GET_IMAGE_BY_GROUP_ID = "SELECT ID, GROUP_ID, IMAGE_URL FROM GROUP_IMAGES WHERE GROUP_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_IMAGE = "INSERT INTO GROUP_IMAGES(GROUP_ID, IMAGE_URL) VALUES (?,?)";
	private static final String UPDATE_IMAGE = "UPDATE GROUP_IMAGES SET GROUP_ID =?, IMAGE_URL = ? WHERE ID = ?";
	private static final String DELETE_IMAGE = "UPDATE GROUP_IMAGES SET IS_DELETED = 'Y' WHERE ID = ?";

	public GroupImageDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<GroupImage> getAllGroupImages() {
		return queryList(GET_ALL_IMAGES, getResultSetMapper());
	}

	@Override
	public GroupImage getGroupImageById(final Long id) {
		return querySingleResult(GET_IMAGE_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public List<GroupImage> getUserImageByGroupId(final Long groupId) {
		return queryList(GET_IMAGE_BY_GROUP_ID, getResultSetMapper(), groupId);
	}

	@Override
	public void addGroupImage(final GroupImage image) {
		executeUpdate(INSERT_IMAGE, image.getGroupId(), image.getImageUrl());
	}

	@Override
	public void updateGroupImage(final GroupImage image) {
		executeUpdate(UPDATE_IMAGE, image.getGroupId(), image.getImageUrl(),
				image.getId());
	}

	@Override
	public void addOrUpdateGroupImage(final GroupImage image) {
		if (image.getId() == null) {
			addGroupImage(image);
		} else {
			updateGroupImage(image);
		}
	}

	@Override
	public void deleteGroupImage(Long id) {
		executeUpdate(DELETE_IMAGE, id);
	}

	private ResultSetMapper<GroupImage> getResultSetMapper() {
		return new ResultSetMapper<GroupImage>() {
			@Override
			public GroupImage map(ResultSet rs) throws SQLException {
				return new GroupImage(rs.getLong(1), rs.getLong(2),
						rs.getString(3));
			}

		};
	}

}
