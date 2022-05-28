package kib.lab8.common.abstractions;

import java.io.Serializable;

public abstract class AbstractMessage implements Serializable {

    private final String message;

    public AbstractMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
