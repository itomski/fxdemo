package de.lubowiecki.fxdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static final String TABLE = "personen";

    public PersonRepository() throws SQLException {
        createTable();
    }

    //create
    public boolean insert(Person person) throws SQLException {

        final String SQL = "INSERT INTO " + TABLE + " (vorname, nachname) VALUES(?, ?)";

        try(Connection con = DbUtils.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, person.getVorname()); // 1 = Position des Fragezeichens
            stmt.setString(2, person.getNachname()); // 1 = Position des Fragezeichens
            stmt.execute();

            return stmt.getUpdateCount() > 0;
        }
    }

    //read
    public List<Person> findAll() throws SQLException {

        final String SQL = "SELECT * FROM " + TABLE;

        try(Connection con = DbUtils.getConnection();
            Statement stmt = con.createStatement()) {

            ResultSet results = stmt.executeQuery(SQL);
            List<Person> personen = new ArrayList<>();

            while(results.next()) {
                Person p = new Person();
                p.setId(results.getInt("id"));
                p.setVorname(results.getString("vorname"));
                p.setNachname(results.getString("nachname"));
                personen.add(p);
            }

            return personen;
        }
    }

    public Person findById(int id) {

        final String SQL = "SELECT * FROM " + TABLE + " WHERE id = ?";
        throw new UnsupportedOperationException("Noch nicht eingebaut!");
    }

    //update
    public boolean update(Person person) {

        final String SQL = "UPDATE " + TABLE + " SET vorname = ?, nachname = ? WHERE id = ?";
        throw new UnsupportedOperationException("Noch nicht eingebaut!");
    }

    // delete
    public boolean delete(Person person) {

        final String SQL = "DELETE FROM " + TABLE + " WHERE id = ?";
        throw new UnsupportedOperationException("Noch nicht eingebaut!");
    }

    public void createTable() throws SQLException {

        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
                            "id INTEGER, " +
                            "vorname TEXT NOT NULL, " +
                            "nachname TEXT NOT NULL, " +
                            "PRIMARY KEY (id AUTOINCREMENT)" +
                            ")";

        try(Connection con = DbUtils.getConnection(); Statement stmt = con.createStatement()) {
            stmt.execute(SQL);
        }
    }
}
