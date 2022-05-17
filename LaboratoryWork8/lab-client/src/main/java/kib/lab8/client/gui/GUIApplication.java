package kib.lab8.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


public class GUIApplication extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/layout/authorization.fxml"));
        Parent root = fxmlLoader.load();
        InputStream iconStream = getClass().getResourceAsStream("/icons/img.png");

        stage.setTitle("Be human");
        stage.setResizable(false);
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setScene(new Scene(root));

        stage.show();
//        ExampleController controller = fxmlLoader.getController();

//        stage.setTitle("Dogs application");
//        stage.setWidth(500);
//        stage.setHeight(400);
//
//
//
//
//
    }

}
