package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.GroupPost;

public interface GroupPostDao {
	
	public List<GroupPost> getGroupPostsByGroupId(final Long groupId);
	public List<GroupPost> getGroupPostsByPostId(final Long postId);
	public GroupPost getGroupPostByGroupIdAndPostId(final Long groupId, final Long postId);
	public void addGroupPost(final GroupPost groupPost);
	public void deleteGroupPost(final Long groupId, final Long postId);

}
