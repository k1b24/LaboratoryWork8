package kib.lab8.client.gui.abstractions;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.localization.LanguagesEnum;
import kib.lab8.client.gui.localization.Localization;
import kib.lab8.client.utils.Exceptions.UserException;

import java.io.IOException;
import java.util.ResourceBundle;


public abstract class AbstractController {

    private Stage currentStage;

    private ResourceBundle resourceBundle;
    private LanguagesEnum currentLocale;

    public void setStage(Stage stage) {
        currentStage = stage;
    }

    public Stage getStage() {
        return currentStage;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }


    public <T extends AbstractController> T changeScene(String resourcePath, Callback<Class<?>, Object> controllerConstructorCallback, LanguagesEnum lang) throws UserException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
        loader.setControllerFactory(controllerConstructorCallback);
        Localization localization = new Localization(lang);
        loader.setResources(localization.getResourceBundle());
        try {
            Parent parent = loader.load();
            T controller = loader.getController();
            controller.setResourceBundle(loader.getResources());
            controller.setCurrentLocale(lang);
            controller.setStage(getStage());
            currentStage.getIcons().add(GUIConfig.getCornerImage());
            Scene scene = new Scene(parent);
            currentStage.setTitle(GUIConfig.TITLE);
            currentStage.setScene(scene);
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UserException(getResourceBundle().getString("new_window_opening_error"));
        }
    }

    public LanguagesEnum getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(LanguagesEnum currentLocale) {
        this.currentLocale = currentLocale;
    }
}
