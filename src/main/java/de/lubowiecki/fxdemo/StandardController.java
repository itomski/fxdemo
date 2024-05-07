package de.lubowiecki.fxdemo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StandardController implements Initializable {

    private List<String> namen = new ArrayList<>();

    private PersonRepository repo;

    @FXML // Annotation macht die Variable/Methode für die View sichtbar/nutzbar
    private TextField vorname;

    @FXML
    private TextField nachname;

    @FXML
    private TextArea namenListe;

    @FXML
    protected void save() {
        /*
        String str = name.getText(); // Inhalt vom Textfeld wird gelesen
        name.clear(); // Textfeld leeren
        namen.add(str);
        */

        Person person = new Person(vorname.getText(), nachname.getText());
        vorname.clear();
        nachname.clear();


        try {
            repo.insert(person); // Speichern in der DB
            namenListe.setText(printList());
        }
        catch (SQLException e) {
            System.out.println("Fehler"); // TODO: in ein GUI-Elemet ausgeben
        }


        //String alterInhalt = namenListe.getText();
        //alterInhalt += str + "\n";
        //namenListe.setText(alterInhalt);
    }

    private String printList() {
        // TODO: Daten aus der DB abfragen
        StringBuilder output = new StringBuilder();

        try {
            for (Person person : repo.findAll()) {
                output.append(person.getVorname())
                        .append(" ")
                        .append(person.getNachname())
                        .append("\n");
            }
        }
        catch(SQLException ex) {
            System.out.println("Problem bei Abfrage");
        }
        return output.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Wird nach der Instanzierung des StandardControllers verwendet

        try {
            repo = new PersonRepository();
            namenListe.setText(printList());
        }
        catch (SQLException e) {
            System.out.println("Problem mit dem Repo");
        }
    }
}