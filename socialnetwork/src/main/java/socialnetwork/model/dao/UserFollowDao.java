package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.UserFollow;

public interface UserFollowDao {
	
	public List<UserFollow> getUserFollowsByUserId(final Long userId);
	public List<UserFollow> getUserFollowsByFollowerId(final Long followerId);
	public void addUserFollow(final UserFollow userFollow);
	public void deleteUserFollow(final Long userId, final Long followerId);
	
	

}
