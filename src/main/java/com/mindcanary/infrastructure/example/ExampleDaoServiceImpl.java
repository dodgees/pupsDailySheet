package com.mindcanary.infrastructure.example;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named
public class ExampleDaoServiceImpl implements ExampleDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Long getLongFromDatabase() {
		Long returnValue = namedParameterJdbcTemplate.queryForObject("select 1", new MapSqlParameterSource(), Long.class);
		return returnValue;
	}

}
