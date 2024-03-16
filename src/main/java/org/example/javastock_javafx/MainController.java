package org.example.javastock_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainController implements Initializable {

    static String[] menu;

    public static void haveMenu(String[] unMenu){
        menu = unMenu;
    }

    @FXML
    private VBox mainVbox;

    @FXML
    private Label mainLabel;

    @FXML
    private ChoiceBox<String> mainChoiceBox;
    private String[] ChoixActions = {};

    @FXML
    private Button mainButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void changementFenetre(ActionEvent evenement) throws IOException, SQLException {
       String uneSelectionMenu = mainChoiceBox.getValue();

        switch (uneSelectionMenu){
            case "Gestion des articles":
                articles.mainArticles();
                break;
            case "Gestion des coureurs":
                coureurs.mainCoureurs();
                break;
            case "Gestion des types d’épreuve":
                typeEpreuves.mainTypeEpreuves();
                break;
            case "Gestion des réservations":
                reservation.mainReservation();
                break;
            case "Article en rupture / réservation en attente":
                break;
            case "Consulter l’historique":
                break;
            case "Quitter":
                break;
            default:
                break;
        }

        root = FXMLLoader.load(getClass().getResource("application.fxml"));
        stage = (Stage)((Node)evenement.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        mainChoiceBox.getItems().addAll(menu);
    }
}
