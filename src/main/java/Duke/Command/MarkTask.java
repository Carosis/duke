package Duke.Command;

import Duke.Tasks.Tasks;
import Duke.Utility.DukeException;
import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;

import java.io.IOException;

public class MarkTask extends Command {
    private final String[] userInputs;

    public MarkTask(String[] UserInput) {
        this.userInputs = UserInput;
    }

    public void execute(TaskList tskList, UI ui, Storage store) throws DukeException {
        Tasks tsk = tskList.storedTaskList.get(Integer.parseInt(userInputs[1]) - 1);

        if (userInputs[0].equals("mark")) {
            ui.show("marked");
            tsk.setIsDone(true);
        } else if (userInputs[0].equals("unmark")) {
            ui.show("unmarked");
            tsk.setIsDone(false);
        }
        ui.printTaskMsg(tsk.toString());

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