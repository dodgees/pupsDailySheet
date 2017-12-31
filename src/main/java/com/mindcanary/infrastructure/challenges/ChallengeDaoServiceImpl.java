package com.mindcanary.infrastructure.challenges;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mindcanary.domain.answer.Answer;
import com.mindcanary.domain.answer.AnswerType;
import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.StatusType;
import com.mindcanary.domain.user.User;
import com.mindcanary.infrastructure.answers.AnswerDaoService;

@Named
public class ChallengeDaoServiceImpl implements ChallengeDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Inject
	private AnswerDaoService answerDaoService;

	@Override
	public List<Challenge> getAllChallenges() {

		String sql = "SELECT * from challenges";

		List<Challenge> challenges = namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
			User toUser = new User(rs.getString("to_user_id"));
			User fromUser = new User(rs.getString("from_user_id"));
			LocalDateTime createdDateTime = rs.getTimestamp("created_timestamp").toLocalDateTime();
			Challenge challenge = new Challenge(rs.getInt("id"), toUser, fromUser, createdDateTime,
					rs.getString("title"), rs.getString("category"), "Awesome Description", AnswerType.MULTIPLE_CHOICE,
					StatusType.ASKED, null);
			return challenge;
		});

		return challenges;
	}

	@Override
	public List<Challenge> saveChallenges(List<Challenge> challenges) {
		List<Challenge> savedChallenges = new ArrayList<>();

		for (Challenge challenge : challenges) {
			Challenge savedChallenged = saveChallenge(challenge);
			List<Answer> savedAnswers = answerDaoService.saveAnswers(savedChallenged.getId(),
					challenge.getAnswerBank());
			savedChallenged.setAnswerBank(savedAnswers);
			savedChallenges.add(savedChallenged);
		}
		return savedChallenges;
	}

	@Override
	public Challenge saveChallenge(Challenge challenge) {
		MapSqlParameterSource params = new MapSqlParameterSource("from_user_id", challenge.getFromUser().getUid())
				.addValue("to_user_id", challenge.getFromUser().getUid()).addValue("title", challenge.getTitle())
				.addValue("question", challenge.getQuestion())
				.addValue("answer_type", challenge.getAnswerType().getName())
				.addValue("status_type", challenge.getStatusType().getName())
				.addValue("created_timestamp", Timestamp.valueOf(challenge.getCreatedDateTime()))
				.addValue("created_by", 1).addValue("category", challenge.getCategory()).addValue("answer", "MAGIC");
		String sql = "INSERT INTO challenges(from_user_id, to_user_id, title, question, answer, answer_type, status_type, created_timestamp, created_by, category)"
				+ "	VALUES (:from_user_id,:to_user_id,:title, :question, :answer, :answer_type, :status_type, :created_timestamp, :created_by, :category );";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder);
		long challengeId = Long.valueOf(keyHolder.getKeys().get("id").toString());

		List<String> answerBank = null;

		challenge.setId(challengeId);

		return challenge;
	}

	@Override
	public List<String> saveAnswerBank(List<String> answerBankItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> getSentChallenges(String firebaseUuid) {
		MapSqlParameterSource params = new MapSqlParameterSource("from_user_id", firebaseUuid);
		String sql = "select * from challenges where from_user_id = :from_user_id";

		List<Challenge> challenges = namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
			User toUser = new User(rs.getString("to_user_id"));
			User fromUser = new User(rs.getString("from_user_id"));
			Timestamp ts = rs.getTimestamp("created_timestamp");
			Challenge challenge = new Challenge(rs.getInt("id"), toUser, fromUser, LocalDateTime.now(),
					rs.getString("title"), rs.getString("category"), rs.getString("question"),
					AnswerType.fromName(rs.getString("answer_type")), StatusType.fromName(rs.getString("status_type")),
					null);
			return challenge;
		});
		return challenges;
	}

}
