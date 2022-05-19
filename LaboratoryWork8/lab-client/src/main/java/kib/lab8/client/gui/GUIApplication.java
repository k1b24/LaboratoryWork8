package kib.lab8.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import static kib.lab8.client.gui.GUIConfig.CONNECTION_PATH;
import static kib.lab8.client.gui.GUIConfig.CORNER_IMAGE;


public class GUIApplication extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader connectionLoader = new FXMLLoader();
        connectionLoader.setLocation(getClass().getResource(CONNECTION_PATH));
        Localization localization = new Localization();
        connectionLoader.setResources(localization.getResourceBundle());
        Parent root = connectionLoader.load();

        InputStream iconStream = getClass().getResourceAsStream(CORNER_IMAGE);
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        stage.setTitle("Be human");
        stage.setResizable(false);
        stage.setScene(new Scene(root));

        stage.show();
    }

}
