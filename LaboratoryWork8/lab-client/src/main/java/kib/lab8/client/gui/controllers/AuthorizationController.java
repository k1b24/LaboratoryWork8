package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    @FXML
    private Button authorizationButton;

    public AuthorizationController() {

    }

    @FXML
    private void buttonClicked() {
        System.out.println("dada");
        authorizationButton.setText("abhsba");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
