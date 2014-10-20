package socialnetwork.model.dao.bean;

import java.io.Serializable;

public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private Long commentId;
	private Integer ratingValue;

	/**
	 * 
	 */
	public Rating() {
		super();
	}

	/**
	 * @param userId
	 * @param commentId
	 * @param ratingValue
	 */
	public Rating(Long userId, Long commentId, Integer ratingValue) {
		super();
		this.userId = userId;
		this.commentId = commentId;
		this.ratingValue = ratingValue;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the commentId
	 */
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * @return the ratingValue
	 */
	public Integer getRatingValue() {
		return ratingValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result
				+ ((ratingValue == null) ? 0 : ratingValue.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		if (ratingValue == null) {
			if (other.ratingValue != null)
				return false;
		} else if (!ratingValue.equals(other.ratingValue))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rating [userId=" + userId + ", commentId=" + commentId
				+ ", ratingValue=" + ratingValue + "]";
	}

}