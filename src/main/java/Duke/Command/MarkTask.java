package Duke.Command;

import Duke.Tasks.Tasks;
import Duke.Utility.DukeException;
import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;

import java.io.IOException;

public class MarkTask extends Command {
    private final String[] userInputs;
    private final String MARKED_TYPE_MESSAGE = "mark";
    private final String UNMARKED_TYPE_MESSAGE = "unmark";
    private final String MARKED_MESSAGE = "marked";
    private final String UNMARKED_MESSAGE = "unmarked";
    private final String ERROR_MESSAGE_PREFIX = "Failed to remove the incantation meow! : ";
    public MarkTask(String[] UserInput) {
        this.userInputs = UserInput;
    }

    public void execute(TaskList tskList, UI ui, Storage store) throws DukeException {
        Tasks tsk = tskList.storedTaskList.get(Integer.parseInt(userInputs[1]) - 1);

        if (userInputs[0].equals(MARKED_TYPE_MESSAGE)) {
            ui.show(MARKED_MESSAGE);
            tsk.setIsDone(true);
        } else if (userInputs[0].equals(UNMARKED_TYPE_MESSAGE)) {
            ui.show(UNMARKED_MESSAGE);
            tsk.setIsDone(false);
        }
        ui.printTaskMsg(tsk.toString());

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