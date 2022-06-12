package be.bf.banque.repository;

import be.bf.banque.models.Compte;
import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.CompteEpargne;
import be.bf.banque.models.Titulaire;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class CompteRepository extends Repository<Compte> {


    public CompteRepository(String dbPath) {
        super(dbPath,"Compte");
    }

    @Override
    public Compte fromResultSet(ResultSet resultSet) {
        try {
            HashMap attributes = this.toHashMap(resultSet);
            Titulaire titulaire = new TitulaireRepository(this.getDB_PATH()).findById((int) attributes.get("titulaire_id"));
            if (attributes.get("desc").equals("COURANT")) {
                Constructor<CompteCourant> constructor = CompteCourant.class.getConstructor(String.class,Titulaire.class,Double.class,Double.class);
                return constructor.newInstance(attributes.get("numero"),titulaire,attributes.get("ligneCredit"),attributes.get("solde"));

            }else if (attributes.get("desc").equals("EPARGNE")) {
                Constructor<CompteEpargne> constructor = CompteEpargne.class.getConstructor(String.class,Titulaire.class,Double.class);
                return constructor.newInstance(attributes.get("numero"),titulaire,attributes.get("solde"));
            } else {
                throw new RuntimeException("The desc column for id " + resultSet.getInt("numero") + " is set incorrectly");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
