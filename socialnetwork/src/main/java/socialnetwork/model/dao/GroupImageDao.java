package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.GroupImage;

public interface GroupImageDao {
	
	public List<GroupImage> getAllGroupImages();
	public GroupImage getGroupImageById(final Long id);
	public List<GroupImage> getUserImageByGroupId(final Long groupId);
	public void addGroupImage(final GroupImage image);
	public void updateGroupImage(final GroupImage image);
	public void addOrUpdateGroupImage(final GroupImage image);
	public void deleteGroupImage(final Long id);

}
