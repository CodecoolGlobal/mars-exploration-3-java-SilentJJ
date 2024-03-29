package com.codecool.stackoverflowtw.dao.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class PSQLConnector {

    @Value("${connectionstring}")
    private String url;
    @Value("${dbpassword}")
    private String dbPassword;
    @Value("${dbusername}")
    private String dbUsername;

    public PSQLConnector() {
        System.out.println("hi");
        /*this.url = environment.getProperty("connectionstring")*/;
    }

    public Connection getConnection() {
        Connection connection = null;
        System.out.println("url:"+url);
        try {
            System.out.println("haho");

             return DriverManager.getConnection(url, dbUsername, dbPassword);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
