
-----	REPONSE AUX QUESTIONS	-----

1- Injection SQL : c'est une technique d'injection de code  permet de detourner une requete en modifiant les informations de celle-ci 
afin d'alterer les informations envoyees ou recues.


2- Différence entre une jointure totale, INNER JOIN, LEFT JOIN et RIGHT JOIN:
	Etant toutes des methodes permettant d’associer plusieurs tables dans une même requête,
		- INNER JOIN : jointure interne pour retourner les enregistrements quand la condition est vrai dans les 2 tables;
		- LEFT JOIN : 	C'est une jointure qui prend toutes les lignes respectant le critère de jointure de la table de gauche même si la condition n’est pas vérifié dans l’autre table.
		- RIGHT JOIN : jointure externe pour retourner toutes les lignes de la table de droite même si la condition n’est pas vérifié dans l’autre table.
		- FULL JOIN : quant à elle sélectionne toutes les lignes respectant le critère de jointure, on ajoute toutes les lignes de la table gauche et de la table droite qui ont été rejetées car elles ne respectaient pas le critère de jointure.
	
3- Différence entre instancier une connexion avec DriverManager.getConnection et DataSource.getConnection
4 - Que se passe t-il si on lance le test unitaire en limitant le nombre de connexion de DataSourceSingleton à 2 et en commentant le contenu de la methode ConnectionHelper.closeSqlResources ? Expliquez ?
