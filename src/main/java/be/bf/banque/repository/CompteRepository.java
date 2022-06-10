package be.bf.banque.repository;

import be.bf.banque.models.Compte;
import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.CompteEpargne;
import be.bf.banque.models.Titulaire;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CompteRepository extends Repository<Compte> {


    public CompteRepository(String dbPath) {
        super(dbPath,"Compte");
    }

    @Override
    public Compte fromResultSet(ResultSet resultSet) {
        try {
            Compte compte = null;
            String numero = resultSet.getString("numero");
            int solde = resultSet.getInt("solde");
            Titulaire titulaire = new TitulaireRepository(this.getDB_PATH()).findById(resultSet.getInt("titulaire_id"));
            String type = resultSet.getString("desc");

            if (type.equals("COURANT")) {
                int ligneDeCredit = resultSet.getInt("ligneCredit");
                compte = new CompteCourant(numero, titulaire, ligneDeCredit, solde);
            }else if (type.equals("EPARGNE")) {
                LocalDate dateRetrait = resultSet.getDate("dateDernierRetrait").toLocalDate();
                compte = new CompteEpargne(numero, titulaire, solde);
            } else {
                throw new RuntimeException("The desc column for id " + resultSet.getInt("numero") + " is set incorrectly");
            }
            return compte;
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
