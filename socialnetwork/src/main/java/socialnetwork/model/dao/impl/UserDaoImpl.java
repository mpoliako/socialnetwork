package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.UserDao;
import socialnetwork.model.dao.bean.User;
import socialnetwork.model.datasource.JDBCDataSource;

public class UserDaoImpl extends AbstractDao implements UserDao {

	private static final String FIND_ALL_USERS = "SELECT ID, DISPLAY_NAME, EMAIL, FIRST_NAME, GENDER, INFORMATION, LAST_NAME, PASSWORD_HASH, PHOTO_URL, REGISTER_DATE, ROLE FROM USERS WHERE IS_DELETED = 'N'";
	private static final String FIND_USER_BY_ID = "SELECT ID, DISPLAY_NAME, EMAIL, FIRST_NAME, GENDER, INFORMATION, LAST_NAME, PASSWORD_HASH, PHOTO_URL, REGISTER_DATE, ROLE FROM USERS WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String FIND_USER_BY_NAME_AND_PASSWORD = "SELECT ID, DISPLAY_NAME, EMAIL, FIRST_NAME, GENDER, INFORMATION, LAST_NAME, PASSWORD_HASH, PHOTO_URL, REGISTER_DATE, ROLE FROM USERS WHERE DISPLAY_NAME = ? AND PASSWORD_HASH = ? AND IS_DELETED = 'N'";
	private static final String FIND_USER_BY_EMAIL = "SELECT ID, DISPLAY_NAME, EMAIL, FIRST_NAME, GENDER, INFORMATION, LAST_NAME, PASSWORD_HASH, PHOTO_URL, REGISTER_DATE, ROLE FROM USERS WHERE EMAIL = ? AND IS_DELETED = 'N'";
	private static final String ADD_USER = "INSERT INTO USERS (DISPLAY_NAME, EMAIL, FIRST_NAME, GENDER, INFORMATION, LAST_NAME, PASSWORD_HASH, PHOTO_URL, REGISTER_DATE, ROLE) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_USER = "UPDATE USERS SET DISPLAY_NAME = ?, EMAIL = ?, FIRST_NAME = ?, GENDER = ?, INFORMATION = ?, LAST_NAME = ?, PASSWORD_HASH = ?, PHOTO_URL = ?,  ROLE = ? WHERE ID = ?";
	private static final String DELETE_USER = "UPDATE USERS SET IS_DELETED = 'Y' WHERE ID = ?";

	public UserDaoImpl(JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<User> getAllUsers() {
		return queryList(FIND_ALL_USERS, getResultSetMapper());
	}

	@Override
	public User findUserById(final Long id) {
		return querySingleResult(FIND_USER_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public User findUserByNameAndPassword(String login, String password) {
		return querySingleResult(FIND_USER_BY_NAME_AND_PASSWORD,
				getResultSetMapper(), login, password);
	}

	@Override
	public User findUserByEmail(String email) {
		return querySingleResult(FIND_USER_BY_EMAIL,
				getResultSetMapper(), email);
	}

	@Override
	public void addUser(final User user) {
		executeUpdate(ADD_USER, user.getDisplayName(), user.getEmail(),
				user.getFirstName(), user.getGender(), user.getInformation(),
				user.getLastName(), user.getPasswordHash(), user.getPhotoUrl(),
				new java.sql.Timestamp(System.currentTimeMillis()),
				user.getRole());
	}

	@Override
	public void updateUser(final User user) {
		executeUpdate(UPDATE_USER, user.getDisplayName(), user.getEmail(),
				user.getFirstName(), user.getGender(), user.getInformation(),
				user.getLastName(), user.getPasswordHash(), user.getPhotoUrl(),
				user.getRole(), user.getId());
	}

	@Override
	public void addOrUpdateUser(final User user) {
		if (user.getId() == null) {
			addUser(user);
		} else {
			updateUser(user);
		}
	}

	@Override
	public void deleteUser(Long id) {
		executeUpdate(DELETE_USER, id);
	}

	private ResultSetMapper<User> getResultSetMapper() {
		return new ResultSetMapper<User>() {
			@Override
			public User map(ResultSet rs) throws SQLException {
				return new User(rs.getLong(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getDate(10), rs.getString(11));
			}

		};
	}

}
