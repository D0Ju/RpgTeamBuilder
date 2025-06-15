package com.example.rpgteambuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class SceneManager {

    private static Stage mainStage ;
    private static final Map<String, String> sceneMap = new HashMap<>();
    /// postavite setter za mainStage
    public static void setStage(Stage stage) {
        mainStage = stage;
    }
    public static void registerScene(String name, String fxmlPath) {
        sceneMap.put(name, fxmlPath);
    }

    public static void switchTo(String name, Object... args) {
        String fxmlPath = sceneMap.get(name);
        if (fxmlPath == null) {
            System.err.println("Scena s imenom '" + name + "' nije registrirana!");
            return;
        }
        else {
            System.out.println("scena je registrirana " + fxmlPath);
        }

        try {
            /// ucitajte resurs fxml po fxmlPath
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            MainController controller = loader.getController(); // Get the loaded controller
            if (name.equals("main") && args.length > 0 && args[0] instanceof Integer) {
                int userId = (Integer) args[0];
                System.out.println("Setting userId in SceneManager: " + userId); // Debug
                controller.setCurrentUserId(userId); // Set the user ID on the loaded controller
            }
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}