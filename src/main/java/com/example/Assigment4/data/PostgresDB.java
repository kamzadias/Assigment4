package com.example.Assigment4.data;

import com.example.Assigment4.data.interfaces.IDB;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/JavaProject";
        try {
            // Establish the connection
            Connection connection = DriverManager.getConnection(connectionUrl, "postgres", "postgres");

            return connection;
        } catch (Exception e) {
            throw new ServerErrorException("Cannot connect to DB", 500);
        }
    }
}
