package kib.lab8.client.gui.abstractions;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.utils.UserException;

import java.io.IOException;
import java.io.InputStream;


public abstract class AbstractController {

    private Stage currentStage;

    public void setStage(Stage stage) {
        currentStage = stage;
    }

    public Stage getStage() {
        return currentStage;
    }

    public <T extends AbstractController> T changeScene(String resourcePath, Callback<Class<?>, Object> controllerConstructorCallback) throws UserException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
        loader.setControllerFactory(controllerConstructorCallback);
        Localization localization = new Localization();
        loader.setResources(localization.getResourceBundle());
        try {
            Parent parent = loader.load();
            T controller = loader.getController();

            controller.setStage(getStage());
            InputStream iconStream = getClass().getResourceAsStream(GUIConfig.CORNER_IMAGE);
            Image image = new Image(iconStream);
            currentStage.getIcons().add(image);
            Scene scene = new Scene(parent);
            currentStage.setTitle(GUIConfig.TITLE);
            currentStage.setScene(scene);
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UserException("Произошла ошибка при открытии нового окна, повторите попытку");
        }
    }
}
