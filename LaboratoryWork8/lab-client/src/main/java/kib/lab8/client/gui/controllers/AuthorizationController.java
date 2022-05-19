package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    @FXML
    private Button authorizationButton;

    @FXML
    private Button registerButton;

    public AuthorizationController() {

    }

    @FXML
    private void authorize() {
        System.out.println("dada");
        authorizationButton.setText("abhsba");
    }

    @FXML
    private void register() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/registration.fxml"));
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
