package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConnectionController {

    @FXML
    private Button button;

    @FXML
    private void buttonClicked() {
        System.out.println("dada");
        button.setText("abhsba");
    }
}
