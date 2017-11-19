package com.mindcanary.infrastructure.challenges;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.user.User;

@Named
public class ChallengeDaoServiceImpl implements ChallengeDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Challenge> getAllChallenges() {
		
			String sql = "SELECT * from challenges";

			return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
				User toUser = new User(rs.getLong("toUserId"), null , null);
				User fromUser = new User(rs.getLong("fromUserId"), null , null);
				LocalDateTime createdDateTime = rs.getTimestamp("created_timestamp").toLocalDateTime();
				Challenge p = new Challenge(rs.getInt("id"),toUser, fromUser, createdDateTime, rs.getString("title"), rs.getString("category"));
			    return p;
			});
	}

}
