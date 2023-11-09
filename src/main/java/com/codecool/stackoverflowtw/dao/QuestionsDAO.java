package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    List<Question> getAllQuestion();
    public Question getQuestionById(int id);
    public List<Question> sortQuestionAlphabetH();
    public List<Question> QuestionSortByDate();
    public void addQuestion (NewQuestionDTO questionDTO);
    public void addLikesToQuestions(int id);
}
