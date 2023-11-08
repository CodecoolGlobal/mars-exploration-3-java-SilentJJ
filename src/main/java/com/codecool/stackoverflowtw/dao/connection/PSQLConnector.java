package com.codecool.stackoverflowtw.dao.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service

public class PSQLConnector {
    private final String url;

    @Autowired
    public PSQLConnector(Environment environment) {
        this.url = environment.getProperty("connectionstring");
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
