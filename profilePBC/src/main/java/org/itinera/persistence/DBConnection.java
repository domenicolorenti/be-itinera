package org.itinera.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static DBConnection instance;
    private final String username = "postgres";
    private final String pass = "admin";
    private final String url = "jdbc:postgresql://localhost:5432/profilePBC";

    private Connection dbConnection = null;

    private DBConnection() {
        try {
            dbConnection = DriverManager.getConnection(url, username, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if(instance == null)
            instance = new DBConnection();

        return instance;
    }

    public Connection getConnection() {
        return dbConnection;
    }
}