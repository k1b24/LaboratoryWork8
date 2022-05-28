package kib.lab8.client.gui.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import kib.lab8.client.gui.abstractions.AbstractController;

public class AddCommandController extends AbstractController {

    @FXML
    private TextField name;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    @FXML
    private TextField realHero;

    @FXML
    private TextField popularity;

    @FXML
    private TextField impactSpeed;

    @FXML
    private TextField weapon;

    @FXML
    private TextField mood;

    @FXML
    private TextField carCoolness;

    @FXML
    private TextField carSpeed;

    @FXML
    private Button applyButton;

    @FXML
    private Button clearFieldsButton;

    @FXML
    private Text carCoolnessText;

    @FXML
    private Text carSpeedText;

    @FXML
    private CheckBox carCheckBox;

    @FXML
    private CheckBox addIfMinCheckBox;

    @FXML
    private void initialize() {
        bindProperties();
    }

    @FXML
    private void clear() {

    }

    @FXML
    private void apply() {
        //TODO гетим текст из филдов и закрываемся
    }


    protected void bindProperties() {
        BooleanBinding booleanBind = name.textProperty().isEmpty()
                .or(x.textProperty().isEmpty())
                .or(y.textProperty().isEmpty())
                .or(realHero.textProperty().isEmpty())
                .or(popularity.textProperty().isEmpty());

        applyButton.disableProperty().bind(booleanBind);

        carCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (carSpeed.isVisible()) {
                carSpeed.setVisible(false);
                carSpeedText.setVisible(false);
                carCoolness.setVisible(false);
                carCoolnessText.setVisible(false);
            } else {
                carSpeed.setVisible(true);
                carSpeedText.setVisible(true);
                carCoolness.setVisible(true);
                carCoolnessText.setVisible(true);
            }
        });
    }
}
