package de.lubowiecki.fxdemo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String FILE = System.getProperty("user.home") + "/kunden.ser";

    @FXML // Macht die Variable sichtbar für FXML
    private TextField vorname;

    @FXML
    private TextField nachname;

    @FXML
    private TextField email;

    @FXML
    private TextArea ausgabe;

    @FXML
    private Label msg;

    //private StringBuilder kundenListe = new StringBuilder();
    private List<Kunde> kundenListe;

//    @FXML
//    private void save() {
//        kundenListe.append(vorname.getText())
//                .append(" ")
//                .append(nachname.getText())
//                .append(", ")
//                .append(email.getText())
//                .append("\n");
//
//        clearFields();
//
//        ausgabe.setText(kundenListe.toString());
//    }

    @FXML
    private void save() {
        Kunde kunde = new Kunde(vorname.getText(), nachname.getText(), email.getText());
        kundenListe.add(kunde);
        saveToFile();
        clearFields();
        updateAusgabe();
    }

    @FXML
    private void saveByEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            if(checkFrom()) {
                msg.setText("Es fehlen noch Daten!");
            }
            else {
                msg.setText("");
                save();
            }
        }
    }

    private boolean checkFrom() {
        if(vorname.getText().isEmpty() || nachname.getText().isEmpty() || email.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private void updateAusgabe() {
        StringBuilder sb = new StringBuilder();
        for(Kunde k : kundenListe) {
            sb.append(k);
        }
        ausgabe.setText(sb.toString());
    }

    private void clearFields() {
        vorname.clear();
        nachname.clear();
        email.clear();
    }

    private void saveToFile() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(kundenListe);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            kundenListe = (List<Kunde>) in.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            kundenListe = new ArrayList<>();
        }
    }

    // Wird automatisch beim aufruf der View ausgeführt
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readFromFile(); // Altdaten laden
        updateAusgabe(); // Ausgabe erzeugen
    }
}
