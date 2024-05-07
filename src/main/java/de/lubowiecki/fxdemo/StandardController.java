package de.lubowiecki.fxdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class StandardController {

    private List<String> namen = new ArrayList<>();

    @FXML // Annotation macht die Variable/Methode für die View sichtbar/nutzbar
    private TextField name;

    @FXML
    private TextArea namenListe;

    @FXML
    protected void save() {
        String str = name.getText(); // Inhalt vom Textfeld wird gelesen
        name.clear(); // Textfeld leeren

        namen.add(str);

        //String alterInhalt = namenListe.getText();
        //alterInhalt += str + "\n";
        //namenListe.setText(alterInhalt);
        namenListe.setText(printList());
    }

    private String printList() {
        StringBuilder output = new StringBuilder();
        for(String name : namen) {
            output.append(name).append("\n");
        }
        return output.toString();
    }
}