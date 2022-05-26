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
import kib.lab8.client.utils.ConnectionModel;
import kib.lab8.client.utils.UserException;

import java.io.IOException;
import java.io.InputStream;


public class ConnectionController {

    @FXML
    private Button connectButton;
    @FXML
    private TextField portField;

    @FXML
    private TextField hostField;

    @FXML
    private Text text;
    private ConnectionModel model;

    public ConnectionController() {
        this.model = new ConnectionModel();
    }

    @FXML
    private void connect() throws IOException {
        try {
            model.initializeConnectionHandler(hostField.getText(), portField.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource(GUIConfig.AUTHORIZATION_PATH));
            loader.setControllerFactory(controllerClass -> new AuthorizationController(model.getConnectionHandler()));
            Localization localization = new Localization();
            loader.setResources(localization.getResourceBundle());
            Parent parent = loader.load();

            Stage currentStage = (Stage) connectButton.getScene().getWindow();
            InputStream iconStream = getClass().getResourceAsStream(GUIConfig.CORNER_IMAGE);
            Image image = new Image(iconStream);
            currentStage.getIcons().add(image);
            Scene scene = new Scene(parent);
            currentStage.setTitle(GUIConfig.TITLE);
            currentStage.setScene(scene);
        } catch (UserException e) {
            e.showAlert();
            hostField.clear();
            portField.clear();
        }
    }


}
