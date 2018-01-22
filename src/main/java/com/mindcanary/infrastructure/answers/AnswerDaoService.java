package com.mindcanary.infrastructure.answers;

import com.mindcanary.domain.answer.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerDaoService {

	List<Answer> save(long challengeId, List<Answer> answerBank);

	Answer save(long challengeId, Answer answer);

	Map<Long, List<Answer>> getByChallengeIds(List<Long> challengeIds, boolean includeCorrectness);

	Map<Long, List<Answer>> getByChallengeIds(List<Long> challengeIds);

	List<Answer> getByChallengeId(Long challengeId, boolean includeCorrectness);

	boolean isCorrect(long challengeId, List<Long> answers);
}
