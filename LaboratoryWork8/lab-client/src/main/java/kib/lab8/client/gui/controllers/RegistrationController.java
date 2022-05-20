package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private Button registerButton;

    @FXML
    private Button authorizationButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField secondPasswordField;

    public RegistrationController() {

    }

    @FXML
    private void register() {
        System.out.println("dada");
        registerButton.setText("abhsba");
    }
}
