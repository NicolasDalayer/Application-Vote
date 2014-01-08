///////////////// //....Vote....// ////////////////

==== INFORMATIONS GENERALE ==== -> Description g�n�rale :Application d�velopp�e dans le cadre du projet Master[IF26] -> Auteur de l'application : Nicolas D'ALAYER & Alexandre ORTIZ -> Date de cr�ation : Automne 2013

==== INFORMATIONS ====

-> Dans le cadre d'un projet en premi�re ann�e de Master � l'Universit� Technologique de Troyes, nous devions cr�er une application mobile android dans le cadre de l'UV "IF26". Cette application doit r�pondre � des besoin qui ont �t�s d�finis lors de l'attribution du projet. Cette application est capable de : _ G�rer l'inscription et la connexion d'un utilisateur, _ Afficher dans un premier onglet, les votes en cours, _ Afficher dans un second onglet, les votes termin�s, _ Afficher dans un troisi�me onglet, les informations concernant l'application, _ Lors d'un clique sur l'item d'un vote en cours, afficher la liste des candidats y participant, _ Lors d'un clique sur l'item d'un candidat participant au vote, envoyer un vote pour ce candidat, _ L'utilisateur ne peut voter qu'une seule fois, _ Lors d'un clique sur l'item d'un vote termin�s, afficher le nom du gagnant en haut de la page, _ Dans cette m�me page, affiche le nombre de voies pour chacun des candidats ayant obtenu au minimum une voie.

Cette application utilise les technologies suivantes au niveau client : _ D�velopp�e � partir du logiciel Android Studio, _ Test�e � partir de l'�mulateur du logiciel Android Studio, _ Utilisation des Fragments, Services, Model, Activity, Contants, XML _ Utilisation des requ�tes HttpGET, _ Utilisation du JSON, _ Conversion du JSON en ObjetJava.

Cette application utilise les technologies suivantes au niveau serveur : _ Utilisation du logiciel WampServer, _ Utilisation d'une base de donn�e MySQL, via PHPMyAdmin, _ Utilisation de fichier PHP pour les requ�tes sur le serveur, _ Utilisation de la technologie PDO pour les requ�tes sur le serveur, _ V�rification du token utilisateur avant chaque action.

Cette application peut avoir les am�liorations suivantes : _ Interface graphique avanc�e, _ Plus de s�curit�