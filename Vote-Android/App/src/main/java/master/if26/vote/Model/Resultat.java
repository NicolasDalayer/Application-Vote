package master.if26.vote.Model;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Model
 * + Name : Resultat.java
 * + Fichiers liés :
 * + Description : Model qui sera utilisé pour traiter les informations
 * envoyées au serveur et reçues par le serveur. Utilisé pour quand
 * il faut récupérer les résultats du vote sélectioné.
 */
public class Resultat {


    public int id;

    public String name;
    public String candidat;
    public String nbvoies;

    public Vote vote;
    public User user;

}
