package com.mindcanary.controllers;

import com.mindcanary.domain.answer.AnswerDomainService;
import com.mindcanary.domain.challenge.StatusType;
import com.mindcanary.infrastructure.RequestScopedData;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Inject
    private RequestScopedData requestScopedData;

    @Inject
    private AnswerDomainService answerDomainService;

    @RequestMapping(value = "/submit/{challengeId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    StatusType getSentChallenges(@PathVariable("challengeId") long challengeId, @RequestParam("answerIds") List<Long> answerIds) {
        String firebaseUuid = requestScopedData.getFirebaseToken().getUid();
        StatusType statusType = answerDomainService.submit(firebaseUuid, challengeId, answerIds);
        return statusType;
    }

}
