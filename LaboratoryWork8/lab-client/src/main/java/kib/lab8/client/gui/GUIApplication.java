package kib.lab8.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kib.lab8.client.gui.controllers.MenuController;

import java.io.IOException;
import java.io.InputStream;

import static kib.lab8.client.gui.GUIConfig.*;


public class GUIApplication extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader mainWindowLoader = new FXMLLoader();
        //mainWindowLoader.setLocation(getClass().getResource(CONNECTION_PATH));
        mainWindowLoader.setLocation(getClass().getResource(MAIN_WINDOW_PATH));
        Localization localization = new Localization();
        mainWindowLoader.setResources(localization.getResourceBundle());
        Parent root = mainWindowLoader.load();

        InputStream iconStream = getClass().getResourceAsStream(CORNER_IMAGE);
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("Be human");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
