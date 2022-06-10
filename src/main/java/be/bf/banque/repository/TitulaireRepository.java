package be.bf.banque.repository;

import be.bf.banque.Main;
import be.bf.banque.models.Titulaire;

import java.sql.*;
import java.util.ArrayList;

public class TitulaireRepository extends Repository<Titulaire> {


    public TitulaireRepository(String dbPath) {
        super(dbPath,"Titulaire");
    }


    @Override
    public Titulaire fromResultSet(ResultSet resultSet) {
        try {
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            return  new Titulaire(nom,prenom);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
