package com.mindcanary.infrastructure.answers;

import java.util.List;

import com.mindcanary.domain.answer.Answer;

public interface AnswerDaoService {

	List<Answer> saveAnswers(long challengeId, List<Answer> answerBank);

	Answer saveAnswer(long challengeId, Answer answer);

}
