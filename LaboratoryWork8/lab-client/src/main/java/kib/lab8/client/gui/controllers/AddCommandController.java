package kib.lab8.client.gui.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.utils.Models.AddCommandModel;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.Exceptions.UserException;
import kib.lab8.common.entities.HumanBeing;
import kib.lab8.common.entities.enums.Mood;
import kib.lab8.common.entities.enums.WeaponType;


public class AddCommandController extends ChildWindowController {

    @FXML
    private TextField name;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    @FXML
    private CheckBox realHero;

    @FXML
    private CheckBox popularity;

    @FXML
    private TextField impactSpeed;

    @FXML
    private ChoiceBox<WeaponType> weapon;

    @FXML
    private ChoiceBox<Mood> mood;

    @FXML
    private CheckBox carCoolness;

    @FXML
    private TextField carSpeed;

    @FXML
    private Button applyButton;

    @FXML
    private Button clearFieldsButton;

    @FXML
    private Label carCoolnessText;

    @FXML
    private Label carSpeedText;

    @FXML
    private CheckBox carCheckBox;

    @FXML
    private CheckBox addIfMinCheckBox;

    private final ObservableList<WeaponType> weaponTypes = FXCollections.observableArrayList(WeaponType.values());
    private final ObservableList<Mood> moods = FXCollections.observableArrayList(Mood.values());
    private final AddCommandModel model = new AddCommandModel(this);


    @FXML
    private void initialize() {
        bindProperties();
        weapon.setItems(weaponTypes);
        mood.setItems(moods);

    }

    @FXML
    private void clear() {
        name.setText("");
        x.setText("");
        y.setText("");
        realHero.setSelected(false);
        popularity.setSelected(false);
        impactSpeed.setText("");
        weapon.setValue(null);
        mood.setValue(null);
        carCheckBox.setSelected(true);
        carCoolness.setSelected(false);
        carSpeed.setText("");
    }

    @FXML
    private void apply(Event event) {
        try {
            HumanBeing createdHuman = model.createHuman();
            createdHuman.setAuthor(super.getParentModel().getUserLogin());
            ExecutableCommand command;
            if (addIfMinCheckBox.isSelected()) {
                command = ExecutableCommand.ADD_IF_MIN_COMMAND;
            } else {
                command = ExecutableCommand.ADD_COMMAND;
            }
            super.getParentModel().executeCommand(command, createdHuman);
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            super.getParentModel().updateCollection();
        } catch (UserException e) {
            e.showAlert();
        }
    }


    protected void bindProperties() {
        BooleanBinding booleanBind = name.textProperty().isEmpty()
                .or(x.textProperty().isEmpty())
                .or(y.textProperty().isEmpty());

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

        impactSpeed.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"-0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
        x.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"-0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
        y.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"-0123456789.".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
        carSpeed.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"-0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    public TextField getName() {
        return name;
    }

    public TextField getX() {
        return x;
    }

    public TextField getY() {
        return y;
    }

    public CheckBox getRealHero() {
        return realHero;
    }

    public CheckBox getPopularity() {
        return popularity;
    }

    public TextField getImpactSpeed() {
        return impactSpeed;
    }

    public ChoiceBox<WeaponType> getWeapon() {
        return weapon;
    }

    public ChoiceBox<Mood> getMood() {
        return mood;
    }

    public CheckBox getCarCoolness() {
        return carCoolness;
    }

    public TextField getCarSpeed() {
        return carSpeed;
    }

    public CheckBox getCarCheckBox() {
        return carCheckBox;
    }
}
