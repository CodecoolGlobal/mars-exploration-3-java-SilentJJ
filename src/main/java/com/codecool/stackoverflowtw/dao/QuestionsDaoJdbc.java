package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.connection.PSQLConnector;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private PSQLConnector connector;

    @Autowired
    public QuestionsDaoJdbc(PSQLConnector connector) {
        System.out.println(connector.toString());
        this.connector = connector;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }
    // itt lesznek a prepare statementek

    public List<Question> getAllQuestion() {
        String sql = "SELECT question.question_id, question.title, question.body, " +
                "question.number_ofLikes, question.create_at, " +
                "COUNT(an.question_id) AS answer_num" +
                "FROM questions " +
                "INNER JOIN answers AS an " +
                "ON question.question_id = an.question_id " +
                "GROUP BY question.question_id";
        try {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<Question> questions = new ArrayList<>();

            while(rs.next()) {
                questions.add(new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at")),
                        rs.getInt("answer_num")
                ));
            }
            return questions;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
