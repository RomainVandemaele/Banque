package be.bf.banque.repository;

import be.bf.banque.Main;
import be.bf.banque.models.Titulaire;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TitulaireRepository extends Repository<Titulaire> {


    public TitulaireRepository(String dbPath) {
        super(dbPath,"Titulaire");
    }


    @Override
    public Titulaire fromResultSet(ResultSet resultSet) {
        try {
            HashMap attributes = this.toHashMap(resultSet);
            return Titulaire.class.getConstructor(String.class,String.class).newInstance(attributes.get("nom"),attributes.get("prenom"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
