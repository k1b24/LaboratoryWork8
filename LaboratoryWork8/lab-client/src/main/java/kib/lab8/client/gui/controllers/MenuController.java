package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MenuController {

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

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
