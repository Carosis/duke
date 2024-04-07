/**
 * Represents an abstract command class.
 * This class is part of the command pattern implementation.
 * It encapsulates the basic execute and isExit functions for all other Commands to include.
 * Subclasses of this class must implement the execute and isExit methods as per their specific command functionalities.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.command;

import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
/**
 * Abstract class for defining command objects.
 * Commands are used to perform specific actions in the task management system.
 * Each command must implement the execute method to carry out its functionality.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks to which the command will be applied.
     * @param ui      The user interface for displaying messages or interacting with the user.
     * @param storage The storage component for saving and loading task data.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}