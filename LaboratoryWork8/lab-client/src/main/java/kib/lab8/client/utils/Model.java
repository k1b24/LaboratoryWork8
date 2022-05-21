package kib.lab8.client.utils;


import kib.lab8.common.util.client_server_communication.requests.LoginRequest;
import kib.lab8.common.util.client_server_communication.responses.AuthenticationResponse;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Model {

    private String userLogin;
    private String userPassword;

    private ConnectionHandlerClient connectionHandler;

    public void initializeConnectionHandler(String address, String port) throws UserException {
        try {
            connectionHandler = new ConnectionHandlerClient(address);
            connectionHandler.setServerPort(Integer.parseInt(port));
        } catch (UnknownHostException e) {
            throw new UserException("Такого адреса не существует в сети, повторите ввод");
        } catch (SocketException e) {
            throw new UserException("Произошла ошибка при открытии сетевого порта, пожалуйста, повторите ввод");
        } catch (NumberFormatException e) {
            throw new UserException("В поле порт должно быть введено число от 1 до 65000");
        }
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
            throw new UserException("Произошла ошибка при чтении ответа от сервера. Пожалуйста, повторите ввод")
        } catch (IOException e) {
            throw new UserException("Произошла ошибка при отправке запроса на сервер. Пожалуйста, повторите ввод")
        }
    }
}
