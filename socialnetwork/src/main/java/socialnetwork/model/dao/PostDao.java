package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.Post;

public interface PostDao {

	public List<Post> getAllPosts();

	public Post getPostById(final Long id);

	public List<Post> getPostsByUserId(final Long userId);
	
	public List<Post> getPostsByGroupId(final Long groupId);

	public List<Post> getPostsByUserLabel(final String label);

	public void addPost(final Post post);

	public void updatePost(final Post post);

	public void addOrUpdatePost(final Post post);
	
	public void deletePost(final Long id);

}
