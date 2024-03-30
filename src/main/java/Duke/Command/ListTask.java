package Duke.Command;

import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;

import java.io.IOException;


public class ListTask extends Command {
    public void execute(TaskList tskList, UI ui, Storage store) {

        ui.show("list");
        ui.printTaskList(tskList);

        try {
            store.save(tskList.getAllTasks());
            ui.printNumberOfTask(tskList);
        } catch (IOException e) {
            ui.showError("Failed to save the incantation: " + e.getMessage() + " ! Meow!");
        }

    }


    @Override
    public boolean isExit() {
        return false;
    }
}