package com.mindcanary.domain.challenge;

import com.mindcanary.domain.answer.Answer;
import com.mindcanary.domain.answer.AnswerType;
import com.mindcanary.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class Challenge {

    private long id;

    private User toUser;

    private User fromUser;

    private LocalDateTime createdDateTime;


    private String category;

    private String question;

    private AnswerType answerType;

    private StatusType statusType;

    private List<Answer> answerBank;

    public Challenge() {
    }

    public Challenge(long id, User toUser, User fromUser, LocalDateTime createdDateTime, String category,
                     String question, AnswerType answerType, StatusType statusType, List<Answer> answerBank) {
        super();
        this.id = id;
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.createdDateTime = createdDateTime;
        this.category = category;
        this.question = question;
        this.answerType = answerType;
        this.statusType = statusType;
        this.answerBank = answerBank;
    }

    public Challenge(String category) {
        super();
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public List<Answer> getAnswerBank() {
        return answerBank;
    }

    public void setAnswerBank(List<Answer> answerBank) {
        this.answerBank = answerBank;
    }

}
