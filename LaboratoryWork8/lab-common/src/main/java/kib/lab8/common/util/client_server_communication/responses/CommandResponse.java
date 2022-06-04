package kib.lab8.common.util.client_server_communication.responses;

import kib.lab8.common.abstractions.AbstractMessage;
import kib.lab8.common.abstractions.ResponseInterface;
import kib.lab8.common.entities.HumanBeing;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandResponse implements Serializable, ResponseInterface {

    private final AbstractMessage message;
    private final ArrayList<HumanBeing> people;
    private long responseId;

    public CommandResponse(AbstractMessage message) {
        this.message = message;
        this.people = null;
    }

    public CommandResponse(AbstractMessage message, ArrayList<HumanBeing> people) {
        this.message = message;
        this.people = people;
    }

    @Override
    public AbstractMessage getMessage() {
        return message;
    }

    @Override
    public Class<?> getType() {
        return this.getClass();
    }

    @Override
    public void setResponseId(long id) {
        responseId = id;
    }

    @Override
    public long getResponseId() {
        return responseId;
    }

    public ArrayList<HumanBeing> getPeople() {
        return people;
    }
}
