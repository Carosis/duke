/**
 * Represents a command to find tasks containing a specific keyword in the task list.
 * This class is part of the command pattern implementation.
 * It encapsulates the functionality needed to search for tasks based on a keyword.
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
import duke.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command for finding tasks containing a specific keyword in the task list.
 * This command is executed when the user wants to search for tasks based on a keyword.
 */
public class FindTaskFunction extends Command {
    private final String keyword;
    private final String NO_TASKS_FOUND_MESSAGE = "Meow? Nothing found meow!";
    private final String SEARCH_MESSAGE = "search";
    private final String LINE_SEPARATOR = "line";
    /**
     * Constructs a FindTaskFunction command with the specified keyword.
     *
     * @param userInput The keyword to search for in the task descriptions.
     */
    public FindTaskFunction(String userInput) {
        this.keyword = userInput;
    }
    /**
     * Executes the find task command.
     *
     * @param tskList The task list to search for tasks.
     * @param ui       The user interface for displaying messages.
     * @param store    The storage component for saving task data.
     * @throws DukeException If no tasks are found matching the keyword.
     */
    public void execute(TaskList tskList, UI ui, Storage store) throws DukeException {


        ArrayList<Tasks> input = tskList.getAllTasks();
        List<Tasks> foundTasks = new ArrayList<>();
        for (Tasks t : input) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(t);
            }
        }
        if (foundTasks.isEmpty()) {
            throw new DukeException(NO_TASKS_FOUND_MESSAGE);
        } else {
            ui.show(SEARCH_MESSAGE);
            for (Tasks t : foundTasks) {
                tskList.printTask(t);
            }
        }
        ui.show(LINE_SEPARATOR);

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