package kib.lab8.client.utils;


import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectionModel {

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

    public ConnectionHandlerClient getConnectionHandler() {
        return connectionHandler;
    }


}
