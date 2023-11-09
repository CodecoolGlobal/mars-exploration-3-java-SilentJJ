package com.codecool.stackoverflowtw.controller.dto;

import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

public record AnswerDTO(int id, String body, int numberOfLikes, LocalDateTime createdAt) {}
