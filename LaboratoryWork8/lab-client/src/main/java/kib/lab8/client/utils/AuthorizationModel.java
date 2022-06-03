package kib.lab8.client.utils;

import kib.lab8.common.util.client_server_communication.requests.LoginRequest;
import kib.lab8.common.util.client_server_communication.responses.AuthenticationResponse;

import java.io.IOException;

public class AuthorizationModel {

    private ConnectionHandlerClient connectionHandler;
    private String userLogin;
    private String userPassword;

    public AuthorizationModel(ConnectionHandlerClient connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

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
            AuthenticationResponse authenticationResponse = (AuthenticationResponse) connectionHandler.sendRequest(loginRequest);
            if (authenticationResponse.getResponseSuccess()) {
                userLogin = login;
                userPassword = password;
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при коммуникации с сервером, "
                    + "повторите попытку", true);
        } catch (ClassNotFoundException e) {
            throw new UserException("Произошла ошибка при получении ответа с сервера. Пожалуйста, повторите попытку", true);
        } catch (RequestResponseMismatchException e) {
            throw new UserException("Сервер прислал лажу", true);
        }
    }

    public void prepareForExit() {
        connectionHandler.closeConnection();
    }
}
