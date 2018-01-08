package com.mindcanary.domain.challenge;

import com.mindcanary.infrastructure.challenges.ChallengeDaoService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class ChallengeDomainServieImpl implements ChallengeDomainService {

	@Inject
	private ChallengeDaoService challengeDaoService;

	@Override
	public List<Challenge> getAllChallenges() {
		List<Challenge> challenges = challengeDaoService.getAllChallenges();
		return challenges;
	}

	@Override
	public List<Challenge> saveChallenges(List<Challenge> challenges) {
		List<Challenge> savedChallenges = challengeDaoService.saveChallenges(challenges);
		return savedChallenges;
	}

	@Override
	public List<Challenge> getSentChallenges(String firebaseUuid) {
		List<Challenge> challenges = challengeDaoService.getSentChallenges(firebaseUuid);
		return challenges;
	}

	@Override
	public List<Challenge> getReceivedChallenges(String firebaseUuid) {
		List<Challenge> challenges = challengeDaoService.getReceivedChallenges(firebaseUuid);
		return challenges;
	}

}