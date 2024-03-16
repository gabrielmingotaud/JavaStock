package org.example.javastock_javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewElementController {

    static String objet, choixUneCategorie = "";
    static int selectedElement;
    static ArrayList<TextField> textFields = new ArrayList<>();
    static ArrayList<ChoiceBox> choiceBoxs = new ArrayList<>();

    static ArrayList<Label> labelCategorie = new ArrayList<>();
    static ArrayList<TextField> textFieldsCategorie = new ArrayList<>();

    static ArrayList<String> labelElement = new ArrayList<>();
    static ArrayList<String> valeursElement = new ArrayList<>();
    public static void mainNewElement(String unObjet, int unIdSelectedElement) throws IOException {

        objet = unObjet;
        selectedElement = unIdSelectedElement;

        labelElement.clear();
        valeursElement.clear();

        switch (objet){
            case "articles":
                if(selectedElement != -1){
                    articles unArticle = articles.getArticleById(selectedElement, articles.listeArticles);
                    valeursElement.add(unArticle.getLibelle_article());
                    valeursElement.add(String.valueOf(unArticle.getQuantite()));
                    choixUneCategorie = unArticle.getType_categorie();
                    valeursElement.add(articles.getHashmapData(articles.getCategorieById(selectedElement)));
                }
                labelElement.add("Libelle: ");
                labelElement.add("Quantité: ");
                break;
            case "coureurs":
                if(selectedElement != -1){
                    coureurs unCoureur = coureurs.getCoureurById(selectedElement, coureurs.listeCoureurs);
                    valeursElement.add(unCoureur.getNom_coureur());
                    valeursElement.add(unCoureur.getPrenom_coureur());
                }
                labelElement.add("Nom du coureur: ");
                labelElement.add("Prénom du coureur: ");
                break;
            case "typeEpreuves":
                if(selectedElement != -1){
                    typeEpreuves unTypeEpreuve = typeEpreuves.getTypeEpreuveById(selectedElement, typeEpreuves.listeTypeEpreuves);
                    valeursElement.add(unTypeEpreuve.getLibelle_epreuve());
                }
                labelElement.add("Libelle epreuve: ");
                break;
            case "reservations":
                if(selectedElement != -1){
                    reservation uneReservation = reservation.getReservationById(selectedElement, reservation.listeReservation);
                    valeursElement.add(String.valueOf(uneReservation.getIdArticle()));
                    valeursElement.add(String.valueOf(uneReservation.getIdCoureur()));
                    valeursElement.add(String.valueOf(uneReservation.getIdEpreuve()));
                }
                labelElement.add("Article: ");
                labelElement.add("Coureur: ");
                labelElement.add("Épreuve: ");

                break;
            default:
                break;
        }


        Stage stage = new Stage();
        GridPane grid = new GridPane();

        addGridArticlesCategories(grid);

        final Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.show();

    }

    public static void closeStage(ActionEvent evenement){
        ((Node)(evenement.getSource())).getScene().getWindow().hide();
    }

    public static void addGridArticlesCategories(GridPane uneGrid){
        labelCategorie.clear();
        textFieldsCategorie.clear();
        uneGrid.getChildren().clear();



        Label unLabelChoiceBox = new Label("Catégorie: ");
        ChoiceBox uneChoiceBox = new ChoiceBox<>();
        uneChoiceBox.getItems().addAll("Textile", "Boisson", "denree_seche");
        uneChoiceBox.setValue(choixUneCategorie);

        ChoiceBox choiceBoxCoureurs = new ChoiceBox();
        ChoiceBox choiceBoxArticles = new ChoiceBox();
        ChoiceBox<String> choiceBoxTypeEpreuves = new ChoiceBox();

        if(objet == "reservations"){
            //ChoiceBox choiceBoxReservation = new ChoiceBox();
            Label unLabelCoureur = new Label("Coureurs :");
            List<String> libelleCoureurs = reservation.listeCoureurs.stream().map(l -> l.getNom_coureur() + " " + l.getPrenom_coureur()).collect(Collectors.toList());
            choiceBoxCoureurs.getItems().addAll(libelleCoureurs);


            Label unLabelArticle = new Label("Articles :");
            List<String> libelleArticles = reservation.listeArticle.stream().map(l -> l.getLibelle_article()).collect(Collectors.toList());
            choiceBoxArticles.getItems().addAll(libelleArticles);

            Label unLabelTypeEpreuves = new Label("Types epreuves :");
            List<String> libelleTypesEpreuves = reservation.listeTypeEpreuves.stream().map(l -> l.getLibelle_epreuve()).collect(Collectors.toList());

            choiceBoxTypeEpreuves.getItems().addAll(libelleTypesEpreuves);
            if(reservation.listeReservation.size() > 0 && selectedElement > 0){
                choiceBoxCoureurs.setValue(coureurs.getCoureurById(reservation.getReservationById(selectedElement, reservation.listeReservation).getIdCoureur(), reservation.listeCoureurs).getNom_coureur() + " " + coureurs.getCoureurById(reservation.getReservationById(selectedElement, reservation.listeReservation).getIdCoureur(), reservation.listeCoureurs).getPrenom_coureur());
                choiceBoxArticles.setValue(articles.getArticleById(reservation.getReservationById(selectedElement, reservation.listeReservation).getIdArticle(), reservation.listeArticle).getLibelle_article());
                choiceBoxTypeEpreuves.setValue(typeEpreuves.getTypeEpreuveById(reservation.getReservationById(selectedElement, reservation.listeReservation).getIdEpreuve(), reservation.listeTypeEpreuves).getLibelle_epreuve());

            }

            uneGrid.add(unLabelCoureur, 0, 0);
            uneGrid.add(choiceBoxCoureurs, 100, 0);

            uneGrid.add(unLabelArticle, 0, 1);
            uneGrid.add(choiceBoxArticles, 100, 1);

            uneGrid.add(unLabelTypeEpreuves, 0, 2);
            uneGrid.add(choiceBoxTypeEpreuves, 100, 2);

        }else {
            int cpt = 0;
            for (int i = 0; i < labelElement.size(); i++) {
                Label unLabel = new Label(labelElement.get(i));
                TextField unTextField = new TextField(selectedElement != -1 ? valeursElement.get(i) : "");
                uneGrid.add(unLabel, 0, i);
                uneGrid.add(unTextField, 100, i);
                textFields.add(unTextField);
                cpt++;
            }
            if (objet == "articles"){
                uneGrid.add(unLabelChoiceBox, 0, cpt);
                uneGrid.add(uneChoiceBox, 100, cpt);
            }
        }

        Button unBouton = new Button();

        if (selectedElement == -1){
            unBouton.setText("Ajouter");
            unBouton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch (objet){
                        case "articles":
                            int idArticleInsert = actionBDD.insertBddReturning("INSERT INTO Articles(libelle_article, quantite, type_categorie) VALUES('" + textFields.get(0).getText() + "', '" + textFields.get(1).getText() + "', '" + choixUneCategorie + "') returning idArticle");
                            if (choixUneCategorie == "Textile"){
                                actionBDD.requeteBdd("INSERT INTO " + choixUneCategorie + "(idArticle, taille, couleur) VALUES('" + idArticleInsert + "', '" + textFieldsCategorie.get(0).getText() + "', '" + textFieldsCategorie.get(1).getText() + "')");
                            }else if (choixUneCategorie == "Boisson"){
                                actionBDD.requeteBdd("INSERT INTO " + choixUneCategorie + "(idArticle, volume) VALUES('" + idArticleInsert + "', '" + textFieldsCategorie.get(0).getText() + "')");
                            }else if (choixUneCategorie == "denree_seche"){
                                actionBDD.requeteBdd("INSERT INTO " + choixUneCategorie + "(idArticle, poid) VALUES('" + idArticleInsert + "', '" + textFieldsCategorie.get(0).getText() + "')");
                            }
                            break;
                        case "coureurs":
                            actionBDD.requeteBdd("INSERT INTO coureur(nom_coureur, prenom_coureur) VALUES('" + textFields.get(0).getText() + "', '" + textFields.get(1).getText() + "')");
                            break;
                        case "typeEpreuves":
                            actionBDD.requeteBdd("INSERT INTO epreuve(libelle_epreuve) VALUES('" + textFields.get(0).getText() + "')");
                            break;
                        case "reservations":
                            actionBDD.requeteBdd("INSERT INTO Reservation(idArticle, id_coureur, id_epreuve) VALUES('" + reservation.getIdByLibelleArticle(choiceBoxArticles.getValue().toString()) + "', '" + reservation.getIdByNomCoureur(choiceBoxCoureurs.getValue().toString()) + "', '" + reservation.getIdByLibelleEpreuve(choiceBoxTypeEpreuves.getValue().toString()) + "')");
                            break;
                        default:
                            break;
                    }
                }
            });
        }else{
            unBouton.setText("Modifier");
            unBouton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch (objet){
                        case "articles":
                            actionBDD.requeteBdd("UPDATE Articles SET libelle_article = '" + textFields.get(0).getText() + "', quantite = '" + Integer.parseInt(textFields.get(1).getText()) + "' WHERE idArticle = " + selectedElement);
                            if (choixUneCategorie.equals("Textile")){
                                actionBDD.requeteBdd("UPDATE Textile SET taille = '" + textFieldsCategorie.get(0).getText() + "', couleur = '" + Integer.parseInt(textFieldsCategorie.get(1).getText()) + "' WHERE idArticle = " + selectedElement);
                            }else if (choixUneCategorie.equals("Boisson")){
                                actionBDD.requeteBdd("UPDATE Boisson SET volume = '" + textFieldsCategorie.get(0).getText() + "' WHERE idArticle = " + selectedElement);
                            }else if (choixUneCategorie.equals("denree_seche")){
                                actionBDD.requeteBdd("UPDATE denree_seche SET poid = '" + textFieldsCategorie.get(0).getText() + "' WHERE idArticle = " + selectedElement);
                            }
                            break;
                        case "coureurs":
                            actionBDD.requeteBdd("UPDATE coureur SET nom_coureur = '" + textFields.get(0).getText() + "', prenom_coureur = '" + textFields.get(1).getText() + "' WHERE id_coureur = " + selectedElement);
                            break;
                        case "typeEpreuves":
                            actionBDD.requeteBdd("UPDATE epreuve SET libelle_epreuve = '" + textFields.get(0).getText() + "' WHERE id_epreuve = " + selectedElement);
                            break;
                        case "reservations":
                            actionBDD.requeteBdd("UPDATE Reservation SET idArticle = '" + reservation.getIdByLibelleArticle(choiceBoxArticles.getValue().toString()) + "', id_coureur = '" + reservation.getIdByNomCoureur(choiceBoxCoureurs.getValue().toString()) + "', id_epreuve = '" + reservation.getIdByLibelleEpreuve(choiceBoxTypeEpreuves.getValue().toString()) + "' WHERE id_reservation = " + selectedElement);
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        Label testLabel = new Label();
        uneChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                choixUneCategorie = (String) uneChoiceBox.getItems().get((Integer) number2);
                addGridArticlesCategories(uneGrid);
            }
        });

        uneGrid.add(unBouton, 200, 600);
        uneGrid.add(testLabel, 300, 600);

        if (choixUneCategorie.equals("Textile")){
            labelCategorie.add(new Label("Taille: "));
            labelCategorie.add(new Label("Couleur: "));
            textFieldsCategorie.add(new TextField());
            textFieldsCategorie.add(new TextField());


        }else if (choixUneCategorie.equals("Boisson")){
            labelCategorie.add(new Label("Volume: "));
            textFieldsCategorie.add(new TextField());

        }else if (choixUneCategorie.equals("denree_seche")){
            labelCategorie.add(new Label("poid: "));
            textFieldsCategorie.add(new TextField());

        }
        for (int i = 0; i < labelCategorie.size(); i++) {
            uneGrid.addRow(i, labelCategorie.get(i));
            uneGrid.addRow(i, textFieldsCategorie.get(i));
        }

        Button quitterButton = new Button();
        quitterButton.setText("Quitter");
        quitterButton.setOnAction((actionEvent -> closeStage(actionEvent)));

        uneGrid.add(quitterButton,300, 1000);
    }


}
