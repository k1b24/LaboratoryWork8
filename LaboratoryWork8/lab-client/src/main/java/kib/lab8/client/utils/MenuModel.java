package kib.lab8.client.utils;

import kib.lab8.client.gui.controllers.MenuController;
import kib.lab8.common.entities.HumanBeing;
import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.responses.CommandResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuModel {

    private final static int UPDATE_TIME = 5000;
    private final ConnectionHandlerClient connectionHandler;
    private final String userLogin;
    private final String userPassword;
    private final Timer timer = new Timer(true);
    private final List<HumanBeing> humanCollection = new CopyOnWriteArrayList<>();
    private final MenuController controller;




    public MenuModel(ConnectionHandlerClient connectionHandler, String userLogin, String userPassword, MenuController controller) {
        this.connectionHandler = connectionHandler;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.controller = controller;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateCollection();
            }
        }, 0, UPDATE_TIME);
    }

    public void prepareForExit() {
        connectionHandler.closeConnection();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void executeCommand(ExecutableCommand command, Object... args) throws UserException {
        String message = command.action(connectionHandler, userLogin, userPassword, args).getMessage();
        controller.getTerminal().appendText(message + "\n");
    }

    public List<HumanBeing> getCollection() {
        return humanCollection;
    }

    public void updateCollection() {
        CommandRequest request = new CommandRequest("show");
        request.setUserLogin(userLogin);
        request.setUserPassword(userPassword);
        try {
            CommandResponse response = (CommandResponse) connectionHandler.sendRequest(request);
            for (HumanBeing recievedHuman : response.getPeople()) {
                boolean updated = false;
                for (int i = 0; i < humanCollection.size(); i++) {
                    if (Objects.equals(recievedHuman.getId(), humanCollection.get(i).getId())) {
                        if ((recievedHuman.hashCode() != humanCollection.get(i).hashCode())) {
                            humanCollection.set(i, recievedHuman);
                        }
                        updated = true;
                    }
                }
                if (!updated) {
                    humanCollection.add(recievedHuman);
                }
            }
            controller.notifyDataChanged(humanCollection);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            controller.closeApplication();
        }
    }

    public MenuController getController() {
        return controller;
    }
}
