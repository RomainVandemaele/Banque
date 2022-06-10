package be.bf.banque.repository;

import be.bf.banque.Main;
import be.bf.banque.models.Titulaire;

import java.sql.*;
import java.util.ArrayList;

public class TitulaireRepository {

    private final String  DB_PATH;

    public TitulaireRepository(String dbPath) {
        this.DB_PATH = dbPath;
    }

    private Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public ArrayList<Titulaire> finAll() {
        ArrayList<Titulaire> titulaires = new ArrayList<>();
        Connection connection = this.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Titulaire");
            ResultSet resultSet = statement.getResultSet();
            if(!resultSet.next()) {
                return null;
            }else {
                resultSet.previous();
                while (resultSet.next()) { titulaires.add(fromResultSet(resultSet)); }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return titulaires;
    }

    private Titulaire fromResultSet(ResultSet resultSet) {
        try {
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            return  new Titulaire(nom,prenom,);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Titulaire findById(int id) throws SQLException {
        Connection connection = this.connect();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Titulaire WHERE id = ?");
        statement.setInt(1,id);
        ResultSet rs = statement.getResultSet();

        if(!rs.next()) {
            connection.close();
            return null;
        }
        Titulaire titulaire = fromResultSet(rs);
        connection.close();
        return titulaire;
    }

}
