package com.example.rpgteambuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label errorLabel;

    private UserRepository userRepository;

    @FXML
    private void initialize() {
        userRepository = new UserRepository();
    }

    @FXML
    private void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("All fields are required!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match!");
            return;
        }

        try {
            if (userRepository.createUser(username, password)) {
                SceneManager.switchTo("login"); // Success: go back to login
            } else {
                errorLabel.setText("Username already exists!");
            }
        } catch (Exception e) {
            errorLabel.setText("Error creating user: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToLogin() {
        SceneManager.switchTo("login");
    }
}