package kib.lab8.server.utils;

import kib.lab8.common.abstractions.RequestInterface;
import kib.lab8.common.abstractions.ResponseInterface;
import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.requests.LoginRequest;
import kib.lab8.common.util.client_server_communication.requests.SignUpRequest;
import kib.lab8.common.util.client_server_communication.responses.AuthenticationResponse;
import kib.lab8.common.util.console_workers.ErrorMessage;
import kib.lab8.common.util.console_workers.SuccessMessage;


public class RequestWorker {

    private final CommandManager commandManager;
    private final DataManager dataManager;

    public RequestWorker(DataManager dataManager) {
        this.dataManager = dataManager;
        this.commandManager = new CommandManager(dataManager);
    }

    public ResponseInterface getResponse(RequestInterface request) {
        Class<?> requestType = request.getType();
        long requestId = request.getRequestId();
        ResponseInterface response;
        if (CommandRequest.class.equals(requestType)) {
            response = workWithCommandRequest((CommandRequest) request);
        } else if (LoginRequest.class.equals(requestType)) {
            response = workWithLoginRequest((LoginRequest) request);
        } else if (SignUpRequest.class.equals(requestType)) {
            response = workWithSignUpRequest((SignUpRequest) request);
        } else {
            response = null;
        }
        if (response != null) {
            response.setResponseId(requestId);
        }
        return response;
    }

    private ResponseInterface workWithSignUpRequest(SignUpRequest request) {
        if (dataManager.createUser(request.getUserLogin(), request.getUserPassword())) {
            return new AuthenticationResponse(new SuccessMessage("Пользователь успешно создан"), true);
        } else {
            return new AuthenticationResponse(new ErrorMessage("Не получилось зарегистрировать нового пользователя, возможно такой логин уже занят"), false);
        }
    }

    private ResponseInterface workWithLoginRequest(LoginRequest request) {
        if (dataManager.loginUser(request.getUserLogin(), request.getUserPassword())) {
            return new AuthenticationResponse(new SuccessMessage("Вы зашли в систему"), true);
        } else {
            return new AuthenticationResponse(new ErrorMessage("Неверно введено имя пользователя или пароль"), false);
        }
    }

    private ResponseInterface workWithCommandRequest(CommandRequest request) {
        return (ResponseInterface) commandManager.executeCommandFromRequest(request);
    }

}
