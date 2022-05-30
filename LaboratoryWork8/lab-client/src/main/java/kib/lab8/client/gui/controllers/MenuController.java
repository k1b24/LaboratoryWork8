package kib.lab8.client.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.MenuModel;
import kib.lab8.client.utils.UserException;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.common.entities.HumanBeing;

import java.io.IOException;
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
        loadUI(GUIConfig.VISUAL_PANE_PATH, visualPane, false, true);
        tableButton.setDisable(false);
        visualizeButton.setDisable(true);
    }

    public MenuController(ConnectionHandlerClient connectionHandler, String username, String password) {
        model = new MenuModel(connectionHandler, username, password, this);
    }

    public TextArea getTerminal() {
        return terminal;
    }

    @FXML
    private void addButtonClicked() {
        //TODO реализовать открытие окна с добавлением
        loadUI(GUIConfig.ADD_MENU_PATH, null, true, false);

    }

    @FXML
    private void clearButtonClicked() {
        ExecutableCommand clearCommand = ExecutableCommand.CLEAR_COMMAND;
        try {
            model.executeCommand(clearCommand);
            model.updateCollection();
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
        loadUI(GUIConfig.UPDATE_WINDOW_PATH, null, true, false);
    }

    @FXML
    private void historyButtonClicked() {
        ExecutableCommand command = ExecutableCommand.HISTORY_COMMAND;
        try {
            model.executeCommand(command);
        } catch (UserException e) {
            e.showAlert();
        }
    }

    @FXML
    private void removeButtonClicked() {
        //TODO пока сделать открытие окна по кнопке, в дальнейшем придумать как удалять их прям из таблицы или из координат
        loadUI(GUIConfig.REMOVE_WINDOW_PATH, null, true, false);
    }

    @FXML
    private void infoButtonClicked() {
        ExecutableCommand command = ExecutableCommand.INFO_COMMAND;
        try {
            model.executeCommand(command);
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
        loadUI(GUIConfig.SETTINGS_PANE_PATH, menuPane, false, false);
    }

    @FXML
    private void FAQButtonClicked() {
        System.out.println("dada");
    }

    @FXML
    private void visualizeButtonClicked() {
        loadUI(GUIConfig.VISUAL_PANE_PATH, visualPane, false, true);
        tableButton.setDisable(false);
        visualizeButton.setDisable(true);
    }

    @FXML
    private void tableButtonClicked() {
        loadUI(GUIConfig.TABLEVIEW_PATH, visualPane, false, true);
        visualizeButton.setDisable(false);
        tableButton.setDisable(true);
    }


    protected void loadUI(String uiPath, Pane targetPane, boolean inNewWindow, boolean visualization) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
            Localization localization = new Localization();
            loader.setResources(localization.getResourceBundle());
            Parent parent = loader.load();
            if (visualization) {
                currentVisualizerController = loader.getController();
                currentVisualizerController.updateInfo(model.getCollection());
                currentVisualizerController.setParentController(this);
            }
            if (inNewWindow) {
                ChildWindowController controller = loader.getController();
                controller.setMenuModel(model);
                Stage stage = new Stage();
                stage.getIcons().add(GUIConfig.getCornerImage());
                stage.setTitle(GUIConfig.TITLE);
                stage.setResizable(false);
                stage.setScene(new Scene(parent));
                stage.show();
            } else {
                targetPane.getChildren().add(parent);
            }
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

    public HumanBeing getSelectedHumanBeingInVisualisationWindow() {
        return currentVisualizerController.getChosenHuman();
    }
}