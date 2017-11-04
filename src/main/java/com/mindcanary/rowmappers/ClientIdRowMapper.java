package com.mindcanary.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindcanary.domain.ClientId;

public class ClientIdRowMapper implements RowMapper<ClientId> {

	@Override
	public ClientId mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClientId clientId = new ClientId();

		clientId.setId(rs.getLong("id"));
		clientId.setClientId(rs.getString("client_id"));
		clientId.setRole(rs.getLong("role"));

		return clientId;
	}
}
