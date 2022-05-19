package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegistrationController {

    @FXML
    private Button registerButton;

    public RegistrationController() {

    }

    @FXML
    private void register() {
        System.out.println("dada");
        registerButton.setText("abhsba");
    }
}
