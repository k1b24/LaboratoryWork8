package kib.lab8.client.gui.abstractions;

import kib.lab8.client.utils.ConnectionHandlerClient;

public abstract class AbstractModel {

    private final ConnectionHandlerClient connectionHandler;

    public AbstractModel(ConnectionHandlerClient connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConnectionHandlerClient getConnectionHandler() {
        return connectionHandler;
    }


}
