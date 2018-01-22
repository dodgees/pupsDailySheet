package com.mindcanary.domain.answer;

import com.mindcanary.domain.challenge.StatusType;

import java.util.List;

public interface AnswerDomainService {

    StatusType submit(String firebaseUuid, long challengeId, List<Long> answerIds);

}
