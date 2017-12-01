package com.mindcanary.domain.challenge;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.mindcanary.infrastructure.challenges.ChallengeDaoService;

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

}