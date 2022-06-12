package be.bf.banque.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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


    protected HashMap toHashMap(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        final int colCount = md.getColumnCount();
        HashMap row = new HashMap(colCount);
        System.out.println(colCount);
        for (int i =1; i<=colCount;++i) { row.put(md.getColumnName(i),rs.getObject(i)); }
        return row;
    }

    public abstract T fromResultSet(ResultSet resultSet);

    public ArrayList<T> findAll() {
        ArrayList<T> list = new ArrayList<>();
        Connection connection = this.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+ this.TABLE_NAME);
            //statement.setString(1,this.TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) { list.add(this.fromResultSet(resultSet)); }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public T findById(int id) {
        try {
            Connection connection = this.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + this.TABLE_NAME + " WHERE id = ?");

            statement.setInt(1,id);
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
