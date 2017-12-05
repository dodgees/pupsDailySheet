package com.mindcanary.infrastructure.challenges;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mindcanary.domain.challenge.AnswerType;
import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.StatusType;
import com.mindcanary.domain.user.User;

@Named
public class ChallengeDaoServiceImpl implements ChallengeDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Challenge> getAllChallenges() {

		String sql = "SELECT * from challenges";

		return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
			User toUser = new User(rs.getLong("to_user_id"), null, null, null);
			User fromUser = new User(rs.getLong("from_user_id"), null, null, null);
			LocalDateTime createdDateTime = rs.getTimestamp("created_timestamp").toLocalDateTime();
			Challenge challenge = new Challenge(rs.getInt("id"), toUser, fromUser, createdDateTime,
					rs.getString("title"), rs.getString("category"), "Awesome Description", AnswerType.MULTIPLE_CHOICE,
					StatusType.ASKED);
			return challenge;
		});
	}

	@Override
	public List<Challenge> saveChallenges(List<Challenge> challenges) {
		List<Map<String, Object>> batchValues = new ArrayList<>(challenges.size());

		for (Challenge challenge : challenges) {
			batchValues.add(new MapSqlParameterSource("from_user_id", 1).addValue("to_user_id", 2)
					.addValue("title", challenge.getTitle()).addValue("description", challenge.getDescription())
					.addValue("answer_type", challenge.getAnswerType().getName())
					.addValue("status_type", challenge.getStatusType().getName())
					.addValue("created_timestamp", Timestamp.valueOf(challenge.getCreatedDateTime()))
					.addValue("created_by", 1).addValue("category", challenge.getCategory()).addValue("answer", "MAGIC")
					.getValues());
		}
		String sql = "INSERT INTO challenges(from_user_id, to_user_id, title, description, answer, answer_type, status_type, created_timestamp, created_by, category)"
				+ "	VALUES (:from_user_id,:to_user_id,:title, :description, :answer, :answer_type, :status_type, :created_timestamp, :created_by, :category );";
		namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[challenges.size()]));
		// I'm hoping there is a better way than this, but its what I've seen in the
		// past and currently works.
		return null;
	}

}
