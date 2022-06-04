package kib.lab8.client.gui.controllers;


import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.Models.RegistrationModel;
import kib.lab8.client.utils.Exceptions.UserException;


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


    @FXML
    private void initialize() {
        bindProperties();
    }

    public RegistrationController(ConnectionHandlerClient connectionHandler) {
        model = new RegistrationModel(connectionHandler);
    }

    @FXML
    private void register() {
        registerButton.disableProperty().unbind();
        registerButton.setDisable(true);
        if (passwordField.getText().equals(secondPasswordField.getText())) {
            try {
                model.sendSignUpRequest(loginField.getText(), passwordField.getText());
                changeScene(GUIConfig.AUTHORIZATION_PATH, controllerClass -> new AuthorizationController(model.getConnectionHandler()), getCurrentLocale());
            } catch (UserException e) {
                bindProperties();
                if (e.isFatal()) {
                    model.prepareForExit();
                    e.showAlert();
                    Platform.exit();
                }
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

    private void bindProperties() {
        BooleanBinding booleanBind = loginField.textProperty().isEmpty()
                .or(passwordField.textProperty().isEmpty())
                .or(secondPasswordField.textProperty().isEmpty());
        registerButton.disableProperty().bind(booleanBind);
    }
}
