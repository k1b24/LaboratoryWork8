package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;


public class ConnectionController {

    @FXML
    private Button connectButton;

    @FXML
    private void connect() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/authorization.fxml"));
        loader.setControllerFactory(controllerClass -> new AuthorizationController());
        Parent parent = loader.load();

        Stage currentStage = (Stage) connectButton.getScene().getWindow();
        InputStream iconStream = getClass().getResourceAsStream("/icons/img.png");
        Image image = new Image(iconStream);
        currentStage.getIcons().add(image);
        Scene scene = new Scene(parent);
        currentStage.setTitle("BE HUMAN");
        currentStage.setScene(scene);
//        System.out.println("dada");
//        button.setText("abhsba");
    }

}
