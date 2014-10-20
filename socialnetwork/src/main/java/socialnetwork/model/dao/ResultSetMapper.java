package socialnetwork.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
	
	public T map(final ResultSet rs) throws SQLException;
	
}
