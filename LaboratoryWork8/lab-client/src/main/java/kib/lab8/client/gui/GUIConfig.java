package kib.lab8.client.gui;

import java.io.InputStream;
import java.util.Random;

import javafx.scene.image.Image;

public class GUIConfig {
    private static final String CORNER_IMAGE = "/icons/img.png";
    public static final String TITLE = "BE HUMAN";
    public static final String CONNECTION_PATH = "/layout/server_connection.fxml";
    public static final String REGISTRATION_PATH = "/layout/registration.fxml";
    public static final String AUTHORIZATION_PATH = "/layout/authorization.fxml";
    public static final String MAIN_WINDOW_PATH = "/layout/menu.fxml";
    public static final String TABLEVIEW_PATH = "/layout/tableview.fxml";
    public static final String SETTINGS_PANE_PATH = "/layout/settings_pane.fxml";
    public static final String ADD_MENU_PATH = "/layout/add_command.fxml";
    public static final String VISUAL_PANE_PATH = "/layout/visualization.fxml";
    public static final String REMOVE_WINDOW_PATH = "/layout/remove_window.fxml";
    public static final String UPDATE_WINDOW_PATH = "/layout/update_by_id_window.fxml";
    public static final String PROFILE_WINDOW_PATH = "/layout/human_profile.fxml";


    public static Image getCornerImage() {
        InputStream iconStream = GUIConfig.class.getResourceAsStream(CORNER_IMAGE);
        return new Image(iconStream);
    }

    public static Image getRandomHumanImage() {
        Random random = new Random();
        InputStream imageStream = GUIConfig.class.getResourceAsStream("/human_images/" + (random.nextInt(8) + 1) + ".jpg");
        return new Image(imageStream);
    }
}
