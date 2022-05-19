package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;

import java.io.InputStream;


public class ConnectionController {

    @FXML
    private Button connectButton;

    @FXML
    private void connect() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/authorization.fxml"));
        loader.setControllerFactory(controllerClass -> new AuthorizationController());
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
//        System.out.println("dada");
//        button.setText("abhsba");
    }

}
