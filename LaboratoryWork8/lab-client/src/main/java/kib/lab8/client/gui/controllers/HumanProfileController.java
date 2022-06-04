package kib.lab8.client.gui.controllers;

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
import kib.lab8.client.utils.HumanProfileModel;
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
    private final String userLogin;
    private boolean updateOpened = false;
    private final HumanProfileModel model;

    public HumanProfileController(HumanBeing currentHuman, String userLogin) {
        this.userLogin = userLogin;
        model = new HumanProfileModel(this, currentHuman);
    }

    @FXML
    private void initialize() {
        bindProperties();
        weaponChoiceBox.setItems(weaponTypes);
        moodChoiceBox.setItems(moods);
        updateVBox.setVisible(false);
        valuesVBox.setVisible(true);
        if (model.getCurrentHuman().getAuthor().equals(userLogin)) {
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
        nameValue.setText(model.getCurrentHuman().getName());
        creationDateValue.setText(String.valueOf(model.getCurrentHuman().getCreationDate()));
        xValue.setText(String.valueOf(model.getCurrentHuman().getCoordinates().getX()));
        yValue.setText(String.valueOf(model.getCurrentHuman().getCoordinates().getY()));
        realHeroValue.setText(String.valueOf(model.getCurrentHuman().isRealHero()));
        popularityValue.setText(String.valueOf(model.getCurrentHuman().isHasToothpick()));
        impactSpeedValue.setText(String.valueOf(model.getCurrentHuman().getImpactSpeed()));
        weaponValue.setText(String.valueOf(model.getCurrentHuman().getWeaponType()));
        moodValue.setText(String.valueOf(model.getCurrentHuman().getMood()));
        carValue.setText(model.getCurrentHuman().getCar() == null ? "-" : "+");
        if (model.getCurrentHuman().getCar() != null) {
            carCoolnessValue.setText(String.valueOf(model.getCurrentHuman().getCar().getCarCoolness()));
            carSpeedValue.setText(String.valueOf(model.getCurrentHuman().getCar().getCarSpeed()));
        } else {
            carCoolnessValue.setVisible(false);
            carSpeedValue.setVisible(false);
        }
        authorValue.setText(model.getCurrentHuman().getAuthor());
    }

    @FXML
    private void update(Event event) {
        if (!updateOpened) {
            updateVBox.setVisible(true);
            valuesVBox.setVisible(false);
            creationDateUpdate.setText(String.valueOf(model.getCurrentHuman().getCreationDate()));
            authorUpdate.setText(model.getCurrentHuman().getAuthor());
            updateOpened = true;
        } else {
            try {
                model.updateHuman();
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
                getParentModel().updateCollection();
            } catch (UserException e) {
                e.showAlert();
            }
        }
    }

    @FXML
    private void remove(Event event) {
        ExecutableCommand command = ExecutableCommand.REMOVE_COMMAND;
        getParentModel().executeCommand(command, (int) model.getCurrentHuman().getId().longValue());
        getParentModel().updateCollection();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
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

    public ImageView getImageView() {
        return imageView;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getxField() {
        return xField;
    }

    public TextField getyField() {
        return yField;
    }

    public CheckBox getRealHeroCheckBox() {
        return realHeroCheckBox;
    }

    public CheckBox getPopularityCheckBox() {
        return popularityCheckBox;
    }

    public TextField getImpactSpeedField() {
        return impactSpeedField;
    }

    public ChoiceBox<WeaponType> getWeaponChoiceBox() {
        return weaponChoiceBox;
    }

    public ChoiceBox<Mood> getMoodChoiceBox() {
        return moodChoiceBox;
    }

    public CheckBox getCarCheckBox() {
        return carCheckBox;
    }

    public CheckBox getCarCoolnessCheckBox() {
        return carCoolnessCheckBox;
    }

    public TextField getCarSpeedField() {
        return carSpeedField;
    }
}
