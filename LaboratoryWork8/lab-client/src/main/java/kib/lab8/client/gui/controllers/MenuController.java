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
import kib.lab8.client.utils.ConnectionHandlerClient;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.MenuModel;
import kib.lab8.client.utils.UserException;

import java.io.IOException;


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

    @FXML
    private void initialize() {
        nickname.setText(model.getUserLogin());
    }

    public MenuController(ConnectionHandlerClient connectionHandler, String username, String password) {
        model = new MenuModel(connectionHandler, username, password);
    }

    @FXML
    private void addButtonClicked() {
        //TODO реализовать открытие окна с добавлением
        loadUI(GUIConfig.ADD_MENU_PATH, null, true);

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
        model.prepareForExit();
        Platform.exit();
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
        loadUI(GUIConfig.SETTINGS_PANE_PATH, menuPane, false);
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
        loadUI(GUIConfig.TABLEVIEW_PATH, visualPane, false);
        visualizeButton.setDisable(false);
        tableButton.setDisable(true);
    }


    private void loadUI(String uiPath, Pane targetPane, boolean inNewWindow) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
            //loader.setControllerFactory(controllerClass -> new TableViewController());

            Localization localization = new Localization();
            loader.setResources(localization.getResourceBundle());
            Parent parent = loader.load();
            if (inNewWindow) {
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

}