package com.example.icadi.lepetitcinema.Persistency.Database;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by icadi on 28-3-18.
 */

public class DatabaseConnector {
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
        return null;
    }

    public boolean insertStatement(String query) {
        return false;
    }

    public boolean updateStatement(String query) {
        return false;
    }

    public boolean deleteStatement(String query) {
        return false;
    }
}
