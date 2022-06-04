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
import kib.lab8.client.gui.localization.Localization;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.gui.abstractions.ChildWindowController;
import kib.lab8.client.utils.ChildUIType;
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
        tableButton.setDisable(false);
        visualizeButton.setDisable(false);
    }

    public MenuController(ConnectionHandlerClient connectionHandler, String username, String password) {
        model = new MenuModel(connectionHandler, username, password, this);
    }

    public TextArea getTerminal() {
        return terminal;
    }

    @FXML
    private void addButtonClicked() {
        loadUI(GUIConfig.ADD_MENU_PATH, null, ChildUIType.SIMPLE_CHILD_WINDOW);
    }

    @FXML
    private void clearButtonClicked() {
        ExecutableCommand clearCommand = ExecutableCommand.CLEAR_COMMAND;
        model.executeCommand(clearCommand);
        model.updateCollection();
    }

    @FXML
    private void updateButtonClicked() {
        loadUI(GUIConfig.UPDATE_WINDOW_PATH, null, ChildUIType.SIMPLE_CHILD_WINDOW);
    }

    @FXML
    private void historyButtonClicked() {
        ExecutableCommand command = ExecutableCommand.HISTORY_COMMAND;
        model.executeCommand(command);
    }

    @FXML
    private void removeButtonClicked() {
        loadUI(GUIConfig.REMOVE_WINDOW_PATH, null, ChildUIType.SIMPLE_CHILD_WINDOW);
    }

    @FXML
    private void infoButtonClicked() {
        ExecutableCommand command = ExecutableCommand.INFO_COMMAND;
        model.executeCommand(command);
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
        loadUI(GUIConfig.SETTINGS_PANE_PATH, menuPane, ChildUIType.ON_MAIN_MENU);
    }


    @FXML
    private void visualizeButtonClicked() {
        loadUI(GUIConfig.VISUAL_PANE_PATH, visualPane, ChildUIType.VISUALISATION);
        tableButton.setDisable(false);
        visualizeButton.setDisable(true);
    }

    @FXML
    private void tableButtonClicked() {
        loadUI(GUIConfig.TABLEVIEW_PATH, visualPane, ChildUIType.VISUALISATION);
        visualizeButton.setDisable(false);
        tableButton.setDisable(true);
    }


    protected void loadUI(String uiPath, Pane targetPane, ChildUIType uiType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
            Localization localization = new Localization(getCurrentLocale());
            loader.setResources(localization.getResourceBundle());
            if (uiType.equals(ChildUIType.VISUALISATION)) {
                Parent parent = loader.load();
                currentVisualizerController = loader.getController();
                model.updateCollection();
                currentVisualizerController.setInfo(model.getCollection());
                currentVisualizerController.setParentController(this);
                targetPane.getChildren().add(parent);
            } else if (uiType.equals(ChildUIType.SIMPLE_CHILD_WINDOW)) {
                Parent parent = loader.load();
                ChildWindowController controller = loader.getController();
                controller.setMenuModel(model);
                Stage stage = new Stage();
                stage.getIcons().add(GUIConfig.getCornerImage());
                stage.setTitle(GUIConfig.TITLE);
                stage.setResizable(false);
                stage.setScene(new Scene(parent));
                stage.show();
            } else if (uiType.equals(ChildUIType.HUMAN_PROFILE_CHILD_WINDOW)) {
                loader.setControllerFactory(controllerClass -> new HumanProfileController(getSelectedHumanBeingInVisualisationWindow(), model.getUserLogin()));
                Parent parent = loader.load();
                ChildWindowController controller = loader.getController();
                controller.setMenuModel(model);
                Stage stage = new Stage();
                stage.getIcons().add(GUIConfig.getCornerImage());
                stage.setTitle(GUIConfig.TITLE);
                stage.setResizable(false);
                stage.setScene(new Scene(parent));
                stage.show();
            } else if (uiType.equals(ChildUIType.ON_MAIN_MENU)) {
                Parent parent = loader.load();
                ChildWindowController controller = loader.getController();
                controller.setMenuModel(model);
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

    public void notifyDataChanged(List<HumanBeing> elementsToRemove, List<HumanBeing> elementsToAdd, List<HumanBeing> elementsToUpdate) {
        if (currentVisualizerController != null) {
            currentVisualizerController.updateInfo(elementsToRemove, elementsToAdd, elementsToUpdate);
        }
    }

    public HumanBeing getSelectedHumanBeingInVisualisationWindow() {
        return currentVisualizerController.getChosenHuman();
    }

    public DataVisualizerController getCurrentVisualizerController() {
        return currentVisualizerController;
    }
}
