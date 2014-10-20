package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.Rating;

public interface RatingDao {
	
	public List<Rating> getAllRatings();
	public List<Rating> getAllRatingsByUserId(final Long userId);
	public List<Rating> getAllRatingsByCommentId(final Long commentId);
	public Rating getRatingByUserAndCommentId(final Long userId,final Long commentId);
	public void addRating(final Rating rating);
	public void updateRating(final Rating rating);
	public void addOrUpdateRating(final Rating rating);	
	public void deleteRating(final Long userId,final Long commentId);

}
