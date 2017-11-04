package com.mindcanary.infrastructure.authentication;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named
public class AuthenticationDaoServiceImpl implements AuthenticationDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public boolean isValidClient(String clientId) {
		String sql = "select count(*) from clients c where c.client_id = :clientId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("clientId", clientId);
		long clientIdCount = namedParameterJdbcTemplate.queryForObject(sql, params, long.class);
		if (clientIdCount > 0) {
			return true;
		}
		return false;
	}

}
