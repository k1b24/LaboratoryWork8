package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kib.lab8.client.gui.abstractions.AbstractController;

public class RegistrationController extends AbstractController {

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
