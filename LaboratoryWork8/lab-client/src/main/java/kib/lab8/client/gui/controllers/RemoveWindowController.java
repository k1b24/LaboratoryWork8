package kib.lab8.client.gui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.UserException;

public class RemoveWindowController extends ChildWindowController {

    @FXML
    private Button removeButton;

    @FXML
    private TextField idField;

    @FXML
    private void initialize() {

    }

    @FXML
    private void remove(Event event) {
        ExecutableCommand command = ExecutableCommand.REMOVE_COMMAND;
        getParentModel().executeCommand(command, Integer.valueOf(idField.getText()));
        try {
            getParentModel().updateCollection();
        } catch (UserException e) {
            if (e.isFatal()) {
                getParentModel().prepareForExit();
                e.showAlert();
                getParentModel().getController().closeApplication();
            } else {
                e.showAlert();
            }
        }
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
