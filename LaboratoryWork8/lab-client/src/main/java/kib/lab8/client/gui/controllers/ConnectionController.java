package kib.lab8.client.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.gui.localization.LanguagesEnum;
import kib.lab8.client.utils.Models.ConnectionModel;
import kib.lab8.client.utils.Exceptions.UserException;


public class ConnectionController extends AbstractController {

    @FXML
    private Button connectButton;

    @FXML
    private TextField portField;

    @FXML
    private TextField hostField;

    @FXML
    private ChoiceBox<LanguagesEnum> languagesChoiceBox;

    @FXML
    private Text text;
    private final ConnectionModel model;
    private final ObservableList<LanguagesEnum> languages = FXCollections.observableArrayList(LanguagesEnum.values());

    @FXML
    private void initialize() {
        languagesChoiceBox.setItems(languages);
        languagesChoiceBox.setOnAction(event -> {
            LanguagesEnum chosenLanguage = languagesChoiceBox.getValue();
            try {
                changeScene(GUIConfig.CONNECTION_PATH, controllerClass -> new ConnectionController(), chosenLanguage);
            } catch (UserException e) {
                e.showAlert();
            }
        });
        portField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                connect();
            }
        });
    }

    public ConnectionController() {
        this.model = new ConnectionModel(this);
    }

    @FXML
    private void connect() {
        try {
            model.initializeConnectionHandler(hostField.getText(), portField.getText());
            changeScene(GUIConfig.AUTHORIZATION_PATH, controllerClass -> new AuthorizationController(model.getConnectionHandler()), getCurrentLocale());
        } catch (UserException e) {
            e.showAlert();
            hostField.clear();
            portField.clear();
        }
    }
}
