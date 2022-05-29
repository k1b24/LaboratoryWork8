package kib.lab8.client.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.MenuModel;
import kib.lab8.client.utils.UserException;
import kib.lab8.common.abstractions.DataVisualizerController;
import kib.lab8.common.entities.HumanBeing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    private TextArea terminal;

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
    private Button settingsButton;

    @FXML
    private Button FAQButton;

    @FXML
    private Button tableButton;

    @FXML
    private Button visualizeButton;

    @FXML
    private Text nickname;
    private final MenuModel model;
    private DataVisualizerController currentVisualizerController;

    @FXML
    private void initialize() {
        nickname.setText(model.getUserLogin());
    }

    public MenuController(ConnectionHandlerClient connectionHandler, String username, String password) {
        model = new MenuModel(connectionHandler, username, password, this);
    }

    @FXML
    private void addButtonClicked() {
        //TODO реализовать открытие окна с добавлением
        System.out.println("dada");
    }

    @FXML
    private void clearButtonClicked() {
        ExecutableCommand clearCommand = ExecutableCommand.CLEAR_COMMAND;
        try {
            terminal.appendText(model.executeCommand(clearCommand) + "\n");
        } catch (UserException e) {
            e.showAlert();
        }
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
        ExecutableCommand command = ExecutableCommand.HISTORY_COMMAND;
        try {
            String s = model.executeCommand(command);
            terminal.appendText(s + "\n");
        } catch (UserException e) {
            e.showAlert();
        }
    }

    @FXML
    private void removeButtonClicked() {
        //TODO пока сделать открытие окна по кнопке, в дальнейшем придумать как удалять их прям из таблицы или из координат
        System.out.println("dada");
    }

    @FXML
    private void infoButtonClicked() {
        ExecutableCommand command = ExecutableCommand.INFO_COMMAND;
        try {
            String s = model.executeCommand(command);
            terminal.appendText(s + "\n");
//            terminal.setText(s);
            System.out.println(s);
        } catch (UserException e) {
            e.showAlert();
        }
    }

    @FXML
    private void exitButtonClicked() {
        closeApplication();
    }

    @FXML
    private void settingsButtonClicked() {
        if (menuPane.isVisible()) {
            buttonsPane.setVisible(true);
            menuPane.setVisible(false);
        } else {
            buttonsPane.setVisible(false);
            menuPane.setVisible(true);
        }
        loadUI(GUIConfig.SETTINGS_PANE_PATH, menuPane);
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
        loadUI(GUIConfig.TABLEVIEW_PATH, visualPane);
        visualizeButton.setDisable(false);
        tableButton.setDisable(true);
    }


    private void loadUI(String uiPath, Pane targetPane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
            Localization localization = new Localization();
            loader.setResources(localization.getResourceBundle());
            Parent parent = loader.load();
            currentVisualizerController = loader.getController();
            targetPane.getChildren().add(parent);
            currentVisualizerController.updateInfo(model.getCollection());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeApplication() {
        model.prepareForExit();
        Platform.exit();
    }

    public void notifyDataChanged(List<HumanBeing> humanBeingList) {
        if (currentVisualizerController != null) {
            currentVisualizerController.updateInfo(humanBeingList);
        }
    }
}