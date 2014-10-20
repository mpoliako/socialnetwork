package socialnetwork.model.dao.bean;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String body;
	private Date createDate;
	private Date deleteDate;
	private String label;
	private Long userId;
	private Long groupId;

	public Post() {
	}
	

	/**
	 * @param id
	 * @param body
	 * @param createDate
	 * @param deleteDate
	 * @param label
	 * @param userId
	 */
	public Post(long id, String body, Date createDate, Date deleteDate,
			String label, Long userId, Long groupId) {
		super();
		this.id = id;
		this.body = body;
		this.createDate = createDate;
		this.deleteDate = deleteDate;
		this.label = label;
		this.userId = userId;
		this.groupId = groupId;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}


	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((deleteDate == null) ? 0 : deleteDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Post other = (Post) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (deleteDate == null) {
			if (other.deleteDate != null)
				return false;
		} else if (!deleteDate.equals(other.deleteDate))
			return false;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Post [id=" + id + ", body=" + body + ", createDate="
				+ createDate + ", deleteDate=" + deleteDate + ", label="
				+ label + ", userId=" + userId + "]";
	}
	
	

}
