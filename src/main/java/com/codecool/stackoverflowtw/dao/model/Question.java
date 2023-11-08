package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;

public class Question {

    private final String title;
    private final String body;
    private int like;
    private LocalDate createdAt;
    private int numOfAnswers;

    public Question(String title, String body, int like, LocalDate createdAt, int numOfAnswers) {
        this.title = title;
        this.body = body;
        this.like = like;
        this.createdAt = createdAt;
        this.numOfAnswers = numOfAnswers;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void addLike() {
        like++;
    }
}
