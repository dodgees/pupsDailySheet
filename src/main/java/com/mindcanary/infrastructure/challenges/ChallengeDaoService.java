package com.mindcanary.infrastructure.challenges;

import com.mindcanary.domain.challenge.Challenge;

import java.util.List;

public interface ChallengeDaoService {

	List<Challenge> getAllChallenges();

	List<Challenge> saveChallenges(List<Challenge> challenges);

	Challenge saveChallenge(Challenge challenge);

	List<String> saveAnswerBank(List<String> answerBankItems);

	List<Challenge> getSentChallenges(String firebaseUuid);

	List<Challenge> getReceivedChallenges(String firebaseUuid);

}
