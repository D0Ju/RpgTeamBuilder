package com.example.rpgteambuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("RPG Team Builder");
        stage.setScene(scene);
        stage.show();

        SceneManager.setStage(stage);
        /// Registrirajte sve potrebne scene
        SceneManager.registerScene("login", "/com/example/rpgteambuilder/login.fxml");
        SceneManager.registerScene("main", "/com/example/rpgteambuilder/main.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}