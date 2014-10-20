package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.Group;

public interface GroupDao {
	
	public List<Group> getAllGroups();
	public Group getGroupById(final Long id);
	public List<Group> getGroupsByOwnerId(final Long ownerId);
	public void addGroup(final Group group);
	public void updateGroup(final Group group);
	public void addOrUpdateGroup(final Group group);
	public void deleteGroup(final Long id);

}
