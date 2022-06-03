package kib.lab8.client.utils;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import kib.lab8.common.abstractions.ResponseInterface;
import kib.lab8.common.util.client_server_communication.requests.SignUpRequest;
import kib.lab8.common.util.client_server_communication.responses.AuthenticationResponse;

import java.io.IOException;

public class RegistrationModel {

    private final ConnectionHandlerClient connectionHandler;

    public RegistrationModel(ConnectionHandlerClient connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConnectionHandlerClient getConnectionHandler() {
        return connectionHandler;
    }

    public void sendSignUpRequest(String userLogin, String userPassword) throws UserException {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserLogin(userLogin);
        signUpRequest.setUserPassword(userPassword);
        try {
            AuthenticationResponse authenticationResponse = (AuthenticationResponse)connectionHandler.sendRequest(signUpRequest);
            if (!authenticationResponse.getResponseSuccess()) {
                throw new UserException(authenticationResponse.getMessage().getMessage());
            }
        } catch (IOException e) {
            Platform.setImplicitExit(false);
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
