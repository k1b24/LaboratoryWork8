package kib.lab8.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.gui.localization.LanguagesEnum;
import kib.lab8.client.gui.localization.Localization;

import java.io.IOException;

import static kib.lab8.client.gui.GUIConfig.*;


public class GUIApplication extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader mainWindowLoader = new FXMLLoader();
        mainWindowLoader.setLocation(getClass().getResource(CONNECTION_PATH));
        Localization localization = new Localization(LanguagesEnum.ENGLISH);
        mainWindowLoader.setResources(localization.getResourceBundle());
        Parent root = mainWindowLoader.load();
        AbstractController controller = mainWindowLoader.getController();
        controller.setStage(primaryStage);
        controller.setResourceBundle(localization.getResourceBundle());
        controller.setCurrentLocale(LanguagesEnum.ENGLISH);
        primaryStage.getIcons().add(getCornerImage());

        primaryStage.setTitle("Be human");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
