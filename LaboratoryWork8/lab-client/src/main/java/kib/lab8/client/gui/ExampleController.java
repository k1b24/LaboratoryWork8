package kib.lab8.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ExampleController {

    @FXML
    private Button button;

    @FXML
    private void buttonClicked() {
        System.out.println("dada");
        button.setText("abhsba");
    }

}
