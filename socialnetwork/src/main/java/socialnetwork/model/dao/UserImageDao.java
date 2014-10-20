package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.UserImage;

public interface UserImageDao {
	
	public List<UserImage> getAllUserImages();
	public UserImage getUserImageById(final Long id);
	public List<UserImage> getUserImageByUserId(final Long userId);
	public void addUserImage(final UserImage image);
	public void updateUserImage(final UserImage image);
	public void addOrUpdateUserImage(final UserImage image);
	public void deleteUserImage(final Long id);

}
