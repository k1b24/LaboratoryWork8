package kib.lab8.client.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import kib.lab8.client.gui.localization.LanguagesEnum;

public class SettingsController {

    @FXML
    private ChoiceBox<LanguagesEnum> languagesChoiceBox;

    private final ObservableList<LanguagesEnum> languages = FXCollections.observableArrayList(LanguagesEnum.values());

    @FXML
    private void initialize() {
        languagesChoiceBox.setItems(languages);
    }
}
