package com.example.rpgteambuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Add your authentication logic here (e.g., check the database)
        if (!username.isEmpty() && !password.isEmpty()) { // Simple placeholder check
            SceneManager.switchTo("main"); // Switch to main screen if login succeeds
        } else {
            System.out.println("Please enter username and password!");
        }
    }
}