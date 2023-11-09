package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public class Answer {

    private final int id;
    private final String body;
    private final int numberOfLikes;
    private final LocalDateTime createdAt;

    public Answer(int id, String body, int numberOfLikes, LocalDateTime createdAt) {
        this.id = id;
        this.body = body;
        this.numberOfLikes = numberOfLikes;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
