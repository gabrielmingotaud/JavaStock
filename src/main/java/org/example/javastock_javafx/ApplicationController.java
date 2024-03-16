package org.example.javastock_javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    static ArrayList<String> applicationElement = new ArrayList<String>();
    static ArrayList<Integer> applicationElementId = new ArrayList<Integer>();
    static String objet;

    static String choixData;
    static int idChoixData, idSelectedElement;

    @FXML
    private VBox applicationVbox;
    private HBox applicationHbox, HboxListView, HboxValue, HboxSupprimer;
    private Pane spacePane;

    @FXML
    private ListView<String> applicationListView;

    @FXML
    private Button applicationButtonAjouter, applicationButtonModifier, applicationButtonSupprimer;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ajouterElement() throws IOException {
        NewElementController.mainNewElement(objet, -1);
    }
    public void modifierElement() throws IOException {
        NewElementController.mainNewElement(objet, idSelectedElement);
    }

    public void supprimerElement(){
        try{
            switch (objet){
                case "articles":
                    actionBDD.requeteBdd("DELETE FROM Articles WHERE idArticle = " + idSelectedElement);
                    break;
                case "coureurs":
                    actionBDD.requeteBdd("DELETE FROM coureur WHERE id_coureur = " + idSelectedElement);
                    break;
                case "typeEpreuves":
                    actionBDD.requeteBdd("DELETE FROM epreuve WHERE id_epreuve = " + idSelectedElement);
                    break;
                case "reservations":
                    actionBDD.requeteBdd("DELETE FROM Reservation WHERE id_reservation = " + idSelectedElement);
                    break;
                default:
                    break;
            }
            applicationListView.getItems().remove(idChoixData);
        }catch(Exception error){
            System.out.println(error);
        }
    }

//    public void refreshListView() throws IOException {
//        applicationElement.clear();
//        applicationElementId.clear();
//        applicationListView.getItems().clear();
//        switch (objet){
//            case "coureurs":
//                coureurs.mainCoureurs();
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < applicationElement.size(); i++) {
            applicationListView.getItems().addAll(applicationElement.get(i));

        }
        applicationElement.clear();


        applicationListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                choixData = applicationListView.getSelectionModel().getSelectedItem();
                idChoixData = applicationListView.getSelectionModel().getSelectedIndex();
                idSelectedElement = applicationElementId.get(idChoixData);

            }
        });
    }
}
