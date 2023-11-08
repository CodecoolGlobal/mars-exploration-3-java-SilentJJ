package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.connection.PSQLConnector;
import com.codecool.stackoverflowtw.dao.model.Question;
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

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }
    // itt lesznek a prepare statementek

    public List<Question> getAllQuestion() {
        String sql = "SELECT questions.question_id, questions.title, questions.body, " +
                "questions.number_of_likes, questions.created_at, " +
                "COUNT(an.question_id) AS answer_num " +
                "FROM questions " +
                "INNER JOIN answers AS an " +
                "ON questions.question_id = an.question_id " +
                "GROUP BY questions.question_id;";
        try {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<Question> questions = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm");

            while(rs.next()) {
                questions.add(new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at").substring(0, 16), formatter),
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
