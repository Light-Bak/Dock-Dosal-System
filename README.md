# Dock-Dosal-System
 Projet service web ISGE-BF


 0 -- Au lancement du programme, l'utilisateur ADMIN est automatiquement insérer dans la BD
 
 Les EndPoints pour la resource "Utilisateur" sont :

 1 -- localhost:8088/utilisateurs/inscription (pour l'inscription)
      Exemple :         {
              	"nom": "SANOU",
              	"prenom": "Bakary",
              	"username": "111111",
              	"password": "11111",
              	"email": "1bakarysanou@gmail.com"
              }

 1 -- localhost:8088/utilisateurs/login (pour le login)

exemple : 
       {
       	"username" : "Light",
       	"password" : "$2a$10$KkB3bXU52TzfqvPHMzPb1u1b9QcUWeWhA4s4ZGn3bTXYaBVJ.6vI2"
       }

en reponse un token est généré avec lequel il poura s'authentifier.(Pour le reste de la sécurité, nous n'avons pas eu le temps de l'implementer). 

3 -- localhost:8088/utilisateurs/modifier/76 (pour modifier un user par sa reference)
exemple : 
       {
       	  "ref": 76,
       		"nom": "SANQSDOU",
       		"prenom": "BAKDDARY",
       		"email": "1bakarysanou@gmail.com",
       		"username": "Light",
       		"password": "188333333333333333388"
       }

  4 -- localhost:8088/utilisateurs/findAll (pour afficher tous les users)
  5 -- localhost:8088/utilisateurs/findOne/34 (pour afficher un user a partir de sa reference)
  6 --localhost:8088/utilisateurs/supprimer/93 (pour supprimer un user par sa reference)



  
