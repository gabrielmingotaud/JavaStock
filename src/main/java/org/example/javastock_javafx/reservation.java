package org.example.javastock_javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.javastock_javafx.articles.getArticleById;
import static org.example.javastock_javafx.coureurs.getCoureurById;
import static org.example.javastock_javafx.typeEpreuves.getTypeEpreuveById;

public class reservation {
    static ArrayList<reservation> listeReservation = new ArrayList<>();

    static ArrayList<articles> listeArticle = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> listeCategories = new ArrayList<>();

    static ArrayList<coureurs> listeCoureurs = new ArrayList<>();
    static ArrayList<typeEpreuves> listeTypeEpreuves = new ArrayList<>();

    int idArticle, idCoureur, idEpreuve, idReservation;

    public reservation(int unIdArticle, int unIdCoureur, int unIdEpreuve, int unIdReservation) {
        this.idArticle = unIdArticle;
        this.idCoureur = unIdCoureur;
        this.idEpreuve = unIdEpreuve;
        this.idReservation = unIdReservation;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public int getIdCoureur() {
        return idCoureur;
    }

    public int getIdEpreuve() {
        return idEpreuve;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public static reservation getReservationById(int unIdArticle, ArrayList<reservation> unArrayList){
        reservation varReturn = null;
        for (int i = 0; i < unArrayList.size(); i++) {
            if (unArrayList.get(i).getIdReservation() == unIdArticle){
                varReturn = unArrayList.get(i);
                break;
            }
        }

        return varReturn;
    }

    public static int getIdByLibelleArticle(String unLibelleArticle){
        int varReturn = -1;
        for (int i = 0; i < listeArticle.size(); i++) {
            if(listeArticle.get(i).getLibelle_article() == unLibelleArticle){
                varReturn = listeArticle.get(i).getIdArticle();
            }
        }
        return varReturn;
    }

    public static int getIdByNomCoureur(String unNomCoureur){
        int varReturn = -1;
        for (int i = 0; i < listeCoureurs.size(); i++) {
            if((listeCoureurs.get(i).getNom_coureur() + " " + listeCoureurs.get(i).getPrenom_coureur()).equals(unNomCoureur)){
                varReturn = listeCoureurs.get(i).getId_coureur();
            }
        }
        return varReturn;
    }

    public static int getIdByLibelleEpreuve(String unlibelleEpreuve){
        int varReturn = -1;
        for (int i = 0; i < listeArticle.size(); i++) {
            if(listeTypeEpreuves.get(i).getLibelle_epreuve() == unlibelleEpreuve){
                varReturn = listeTypeEpreuves.get(i).getId_epreuve();
            }
        }
        return varReturn;
    }

    public static void mainReservation() throws IOException {

        listeReservation = actionBDD.getAllReservations();
        listeArticle = actionBDD.getAllArticles();

        listeCategories.addAll(actionBDD.getAllTextile());
        listeCategories.addAll(actionBDD.getAllBoisson());
        listeCategories.addAll(actionBDD.getAllDenreeSeche());

        listeCoureurs = actionBDD.getAllCoureurs();
        listeTypeEpreuves = actionBDD.getAllEpreuves();

        ApplicationController.objet = "reservations";

        if (listeReservation.size() > 0){
            for (int i = 0; i < listeReservation.size(); i++) {
                ApplicationController.applicationElement.add("Id: " + listeReservation.get(i).getIdReservation() + " Coureur: " + getCoureurById(listeReservation.get(i).getIdCoureur(), listeCoureurs).getNom_coureur() + " " + getCoureurById(listeReservation.get(i).getIdCoureur(), listeCoureurs).getPrenom_coureur() + " Épreuve: " + getTypeEpreuveById(listeReservation.get(i).getIdEpreuve(), listeTypeEpreuves).getLibelle_epreuve() + " Article: " + getArticleById(listeReservation.get(i).getIdArticle(), listeArticle).getLibelle_article());
                ApplicationController.applicationElementId.add(listeReservation.get(i).getIdReservation());
            }
        }
    }

}
