package com.example.rpgteambuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel; // Add this to your FXML for error messages

    private UserRepository userRepository;

    @FXML
    private void initialize() {
        userRepository = new UserRepository();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter username and password!");
            return;
        }

        if (userRepository.authenticate(username, password)) {
            int userId = userRepository.getUserId(username);
            if (userId != -1) {
                SceneManager.switchTo("main", userId); // Pass user ID
            } else {
                showAlert("Error", "User ID could not be retrieved.");
            }
        } else {
            errorLabel.setText("Invalid username or password!");
        }
    }
    @FXML
    private void handleSignUpNavigation() {
        SceneManager.switchTo("signup");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}