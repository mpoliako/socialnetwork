package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.Message;

public interface MessageDao {

	public List<Message> getAllMessages();

	public Message findMessageById(final Long id);

	public List<Message> getMessagesBySender(final Long senderId);

	public List<Message> getMessagesByReceiver(final Long receiverId);

	public List<Message> getMessagesBySenderAndReceiver(final Long senderId,
			final Long receiverId);

	public void addMessage(final Message message);

	public void updateMessage(final Message message);

	public void addOrUpdateMessage(final Message message);
	
	public void deleteMessage(final Long id);

}
