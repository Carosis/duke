/**
 * Represents a command to delete a task from the task list.
 * This class is part of the command pattern implementation.
 * It encapsulates the information needed to delete a new task.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.command;

import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
import duke.tasks.Tasks;

import java.io.IOException;
/**
 * Represents a command for deleting a task from the task list.
 * This command is executed when the user wants to delete a task from the task list.
 */
public class DeleteTaskFunction extends Command {

    private final int taskNo;
    private final String SUCCESS_MESSAGE = "deleted";
    private final String ERROR_MESSAGE_PREFIX = "Failed to remove the incantation meow!: ";

    /**
     * Constructs a DeleteTaskFunction command with the specified task number.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteTaskFunction(int index) {
        this.taskNo = index;
    }
    /**
     * Executes the delete task command.
     *
     * @param tskList The task list from which the task will be deleted.
     * @param ui       The user interface for displaying messages.
     * @param store    The storage component for saving task data.
     */
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
    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as deleting a task is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}