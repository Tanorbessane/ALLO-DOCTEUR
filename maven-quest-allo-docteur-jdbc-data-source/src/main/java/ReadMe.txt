
-----	REPONSE AUX QUESTIONS	-----

1- Injection SQL : c'est une technique d'injection de code  permet de detourner une requete en modifiant les informations de celle-ci 
afin d'alterer les informations envoyees ou recues.


2- Diff�rence entre une jointure totale, INNER JOIN, LEFT JOIN et RIGHT JOIN:
	Etant toutes des methodes permettant d�associer plusieurs tables dans une m�me requ�te,
		- INNER JOIN : jointure interne pour retourner les enregistrements quand la condition est vrai dans les 2 tables;
		- LEFT JOIN : 	C'est une jointure qui prend toutes les lignes respectant le crit�re de jointure de la table de gauche m�me si la condition n�est pas v�rifi� dans l�autre table.
		- RIGHT JOIN : jointure externe pour retourner toutes les lignes de la table de droite m�me si la condition n�est pas v�rifi� dans l�autre table.
		- FULL JOIN : quant � elle s�lectionne toutes les lignes respectant le crit�re de jointure, on ajoute toutes les lignes de la table gauche et de la table droite qui ont �t� rejet�es car elles ne respectaient pas le crit�re de jointure.
	
3- Diff�rence entre instancier une connexion avec DriverManager.getConnection et DataSource.getConnection
4 - Que se passe t-il si on lance le test unitaire en limitant le nombre de connexion de DataSourceSingleton � 2 et en commentant le contenu de la methode ConnectionHelper.closeSqlResources ? Expliquez ?
