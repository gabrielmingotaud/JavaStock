package org.example.javastock_javafx;

import java.io.IOException;
import java.util.ArrayList;

public class coureurs {

    private int id_coureur;
    private String nom_coureur, prenom_coureur;

    public static ArrayList<coureurs> listeCoureurs = new ArrayList<>();

    public coureurs(int unIdCoureur, String unNomCoureur, String unPrenomCoureur) {
        this.id_coureur = unIdCoureur;
        this.nom_coureur = unNomCoureur;
        this.prenom_coureur = unPrenomCoureur;
    }

    public int getId_coureur() {
        return id_coureur;
    }

    public String getNom_coureur() {
        return nom_coureur;
    }

    public String getPrenom_coureur() {
        return prenom_coureur;
    }

    public static coureurs getCoureurById(int unIdCoureur, ArrayList<coureurs> unArrayList){
        coureurs varReturn = null;
        for (int i = 0; i < unArrayList.size(); i++) {
            if (unArrayList.get(i).getId_coureur() == unIdCoureur){
                varReturn = unArrayList.get(i);
                break;
            }
        }

        return varReturn;
    }

    public static void mainCoureurs() throws IOException {
        listeCoureurs = actionBDD.getAllCoureurs();
        ApplicationController.objet = "coureurs";
        for (int i = 0; i < listeCoureurs.size(); i++) {
            ApplicationController.applicationElement.add("Id: " + listeCoureurs.get(i).getId_coureur() + " Nom: " + listeCoureurs.get(i).getNom_coureur() + " Prénom: " + listeCoureurs.get(i).getPrenom_coureur());
            ApplicationController.applicationElementId.add(listeCoureurs.get(i).getId_coureur());
        }
    }


}
