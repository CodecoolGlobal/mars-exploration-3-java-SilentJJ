package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        // TODO
        List<Question> questions = questionsDAO.getAllQuestion();
        List<QuestionDTO> questionDTOS = questions.stream().map(question -> new QuestionDTO(question.getId(), question.getTitle(), question.getBody(), question.getCreatedAt(), question.getNumOfAnswers(), question.getLike())).toList();

        return questionDTOS;
    }

    public QuestionDTO getQuestionById(int id) {
        // TODO
        questionsDAO.sayHi();
        Question question = questionsDAO.getQuestion(id);
        QuestionDTO questionDTO = new QuestionDTO(question.getId(), question.getTitle(), question.getBody(), question.getCreatedAt(), question.getNumOfAnswers(), question.getLike());
        return questionDTO;
    }

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        return createdId;
    }
}
