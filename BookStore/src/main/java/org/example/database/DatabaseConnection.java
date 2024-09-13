package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect(String dbPath) {
        String jdbcUrl = "jdbc:sqlite:" + dbPath;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connected to SQLite DB.");
        } catch (SQLException e) {
            System.err.println("Not connected to SQLite DB: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Not connected to SQLite DB: " + e.getMessage());
            }
        }
    }
}
