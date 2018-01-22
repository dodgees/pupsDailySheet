package com.mindcanary.infrastructure.challenges;

import com.mindcanary.domain.answer.Answer;
import com.mindcanary.domain.answer.AnswerType;
import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.challenge.StatusType;
import com.mindcanary.domain.user.User;
import com.mindcanary.infrastructure.answers.AnswerDaoService;
import com.mindcanary.infrastructure.users.UserDaoService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
public class ChallengeDaoServiceImpl implements ChallengeDaoService {

    @Inject
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Inject
    private AnswerDaoService answerDaoService;

    @Inject
    private UserDaoService userDaoService;

    @Override
    public List<Challenge> getAll() {

        String sql = "SELECT * FROM challenges";

        List<Challenge> challenges = namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
            User toUser = userDaoService.getByUuid(rs.getString("to_user_id"));
            User fromUser = userDaoService.getByUuid(rs.getString("from_user_id"));

            LocalDateTime createdDateTime = rs.getTimestamp("created_timestamp").toLocalDateTime();
            Challenge challenge = new Challenge(rs.getInt("id"), toUser, fromUser, createdDateTime,
                    rs.getString("title"), rs.getString("category"), "Awesome Description", AnswerType.MULTIPLE_CHOICE,
                    StatusType.ASKED, null);
            return challenge;
        });

        return challenges;
    }

    @Override
    public List<Challenge> saveAll(List<Challenge> challenges) {
        List<Challenge> savedChallenges = new ArrayList<>();

        for (Challenge challenge : challenges) {
            Challenge savedChallenged = save(challenge);
            List<Answer> savedAnswers = answerDaoService.save(savedChallenged.getId(),
                    challenge.getAnswerBank());
            savedChallenged.setAnswerBank(savedAnswers);
            savedChallenges.add(savedChallenged);
        }
        return savedChallenges;
    }

    @Override
    public Challenge save(Challenge challenge) {
        MapSqlParameterSource params = new MapSqlParameterSource("from_user_id",
                challenge.getFromUser().getFirebaseUuid())
                .addValue("to_user_id", challenge.getToUser().getFirebaseUuid())
                .addValue("title", challenge.getTitle()).addValue("question", challenge.getQuestion())
                .addValue("answer_type", challenge.getAnswerType().getName())
                .addValue("status_type", challenge.getStatusType().getName())
                .addValue("created_timestamp", Timestamp.valueOf(challenge.getCreatedDateTime()))
                .addValue("created_by", challenge.getFromUser().getFirebaseUuid())
                .addValue("category", challenge.getCategory()).addValue("answer", "MAGIC");
        String sql = "INSERT INTO challenges(from_user_id, to_user_id, title, question, answer_type, status_type, created_timestamp, created_by, category)"
                + "	VALUES (:from_user_id,:to_user_id,:title, :question, :answer_type, :status_type, :created_timestamp, :created_by, :category );";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder);
        long challengeId = Long.valueOf(keyHolder.getKeys().get("id").toString());

        challenge.setId(challengeId);

        return challenge;
    }

    @Override
    public List<Challenge> getSent(String firebaseUuid) {
        MapSqlParameterSource params = new MapSqlParameterSource("from_user_id", firebaseUuid);
        String sql = "SELECT * FROM challenges WHERE from_user_id = :from_user_id";

        List<Challenge> challenges = getChallenges(params, sql);
        return challenges;
    }

    @Override
    public List<Challenge> getReceived(String firebaseUuid) {
        MapSqlParameterSource params = new MapSqlParameterSource("to_user_id", firebaseUuid);
        String sql = "SELECT * FROM challenges WHERE to_user_id = :to_user_id";
        List<Challenge> challenges = getChallenges(params, sql);
        List<Long> challengeIds = challenges.stream().map(Challenge::getId).collect(Collectors.toList());
        Map<Long, List<Answer>> answersMap = answerDaoService.getByChallengeIds(challengeIds);
        for (Challenge challenge : challenges) {
            challenge.setAnswerBank(answersMap.get(challenge.getId()));
        }
        return challenges;
    }

    @Override
    public List<Challenge> getByIds(List<Long> challengeIds) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", challengeIds);
        String sql = "SELECT * FROM challenges WHERE id in (:id)";
        List<Challenge> challenges = getChallenges(params, sql);
        return challenges;
    }

    @Override
    public void updateStatus(long challengeId, StatusType statusType) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("challengeId", challengeId);
        params.addValue("statusType", statusType.getName());
        String sql = "UPDATE challenges SET status_type = :statusType WHERE id=:challengeId";
        namedParameterJdbcTemplate.update(sql, params);
    }

    private List<Challenge> getChallenges(MapSqlParameterSource params, String sql) {
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            User toUser = userDaoService.getByUuid(rs.getString("to_user_id"));
            User fromUser = userDaoService.getByUuid(rs.getString("from_user_id"));
            Timestamp ts = rs.getTimestamp("created_timestamp");
            Challenge challenge = new Challenge(rs.getInt("id"), toUser, fromUser, LocalDateTime.now(),
                    rs.getString("title"), rs.getString("category"), rs.getString("question"),
                    AnswerType.fromName(rs.getString("answer_type")), StatusType.fromName(rs.getString("status_type")),
                    null);
            return challenge;
        });
    }

}
