package kib.lab8.client.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kib.lab8.common.entities.enums.Mood;
import kib.lab8.common.entities.enums.WeaponType;


public class HumanProfileController {

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
    private VBox updateVBox;

    @FXML
    private VBox valuesVBox;

    @FXML
    private TextField nameField;

    @FXML
    private Text creationDateUpdate;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private CheckBox realHeroCheckBox;

    @FXML
    private CheckBox popularityCheckBox;

    @FXML
    private TextField impactSpeedField;

    @FXML
    private ChoiceBox<WeaponType> weaponChoiceBox;

    @FXML
    private ChoiceBox<Mood> moodChoiceBox;

    @FXML
    private CheckBox carCheckBox;

    @FXML
    private CheckBox carCoolnessCheckBox;

    @FXML
    private TextField carSpeedField;

    @FXML
    private Text authorUpdate;

    private final ObservableList<WeaponType> weaponTypes = FXCollections.observableArrayList(WeaponType.values());
    private final ObservableList<Mood> moods = FXCollections.observableArrayList(Mood.values());

    @FXML
    private void initialize() {
        //TODO енэйблить и дисэйблить кнопки в зависимости от того, кто открыл
        weaponChoiceBox.setItems(weaponTypes);
        moodChoiceBox.setItems(moods);
        updateVBox.setVisible(false);
        valuesVBox.setVisible(true);
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
