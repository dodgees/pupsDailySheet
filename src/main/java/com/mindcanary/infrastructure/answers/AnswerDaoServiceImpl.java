package com.mindcanary.infrastructure.answers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mindcanary.domain.answer.Answer;

@Named
public class AnswerDaoServiceImpl implements AnswerDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Answer> saveAnswers(long challengeId, List<Answer> answerBank) {
		List<Answer> savedAnswers = new ArrayList<>();
		for (Answer answer : answerBank) {
			Answer savedAnswer = saveAnswer(challengeId, answer);
			savedAnswers.add(savedAnswer);
		}
		return savedAnswers;
	}

	@Override
	public Answer saveAnswer(long challengeId, Answer answer) {
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

}
