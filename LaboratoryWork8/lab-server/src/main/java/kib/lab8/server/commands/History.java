package kib.lab8.server.commands;

import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.responses.CommandResponse;
import kib.lab8.common.util.console_workers.SuccessMessage;
import kib.lab8.server.utils.CommandManager;
import kib.lab8.server.abstractions.AbstractCommand;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class History extends AbstractCommand {

    private final CommandManager commandManager;

    public History(CommandManager commandManager) {
        super("history", "Вывести информацию по последним"
                + " 10 исполненным командам", false, null);
        this.commandManager = commandManager;
    }

    @Override
    public Object execute(CommandRequest request) {
        ArrayDeque<AbstractCommand> listToReturn = (ArrayDeque<AbstractCommand>) commandManager.getLastExecutedCommands();
        return new CommandResponse(new SuccessMessage(listToReturn.stream()
                .map(AbstractCommand::getName)
                .collect(Collectors.joining("\n"))));
    }
}
