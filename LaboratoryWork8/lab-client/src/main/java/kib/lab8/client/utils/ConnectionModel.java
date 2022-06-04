package kib.lab8.client.utils;


import kib.lab8.client.gui.controllers.ConnectionController;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectionModel {

    private final ConnectionController controller;
    private ConnectionHandlerClient connectionHandler;

    public ConnectionModel (ConnectionController controller) {
        this.controller = controller;
    }


    public void initializeConnectionHandler(String address, String port) throws UserException {
        try {
            connectionHandler = new ConnectionHandlerClient(address);
            connectionHandler.setServerPort(Integer.parseInt(port));
        } catch (UnknownHostException e) {
            throw new UserException(controller.getResourceBundle().getString("address_doesnt_exist_error"));
        } catch (SocketException e) {
            throw new UserException(controller.getResourceBundle().getString("port_opening_error"));
        } catch (NumberFormatException e) {
            throw new UserException(controller.getResourceBundle().getString("port_bounds_error"));
        }
    }

    public ConnectionHandlerClient getConnectionHandler() {
        return connectionHandler;
    }


}
