package kib.lab8.client.gui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.utils.ChildUIType;
import kib.lab8.client.utils.UserException;
import kib.lab8.common.entities.HumanBeing;

public class UpdateByIdWindowController extends ChildWindowController {

    @FXML
    private Button updateButton;

    @FXML
    private TextField idField;

    @FXML
    private void initialize() {
        idField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    @FXML
    private void update(Event event) {
        int id = Integer.parseInt(idField.getText());
        try {
            HumanBeing human = getParentModel().getHumanById(id);
            getParentModel().getController().getCurrentVisualizerController().setChosenHuman(human);
            getParentModel().getController().loadUI(GUIConfig.PROFILE_WINDOW_PATH, null, ChildUIType.HUMAN_PROFILE_CHILD_WINDOW);
        } catch (UserException e) {
            e.showAlert();
        }
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
