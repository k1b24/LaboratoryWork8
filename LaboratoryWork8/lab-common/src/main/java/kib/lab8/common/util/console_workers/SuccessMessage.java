package kib.lab8.common.util.console_workers;

import kib.lab8.common.abstractions.AbstractMessage;

public class SuccessMessage extends AbstractMessage {

    public SuccessMessage(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "✓ " + super.getMessage();
    }
}
