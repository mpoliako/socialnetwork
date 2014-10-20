package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.RatingDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.bean.Rating;
import socialnetwork.model.datasource.JDBCDataSource;

public class RatingDaoImpl extends AbstractDao implements RatingDao {

	private static final String GET_ALL_RATINGS = "SELECT USER_ID, COMMENT_ID, RATING_VALUE FROM RATING WHERE IS_DELETED = 'N'";
	private static final String GET_RATINGS_BY_USER_ID = "SELECT USER_ID, COMMENT_ID, RATING_VALUE FROM RATING WHERE USER_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_RATINGS_BY_COMMENT_ID = "SELECT USER_ID, COMMENT_ID, RATING_VALUE FROM RATING WHERE COMMENT_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_RATING_BY_USER_AND_COMMENT_ID = "SELECT USER_ID, COMMENT_ID, RATING_VALUE FROM RATING WHERE USER_ID = ? AND COMMENT_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_RATING = "INSERT INTO RATING(USER_ID, COMMENT_ID, RATING_VALUE) VALUES(?,?,?)";
	private static final String UPDATE_RATING = "UPDATE RATING SET RATING_VALUE = ? WHERE USER_ID = ? AND COMMENT_ID = ?";
	private static final String DELETE_RATING = "UPDATE RATING SET IS_DELETED = 'Y' WHERE USER_ID = ? AND COMMENT_ID = ?";

	public RatingDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Rating> getAllRatings() {
		return queryList(GET_ALL_RATINGS, getResultSetMapper());
	}

	@Override
	public List<Rating> getAllRatingsByUserId(final Long userId) {
		return queryList(GET_RATINGS_BY_USER_ID, getResultSetMapper(), userId);
	}

	@Override
	public List<Rating> getAllRatingsByCommentId(final Long commentId) {
		return queryList(GET_RATINGS_BY_COMMENT_ID, getResultSetMapper(), commentId);
	}

	@Override
	public Rating getRatingByUserAndCommentId(final Long userId, final Long commentId) {
		return querySingleResult(GET_RATING_BY_USER_AND_COMMENT_ID, getResultSetMapper(), userId, commentId);
	}

	@Override
	public void addRating(final Rating rating) {
		executeUpdate(INSERT_RATING, rating.getUserId(), rating.getCommentId(), rating.getRatingValue());
	}

	@Override
	public void updateRating(final Rating rating) {
		executeUpdate(UPDATE_RATING, rating.getRatingValue(), rating.getUserId(), rating.getCommentId());
	}

	@Override
	public void addOrUpdateRating(final Rating rating) {
		if (rating.getUserId() == null && rating.getCommentId() == null) {
			addRating(rating);
		} else {
			updateRating(rating);
		}
	}

	@Override
	public void deleteRating(Long userId, Long commentId) {
		executeUpdate(DELETE_RATING, userId, commentId);
	}

	private ResultSetMapper<Rating> getResultSetMapper() {
		return new ResultSetMapper<Rating>() {
			@Override
			public Rating map(ResultSet rs) throws SQLException {
				return new Rating(rs.getLong(1), rs.getLong(2), rs.getInt(3));
			}

		};
	}

}
