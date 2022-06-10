package be.bf.banque.repository;

import be.bf.banque.models.Compte;
import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.CompteEpargne;
import be.bf.banque.models.Titulaire;

import java.sql.*;
import java.util.ArrayList;

public class CompteRepository {

    private final String  DB_PATH;

    private CompteRepository(String dbPath) {
        this.DB_PATH = dbPath;
    }

    private Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
        } catch (SQLException e) {throw new RuntimeException(e);}
        return connection;
    }

    private Compte fromResultSet(ResultSet resultSet) throws SQLException {
        String numero = resultSet.getString("numero");
        int solde = resultSet.getInt("solde");
        Titulaire titulaire = new TitulaireRepository(DB_PATH).findById(resultSet.getInt("titulaire_id"));
        String type = resultSet.getString("desc");
        if(type == "COURANT") {
            int ligneDeCredit = resultSet.getInt("ligneCredit");
            return new CompteCourant(numero,titulaire,ligneDeCredit,solde);
        }if(type.equals("EPARGNE")) {
            return new CompteEpargne(numero,titulaire,solde);
        }else {
            throw new RuntimeException("The desc column for id "+resultSet.getInt("numero") + " is set incorrectly");
        }
    }

    public ArrayList<Compte> findAll() {
        ArrayList<Compte> comptes = new ArrayList<>();
        Connection connection = this.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Compte");
            ResultSet resultSet = statement.getResultSet();
            if(!resultSet.next()) {
                return null;
            }else {
                resultSet.previous();
                while (resultSet.next()) { comptes.add(fromResultSet(resultSet)); }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return comptes;
    }

    public Compte findById(int id)  {
        try {
            Connection connection = this.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Compte WHERE id = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.getResultSet();

            if(!rs.next()) {
                connection.close();
                return null;
            }
            Compte compte = fromResultSet(rs);
            connection.close();
            return compte;
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
