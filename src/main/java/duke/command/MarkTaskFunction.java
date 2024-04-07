/**
 * Represents a command to mark or unmark a task in the task list as done.
 * This class is part of the command pattern implementation.
 * It encapsulates the functionality needed to mark or unmark a task.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.command;

import duke.tasks.Tasks;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;

import java.io.IOException;
/**
 * Represents a command for marking or unmarking a task as done in the task list.
 * This command is executed when the user wants to mark or unmark a task as done.
 */
public class MarkTaskFunction extends Command {
    private final String[] userInputs;
    private final String MARKED_TYPE_MESSAGE = "mark";
    private final String UNMARKED_TYPE_MESSAGE = "unmark";
    private final String MARKED_MESSAGE = "marked";
    private final String UNMARKED_MESSAGE = "unmarked";
    private final String ERROR_MESSAGE_PREFIX = "Failed to remove the incantation meow! : ";
    /**
     * Constructs a MarkTaskFunction command with the specified user inputs.
     *
     * @param userInput The user inputs, including the type of action (mark/unmark) and task index.
     */
    public MarkTaskFunction(String[] userInput) {
        this.userInputs = userInput;
    }
    /**
     * Executes the mark/unmark task command.
     *
     * @param tskList The task list containing the task to be marked or unmarked.
     * @param ui       The user interface for displaying messages.
     * @param store    The storage component for saving task data.
     * @throws DukeException If there is an error marking or unmarking the task.
     */
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
    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as marking/unmarking a task is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}