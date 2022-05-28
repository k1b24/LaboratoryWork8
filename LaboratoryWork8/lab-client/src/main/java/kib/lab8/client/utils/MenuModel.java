package kib.lab8.client.utils;

import java.util.Timer;
import java.util.TimerTask;

public class MenuModel {

    private final static int UPDATE_TIME = 30000; //2 mins
    private final ConnectionHandlerClient connectionHandler;
    private final String userLogin;
    private final String userPassword;
    private final Timer timer = new Timer(true);




    public MenuModel(ConnectionHandlerClient connectionHandler, String userLogin, String userPassword) {
        this.connectionHandler = connectionHandler;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //TODO Recieve collection in loop
            }
        }, 0, UPDATE_TIME);
    }

    public void prepareForExit() {
        connectionHandler.closeConnection();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String executeCommand(ExecutableCommand command) throws UserException {
        return command.action(connectionHandler, userLogin, userPassword).getMessage();
    }
}
