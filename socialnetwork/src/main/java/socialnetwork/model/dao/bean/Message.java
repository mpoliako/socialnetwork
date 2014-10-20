package socialnetwork.model.dao.bean;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date creationDate;
	private Long fromUserId;
	private String text;
	private String title;
	private Long toUserId;

	public Message() {
	}
	
	

	/**
	 * @param id
	 * @param creationDate
	 * @param fromUserId
	 * @param text
	 * @param title
	 * @param toUserId
	 */
	public Message(long id, Date creationDate, Long fromUserId,
			String text, String title, Long toUserId) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.fromUserId = fromUserId;
		this.text = text;
		this.title = title;
		this.toUserId = toUserId;
	}
	
	public Message(Long fromUserId,
			String text, String title, Long toUserId) {
		super();
		this.fromUserId = fromUserId;
		this.text = text;
		this.title = title;
		this.toUserId = toUserId;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((fromUserId == null) ? 0 : fromUserId.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((toUserId == null) ? 0 : toUserId.hashCode());
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
		Message other = (Message) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (fromUserId == null) {
			if (other.fromUserId != null)
				return false;
		} else if (!fromUserId.equals(other.fromUserId))
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (toUserId == null) {
			if (other.toUserId != null)
				return false;
		} else if (!toUserId.equals(other.toUserId))
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [id=" + id + ", creationDate=" + creationDate
				+ ", fromUserId=" + fromUserId + ", text=" + text + ", title="
				+ title + ", toUserId=" + toUserId + "]";
	}
	
	
	
	

}
