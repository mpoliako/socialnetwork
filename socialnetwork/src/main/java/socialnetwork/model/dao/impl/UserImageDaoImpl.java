package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.UserImageDao;
import socialnetwork.model.dao.bean.UserImage;
import socialnetwork.model.datasource.JDBCDataSource;

public class UserImageDaoImpl extends AbstractDao implements UserImageDao {
	
	private static final String GET_ALL_IMAGES = "SELECT ID, IMAGE_URL, USER_ID FROM USERS_IMAGES WHERE IS_DELETED = 'N'";
	private static final String GET_IMAGE_BY_ID = "SELECT ID, IMAGE_URL, USER_ID FROM USERS_IMAGES WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String GET_IMAGE_BY_USER_ID = "SELECT ID, IMAGE_URL, USER_ID FROM USERS_IMAGES WHERE USER_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_IMAGE = "INSERT INTO USERS_IMAGES(USER_ID, IMAGE_URL) VALUES (?,?)";
	private static final String UPDATE_IMAGE = "UPDATE USERS_IMAGES SET USER_ID =?, IMAGE_URL = ? WHERE ID = ?";
	private static final String DELETE_IMAGE = "UPDATE USERS_IMAGES SET IS_DELETED = 'Y' WHERE ID = ?";

	public UserImageDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<UserImage> getAllUserImages() {
		return queryList(GET_ALL_IMAGES, getResultSetMapper());
	}

	@Override
	public UserImage getUserImageById(final Long id) {
		return querySingleResult(GET_IMAGE_BY_ID, getResultSetMapper(), id);
	}
	
	@Override
	public List<UserImage> getUserImageByUserId(Long userId) {
		return queryList(GET_IMAGE_BY_USER_ID, getResultSetMapper(), userId);
	}

	@Override
	public void addUserImage(final UserImage image) {
		executeUpdate(INSERT_IMAGE, image.getUserId(), image.getImageUrl());
	}

	@Override
	public void updateUserImage(final UserImage image) {
		executeUpdate(UPDATE_IMAGE, image.getUserId(), image.getImageUrl(), image.getId());
	}

	@Override
	public void addOrUpdateUserImage(final UserImage image) {
		if (image.getId() == null) {
			addUserImage(image);
		} else {
			updateUserImage(image);
		}
	}

	@Override
	public void deleteUserImage(Long id) {
		executeUpdate(DELETE_IMAGE, id);
	}
	
	private ResultSetMapper<UserImage> getResultSetMapper() {
		return new ResultSetMapper<UserImage>() {
			@Override
			public UserImage map(ResultSet rs) throws SQLException {
				return new UserImage(rs.getLong(1), rs.getString(2), rs.getLong(3));
			}

		};
	}

}
