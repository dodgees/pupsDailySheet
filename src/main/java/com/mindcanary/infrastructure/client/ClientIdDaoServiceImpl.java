package com.mindcanary.infrastructure.client;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mindcanary.domain.ClientId;
import com.mindcanary.rowmappers.ClientIdRowMapper;

@Named
public class ClientIdDaoServiceImpl implements ClientIdDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<ClientId> getAll() {
		String sql = "select * from clients";
		List<ClientId> clientIds = namedParameterJdbcTemplate.query(sql, new ClientIdRowMapper());
		return clientIds;
	}

	@Override
	public List<ClientId> updateAll(List<ClientId> clientIds) {
		List<ClientId> clientIdsToUpdate = new ArrayList<>();
		List<ClientId> clientIdsToCreate = new ArrayList<>();
		List<ClientId> currentClientIds = getAll();
		for (ClientId clientId : clientIds) {
			if (currentClientIds.contains(clientId)) {
				clientIdsToUpdate.add(clientId);
			} else {
				clientIdsToCreate.add(clientId);
			}
		}
		for (ClientId clientId : clientIdsToUpdate) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("clientId", clientId.getClientId());
			params.addValue("role", clientId.getRole());
			String sql = "update clients set role = :role where client_id = :clientId";
			namedParameterJdbcTemplate.update(sql, params);
		}
		for (ClientId clientId : clientIdsToCreate) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("clientId", clientId.getClientId());
			params.addValue("role", clientId.getRole());
			String sql = "insert into clients (client_id, role) values(:clientId, :role)";
			namedParameterJdbcTemplate.update(sql, params);
		}
		List<ClientId> clientIdReturnList = getAll();
		return clientIdReturnList;
	}

	@Override
	public void deleteById(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		String sql = "delete from clients where id = :id";
		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public ClientId getByClientId(String clientId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clientId", clientId);
		String sql = "select * from clients where client_id = :clientId";
		List<ClientId> clientIdList = namedParameterJdbcTemplate.query(sql, params, new ClientIdRowMapper());
		return clientIdList.get(0);
	}

}
