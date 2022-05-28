package kib.lab8.client.utils;

import kib.lab8.common.entities.HumanBeing;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuModel {

    private final static int UPDATE_TIME = 30000; //2 mins
    private final ConnectionHandlerClient connectionHandler;
    private final String userLogin;
    private final String userPassword;
    private final Timer timer = new Timer(true);
    private List<HumanBeing> humanCollection = new CopyOnWriteArrayList<>();




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
