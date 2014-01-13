package master.if26.vote.Constants;

/**
 * + Projet : IF26 - Application de vote
 * + Date : Automne 2014
 * + Lieu : Université Technologique de Troyes (10000)
 * + Auteur : Nicolas D'ALAYER DE COSTEMORE D'ARC & Alexandre ORTIZ
 * -----------------------------------------------------------------
 * + Type : Constants
 * + Name : WebServiceConstants.java
 * + Fichiers liés :
 * + Description : Fichier contenant toutes les contantes pour les requêtes
 * et réponses entre l'application et le serveur.
 */
public class WebServiceConstants {

    /**
     * Renseignement de l'adresse du serveur
     */
    public static final String ROOT = "http://10.0.2.2/appvote/";

    public static class LOGIN
    {
        public static final String URI = ROOT + "login.php";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String TOKEN = "token";
    }

    public static class CREATE
    {
        public static final String URI = ROOT + "createaccount.php";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String TOKEN = "token";
    }

    public static class VOTE
    {
        public static final String URI = ROOT + "vote.php";
        public static final String TOKEN = "token";
        public static final String VOTES = "votes";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESC = "description";
        public static final String DEADLINE = "deadline";
    }


    public static class CANDIDATS
    {
        public static final String URI = ROOT + "candidats.php";
        public static final String TOKEN = "token";
        public static final String VOTE = "vote";

        public static final String CANDIDATS = "candidats";
        public static final String ID = "id";
        public static final String CANDIDAT = "nom";
    }

    public static class CANDIDAT
    {
        public static final String URI = ROOT + "votes.php";
        public static final String TOKEN = "token";
        public static final String VOTE = "vote";
        public static final String CANDIDAT = "candidat";
        public static final String IDVOTE = "idvote";
        public static final String IDCANDIDAT = "idcandidat";
    }



    public static class VOTESCLOSE
    {
        public static final String URI = ROOT + "votesclose.php";
        public static final String TOKEN = "token";
        public static final String VOTES = "votes";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESC = "description";
        public static final String DEADLINE = "deadline";
    }


    public static class RESULTATS
    {
        public static final String URI = ROOT + "resultats.php";
        public static final String TOKEN = "token";
        public static final String VOTE = "vote";
        public static final String RESULTAT = "resultats";

        public static final String CANDIDAT = "idcandidat";
        public static final String NBVOIES = "nbvoies";
    }

    public static class WINNERS
    {
        public static final String URI = ROOT + "winner.php";
        public static final String TOKEN = "token";
        public static final String VOTE = "vote";
        public static final String RESULTAT = "resultats";

        public static final String CANDIDAT = "idcandidat";
        public static final String WINNER = "winner";
    }

}


