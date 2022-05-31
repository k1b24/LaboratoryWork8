package kib.lab8.client.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.UserException;
import kib.lab8.common.entities.enums.Mood;
import kib.lab8.common.entities.enums.WeaponType;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.common.entities.HumanBeing;


public class HumanProfileController extends ChildWindowController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label nameValue;

    @FXML
    private Label creationDateValue;

    @FXML
    private Label xValue;

    @FXML
    private Label yValue;

    @FXML
    private Label realHeroValue;

    @FXML
    private Label popularityValue;

    @FXML
    private Label impactSpeedValue;

    @FXML
    private Label weaponValue;

    @FXML
    private Label moodValue;

    @FXML
    private Label carValue;

    @FXML
    private Label carCoolnessValue;

    @FXML
    private Label carSpeedValue;

    @FXML
    private Label authorValue;

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
    private final HumanBeing currentHuman;
    private final String userLogin;

    public HumanProfileController(HumanBeing currentHuman, String userLogin) {
        this.currentHuman = currentHuman;
        this.userLogin = userLogin;
    }

    @FXML
    private void initialize() {
        //TODO енэйблить и дисэйблить кнопки в зависимости от того, кто открыл
        bindProperties();
        weaponChoiceBox.setItems(weaponTypes);
        moodChoiceBox.setItems(moods);
        updateVBox.setVisible(false);
        valuesVBox.setVisible(true);
        if (currentHuman.getAuthor().equals(userLogin)) {
            updateButton.setDisable(false);
            removeButton.setDisable(false);
        } else {
            updateButton.setDisable(true);
            removeButton.setDisable(true);
        }
        setTextFieldValues();
        imageView.setImage(GUIConfig.getRandomHumanImage());
    }

    private void setTextFieldValues() {
        nameValue.setText(currentHuman.getName());
        creationDateValue.setText(String.valueOf(currentHuman.getCreationDate()));
        xValue.setText(String.valueOf(currentHuman.getCoordinates().getX()));
        yValue.setText(String.valueOf(currentHuman.getCoordinates().getY()));
        realHeroValue.setText(String.valueOf(currentHuman.isRealHero()));
        popularityValue.setText(String.valueOf(currentHuman.isHasToothpick()));
        impactSpeedValue.setText(String.valueOf(currentHuman.getImpactSpeed()));
        weaponValue.setText(String.valueOf(currentHuman.getWeaponType()));
        moodValue.setText(String.valueOf(currentHuman.getMood()));
        carValue.setText(currentHuman.getCar() == null ? "-" : "+");
        if (currentHuman.getCar() != null) {
            carCoolnessValue.setText(String.valueOf(currentHuman.getCar().getCarCoolness()));
            carSpeedValue.setText(String.valueOf(currentHuman.getCar().getCarSpeed()));
        } else {
            carCoolnessValue.setVisible(false);
            carSpeedValue.setVisible(false);
        }
        authorValue.setText(currentHuman.getAuthor());
    }

    @FXML
    private void update() {
        updateVBox.setVisible(true);
        valuesVBox.setVisible(false);

    }

    @FXML
    private void remove(Event event) {
        ExecutableCommand command = ExecutableCommand.REMOVE_COMMAND;
        try {
            getParentModel().executeCommand(command, (int) currentHuman.getId().longValue());
            getParentModel().updateCollection();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (UserException e) {
            e.showAlert();
        }
    }

    @FXML
    private void close(Event event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindProperties() {
        carCheckBox.setSelected(true);
        carCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (carSpeedField.isVisible()) {
                carSpeedField.setVisible(false);
                carCoolnessCheckBox.setVisible(false);
            } else {
                carSpeedField.setVisible(true);
                carCoolnessCheckBox.setVisible(true);
            }
        });
    }
}
