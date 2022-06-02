package kib.lab8.client.gui.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.RegistrationModel;
import kib.lab8.client.utils.UserException;


public class RegistrationController extends AbstractController {

    @FXML
    private Button registerButton;

    @FXML
    private Button authorizationButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField secondPasswordField;
    private final RegistrationModel model;


    public RegistrationController(ConnectionHandlerClient connectionHandler) {
        model = new RegistrationModel(connectionHandler);
    }

    @FXML
    private void register() {
        registerButton.setDisable(true);
        if (passwordField.getText().equals(secondPasswordField.getText())) {
            try {
                model.sendSignUpRequest(loginField.getText(), passwordField.getText());
                changeScene(GUIConfig.AUTHORIZATION_PATH, controllerClass -> new AuthorizationController(model.getConnectionHandler()), getCurrentLocale());
            } catch (UserException e) {
                registerButton.setDisable(false);
                e.showAlert();
                loginField.clear();
                passwordField.clear();
                secondPasswordField.clear();
            }
        }
    }

    public void authorization() {
        try {
            changeScene(GUIConfig.AUTHORIZATION_PATH, controllerClass -> new AuthorizationController(model.getConnectionHandler()), getCurrentLocale());
        } catch (UserException e) {
            e.showAlert();
        }
    }
}
