/**
 * Represents a command to list all tasks in the task list.
 * This class is part of the command pattern implementation.
 * It encapsulates the functionality needed to display all tasks in the task list.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.command;

import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;

import java.io.IOException;

/**
 * Represents a command for listing all tasks in the task list.
 * This command is executed when the user wants to see all tasks in the task list.
 */
public class ListTaskFunction extends Command {
    private final String LIST_MESSAGE = "list";
    private final String ERROR_MESSAGE_PREFIX = "Failed to save the incantation meow! : ";
    /**
     * Executes the list task command.
     *
     * @param tskList The task list to be listed.
     * @param ui       The user interface for displaying messages.
     * @param store    The storage component for saving task data.
     */
    public void execute(TaskList tskList, UI ui, Storage store) {

        ui.show(LIST_MESSAGE);
        ui.printTaskList(tskList);

        try {
            store.save(tskList.getAllTasks());
            ui.printNumberOfTask(tskList);
        } catch (IOException e) {
            ui.showError(ERROR_MESSAGE_PREFIX+ e.getMessage());
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