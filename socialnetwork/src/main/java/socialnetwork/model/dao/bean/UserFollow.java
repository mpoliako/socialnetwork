package socialnetwork.model.dao.bean;

import java.io.Serializable;

public class UserFollow implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private Long followerId;

	/**
	 * 
	 */
	public UserFollow() {
		super();
	}

	/**
	 * @param userId
	 * @param followerId
	 */
	public UserFollow(Long userId, Long followerId) {
		super();
		this.userId = userId;
		this.followerId = followerId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the followerId
	 */
	public Long getFollowerId() {
		return followerId;
	}

	/**
	 * @param followerId
	 *            the followerId to set
	 */
	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
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
				+ ((followerId == null) ? 0 : followerId.hashCode());
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
		UserFollow other = (UserFollow) obj;
		if (followerId == null) {
			if (other.followerId != null)
				return false;
		} else if (!followerId.equals(other.followerId))
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
		return "UserFollow [userId=" + userId + ", followerId=" + followerId
				+ "]";
	}

}