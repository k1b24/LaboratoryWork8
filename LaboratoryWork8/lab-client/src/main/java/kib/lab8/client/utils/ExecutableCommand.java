package kib.lab8.client.utils;

import kib.lab8.common.abstractions.AbstractMessage;
import kib.lab8.common.entities.HumanBeing;
import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.responses.CommandResponse;

import java.io.IOException;

public enum ExecutableCommand {

    INFO_COMMAND {
        @Override
        public AbstractMessage action(ConnectionHandlerClient connectionHandler, String login, String password, Object... args) throws UserException {
            CommandRequest request = new CommandRequest("info");
            return executeCommand(connectionHandler, request, login, password);
        }
    },
    ADD_COMMAND {
        @Override
        public AbstractMessage action(ConnectionHandlerClient connectionHandler, String login, String password, Object... args) throws UserException {
            CommandRequest request = new CommandRequest("add", (HumanBeing) args[0]);
            return executeCommand(connectionHandler, request, login, password);
        }
    },
    HISTORY_COMMAND {
        @Override
        public AbstractMessage action(ConnectionHandlerClient connectionHandler, String login, String password, Object... args) throws UserException {
            CommandRequest request = new CommandRequest("history");
            return executeCommand(connectionHandler, request, login, password);
        }
    },
    CLEAR_COMMAND {
        @Override
        public AbstractMessage action(ConnectionHandlerClient connectionHandler, String login, String password, Object... args) throws UserException {
            CommandRequest request = new CommandRequest("clear");
            return executeCommand(connectionHandler, request, login, password);
        }
    }, ADD_IF_MIN_COMMAND {
        @Override
        public AbstractMessage action(ConnectionHandlerClient connectionHandler, String login, String password, Object... args) throws UserException {
            CommandRequest request = new CommandRequest("add_if_min", (HumanBeing) args[0]);
            return executeCommand(connectionHandler, request, login, password);
        }
    };

    public abstract AbstractMessage action(ConnectionHandlerClient connectionHandler, String login, String password, Object... args) throws UserException;

    protected AbstractMessage executeCommand(ConnectionHandlerClient connectionHandler, CommandRequest request, String login, String password) throws UserException {
        request.setUserLogin(login);
        request.setUserPassword(password);
        try {
            CommandResponse response = (CommandResponse) connectionHandler.sendRequest(request);
            return response.getMessage();
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при коммуникации с сервером, "
                    + "повторите попытку");
        } catch (ClassNotFoundException e) {
            throw new UserException("Произошла ошибка при получении ответа с сервера. Пожалуйста, повторите попытку");
        }
    }
}
