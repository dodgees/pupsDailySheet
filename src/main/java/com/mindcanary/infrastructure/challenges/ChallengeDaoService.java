package com.mindcanary.infrastructure.challenges;

import com.mindcanary.domain.challenge.Challenge;

import java.util.List;

public interface ChallengeDaoService {

	List<Challenge> getAll();

	List<Challenge> saveAll(List<Challenge> challenges);

	Challenge save(Challenge challenge);

	List<Challenge> getSent(String firebaseUuid);

	List<Challenge> getReceived(String firebaseUuid);

}
