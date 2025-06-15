package com.example.rpgteambuilder;

// junije palmotic
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        // Postavite odgovarajući naziv baze podataka, korisnika i password
        String databaseName = "RpgTeamBuilder"; // Your PostgreSQL database name
        String databaseUser = "postgres"; // Your PostgreSQL username (default is 'postgres')
        String databasePassword = "admin"; // Your PostgreSQL password
        // Postaviti URL do baze
        String url = "jdbc:postgresql://localhost:5432/" + databaseName;
        try {
            Class.forName("org.postgresql.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}