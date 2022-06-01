package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.utils.AuthorizationModel;
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.UserException;

public class AuthorizationController extends AbstractController {

    @FXML
    private Button authorizationButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;
    private final AuthorizationModel model;

    @FXML
    private void initialize() {
        passwordField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                authorize();
            }
        });
    }

    public AuthorizationController(ConnectionHandlerClient connectionHandler) {
        model = new AuthorizationModel(connectionHandler);
    }

    @FXML
    private void authorize() {
        try {
            boolean success = model.authorize(loginField.getText(), passwordField.getText());
            if (success) {
                changeScene(GUIConfig.MAIN_WINDOW_PATH, controllerClass ->
                        new MenuController(model.getConnectionHandler(), model.getUserLogin(), model.getUserPassword()), getCurrentLocale());
            } else {
                System.out.println("net takogo uzera");
                //покажи текст об ошибке авторизации
            }
        } catch (UserException e) {
            loginField.clear();
            passwordField.clear();
            e.showAlert();
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
