package com.mindcanary.domain.challenge;

import java.util.List;

public interface ChallengeDomainService {

	List<Challenge> getAllChallenges();

	List<Challenge> saveChallenges(List<Challenge> challenges);

	List<Challenge> getSentChallenges(String firebaseUuid);
}
