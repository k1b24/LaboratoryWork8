package kib.lab8.client.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.gui.abstractions.AbstractController;

import java.io.IOException;

import static kib.lab8.client.gui.GUIConfig.*;

public class MenuController extends AbstractController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane menuPane;

    @FXML
    private Pane buttonsPane;

    @FXML
    private Pane visualPane;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private Button addButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button executeScriptButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button FAQButton;

    @FXML
    private Button tableButton;

    @FXML
    private Button visualizeButton;

    @FXML
    private Text userInfo;

    @FXML
    private Text nickname;

    @FXML
    public void initialize() {

    }

    @FXML
    private void signUpButtonClicked() {
        signUpButton.setText("qwe");
        nickname.setText("rondize");
        System.out.println("dada");
    }

    @FXML
    private void signInButtonClicked() {
        signInButton.setText("qwe");
        System.out.println("dada");
    }

    @FXML
    private void addButtonClicked() {
        //TODO реализовать открытие окна с добавлением
        System.out.println("dada");
    }

    @FXML
    private void clearButtonClicked() {
        //TODO скорее всего придется делать очистку по-другому, но пока так
        System.out.println("dada");
    }

    @FXML
    private void executeScriptButtonClicked() {
        //TODO пробрасывать и выполнять или ваще нахуй убрать
        System.out.println("dada");
    }

    @FXML
    private void updateButtonClicked() {
        //TODO реализовать открытие окна с полями для ввода
        System.out.println("dada");
    }

    @FXML
    private void historyButtonClicked() {
        //TODO мб сделать отдельное поле куда по дефолту будут все команды падать, либо по нажатию открывать в мейне ее
        System.out.println("dada");
    }

    @FXML
    private void removeButtonClicked() {
        //TODO пока сделать открытие окна по кнопке, в дальнейшем придумать как удалять их прям из таблицы или из координат
        System.out.println("dada");
    }

    @FXML
    private void infoButtonClicked() {
        //TODO можно убрать в FAQ, или опять же просто отображать по нажатию, а лучше и то, и другое
        System.out.println("dada");
    }

    @FXML
    private void exitButtonClicked() {
        System.out.println("dada");
    }

    @FXML
    private void settingsButtonClicked() {
        System.out.println("dada");
        if (menuPane.isVisible()) {
            buttonsPane.setVisible(true);
            menuPane.setVisible(false);
        } else {
            buttonsPane.setVisible(false);
            menuPane.setVisible(true);
        }
        loadUI(SETTINGS_PANE_PATH, menuPane);
    }

    @FXML
    private void FAQButtonClicked() {
        System.out.println("dada");
    }

    @FXML
    private void visualizeButtonClicked() {
        System.out.println("dada");
        visualizeButton.setDisable(true);
        tableButton.setDisable(false);
    }

    @FXML
    private void tableButtonClicked() {
        loadUI(TABLEVIEW_PATH, visualPane);
        visualizeButton.setDisable(false);
        tableButton.setDisable(true);
    }

    private void loadUI(String uiPath, Pane targetPane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
            //loader.setControllerFactory(controllerClass -> new TableViewController());

            Localization localization = new Localization();
            loader.setResources(localization.getResourceBundle());
            Parent parent = loader.load();
            targetPane.getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}