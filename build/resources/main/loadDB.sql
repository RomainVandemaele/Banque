DROP TABLE IF EXISTS Compte;
DROP TABLE IF EXISTS Banque;
DROP TABLE IF EXISTS Titulaire;
CREATE TABLE Titulaire (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        ssin VARCHAR(50) NOT NULL UNIQUE,
        nom VARCHAR(50) NOT NULL,
        prenom VARCHAR(50) NOT NULL
);

CREATE TABLE Banque (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nom VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Compte (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        numero VARCHAR(50) NOT NULL UNIQUE,
                        solde REAL NOT NULL DEFAULT 0,
                        ligneCredit REAL NULL,
                        dateDernierRetrait DATE NULL,
                        desc VARCHAR(50) NOT NULL,
                        titulaire_id INTEGER REFERENCES Titulaire(id),
                        banque_id INTEGER REFERENCES Banque(id)
);

INSERT INTO Banque (nom) VALUES ('PicsouBanque');
INSERT INTO Titulaire(ssin, nom, prenom) VALUES ('19910719-443', 'Ovyn', 'Flavian');
INSERT INTO Compte(numero, ligneCredit, dateDernierRetrait, desc, titulaire_id, banque_id) VALUES ('BE12 1234 1234 1234', 50, NULL, 'COURANT', 1, 1),('BE12 1234 1234 1235', null, 'now', 'EPARGNE', 1,1);