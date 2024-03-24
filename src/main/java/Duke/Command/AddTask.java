package Duke.Command;

import Duke.Tasks.*;
import Duke.Utility.*;
import java.io.IOException;

public class AddTask extends Command {
    private final Tasks tsk;

    public AddTask(Tasks tsk) {
        this.tsk=tsk;
    }

    public void execute(TaskList tskList, UI ui, Storage store) {
        tskList.addTask(tsk);
        ui.show("added");
        ui.printIndividualTask(tsk);
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