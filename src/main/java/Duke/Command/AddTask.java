package Duke.Command;

import Duke.Tasks.Tasks;
import Duke.Utility.Storage;
import Duke.Utility.TaskList;
import Duke.Utility.UI;

import java.io.IOException;

public class AddTask extends Command {
    private final Tasks tsk;

    private final String SUCCESS_MESSAGE = "added";
    private final String ERROR_MESSAGE_PREFIX = "Failed to save the incantation meow! : ";
    public AddTask(Tasks tsk) {
        this.tsk=tsk;
    }

    public void execute(TaskList tskList, UI ui, Storage store) {
        tskList.addTask(tsk);
        ui.show(SUCCESS_MESSAGE);
        ui.printIndividualTask(tsk);
        try {
            store.save(tskList.getAllTasks());
            ui.printNumberOfTask(tskList);
        } catch (IOException e) {
            ui.showError(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}