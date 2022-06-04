package kib.lab8.client.gui.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.gui.localization.LanguagesEnum;
import kib.lab8.client.utils.AuthorizationModel;
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.UserException;

public class AuthorizationController extends AbstractController {

    @FXML
    private Button authorizationButton;
    @FXML
    private TextField loginField;

    @FXML
    private ChoiceBox<LanguagesEnum> languagesChoiceBox;

    @FXML
    private PasswordField passwordField;
    private final AuthorizationModel model;
    private final ObservableList<LanguagesEnum> languages = FXCollections.observableArrayList(LanguagesEnum.values());

    @FXML
    private void initialize() {
        passwordField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                authorize();
            }
        });

        languagesChoiceBox.setItems(languages);
        languagesChoiceBox.setOnAction(event -> {
            LanguagesEnum chosenLanguage = languagesChoiceBox.getValue();
            try {
                changeScene(GUIConfig.AUTHORIZATION_PATH, controllerClass -> new AuthorizationController(model.getConnectionHandler()), chosenLanguage);
            } catch (UserException e) {
                e.showAlert();
            }
        });
    }

    public AuthorizationController(ConnectionHandlerClient connectionHandler) {
        model = new AuthorizationModel(connectionHandler);
    }

    @FXML
    private void authorize() {
        authorizationButton.setDisable(true);
        try {
            boolean success = model.authorize(loginField.getText(), passwordField.getText());
            if (success) {
                changeScene(GUIConfig.MAIN_WINDOW_PATH, controllerClass ->
                        new MenuController(model.getConnectionHandler(), model.getUserLogin(), model.getUserPassword()), getCurrentLocale());
            } else {
                System.out.println("net takogo uzera");
                //TODO
                authorizationButton.setDisable(false);
                //покажи текст об ошибке авторизации
            }
        } catch (UserException e) {
            if (e.isFatal()) {
                model.prepareForExit();
                e.showAlert();
                Platform.exit();
            }
        }
    }

    @FXML
    private void register() {
        try {
            changeScene(GUIConfig.REGISTRATION_PATH, controllerClass -> new RegistrationController(model.getConnectionHandler()), getCurrentLocale());
        } catch (UserException e) {
            e.showAlert();
        }
    }
}
