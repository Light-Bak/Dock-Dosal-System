# Dock-Dosal-System
service web


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

en reponse un token est généré avec lequel il poura s'authentifier.

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

  Les EndPoints pour la resource "Correction" sont :

 1 -- localhost:8088/correction/ajouter (pour ajourter une correction une seule fois dans la journée)
Exemple :
      {
      	"valeur" : 65,
      	"userId" : 1
      }

2 -- localhost:8088/correction/modifier/57 (pour modifier une corection a partir de sa reference)
     exemple :
            {
       	"ref" :57,	
       	"valeur": 100
       	}
3 -- localhost:8088/correction/searchIntervalDate/2024-03-21,2024-03-20 (pour retrouver toutes les corrections dans  
    un intervalle de temps donnés)
4 -- localhost:8088/correction/supprimer/84 (pour supprimer une correctiona partir de sa reference)
5 -- localhost:8088/correction/findAll/1 (pour afficher les corrections de l'utilisateur a partir de son ID)
6 -- localhost:8088/correction/findOne/68 (pour afficher une seule correction a partir de sa reference)
  
