package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.GroupUser;

public interface GroupUserDao {
	
	public List<GroupUser> getGroupUsersbyUserId(final Long userId);
	public List<GroupUser> getGroupUsersbyGroupId(final Long groupId);
	public GroupUser getGroupUserbyUserIdAndGroupId(final Long userId, Long groupId);
	public void addGroupUser(final GroupUser groupUser);
	public void deleteGroupUser(final Long groupId, final Long userId);

}
