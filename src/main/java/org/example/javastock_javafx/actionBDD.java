package org.example.javastock_javafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class actionBDD {

    public static boolean requeteBdd(String uneRequetesql){
        boolean varReturn;
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            int resultatRequeteAjoutBdd = statement.executeUpdate(uneRequetesql);
            varReturn = true;

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
            varReturn = false;
        }

        return varReturn;
    }

    public static int insertBddReturning(String uneRequetesql){

        ResultSet unResultatinsertReturning = null;
        int returning = -1;

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            unResultatinsertReturning = statement.executeQuery(uneRequetesql);

            while (unResultatinsertReturning.next()){

                returning = unResultatinsertReturning.getInt(1);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return returning;
    }

//    public static ResultSet selectBddNew(String uneRequetesql){
//
//        ResultSet unResultatSelect = null;
//
//        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
//            unResultatSelect = statement.executeQuery(uneRequetesql);
//
//        }catch (SQLException e){
//            System.err.println("Erreur: " + e);
//        }
//
//        return unResultatSelect;
//    }

//    public static void selectBdd(String uneRequetesql, String objet){
//
//        ResultSet unResultatRequeteSelectBdd = null;
//
//        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
//            unResultatRequeteSelectBdd = statement.executeQuery(uneRequetesql);
//
//            while (unResultatRequeteSelectBdd.next()){
//
//                switch (objet){
//                    case "articles":
//                        int idArticle = unResultatRequeteSelectBdd.getInt("idArticle");
//                        String libelleArticle = unResultatRequeteSelectBdd.getString("libelle_article");
//                        int quantite = unResultatRequeteSelectBdd.getInt("quantite");
//                        String typeCategorie = unResultatRequeteSelectBdd.getString("type_categorie");
//
//                        articles unArticle = new articles(idArticle, libelleArticle, quantite, typeCategorie);
//
//                        articles.listeArticles.add(unArticle);
//                        break;
//                    case "textile":
//                        int idArticleTextile = unResultatRequeteSelectBdd.getInt("idArticle");
//                        String taille = unResultatRequeteSelectBdd.getString("taille");
//                        String couleur = unResultatRequeteSelectBdd.getString("couleur");
//
//
//                        HashMap<String, String> unTextile = new HashMap<String, String>();
//                        unTextile.put("id", String.valueOf(idArticleTextile));
//                        unTextile.put("taille", taille);
//                        unTextile.put("couleur", couleur);
//
//                        articles.listeCategories.add(unTextile);
//                        break;
//                    case "boisson":
//                        int idArticleBoisson = unResultatRequeteSelectBdd.getInt("idArticle");
//                        String volume = unResultatRequeteSelectBdd.getString("volume");
//
//                        HashMap<String, String> uneBoisson = new HashMap<String, String>();
//                        uneBoisson.put("id", String.valueOf(idArticleBoisson));
//                        uneBoisson.put("volume", volume);
//
//                        articles.listeCategories.add(uneBoisson);
//                        break;
//                    case "denree_seche":
//                        int idArticleDenreeSeche = unResultatRequeteSelectBdd.getInt("idArticle");
//                        String poid = unResultatRequeteSelectBdd.getString("poid");
//
//                        HashMap<String, String> uneDenreeSeche = new HashMap<String, String>();
//                        uneDenreeSeche.put("id", String.valueOf(idArticleDenreeSeche));
//                        uneDenreeSeche.put("poid", poid);
//
//                        articles.listeCategories.add(uneDenreeSeche);
//                        break;
//                    case "coureurs":
//
//                        int idCoureur = unResultatRequeteSelectBdd.getInt("id_coureur");
//                        String nomCoureur = unResultatRequeteSelectBdd.getString("nom_coureur");
//                        String prenomCoureur = unResultatRequeteSelectBdd.getString("prenom_coureur");
//                        coureurs unCoureur = new coureurs(idCoureur, nomCoureur, prenomCoureur);
//
//                        coureurs.listeCoureurs.add(unCoureur);
//                        break;
//                    case "typeEpreuves":
//                        int idTypeEpreuve = unResultatRequeteSelectBdd.getInt("id_epreuve");
//                        String libelleTypeEpreuve = unResultatRequeteSelectBdd.getString("libelle_epreuve");
//                        typeEpreuves unTypeEpreuve = new typeEpreuves(idTypeEpreuve, libelleTypeEpreuve);
//                        typeEpreuves.listeTypeEpreuves.add(unTypeEpreuve);
//                    default:
//                        break;
//                }
//            }
//
//        }catch (SQLException e){
//            System.err.println("Erreur: " + e);
//        }
//
//    }

    // Réservations
    public static ArrayList<reservation> getAllReservations(){

        ArrayList<reservation> uneListeReservation = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet reservation = statement.executeQuery("SELECT * FROM Reservation");

            while (reservation.next()){
                int unIdArticle = reservation.getInt("idArticle");
                int unIdCoureur = reservation.getInt("id_coureur");
                int unIdEpreuve = reservation.getInt("id_epreuve");
                int unIdReservation = reservation.getInt("id_reservation");

                reservation uneReservation = new reservation(unIdArticle, unIdCoureur, unIdEpreuve, unIdReservation);

                uneListeReservation.add(uneReservation);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListeReservation;
    }

    // Articles
    public static ArrayList<articles> getAllArticles(){

        ArrayList<articles> uneListeArticles = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet articles = statement.executeQuery("SELECT * FROM Articles");

            while (articles.next()){
                int unIdArticle = articles.getInt("idArticle");
                String unLibelleCategorie = articles.getString("libelle_article");
                int uneQuantite = articles.getInt("quantite");
                String unTypeCategorie = articles.getString("type_categorie");

                articles unArticle = new articles(unIdArticle, unLibelleCategorie, uneQuantite, unTypeCategorie);

                uneListeArticles.add(unArticle);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListeArticles;
    }

    public static ArrayList<HashMap<String, String>> getAllTextile(){

        ArrayList<HashMap<String, String>> uneListeTextiles = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet textiles = statement.executeQuery("SELECT * FROM Textile");

            while (textiles.next()){
                int unIdArticle = textiles.getInt("idArticle");
                String uneTaille = textiles.getString("taille");
                String uneCouleur = textiles.getString("couleur");

                HashMap<String, String> unTextile = new HashMap<String, String>();
                unTextile.put("id", String.valueOf(unIdArticle));
                unTextile.put("taille", uneTaille);
                unTextile.put("couleur", uneCouleur);

                uneListeTextiles.add(unTextile);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListeTextiles;
    }

    public static ArrayList<HashMap<String, String>> getAllBoisson(){

        ArrayList<HashMap<String, String>> uneListeBoissons = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet boissons = statement.executeQuery("SELECT * FROM Boisson");

            while (boissons.next()){
                int idArticleBoisson = boissons.getInt("idArticle");
                String volume = boissons.getString("volume");

                HashMap<String, String> uneBoisson = new HashMap<String, String>();
                uneBoisson.put("id", String.valueOf(idArticleBoisson));
                uneBoisson.put("volume", volume);

                uneListeBoissons.add(uneBoisson);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListeBoissons;
    }

    public static ArrayList<HashMap<String, String>> getAllDenreeSeche(){

        ArrayList<HashMap<String, String>> uneListedDenreeSeche = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet denreeSeche = statement.executeQuery("SELECT * FROM denree_seche");

            while (denreeSeche.next()){
                int idArticleDenreeSeche = denreeSeche.getInt("idArticle");
                String poid = denreeSeche.getString("poid");

                HashMap<String, String> uneDenreeSeche = new HashMap<String, String>();
                uneDenreeSeche.put("id", String.valueOf(idArticleDenreeSeche));
                uneDenreeSeche.put("poid", poid);

                uneListedDenreeSeche.add(uneDenreeSeche);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListedDenreeSeche;
    }

    // Coureurs
    public static ArrayList<coureurs> getAllCoureurs(){

        ArrayList<coureurs> uneListeCoureurs = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet coureurs = statement.executeQuery("SELECT * FROM coureur");

            while (coureurs.next()){
                int idCoureur = coureurs.getInt("id_coureur");
                String nomCoureur = coureurs.getString("nom_coureur");
                String prenomCoureur = coureurs.getString("prenom_coureur");
                coureurs unCoureur = new coureurs(idCoureur, nomCoureur, prenomCoureur);

                uneListeCoureurs.add(unCoureur);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListeCoureurs;
    }

    // Épreuves
    public static ArrayList<typeEpreuves> getAllEpreuves(){

        ArrayList<typeEpreuves> uneListeEpreuves = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/javastock", "postgres", "root"); Statement statement = connection.createStatement()){
            ResultSet epreuves = statement.executeQuery("SELECT * FROM epreuve");

            while (epreuves.next()){
                int idTypeEpreuve = epreuves.getInt("id_epreuve");
                String libelleTypeEpreuve = epreuves.getString("libelle_epreuve");
                typeEpreuves unTypeEpreuve = new typeEpreuves(idTypeEpreuve, libelleTypeEpreuve);
                uneListeEpreuves.add(unTypeEpreuve);
            }

        }catch (SQLException e){
            System.err.println("Erreur: " + e);
        }

        return uneListeEpreuves;
    }


}
