/**
 * Represents a command to print a goodbye message and exit the program.
 * This class is part of the command pattern implementation.
 * It encapsulates the functionality needed to print a goodbye message and exit the program.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.command;

import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
/**
 * Represents a command for printing a goodbye message and exiting the program.
 * This command is executed when the user wants to end the program.
 */
public class PrintByeFunction extends Command {
    private final String GOODBYE_MESSAGE = "goodbye";

    /**
     * Executes the goodbye command.
     *
     * @param tskList The task list (not used in this command).
     * @param ui       The user interface for displaying messages.
     * @param store    The storage component (not used in this command).
     */
    public void execute(TaskList tskList, UI ui, Storage store) {
        ui.show(GOODBYE_MESSAGE);
    }
    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns true, as this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}