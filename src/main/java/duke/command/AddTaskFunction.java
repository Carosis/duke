/**
 * Represents a command to add a task to the task list.
 * This class is part of the command pattern implementation.
 * It encapsulates all the information needed to add a new task.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.command;

import duke.tasks.Tasks;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.UI;

import java.io.IOException;
/**
 * Represents a command for adding a task to the task list.
 * This command is executed when the user wants to add a new task to the task list.
 */
public class AddTaskFunction extends Command {
    private final Tasks tsk;

    private final String SUCCESS_MESSAGE = "added";
    private final String ERROR_MESSAGE_PREFIX = "Failed to save the incantation meow! : ";

    /**
     * Constructs an AddTaskFunction command with the specified task.
     *
     * @param tsk The task to be added to the task list.
     */
    public AddTaskFunction(Tasks tsk) {
        this.tsk=tsk;
    }
    /**
     * Executes the add task command.
     *
     * @param tskList The task list to which the task will be added.
     * @param ui       The user interface for displaying messages.
     * @param store    The storage component for saving task data.
     */
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
    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as adding a task is not an exit command.
     */

    @Override
    public boolean isExit() {
        return false;
    }
}