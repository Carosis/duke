package Duke.Command;

import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;
import Duke.Tasks.Tasks;

import java.io.IOException;

public class DeleteTask extends Command {

    private final int taskNo;
    private final String SUCCESS_MESSAGE = "deleted";
    private final String ERROR_MESSAGE_PREFIX = "Failed to remove the incantation meow!: ";

    public DeleteTask(int index) {
        this.taskNo = index;
    }

    public void execute(TaskList tskList, UI ui, Storage store) {

        try {
            Tasks temp = tskList.getTask(taskNo);
            tskList.deleteTask(taskNo);
            ui.printIndividualTask(temp);
            ui.show(SUCCESS_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(ERROR_MESSAGE_PREFIX + e.getMessage());
        }

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