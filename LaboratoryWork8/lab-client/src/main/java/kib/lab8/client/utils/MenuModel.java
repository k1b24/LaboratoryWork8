package kib.lab8.client.utils;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import kib.lab8.client.gui.controllers.MenuController;
import kib.lab8.common.entities.HumanBeing;
import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.responses.CommandResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuModel {

    private static final int THREADS = Runtime.getRuntime().availableProcessors();
    private final static int UPDATE_TIME = 5000;
    private final ConnectionHandlerClient connectionHandler;
    private final String userLogin;
    private final String userPassword;
    private final Timer timer = new Timer(true);
    private final List<HumanBeing> humanCollection = new CopyOnWriteArrayList<>();
    private final MenuController controller;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
    private final ScheduledService<Void> scheduledService = new ScheduledService<Void>() {

        @Override
        protected Task<Void> createTask() {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() {
                    try {
                        updateCollection();
                    } catch (UserException e) {
                        if (e.isFatal()) {
                            prepareForExit();
                            e.showAlert();
                            controller.closeApplication();
                        }
                    }
                    return null;
                }
            };
            return task;
        }
    };


    public MenuModel(ConnectionHandlerClient connectionHandler, String userLogin, String userPassword, MenuController controller) {
        this.connectionHandler = connectionHandler;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.controller = controller;
        scheduledService.setPeriod(Duration.millis(5000));
        scheduledService.start();
    }

    public void prepareForExit() {
        scheduledService.cancel();
        executorService.shutdown();
        connectionHandler.closeConnection();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void executeCommand(ExecutableCommand command, Object... args) {
        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return command.action(connectionHandler, userLogin, userPassword, args).getMessage();
            }
        };
        task.setOnFailed(event -> {
            UserException exception = (UserException) task.getException();
            if (exception.isFatal()) {
                exception.showAlert();
                executorService.shutdown();
                timer.cancel();
                controller.closeApplication();
            }
        });
        task.setOnSucceeded(event -> controller.getTerminal().appendText(task.getValue() + "\n"));
        executorService.execute(task);
    }

    public List<HumanBeing> getCollection() {
        return humanCollection;
    }

    public void updateCollection() throws UserException {
        CommandRequest request = new CommandRequest("show");
        request.setUserLogin(userLogin);
        request.setUserPassword(userPassword);
        try {
            CommandResponse response = (CommandResponse) connectionHandler.sendRequest(request);
            List<Long> recievedIDs = new ArrayList<>();
            for (HumanBeing recievedHuman : response.getPeople()) {
                recievedIDs.add(recievedHuman.getId());
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
            humanCollection.removeIf(human -> !recievedIDs.contains(human.getId()));
            controller.notifyDataChanged(humanCollection);
        } catch (SocketTimeoutException e) {
            throw new UserException("От сервера не был получен ответ, закрываюсь...", true);
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при коммуникации с сервером, "
                    + "повторите попытку");
        } catch (ClassNotFoundException e) {
            throw new UserException("Произошла ошибка при получении ответа с сервера. Пожалуйста, повторите попытку");
        }
    }

    public MenuController getController() {
        return controller;
    }

    public HumanBeing getHumanById(int id) throws UserException {
        HumanBeing chosenHuman = humanCollection.stream().filter(human -> human.getId() == id).findFirst().orElse(null);
        if (chosenHuman == null) {
            throw new UserException("Нет человека с таким ID");
        }
        return chosenHuman;
    }

}
