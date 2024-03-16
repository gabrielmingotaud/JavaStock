package org.example.javastock_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stageMain) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1800, 900);
        stageMain.setTitle("JavaStock");
        stageMain.setScene(scene);
        stageMain.show();
    }

    public static void main(String[] args) {

        String[] menuMain = {"Gestion des articles", "Gestion des coureurs", "Gestion des types d’épreuve", "Gestion des réservations", "Article en rupture / réservation en attente", "Consulter l’historique", "Quitter"};

        MainController.haveMenu(menuMain);

        launch();
    }
}