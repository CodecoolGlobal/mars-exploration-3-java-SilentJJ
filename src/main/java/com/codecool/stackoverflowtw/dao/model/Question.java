package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public class Question {

    private final int id;
    private final String title;
    private final String body;
    private int like;
    private final LocalDateTime createdAt;
    private final int numOfAnswers;

    public Question(int id, String title, String body, int like, LocalDateTime createdAt, int numOfAnswers) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.like = like;
        this.createdAt = createdAt;
        this.numOfAnswers = numOfAnswers;
    }

    public int getId() {
        return id;
    }

    public int getNumOfAnswers() {
        return numOfAnswers;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getLike() {
        return like;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addLike() {
        like++;
    }
}
