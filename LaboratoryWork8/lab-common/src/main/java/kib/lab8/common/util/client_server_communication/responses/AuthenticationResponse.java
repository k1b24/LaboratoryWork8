package kib.lab8.common.util.client_server_communication.responses;

import kib.lab8.common.abstractions.AbstractMessage;
import kib.lab8.common.abstractions.ResponseInterface;

public class AuthenticationResponse implements ResponseInterface {

    private final AbstractMessage message;
    private final boolean responseSuccess;

    public AuthenticationResponse(AbstractMessage message, boolean responseSuccess) {
        this.message = message;
        this.responseSuccess = responseSuccess;
    }

    public boolean getResponseSuccess() {
        return responseSuccess;
    }

    @Override
    public AbstractMessage getMessage() {
        return message;
    }

    @Override
    public Class<?> getType() {
        return this.getClass();
    }
}
