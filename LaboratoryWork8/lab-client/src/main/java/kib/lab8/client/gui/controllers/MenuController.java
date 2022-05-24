package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kib.lab8.client.gui.Localization;
import kib.lab8.client.utils.Model;

import java.io.IOException;

import static kib.lab8.client.gui.GUIConfig.CONNECTION_PATH;
import static kib.lab8.client.gui.GUIConfig.MAIN_WINDOW_PATH;

public class MenuController {

    @FXML
    private AnchorPane mainPane;

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
    private Text userInfo;

    @FXML
    private Text nickname;
    private Model model;

    @FXML
    public void initialize() {
        model = new Model();


        FXMLLoader connectionLoader = new FXMLLoader();
        connectionLoader.setLocation(getClass().getResource(CONNECTION_PATH));
        connectionLoader.setControllerFactory(controllerClass -> new ConnectionController(model));
        Localization localization = new Localization();
        connectionLoader.setResources(localization.getResourceBundle());
        try {
            Parent root = connectionLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Be human");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
            stage.initModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    }

    @FXML
    private void FAQButtonClicked() {
        System.out.println("dada");
    }
}