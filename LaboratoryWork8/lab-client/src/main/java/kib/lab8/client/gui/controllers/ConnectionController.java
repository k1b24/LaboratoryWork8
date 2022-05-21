package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.utils.Model;
import kib.lab8.client.utils.UserException;

import java.io.IOException;
import java.io.InputStream;

import static kib.lab8.client.gui.GUIConfig.*;


public class ConnectionController {

    @FXML
    private Button connectButton;
    private final Model model = new Model();

    @FXML
    private TextField portField;

    @FXML
    private TextField hostField;

    @FXML
    private Text text;

    @FXML
    private void connect() throws IOException {
        try {
            model.initializeConnectionHandler(hostField.getText(), portField.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource(AUTHORIZATION_PATH));
            loader.setControllerFactory(controllerClass -> new AuthorizationController(model));
            Localization localization = new Localization();
            loader.setResources(localization.getResourceBundle());
            Parent parent = loader.load();

            Stage currentStage = (Stage) connectButton.getScene().getWindow();
            InputStream iconStream = getClass().getResourceAsStream(CORNER_IMAGE);
            Image image = new Image(iconStream);
            currentStage.getIcons().add(image);
            Scene scene = new Scene(parent);
            currentStage.setTitle(TITLE);
            currentStage.setScene(scene);
        } catch (UserException e) {
            e.showAlert();
        }
    }


}
