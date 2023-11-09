package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.connection.PSQLConnector;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private PSQLConnector connector;

    @Autowired
    public QuestionsDaoJdbc(PSQLConnector connector) {
        System.out.println(connector.toString());
        this.connector = connector;
    }

    public void addQuestion (NewQuestionDTO questionDTO) {
        String title = questionDTO.title();
        String body = questionDTO.description();
        int likes = 0;
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        String sql = "INSERT INTO questions(title, body, number_of_likes, created_at) " +
                "VALUES (?,?,?,?)";

                try {
                    Connection conn = connector.getConnection();
                    PreparedStatement psmt = conn.prepareStatement(sql);
                    psmt.setString(1,title);
                    psmt.setString(2,body);
                    psmt.setInt(3,likes);
                    psmt.setTimestamp(4,createdAt);
                    psmt.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

    }

    public List<Question> getAllQuestion() {
        String sql = "SELECT questions.question_id, questions.title, questions.body, " +
                "questions.number_of_likes, questions.created_at, " +
                "COUNT(an.question_id) AS answer_num " +
                "FROM questions " +
                "LEFT JOIN answers AS an " +
                "ON questions.question_id = an.question_id " +
                "GROUP BY questions.question_id;";
        try {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<Question> questions = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            while(rs.next()) {
                questions.add(new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at").substring(0,16), formatter),
                        rs.getInt("answer_num")
                ));
            }
            return questions;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Question getQuestionById(int id) {
        String sql = "SELECT questions.question_id, questions.title, questions.body, " +
                "questions.number_of_likes, questions.created_at, " +
                "COUNT(an.question_id) AS answer_num " +
                "INNER JOIN answers as an ON questions.question_id = an.question_id " +
                "WHERE questions.question_id = " + id;

        try{
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm");
            Question searchedQuestion = null;
            while (rs.next()){
                 searchedQuestion = new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at").substring(0, 16), formatter),
                        rs.getInt("answer_num")
                );
            }
            return searchedQuestion;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    public List<Question> sortQuestionAlphabetH(){
        String sql = "SELECT questions.question_id, questions.title, questions.body, questions.number_of_likes, questions.created_at,  +\n" +
                "COUNT(an.question_id) AS answer_num\n" +
                "FROM questions\n" +
                "LEFT JOIN answers AS an\n" +
                "ON questions.question_id = an.question_id\n" +
                "GROUP BY questions.question_id,questions.title\n" +
                "ORDER BY questions.title";
        try{
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm");
            List<Question> sortedQuestions = new ArrayList<>();
            while (rs.next()){
                sortedQuestions.add(new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at").substring(0, 16), formatter),
                        rs.getInt("answer_num")
                ));
            }
            return sortedQuestions;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Question> QuestionSortByDate(){
        String sql = "SELECT questions.question_id, questions.title, questions.body, questions.number_of_likes, questions.created_at,\n" +
                "COUNT(an.question_id) AS answer_num\n" +
                "FROM questions\n" +
                "LEFT JOIN answers AS an\n" +
                "ON questions.question_id = an.question_id\n" +
                "GROUP BY questions.question_id,questions.created_at\n" +
                "ORDER BY questions.created_at";
        try {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            List<Question> sortedQuestionsByDate = new ArrayList<>();
            while (rs.next()) {
                sortedQuestionsByDate.add(new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at").substring(0, 16), formatter),
                        rs.getInt("answer_num")));
            }
            return sortedQuestionsByDate;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public void deleteQuestion (int id) {
        String sql = "DELETE FROM questions WHERE question_id = ? ";
        try {
            Connection conn = connector.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            psmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  };
