package Duke.Command;

import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;
import Duke.Tasks.Tasks;

import java.io.IOException;

public class DeleteTask extends Command {

    private final int taskNo;

    public DeleteTask(int index) {
        this.taskNo = index;
    }

    public void execute(TaskList tskList, UI ui, Storage store) {

        try {
            Tasks temp = tskList.getTask(taskNo);
            tskList.deleteTask(taskNo);
            ui.printIndividualTask(temp);
            ui.show("deleted");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Failed to remove the incantation: " + e.getMessage() + " ! Meow!");
        }

        try {
            store.save(tskList.getAllTasks());
            ui.printNumberOfTask(tskList);
        } catch (IOException e) {
            ui.showError("Failed to remove the incantation: " + e.getMessage() + " ! Meow!");
        }

    }
    @Override
    public boolean isExit() {
        return false;
    }
}