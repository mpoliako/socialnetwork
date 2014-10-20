package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.GroupDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.Group;
import socialnetwork.model.datasource.JDBCDataSource;

public class GroupDaoImpl extends AbstractDao implements GroupDao {

	private static final String GET_ALL_GROUPS = "SELECT ID, NAME, OWNER_ID, PARENT_ID FROM GROUPS WHERE IS_DELETED = 'N'";
	private static final String GET_GROUP_BY_ID = "SELECT ID, NAME, OWNER_ID, PARENT_ID FROM GROUPS WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String GET_GROUP_BY_OWNER_ID = "SELECT ID, NAME, OWNER_ID, PARENT_ID FROM GROUPS WHERE OWNER_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_GROUP = "INSERT INTO GROUPS(NAME, OWNER_ID, PARENT_ID) VALUES (?,?,?)";
	private static final String UPDATE_GROUP = "UPDATE GROUPS SET NAME = ?, OWNER_ID = ?, PARENT_ID = ? WHERE ID = ?";
	private static final String DELETE_GROUP = "UPDATE GROUPS SET IS_DELETED = 'Y' WHERE ID = ?";

	public GroupDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Group> getAllGroups() {
		return queryList(GET_ALL_GROUPS, getResultSetMapper());
	}

	@Override
	public Group getGroupById(final Long id) {
		return querySingleResult(GET_GROUP_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public List<Group> getGroupsByOwnerId(final Long ownerId) {
		return queryList(GET_GROUP_BY_OWNER_ID, getResultSetMapper(), ownerId);
	}

	@Override
	public void addGroup(final Group group) {
		executeUpdate(INSERT_GROUP, group.getName(), group.getOwnerId(),
				group.getParentId());
	}

	@Override
	public void updateGroup(final Group group) {
		executeUpdate(UPDATE_GROUP, group.getName(), group.getOwnerId(),
				group.getParentId(), group.getId());
	}

	@Override
	public void addOrUpdateGroup(final Group group) {
		if (group.getId() == null) {
			addGroup(group);
		} else {
			updateGroup(group);
		}
	}

	@Override
	public void deleteGroup(Long id) {
		executeUpdate(DELETE_GROUP, id);
	}

	private ResultSetMapper<Group> getResultSetMapper() {
		return new ResultSetMapper<Group>() {
			@Override
			public Group map(ResultSet rs) throws SQLException {
				return new Group(rs.getLong(1), rs.getString(2), rs.getLong(3),
						rs.getLong(4));
			}

		};
	}

}
