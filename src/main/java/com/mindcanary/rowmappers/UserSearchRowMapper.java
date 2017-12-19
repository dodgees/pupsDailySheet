package com.mindcanary.rowmappers;

import com.mindcanary.domain.user.UserSearch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSearchRowMapper implements RowMapper<UserSearch> {

	@Override
	public UserSearch mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserSearch userSearch = new UserSearch(rs.getString("firebase_uuid"));

		userSearch.setFirstName(rs.getString("first_name"));
		userSearch.setLastName(rs.getString("last_name"));

		return userSearch;
	}
}
