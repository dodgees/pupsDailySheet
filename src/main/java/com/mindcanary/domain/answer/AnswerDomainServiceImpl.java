package com.mindcanary.domain.answer;

import com.mindcanary.domain.challenge.StatusType;

import javax.inject.Named;
import java.util.List;

@Named
public class AnswerDomainServiceImpl implements AnswerDomainService {
    @Override
    public StatusType submit(String firebaseUuid, long challengeId, List<Long> answerIds) {
        return null;
    }
}
