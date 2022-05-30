package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import kib.lab8.client.gui.abstractions.ChildWindowController;


public class HumanProfileController extends ChildWindowController {

    @FXML
    private Text nameValue;

    @FXML
    private Text creationDateValue;

    @FXML
    private Text xValue;

    @FXML
    private Text yValue;

    @FXML
    private Text realHeroValue;

    @FXML
    private Text popularityValue;

    @FXML
    private Text impactSpeedValue;

    @FXML
    private Text weaponValue;

    @FXML
    private Text moodValue;

    @FXML
    private Text carValue;

    @FXML
    private Text carCoolnessValue;

    @FXML
    private Text carSpeedValue;

    @FXML
    private Text authorValue;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        //TODO енэйблить и дисэйблить кнопки в зависимости от того, кто открыл
    }

    @FXML
    private void update() {

    }

    @FXML
    private void remove() {

    }

    @FXML
    private void close() {

    }
}
