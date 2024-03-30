package Duke.Command;

import Duke.Tasks.Tasks;
import Duke.Utility.*;
import java.io.IOException;
import java.util.*;


public class listTask extends Command {
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