package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.PostTag;

public interface PostTagDao {
	
	public List<PostTag> getPostTagsByPostId(final Long postId);
	public List<PostTag> getPostTagsByTagId(final Long tagId);
	public PostTag getPostTagByPostIdAndTagId(final Long postId,final Long tagId);
	public void addPostTag(final PostTag postTag);
	public void deletePostTag(final Long postId,final Long tagId);

}
