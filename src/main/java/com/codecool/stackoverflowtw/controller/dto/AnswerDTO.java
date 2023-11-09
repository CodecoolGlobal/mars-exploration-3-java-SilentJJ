package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(int id, String body, int numberOfLikes, LocalDateTime createdAt) {}
