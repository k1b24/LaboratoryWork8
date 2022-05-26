package kib.lab8.client.utils;

import kib.lab8.common.util.client_server_communication.requests.LoginRequest;
import kib.lab8.common.util.client_server_communication.responses.AuthenticationResponse;

import java.io.IOException;

public class AuthorizationModel {

    private ConnectionHandlerClient connectionHandler;
    private String userLogin;
    private String userPassword;

    public void setConnectionHandler(ConnectionHandlerClient connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConnectionHandlerClient getConnectionHandler() {
        return connectionHandler;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean authorize(String login, String password) throws UserException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserLogin(login);
        loginRequest.setUserPassword(password);
        try {
            connectionHandler.sendRequest(loginRequest);
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при отправке запроса на сервер, "
                    + "повторите попытку");
        }
        try {
            AuthenticationResponse authenticationResponse = (AuthenticationResponse) connectionHandler.recieveResponse();
            if (authenticationResponse.getResponseSuccess()) {
                userLogin = login;
                userPassword = password;
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            throw new UserException("Произошла ошибка при чтении ответа от сервера. Пожалуйста, повторите ввод");
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при отправке запроса на сервер. Пожалуйста, повторите ввод");
        }
    }
}
