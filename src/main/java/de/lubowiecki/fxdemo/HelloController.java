package de.lubowiecki.fxdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField eingabe;

    @FXML
    protected void onHelloButtonClick() {
        String old = eingabe.getText();
        welcomeText.setText(LocalDateTime.now() + ":" + eingabe.getText());
    }
}