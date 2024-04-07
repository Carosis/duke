package duke.command;

import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;

import java.io.IOException;


public class ListTaskFunction extends Command {
    private final String LIST_MESSAGE = "list";
    private final String ERROR_MESSAGE_PREFIX = "Failed to save the incantation meow! : ";
    public void execute(TaskList tskList, UI ui, Storage store) {

        ui.show(LIST_MESSAGE);
        ui.printTaskList(tskList);

        try {
            store.save(tskList.getAllTasks());
            ui.printNumberOfTask(tskList);
        } catch (IOException e) {
            ui.showError(ERROR_MESSAGE_PREFIX+ e.getMessage());
        }

    }


    @Override
    public boolean isExit() {
        return false;
    }
}