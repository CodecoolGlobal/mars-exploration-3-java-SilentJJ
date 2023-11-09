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
        List<Question> questions = questionsDAO.getAllQuestion();
        List<QuestionDTO> questionDTOS = questions.stream().map(question -> new QuestionDTO(question.getId(), question.getTitle(), question.getBody(), question.getCreatedAt(), question.getNumOfAnswers(), question.getLike())).toList();

        return questionDTOS;
    }

    public QuestionDTO getQuestionById(int id) {
        Question searchedQuestion = questionsDAO.getQuestionById(id);
        QuestionDTO questionDTO = new QuestionDTO(searchedQuestion.getId(),searchedQuestion.getTitle(),searchedQuestion.getBody(),searchedQuestion.getCreatedAt(),searchedQuestion.getNumOfAnswers(),searchedQuestion.getLike());
        return questionDTO;
    }

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return 0;
    }
    public List<QuestionDTO> sortQuestionsAlphabet(){
        List<Question> sortedQuestion =questionsDAO.sortQuestionAlphabetH();
        List<QuestionDTO> questionDTO = sortedQuestion.stream().map(question -> new QuestionDTO(question.getId(), question.getTitle(), question.getBody(), question.getCreatedAt(), question.getNumOfAnswers(), question.getLike())).toList();

        return questionDTO;
    }
    public List<QuestionDTO> sortedQuestionByDate(){
        List<Question> sortedQuestionByDate = questionsDAO.QuestionSortByDate();
        List<QuestionDTO> questionDTO = sortedQuestionByDate.stream().map(question -> new QuestionDTO(question.getId(), question.getTitle(), question.getBody(), question.getCreatedAt(), question.getNumOfAnswers(), question.getLike())).toList();
        return questionDTO;
    }
}
