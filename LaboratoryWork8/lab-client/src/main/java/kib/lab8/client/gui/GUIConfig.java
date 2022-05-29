package kib.lab8.client.gui;

import java.io.InputStream;
import javafx.scene.image.Image;

public class GUIConfig {
    private static final String CORNER_IMAGE = "/icons/img.png";
    public static final String TITLE = "BE HUMAN";
    public static final String CONNECTION_PATH = "/layout/serverConnection.fxml";
    public static final String REGISTRATION_PATH = "/layout/registration.fxml";
    public static final String AUTHORIZATION_PATH = "/layout/authorization.fxml";
    public static final String MAIN_WINDOW_PATH = "/layout/mainApp.fxml";
    public static final String TABLEVIEW_PATH = "/layout/tableview.fxml";
    public static final String SETTINGS_PANE_PATH = "/layout/settingsPane.fxml";
    public static final String ADD_MENU_PATH = "/layout/addCommand.fxml";
    public static final String VISUAL_PANE_PATH = "/layout/visualization.fxml";

    public static Image getCornerImage() {
        InputStream iconStream = GUIConfig.class.getResourceAsStream(CORNER_IMAGE);
        return new Image(iconStream);
    }
}
