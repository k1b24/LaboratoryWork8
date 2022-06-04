package kib.lab8.client.utils;

import kib.lab8.client.gui.controllers.AuthorizationController;
import kib.lab8.common.util.client_server_communication.requests.LoginRequest;
import kib.lab8.common.util.client_server_communication.responses.AuthenticationResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class AuthorizationModel {

    private ConnectionHandlerClient connectionHandler;
    private String userLogin;
    private String userPassword;
    private final AuthorizationController controller;

    public AuthorizationModel(ConnectionHandlerClient connectionHandler, AuthorizationController controller) {
        this.connectionHandler = connectionHandler;
        this.controller = controller;
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
        } catch (SocketTimeoutException e) {
            throw new UserException(controller.getResourceBundle().getString("no_response_error"));
        } catch (IOException e) {
            throw new UserException(controller.getResourceBundle().getString("response_receiving_error"), true);
        } catch (ClassNotFoundException | RequestResponseMismatchException e) {
            throw new UserException(controller.getResourceBundle().getString("server_communication_error"), true);
        }
    }

    public void prepareForExit() {
        connectionHandler.closeConnection();
    }
}
