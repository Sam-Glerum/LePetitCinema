package com.example.icadi.lepetitcinema.Persistency.Database;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by icadi on 28-3-18.
 */

public class DatabaseConnector{
    SQLiteDatabase sqLiteDatabase;
    Connection connection;
    String databaseName;
    String url;
    String username;
    String password;
    int port;

    public DatabaseConnector(String databaseName, String url, String username, String password, int port) {
        this.databaseName = databaseName;
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet selectStatement(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean insertStatement(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatement(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStatement(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
