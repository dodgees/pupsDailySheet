package com.mindcanary.infrastructure.challenges;

import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.StatusType;

import java.util.List;

public interface ChallengeDaoService {

	List<Challenge> getAll();

	List<Challenge> saveAll(List<Challenge> challenges);

	Challenge save(Challenge challenge);

	List<Challenge> getSent(String firebaseUuid);

	List<Challenge> getReceived(String firebaseUuid);

	List<Challenge> getByIds(List<Long> challengeIds);

	void updateStatus(long challengeId, StatusType statusType);
}
