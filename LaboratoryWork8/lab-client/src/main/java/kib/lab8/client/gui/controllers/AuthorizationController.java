package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.utils.Model;
import kib.lab8.client.utils.UserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    @FXML
    private Button authorizationButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;
    private final Model model;

    public AuthorizationController(Model model) {
        this.model = model;
    }

    @FXML
    private void authorize() {
        try {
            boolean success = model.authorize(loginField.getText(), passwordField.getText());
            if (success) {
                //переходи в MAIN WINDOW
            } else {
                //покажи текст об ошибке авторизации
            }
        } catch (UserException e) {
            e.showAlert();
        }
    }

    @FXML
    private void register() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(GUIConfig.REGISTRATION_PATH));
        loader.setControllerFactory(controllerClass -> new RegistrationController());
        Localization localization = new Localization();
        loader.setResources(localization.getResourceBundle());
        Parent parent = loader.load();

        Stage currentStage = (Stage) registerButton.getScene().getWindow();
        InputStream iconStream = getClass().getResourceAsStream(GUIConfig.CORNER_IMAGE);
        Image image = new Image(iconStream);
        currentStage.getIcons().add(image);
        Scene scene = new Scene(parent);
        currentStage.setTitle(GUIConfig.TITLE);
        currentStage.setScene(scene);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
