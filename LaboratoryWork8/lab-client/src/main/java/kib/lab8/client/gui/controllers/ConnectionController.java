package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.utils.ConnectionModel;
import kib.lab8.client.utils.UserException;

import java.io.IOException;
import java.io.InputStream;


public class ConnectionController extends AbstractController {

    @FXML
    private Button connectButton;
    @FXML
    private TextField portField;

    @FXML
    private TextField hostField;

    @FXML
    private Text text;
    private final ConnectionModel model;

    public ConnectionController() {
        this.model = new ConnectionModel();
    }

    @FXML
    private void connect() {
        try {
            model.initializeConnectionHandler(hostField.getText(), portField.getText());
            changeScene(GUIConfig.AUTHORIZATION_PATH, controllerClass -> new AuthorizationController(model.getConnectionHandler()));
        } catch (UserException e) {
            e.showAlert();
            hostField.clear();
            portField.clear();
        }
    }
}
