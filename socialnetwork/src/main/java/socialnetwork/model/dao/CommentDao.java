package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.Comment;

public interface CommentDao {
	
	public List<Comment> getAllComments();
	public Comment getCommentById(final Long id);
	public List<Comment> getCommentsByUserId(final Long userId);
	public List<Comment> getCommentsByPostId(final Long postId);
	public void addComment(final Comment comment);
	public void updateComment(final Comment comment);
	public void addOrUpdateComment(final Comment comment);
	public void deleteComment(final Long id);
	

}
