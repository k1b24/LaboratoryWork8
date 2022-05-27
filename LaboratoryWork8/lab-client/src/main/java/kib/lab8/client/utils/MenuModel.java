package kib.lab8.client.utils;

public class MenuModel {

    private final ConnectionHandlerClient connectionHandler;
    private final String userLogin;
    private final String userPassword;

    public MenuModel(ConnectionHandlerClient connectionHandler, String userLogin, String userPassword) {
        this.connectionHandler = connectionHandler;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public void prepareForExit() {
        connectionHandler.closeConnection();
    }

    public String getUserLogin() {
        return userLogin;
    }
}
