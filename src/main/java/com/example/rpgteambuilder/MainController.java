package com.example.rpgteambuilder;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController {
    @FXML
    private ListView<String> teamListView;

    @FXML
    private void handleAddTeam() {
        // Logic to add a team (e.g., prompt user for team name and add to list)
        teamListView.getItems().add("New Team");
    }

    @FXML
    private void handleAddCharacter() {
        // Logic to add a character (you can expand this later)
        System.out.println("Add Character clicked!");
    }
}