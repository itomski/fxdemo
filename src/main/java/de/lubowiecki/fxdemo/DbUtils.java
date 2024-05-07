package de.lubowiecki.fxdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private static final String HOME = System.getProperty("user.home"); // Ermittelt den Benutzerordner
    private static final String URL = "jdbc:sqlite:" + HOME + "/userverwaltung.db";

    private DbUtils() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
