package com.mindcanary.controllers;

import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.ChallengeDomainService;
import com.mindcanary.domain.challenge.StatusType;
import com.mindcanary.domain.user.User;
import com.mindcanary.infrastructure.RequestScopedData;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    @Inject
    private RequestScopedData requestScopedData;

    @Inject
    private ChallengeDomainService challengeDomainService;

    @RequestMapping(value = "/sent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<Challenge> getSentChallenges() {
        String firebaseUuid = requestScopedData.getFirebaseToken().getUid();
        List<Challenge> challenges = challengeDomainService.getSentChallenges(firebaseUuid);
        return challenges;
    }

    @RequestMapping(value = "/received", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<Challenge> getReceivedChallenges() {
        String firebaseUuid = requestScopedData.getFirebaseToken().getUid();
        List<Challenge> challenges = challengeDomainService.getReceivedChallenges(firebaseUuid);
        return challenges;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<Challenge> getAllChallenges() {
        List<Challenge> challenges = challengeDomainService.getAllChallenges();
        return challenges;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<Challenge> createChallenge(@RequestBody Challenge challenge) {
        // set created time on server so it can't be manipulated.
        User user = new User(requestScopedData.getFirebaseToken().getUid());
        challenge.setFromUser(user);
        challenge.setCreatedDateTime(LocalDateTime.now());
        challenge.setStatusType(StatusType.ASKED);
        List<Challenge> savedChallenges = challengeDomainService.saveChallenges(Arrays.asList(challenge));
        return savedChallenges;
    }

}
