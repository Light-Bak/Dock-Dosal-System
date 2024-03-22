INSERT INTO utilisateurs (id, email, nom, password, prenom, ref, role, username)
SELECT 1, 'admin@dockdorsal.com', 'ADMINISTRATEUR', '$2a$10$KkB3bXU52TzfqvPHMzPb1u1b9QcUWeWhA4s4ZGn3bTXYaBVJ.6vI2', 'ADMINISTRATEUR', 200, 'ADMIN', 'Light'
WHERE NOT EXISTS (
    SELECT 1 FROM utilisateurs WHERE id = 1
);


--Mot de passe = 12345