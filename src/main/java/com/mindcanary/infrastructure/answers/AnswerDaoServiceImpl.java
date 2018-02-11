package com.mindcanary.infrastructure.answers;

import com.mindcanary.domain.answer.Answer;
import com.mindcanary.infrastructure.challenges.ChallengeDaoService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
public class AnswerDaoServiceImpl implements AnswerDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Inject
	private ChallengeDaoService challengeDaoService;

	@Override
	public List<Answer> save(long challengeId, List<Answer> answerBank) {
		List<Answer> savedAnswers = new ArrayList<>();
		for (Answer answer : answerBank) {
			Answer savedAnswer = save(challengeId, answer);
			savedAnswers.add(savedAnswer);
		}
		return savedAnswers;
	}

	@Override
	public Answer save(long challengeId, Answer answer) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("value", answer.getValue());
		params.addValue("correct", answer.isCorrect());
		params.addValue("challenge_id", challengeId);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO public.answers(value, correct, challenge_id) "
				+ "VALUES (:value, :correct, :challenge_id)";
		namedParameterJdbcTemplate.update(sql, params, keyHolder);

		return answer;
	}


	@Override
	public Map<Long, List<Answer>> getByChallengeIds(List<Long> challengeIds, boolean includeCorrectness) {
		Map<Long, List<Answer>> answerMap = new HashMap<>();
		for (Long id : challengeIds) {
			List<Answer> answers = getByChallengeId(id, includeCorrectness);
			answerMap.put(id, answers);
		}
		return answerMap;
	}

	@Override
	public Map<Long, List<Answer>> getByChallengeIds(List<Long> challengeIds) {
		Map<Long, List<Answer>> answerMap = new HashMap<>();
		for (Long id : challengeIds) {
			List<Answer> answers = getByChallengeId(id, false);
			answerMap.put(id, answers);
		}
		return answerMap;
	}

	@Override
	public List<Answer> getByChallengeId(Long challengeId, boolean includeCorrectness) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("challengeId", challengeId);

		String sql = "SELECT * FROM answers WHERE answers.challenge_id = (:challengeId)";
		List<Answer> answers = namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
			Answer answer = new Answer();
			answer.setChallengeId(rs.getLong("challenge_id"));
			answer.setId(rs.getLong("id"));
			if (includeCorrectness) {
				answer.setCorrect(rs.getBoolean("correct"));
			}
			answer.setValue(rs.getString("value"));
			answer.setSubmittedByUuid(rs.getString("submitted_by"));
			return answer;
		});
		return answers;
	}

	@Override
	public boolean isCorrect(long challengeId, List<Long> userAnswerIds) {
		List<Answer> userAnswers = new ArrayList<>();
		for (Long answerId : userAnswerIds) {
			userAnswers.add(new Answer(answerId));
		}
		List<Answer> answers = getByChallengeId(challengeId, true);
		List<Answer> correctAnswers = answers.stream().filter(answer -> answer.isCorrect()).collect(Collectors.toList());
		if (correctAnswers.containsAll(userAnswers) && userAnswers.containsAll(correctAnswers)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateSubmittedByUser(String firebaseUuid, List<Long> answerIds) {
		for (Long answerId : answerIds) {
			updateSubmittedByUser(firebaseUuid, answerId);
		}
	}

	@Override
	public void updateSubmittedByUser(String firebaseUuid, long answerId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firebaseUuid", firebaseUuid);
		params.addValue("answerId", answerId);

		String sql = "UPDATE answers SET submitted_by=:firebaseUuid WHERE id=:answerId";

		namedParameterJdbcTemplate.update(sql, params);
	}

}
