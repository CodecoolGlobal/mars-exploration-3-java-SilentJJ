package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface AnswersDAO {
    Answer getAnswer(int id);
    List<Answer> getAllAnswerForQuestion(int id);
    int addNewAnswer(NewAnswerDTO answer);
    boolean deleteAnswerById(int id);
}
