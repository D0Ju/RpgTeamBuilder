package com.example.rpgteambuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository {
    private DatabaseConnection dbConnection;

    public TeamRepository() {
        dbConnection = new DatabaseConnection();
    }
    public void updateTeamName(int teamId, String newName) {
        String sql = "UPDATE teams SET name = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, teamId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Team> getTeamsForUser(int userId) {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams WHERE user_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                teams.add(new Team(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public void createTeam(int userId, String name) {
        String sql = "INSERT INTO teams (user_id, name) VALUES (?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeam(int teamId) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}