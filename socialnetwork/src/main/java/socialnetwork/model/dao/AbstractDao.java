package socialnetwork.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import socialnetwork.model.datasource.JDBCDataSource;

public abstract class AbstractDao {

	protected final JDBCDataSource datasource;

	public AbstractDao(final JDBCDataSource datasource) {
		super();
		this.datasource = datasource;
	}

	protected Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	protected void setParams(final PreparedStatement statement,
			final Object... params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof String) {
				statement.setString(i + 1, (String) params[i]);
			} else if (params[i] instanceof Long) {
				statement.setLong(i + 1, (Long) params[i]);
			} else if (params[i] instanceof java.sql.Timestamp) {
				statement.setTimestamp(i + 1, (java.sql.Timestamp) params[i]);
			} else {
				statement.setObject(i + 1, params[i]);
			}
		}
	}

	protected void executeUpdate(final String sql, final Object... params) {
		try (Connection dbConnection = getConnection();
				PreparedStatement statement = dbConnection
						.prepareStatement(sql)) {
			if (params != null && params.length != 0) {
				setParams(statement, params);
			}
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	protected <T> List<T> queryList(final String sql,
			ResultSetMapper<T> mapper, final Object... params) {

		List<T> objcts = new ArrayList<>();

		try (Connection dbConnection = getConnection();
				PreparedStatement statement = dbConnection
						.prepareStatement(sql)) {

			if (params != null && params.length != 0) {
				setParams(statement, params);
			}

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				objcts.add(mapper.map(rs));
			}

			return objcts;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return objcts;

		}
	}

	protected <T> T querySingleResult(final String sql,
			ResultSetMapper<T> mapper, final Object... params) {
		return queryList(sql, mapper, params).get(0);
	}
}
