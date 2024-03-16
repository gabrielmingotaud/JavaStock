package org.example.javastock_javafx;

import java.io.IOException;
import java.util.ArrayList;

public class typeEpreuves {

    private int id_epreuve;
    private String libelle_epreuve;

    public static ArrayList<typeEpreuves> listeTypeEpreuves = new ArrayList<>();

    public typeEpreuves(int unIdTypeEpreuve, String unLibelleTypeEpreuve) {
        this.id_epreuve = unIdTypeEpreuve;
        this.libelle_epreuve = unLibelleTypeEpreuve;
    }

    public int getId_epreuve() {
        return id_epreuve;
    }

    public String getLibelle_epreuve(){
        return libelle_epreuve;
    }

    public static typeEpreuves getTypeEpreuveById(int unIdEpreuve, ArrayList<typeEpreuves> unArrayList){
        typeEpreuves varReturn = null;
        for (int i = 0; i < unArrayList.size(); i++) {
            if (unArrayList.get(i).getId_epreuve() == unIdEpreuve){
                varReturn = unArrayList.get(i);
                break;
            }
        }

        return varReturn;
    }

    public static void mainTypeEpreuves() throws IOException {
        listeTypeEpreuves = actionBDD.getAllEpreuves();
        ApplicationController.objet = "typeEpreuves";
        for (int i = 0; i < listeTypeEpreuves.size(); i++) {
            ApplicationController.applicationElement.add("Id: " + listeTypeEpreuves.get(i).getId_epreuve() + " Libelle: " + listeTypeEpreuves.get(i).getLibelle_epreuve());
            ApplicationController.applicationElementId.add(listeTypeEpreuves.get(i).getId_epreuve());
        }

    }


}
