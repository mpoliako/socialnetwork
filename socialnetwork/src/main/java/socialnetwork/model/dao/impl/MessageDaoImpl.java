package socialnetwork.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import socialnetwork.model.dao.AbstractDao;
import socialnetwork.model.dao.ResultSetMapper;
import socialnetwork.model.dao.MessageDao;
import socialnetwork.model.dao.bean.Message;
import socialnetwork.model.datasource.JDBCDataSource;

public class MessageDaoImpl extends AbstractDao implements MessageDao {

	private static final String GET_ALL_MESSAGES = "SELECT ID, CREATION_DATE, FROM_USER_ID, TEXT, TITLE, TO_USER_ID FROM MESSAGES WHERE IS_DELETED = 'N'";
	private static final String GET_MESSAGE_BY_ID = "SELECT ID, CREATION_DATE, FROM_USER_ID, TEXT, TITLE, TO_USER_ID FROM MESSAGES WHERE ID = ? AND IS_DELETED = 'N'";
	private static final String GET_MESSAGE_BY_SENDER_ID = "SELECT ID, CREATION_DATE, FROM_USER_ID, TEXT, TITLE, TO_USER_ID FROM MESSAGES WHERE FROM_USER_ID = ? AND IS_DELETED = 'N' AND IS_DELETED = 'N'";
	private static final String GET_MESSAGE_BY_RECEIVER_ID = "SELECT ID, CREATION_DATE, FROM_USER_ID, TEXT, TITLE, TO_USER_ID FROM MESSAGES WHERE TO_USER_ID = ? AND IS_DELETED = 'N'";
	private static final String GET_MESSAGE_BY_SENDER_AND_ID = "SELECT ID, CREATION_DATE, FROM_USER_ID, TEXT, TITLE, TO_USER_ID FROM MESSAGES WHERE FROM_USER_ID = ? AND TO_USER_ID = ? AND IS_DELETED = 'N'";
	private static final String INSERT_MESSAGE = "INSERT INTO MESSAGES(CREATION_DATE, FROM_USER_ID, TEXT, TITLE, TO_USER_ID) VALUES (?,?,?,?,?)";
	private static final String UPDATE_MESSAGE = "UPDATE MESSAGES SET FROM_USER_ID = ?, TEXT = ?, TITLE = ?, TO_USER_ID = ? WHERE ID = ?";
	private static final String DELETE_MESSAGE = "UPDATE MESSAGES SET IS_DELETED = 'Y' WHERE ID = ?";

	public MessageDaoImpl(final JDBCDataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Message> getAllMessages() {
		return queryList(GET_ALL_MESSAGES, getResultSetMapper());
	}

	@Override
	public Message findMessageById(final Long id) {
		return querySingleResult(GET_MESSAGE_BY_ID, getResultSetMapper(), id);
	}

	@Override
	public List<Message> getMessagesBySender(final Long senderId) {
		return queryList(GET_MESSAGE_BY_SENDER_ID, getResultSetMapper(),
				senderId);
	}

	@Override
	public List<Message> getMessagesByReceiver(final Long receiverId) {
		return queryList(GET_MESSAGE_BY_RECEIVER_ID, getResultSetMapper(),
				receiverId);
	}

	@Override
	public List<Message> getMessagesBySenderAndReceiver(final Long senderId,
			final Long receiverId) {
		return queryList(GET_MESSAGE_BY_SENDER_AND_ID, getResultSetMapper(),
				senderId, receiverId);
	}

	@Override
	public void addMessage(final Message message) {
		executeUpdate(INSERT_MESSAGE,
				new java.sql.Timestamp(System.currentTimeMillis()),
				message.getFromUserId(), message.getText(), message.getTitle(),
				message.getToUserId());
	}

	@Override
	public void updateMessage(final Message message) {
		executeUpdate(UPDATE_MESSAGE, message.getFromUserId(),
				message.getText(), message.getTitle(), message.getToUserId(),
				message.getId());
	}

	@Override
	public void addOrUpdateMessage(final Message message) {
		if (message.getId() == null) {
			addMessage(message);
		} else {
			updateMessage(message);
		}
	}

	@Override
	public void deleteMessage(Long id) {
		executeUpdate(DELETE_MESSAGE, id);
	}

	private ResultSetMapper<Message> getResultSetMapper() {
		return new ResultSetMapper<Message>() {
			@Override
			public Message map(ResultSet rs) throws SQLException {
				return new Message(rs.getLong(1), rs.getDate(2), rs.getLong(3),
						rs.getString(4), rs.getString(5), rs.getLong(6));
			}

		};
	}

}
