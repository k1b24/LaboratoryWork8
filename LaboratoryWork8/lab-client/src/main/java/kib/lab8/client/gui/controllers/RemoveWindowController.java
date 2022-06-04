package kib.lab8.client.gui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.utils.ExecutableCommand;

public class RemoveWindowController extends ChildWindowController {

    @FXML
    private TextField idField;

    @FXML
    private void initialize() {

    }

    @FXML
    private void remove(Event event) {
        ExecutableCommand command = ExecutableCommand.REMOVE_COMMAND;
        getParentModel().executeCommand(command, Integer.valueOf(idField.getText()));
        getParentModel().updateCollection();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
