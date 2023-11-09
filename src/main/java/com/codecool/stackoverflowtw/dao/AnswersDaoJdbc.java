package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.connection.PSQLConnector;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.sql.*;
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

    public Answer getAnswer(int id) {
        String sql = "SELECT * FROM answers WHERE answers.answer_id = ?";
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
        String sql = "SELECT * FROM answers WHERE answers.question_id = ?";

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

    public int addNewAnswer(NewAnswerDTO answer) {
        String body = answer.body();
        int questionId = answer.questionId();
        int likes = 0;
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        String sql = "INSERT INTO questions(body, number_of_likes, question_id, created_at) " +
                "VALUES (?,?,?,?)";

        try {
            Connection conn = connector.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,body);
            psmt.setInt(2,likes);
            psmt.setInt(3,questionId);
            psmt.setTimestamp(4,createdAt);
            psmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;

    }

    public boolean deleteAnswerById(int id) {
        String sql = "DELETE FROM answers WHERE answer_id = ? ";
        try {
            Connection conn = connector.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            psmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
