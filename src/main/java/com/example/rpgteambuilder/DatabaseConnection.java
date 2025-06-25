package com.example.rpgteambuilder;

// junije palmotic
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/RpgTeamBuilder";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}