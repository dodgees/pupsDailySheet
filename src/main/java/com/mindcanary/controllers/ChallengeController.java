package com.mindcanary.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.ChallengeDomainService;
import com.mindcanary.domain.challenge.StatusType;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

	@Inject
	private ChallengeDomainService challengeDomainService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<Challenge> getAllChallenges() {
		List<Challenge> challenges = challengeDomainService.getAllChallenges();
		return challenges;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<Challenge> createChallenge(@RequestBody Challenge challenge) {
		// set created time on server so it can't be manipulated.
		challenge.setCreatedDateTime(LocalDateTime.now());
		challenge.setStatusType(StatusType.ASKED);
		challenge.setCategory("Magic");
		List<Challenge> savedChallenges = challengeDomainService.saveChallenges(Arrays.asList(challenge));
		return savedChallenges;
	}

}
