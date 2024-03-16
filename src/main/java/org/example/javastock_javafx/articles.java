package org.example.javastock_javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class articles {

    private int idArticle, quantite;
    private String libelle_article, type_categorie, indicateur;

    public static ArrayList<articles> listeArticles = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> listeCategories = new ArrayList<>();



    public articles(int unIdArticle, String unLibelleArticle, int uneQuantite, String uneCategorie) {
        this.idArticle = unIdArticle;
        this.libelle_article = unLibelleArticle;
        this.quantite = uneQuantite;
        this.type_categorie = uneCategorie;
    }

    public int getIdArticle(){
        return idArticle;
    }

    public String getLibelle_article() {
        return libelle_article;
    }

    public String getType_categorie() {
        return type_categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getIndicateur() {
        return indicateur;
    }

    public static articles getArticleById(int unIdArticle, ArrayList<articles> unArrayList){
        articles varReturn = null;
        for (int i = 0; i < unArrayList.size(); i++) {
            if (unArrayList.get(i).getIdArticle() == unIdArticle){
                varReturn = unArrayList.get(i);
                break;
            }
        }

        return varReturn;
    }

    public static HashMap<String, String> getCategorieById(int unIdArticle){
        HashMap<String, String> varReturn = null;
        for (int i = 0; i < listeCategories.size(); i++) {
            if (Integer.parseInt(listeCategories.get(i).get("id")) == unIdArticle){
                varReturn = listeCategories.get(i);
            }
        }
        return varReturn;
    }

    public static String getHashmapData(HashMap<String, String> unHashmap){
        String varReturn = "";

        if (unHashmap != null){
            for (Map.Entry<String, String> unHashmapEntry : unHashmap.entrySet()) {
                if (unHashmapEntry.getKey() != "id"){
                    varReturn += unHashmapEntry.getKey() + ": " + unHashmapEntry.getValue() + " ";
                }
            }
        }


        return varReturn;
    };

    public static void mainArticles() throws IOException {

        listeArticles = actionBDD.getAllArticles();
        listeCategories.addAll(actionBDD.getAllTextile());
        listeCategories.addAll(actionBDD.getAllBoisson());
        listeCategories.addAll(actionBDD.getAllDenreeSeche());


        ApplicationController.objet = "articles";
       for (int i = 0; i < listeArticles.size(); i++) {
           ApplicationController.applicationElement.add("Id: " + listeArticles.get(i).getIdArticle() + " Catégorie: " + listeArticles.get(i).getType_categorie() + " Libelle article: " + listeArticles.get(i).getLibelle_article() + " Quantité: " + listeArticles.get(i).getQuantite() + " " + getHashmapData(getCategorieById(listeArticles.get(i).getIdArticle())));
           ApplicationController.applicationElementId.add(listeArticles.get(i).getIdArticle());
       }
   }


}
