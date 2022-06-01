package kib.lab8.client.utils;

import javafx.scene.control.Alert;

public class UserException extends Exception {

    private final boolean fatal;
    public UserException(String message) {
        super(message);
        fatal = false;
    }

    public UserException(String message, boolean fatal) {
        super(message);
        this.fatal = fatal;
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(getMessage());
        alert.showAndWait();
    }

    public boolean isFatal() {
        return fatal;
    }
}
