package kib.lab8.client.utils;

import javafx.scene.control.Alert;

public class UserException extends Exception {

    Alert alert;

    public UserException(String message) {
        super(message);
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
    }

    public void showAlert() {
        alert.show();
    }
}
