///////////////// //....Vote....// ////////////////

==== INFORMATIONS GENERALE ==== -> Description générale :Application développée dans le cadre du projet Master[IF26] -> Auteur de l'application : Nicolas D'ALAYER & Alexandre ORTIZ -> Date de création : Automne 2013

==== INFORMATIONS ====

-> Dans le cadre d'un projet en première année de Master à l'Université Technologique de Troyes, nous devions créer une application mobile android dans le cadre de l'UV "IF26". Cette application doit répondre à des besoin qui ont étés définis lors de l'attribution du projet. Cette application est capable de : _ Gérer l'inscription et la connexion d'un utilisateur, _ Afficher dans un premier onglet, les votes en cours, _ Afficher dans un second onglet, les votes terminés, _ Afficher dans un troisième onglet, les informations concernant l'application, _ Lors d'un clique sur l'item d'un vote en cours, afficher la liste des candidats y participant, _ Lors d'un clique sur l'item d'un candidat participant au vote, envoyer un vote pour ce candidat, _ L'utilisateur ne peut voter qu'une seule fois, _ Lors d'un clique sur l'item d'un vote terminés, afficher le nom du gagnant en haut de la page, _ Dans cette même page, affiche le nombre de voies pour chacun des candidats ayant obtenu au minimum une voie.

Cette application utilise les technologies suivantes au niveau client : _ Développée à partir du logiciel Android Studio, _ Testée à partir de l'émulateur du logiciel Android Studio, _ Utilisation des Fragments, Services, Model, Activity, Contants, XML _ Utilisation des requêtes HttpGET, _ Utilisation du JSON, _ Conversion du JSON en ObjetJava.

Cette application utilise les technologies suivantes au niveau serveur : _ Utilisation du logiciel WampServer, _ Utilisation d'une base de donnée MySQL, via PHPMyAdmin, _ Utilisation de fichier PHP pour les requêtes sur le serveur, _ Utilisation de la technologie PDO pour les requêtes sur le serveur, _ Vérification du token utilisateur avant chaque action.

Cette application peut avoir les améliorations suivantes : _ Interface graphique avancée, _ Plus de sécurité