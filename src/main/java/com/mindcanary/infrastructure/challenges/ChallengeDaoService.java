package com.mindcanary.infrastructure.challenges;

import java.util.List;

import com.mindcanary.domain.challenge.Challenge;

public interface ChallengeDaoService {

	List<Challenge> getAllChallenges();

}
