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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
                protected Void call() throws UserException {
                    updateCollection();
                    return null;
                }
            };
            task.setOnFailed(event -> {
                UserException exception = (UserException) task.getException();
                if (exception.isFatal()) {
                    exception.showAlert();
                    prepareForExit();
                    controller.closeApplication();
                } else {
                    exception.showAlert();
                }
            });
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
                prepareForExit();
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
            List<HumanBeing> recievedPeople = ((CommandResponse) connectionHandler.sendRequest(request)).getPeople();
            List<Long> currentIds = humanCollection.stream().map(HumanBeing::getId).collect(Collectors.toList());
            controller.notifyDataChanged(getElementsToRemove(recievedPeople, currentIds),
                    getElementsToAdd(recievedPeople, currentIds),
                    getElementsToUpdate(recievedPeople));
            humanCollection.clear();
            humanCollection.addAll(recievedPeople);
        } catch (SocketTimeoutException e) {
            throw new UserException("От сервера не был получен ответ, закрываюсь...", true);
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при коммуникации с сервером, "
                    + "повторите попытку");
        } catch (ClassNotFoundException e) {
            throw new UserException("Произошла ошибка при получении ответа с сервера. Пожалуйста, повторите попытку");
        }
    }

    private List<HumanBeing> getElementsToAdd(List<HumanBeing> recievedPeople, List<Long> currentIds) {
        List<Long> recievedIds = recievedPeople.stream().map(HumanBeing::getId).collect(Collectors.toList());
        List<HumanBeing> newElements = new ArrayList<>();
        for (Long id : recievedIds) {
            if (!currentIds.contains(id)) {
                recievedPeople.stream().filter(humanBeing -> humanBeing.getId().equals(id)).findFirst().ifPresent(newElements::add);
            }
        }
        return newElements;
    }

    private List<HumanBeing> getElementsToRemove(List<HumanBeing> recievedPeople, List<Long> currentIds) {
        List<Long> recievedIds = recievedPeople.stream().map(HumanBeing::getId).collect(Collectors.toList());
        List<HumanBeing> elementsToRemove = new ArrayList<>();
        for (Long id : currentIds) {
            if (!recievedIds.contains(id)) {
                humanCollection.stream().filter(humanBeing -> humanBeing.getId().equals(id)).findFirst().ifPresent(elementsToRemove::add);
            }
        }
        return elementsToRemove;
    }

    private List<HumanBeing> getElementsToUpdate(List<HumanBeing> recievedPeople) {
        List<HumanBeing> elementsToUpdate = new ArrayList<>();
        for (HumanBeing recievedHuman : recievedPeople) {
            for (HumanBeing human : humanCollection) {
                if (human.getId().equals(recievedHuman.getId()) && human.hashCode() != recievedHuman.hashCode()) {
                    elementsToUpdate.add(recievedHuman);
                }
            }
        }
        return elementsToUpdate;
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
