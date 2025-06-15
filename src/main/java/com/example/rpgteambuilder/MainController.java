package com.example.rpgteambuilder;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainController {
    @FXML
    private ListView<String> teamListView;
    @FXML
    private VBox characterContainer;

    private TeamRepository teamRepository;
    private CharacterRepository characterRepository;
    private DatabaseConnection dbConnection; // Add this for database access
    private int currentUserId = 1; // Replace with actual user ID from login
    private Character selectedCharacter; // Track selected character for deletion

    @FXML
    private void initialize() {
        teamRepository = new TeamRepository();
        characterRepository = new CharacterRepository();
        dbConnection = new DatabaseConnection(); // Initialize here
        loadTeams();

        teamListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                int teamId = getTeamIdFromName(newVal);
                displayCharacters(teamId);
            }
        });
    }

    private void loadTeams() {
        teamListView.getItems().clear();
        List<Team> teams = teamRepository.getTeamsForUser(currentUserId);
        teamListView.getItems().addAll(teams.stream().map(Team::getName).toList());
    }

    private int getTeamIdFromName(String name) {
        return teamRepository.getTeamsForUser(currentUserId).stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .map(Team::getId)
                .orElse(-1);
    }

    private void displayCharacters(int teamId) {
        characterContainer.getChildren().clear();
        List<Character> characters = characterRepository.getCharactersForTeam(teamId);
        for (Character character : characters) {
            HBox hbox = new HBox(10);
            Label label = new Label(character.toSummaryString());
            label.setStyle("-fx-padding: 5; -fx-background-color: lightgray; -fx-cursor: hand;");
            label.setOnMouseClicked(event -> {
                selectedCharacter = character;
                showDetailedStats(character);
            });
            Button editButton = new Button("Edit");
            editButton.setOnAction(event -> editCharacter(character));
            hbox.getChildren().addAll(label, editButton);
            characterContainer.getChildren().add(hbox);
        }
    }

    private void showDetailedStats(Character character) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Character Details");
        alert.setHeaderText(character.getName() + " (" + character.getCharacterClass() + ")");
        alert.setContentText(character.toString());
        alert.showAndWait();
    }

    @FXML
    private void handleAddTeam() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Team");
        dialog.setHeaderText("Enter team name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            teamRepository.createTeam(currentUserId, name);
            loadTeams();
        });
    }

    @FXML
    private void handleAddCharacter() {
        String selectedTeam = teamListView.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            showAlert("Error", "Please select a team first.");
            return;
        }
        int teamId = getTeamIdFromName(selectedTeam);
        int characterCount = characterRepository.getCharacterCountForTeam(teamId);
        if (characterCount >= 6) {
            showAlert("Error", "Team already has the maximum of 6 characters.");
            return;
        }
        ChoiceDialog<String> classDialog = new ChoiceDialog<>("Warrior", Arrays.asList(
                "Warrior", "Mage", "Rogue", "Cleric", "Ranger", "Paladin", "Sorcerer",
                "Bard", "Druid", "Monk", "Barbarian", "Necromancer"));
        classDialog.setTitle("Select Character Class");
        classDialog.setHeaderText("Choose a class for the character:");
        Optional<String> classResult = classDialog.showAndWait();
        if (classResult.isPresent()) {
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setTitle("Add Character");
            nameDialog.setHeaderText("Enter character name:");
            Optional<String> nameResult = nameDialog.showAndWait();
            nameResult.ifPresent(name -> {
                characterRepository.createCharacter(teamId, name, classResult.get());
                displayCharacters(teamId);
            });
        }
    }

    @FXML
    private void handleEditTeam() {
        String selectedTeam = teamListView.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            showAlert("Error", "Please select a team to edit.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog(selectedTeam);
        dialog.setTitle("Edit Team");
        dialog.setHeaderText("Enter new name for team '" + selectedTeam + "':");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            int teamId = getTeamIdFromName(selectedTeam);
            teamRepository.updateTeamName(teamId, newName);
            loadTeams();
        });
    }

    @FXML
    private void handleDeleteTeam() {
        String selectedTeam = teamListView.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            showAlert("Error", "Please select a team to delete.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Are you sure you want to delete the team '" + selectedTeam + "'?");
        confirmAlert.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int teamId = getTeamIdFromName(selectedTeam);
            teamRepository.deleteTeam(teamId);
            loadTeams();
            characterContainer.getChildren().clear();
        }
    }


    @FXML
    private void handleDeleteCharacter() {
        if (selectedCharacter == null) {
            showAlert("Error", "Please select a character by clicking on it first.");
            return;
        }
        String selectedTeam = teamListView.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            showAlert("Error", "Please select a team.");
            return;
        }
        int teamId = getTeamIdFromName(selectedTeam);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete " + selectedCharacter.getName() + "?");
        confirm.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteCharacter(selectedCharacter.getId());
            displayCharacters(teamId);
            selectedCharacter = null;
        }
    }
    private void editCharacter(Character character) {
        TextInputDialog nameDialog = new TextInputDialog(character.getName());
        nameDialog.setTitle("Edit Character");
        nameDialog.setHeaderText("Enter new name for " + character.getName() + ":");
        Optional<String> nameResult = nameDialog.showAndWait();
        if (nameResult.isPresent()) {
            ChoiceDialog<String> classDialog = new ChoiceDialog<>(character.getCharacterClass(), Arrays.asList(
                    "Warrior", "Mage", "Rogue", "Cleric", "Ranger", "Paladin", "Sorcerer",
                    "Bard", "Druid", "Monk", "Barbarian", "Necromancer"));
            classDialog.setTitle("Select New Class");
            classDialog.setHeaderText("Choose a new class for " + character.getName() + ":");
            Optional<String> classResult = classDialog.showAndWait();
            if (classResult.isPresent()) {
                characterRepository.updateCharacter(character.getId(), nameResult.get(), classResult.get());
                int teamId = getTeamIdFromName(teamListView.getSelectionModel().getSelectedItem());
                displayCharacters(teamId);
            }
        }
    }
    private void deleteCharacter(int characterId) {
        String sql = "DELETE FROM characters WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, characterId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete character: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}