package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.AnswersDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<AnswerDTO> getAllAnswerByQuestionId(int id) {
        // TODO
        List<Answer> answers = answersDAO.getAllAnswerForQuestion(id);
        List<AnswerDTO> questionDTOS = answers.stream().map(answer -> new AnswerDTO(answer.getId(), answer.getBody(), answer.getNumberOfLikes(), answer.getCreatedAt())).toList();

        return questionDTOS;
    }

    public AnswerDTO getAnswerById(int id) {
        // TODO
        Answer answer = answersDAO.getAnswer(id);
        AnswerDTO answerDTO = new AnswerDTO(answer.getId(), answer.getBody(), answer.getNumberOfLikes(), answer.getCreatedAt());
        return answerDTO;
    }

    public boolean deleteAnswerById(int id) {
        // TODO
        return false;
    }

    public int addNewAnswer(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        return createdId;
    }
}
