package kib.lab8.client.utils;

import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.responses.CommandResponse;

import java.io.IOException;
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
                //затычка
                CommandRequest request = new CommandRequest("show");
                request.setUserLogin(userLogin);
                request.setUserPassword(userPassword);
                try {
                    connectionHandler.sendRequest(request);
                    CommandResponse response = (CommandResponse) connectionHandler.recieveResponse();
                    System.out.println(response.getPeople());
                } catch (IOException | ClassNotFoundException ignored) {

                }
                //затычка
            }
        }, 0, UPDATE_TIME);
    }

    public void prepareForExit() {
        connectionHandler.closeConnection();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void executeCommand() {

    }
}
