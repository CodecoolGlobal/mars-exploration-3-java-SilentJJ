package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.connection.PSQLConnector;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO{

    private PSQLConnector connector;

    @Autowired
    public AnswersDaoJdbc(PSQLConnector connector) {
        System.out.println(connector.toString());
        this.connector = connector;
    }


    // itt lesznek a prepare statementek

    public Answer getAnswer(int id) {
        String sql = "SELECT * FROM answers WHERE answers.answers_id = ?";

        try {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();

            DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm");

            return new Answer(
                    rs.getInt("answer_id"),
                    rs.getString("body"),
                    rs.getInt("number_of_likes"),
                    LocalDateTime.parse(rs.getString("created_at").substring(0, 16), formatter)
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Answer> getAllAnswerForQuestion(int id) {
        String sql = "SELECT * FROM answers WHERE answers.answer_id = ?";

        try {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            List<Answer> answers = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm");

            while (rs.next()) {
                answers.add(new Answer(
                        rs.getInt("answer_id"),
                        rs.getString("body"),
                        rs.getInt("number_of_likes"),
                        LocalDateTime.parse(rs.getString("created_at").substring(0, 16), formatter)
                ));
            }

            return answers;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
