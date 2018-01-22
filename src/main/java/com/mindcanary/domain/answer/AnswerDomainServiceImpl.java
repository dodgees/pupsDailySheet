package com.mindcanary.domain.answer;

import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.StatusType;
import com.mindcanary.exceptions.AuthorizationException;
import com.mindcanary.infrastructure.answers.AnswerDaoService;
import com.mindcanary.infrastructure.challenges.ChallengeDaoService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
public class AnswerDomainServiceImpl implements AnswerDomainService {

    @Inject
    private AnswerDaoService answerDaoService;

    @Inject
    private ChallengeDaoService challengeDaoService;

    @Override
    public StatusType submit(String firebaseUuid, long challengeId, List<Long> answerIds) {
        Challenge challenge = challengeDaoService.getByIds(Arrays.asList(challengeId)).get(0);
        if (!challenge.getToUser().getFirebaseUuid().equals(firebaseUuid)) {
            throw new AuthorizationException();
        } else {
            boolean isCorrect = answerDaoService.isCorrect(challengeId, answerIds);
            challengeDaoService.updateStatus(challengeId, StatusType.fromAnswered(isCorrect));
            return StatusType.fromAnswered(isCorrect);
        }
    }
}
