package kib.lab8.client.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.gui.localization.LanguagesEnum;
import kib.lab8.client.utils.UserException;

public class SettingsController extends ChildWindowController {

    @FXML
    private ChoiceBox<LanguagesEnum> languagesChoiceBox;

    private final ObservableList<LanguagesEnum> languages = FXCollections.observableArrayList(LanguagesEnum.values());

    @FXML
    private void initialize() {
        languagesChoiceBox.setItems(languages);
        languagesChoiceBox.setOnAction(event -> {
            LanguagesEnum chosenLanguage = languagesChoiceBox.getValue();
            try {
                getParentModel().getController().changeScene(GUIConfig.MAIN_WINDOW_PATH, controllerClass -> new MenuController(getParentModel().getConnectionHandler(), getParentModel().getUserLogin(), getParentModel().getUserPassword()), chosenLanguage);
            } catch (UserException e) {
                e.showAlert();
            }
        });
    }
}
