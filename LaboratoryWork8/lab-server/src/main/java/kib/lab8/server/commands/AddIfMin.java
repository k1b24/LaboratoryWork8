package kib.lab8.server.commands;

import kib.lab8.common.abstractions.AbstractMessage;
import kib.lab8.common.util.console_workers.ErrorMessage;
import kib.lab8.common.util.client_server_communication.requests.CommandRequest;
import kib.lab8.common.util.client_server_communication.responses.CommandResponse;
import kib.lab8.common.util.console_workers.SuccessMessage;
import kib.lab8.server.abstractions.AbstractCommand;
import kib.lab8.server.utils.DataManager;


public class AddIfMin extends AbstractCommand {

    public AddIfMin(DataManager target) {
        super("add_if_min", "Добавить введенный элемент в коллекцию, если его значение минимально,"
                + " принимает на вход [Имя, наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", false, target);
    }

    @Override
    public Object execute(CommandRequest request) {
        boolean isAdded = super.getDataManager().addHumanBeingIfMinimal(request.getHumanToSend());
        AbstractMessage message;
        if (isAdded) {
            message = new SuccessMessage("Объект успешно добавлен в коллекцию");
        } else {
            message = new ErrorMessage("Объект не был добавлен в коллекцию, так как он не является минимальным");
        }
        return new CommandResponse(message);
    }
}
