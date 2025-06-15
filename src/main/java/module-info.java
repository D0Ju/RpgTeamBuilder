module com.example.rpgteambuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.rpgteambuilder to javafx.fxml;
    exports com.example.rpgteambuilder;
}