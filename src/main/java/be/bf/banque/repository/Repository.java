package be.bf.banque.repository;

import be.bf.banque.models.Compte;

import java.sql.*;
import java.util.ArrayList;

public abstract class Repository<T> {
    private final String  DB_PATH;
    private final String  TABLE_NAME;

    public Repository(String db_path, String tableName) {
        this.DB_PATH = db_path;
        this.TABLE_NAME = tableName;
    }

    public String getDB_PATH() { return this.DB_PATH;}

    protected Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
        } catch (SQLException e) {throw new RuntimeException(e);}
        return connection;
    }

    public abstract T fromResultSet(ResultSet resultSet);

    public ArrayList<T> findAll(int id) {
        ArrayList<T> list = new ArrayList<>();
        Connection connection = this.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ?");
            statement.setString(1,this.TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) { list.add(this.fromResultSet(resultSet)); }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public T findFromId(int id) {
        try {
            Connection connection = this.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ? WHERE id = ?");
            statement.setString(1,this.TABLE_NAME);
            statement.setInt(2,id);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
                connection.close();
                return null;
            }
            T instance = fromResultSet(rs);
            connection.close();
            return instance;
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



}
