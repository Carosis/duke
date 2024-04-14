/**
 * Represents a command to update a task in the task list with new information.
 * This class is part of the command pattern implementation.
 * It encapsulates the functionality needed to update different aspects of a task.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.command;

import duke.tasks.DeadlineTask;
import duke.tasks.DoWithInTimeTask;
import duke.tasks.EventTask;
import duke.tasks.Tasks;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
import duke.utility.InputParser;

/**
 * Represents a command for updating a task in the task list.
 * This command allows updating various properties of a task such as description, dates, etc.
 */
public class UpdateTaskFunction extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();
    private final String UPDATE_MESSAGE = "update";
    private final String INVALID_INPUT_MESSAGE = "Invalid input meow!";

    /**
     * Constructs an UpdateTaskFunction command with the specified user inputs.
     *
     * @param userInput The user inputs, including task index and update information.
     */
    public UpdateTaskFunction(String[] userInput) {
        this.userInputs = userInput;
    }

    /**
     * Executes the update task command.
     *
     * @param tskList The task list containing the task to be updated.
     * @param ui      The user interface for displaying messages.
     * @param store   The storage component for saving task data.
     */
    public void execute(TaskList tskList, UI ui, Storage store) throws DukeException {
        try {
            Tasks tsk = tskList.storedTaskList.get(Integer.parseInt(userInputs[0]) - 1);
            String LINE_SEPARATOR = "line";
            switch (userInputs[1]) {
                case "description":
                    updateDescription(tsk, ui);
                    break;
                case "from":
                    updateEventFrom(tsk, ui);
                    break;
                case "to":
                    updateEventTo(tsk, ui);
                    break;
                case "by":
                    updateDeadlineBy(tsk, ui);
                    break;
                case "between":
                    updateEDoWithInTimeBetween(tsk, ui);
                    break;
                default:
                    System.err.println(INVALID_INPUT_MESSAGE);
                    ui.show(LINE_SEPARATOR);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Meow!!! The task ID invalid.");
        }

    }

    /**
     * Updates the description of the specified task.
     *
     * @param task The task to update.
     * @param ui   The user interface for displaying messages.
     */
    private void updateDescription(Tasks task, UI ui) {
        try {
            task.setDescription(userInputs[2]);
            ui.show(UPDATE_MESSAGE);
            ui.printTaskMsg(task.toString());
        } catch (ClassCastException e) {
            String INVALID_DESCRIPTION_MESSAGE = "Invalid description meow!";
            System.err.println(INVALID_DESCRIPTION_MESSAGE);
        }
    }

    /**
     * Updates the start time of an event task.
     *
     * @param task The event task to update.
     * @param ui   The user interface for displaying messages.
     */
    private void updateEventFrom(Tasks task, UI ui) {
        try {
            EventTask eventTask = (EventTask) task;
            String tempFrom = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
            eventTask.setFrom(tempFrom);
            if (userInputs.length >= 4) {
                String tempTo = inputParser.formatOutput(inputParser.parseDate(userInputs[3]));
                eventTask.setTo(tempTo);
            }
            ui.show(UPDATE_MESSAGE);
            ui.printTaskMsg(task.toString());
        } catch (ClassCastException | DukeException e) {
            String INVALID_UPDATE_EVENT_FROM_MESSAGE = "Invalid update event from meow!";
            System.err.println(INVALID_UPDATE_EVENT_FROM_MESSAGE);
        }
    }

    /**
     * Updates the end time of an event task.
     *
     * @param task The event task to update.
     * @param ui   The user interface for displaying messages.
     */
    private void updateEventTo(Tasks task, UI ui) {
        try {
            EventTask temp = (EventTask) task;
            String tempTo = inputParser.formatOutput(inputParser.parseDate(userInputs[2].trim()));
            temp.setTo(tempTo);
            ui.show(UPDATE_MESSAGE);
            ui.printTaskMsg(task.toString());
        } catch (ClassCastException | DukeException e) {
            String INVALID_UPDATE_EVENT_TO_MESSAGE = "Invalid update event to meow!";
            System.err.println(INVALID_UPDATE_EVENT_TO_MESSAGE);
        }
    }

    /**
     * Updates the deadline of a task.
     *
     * @param task The deadline task to update.
     * @param ui   The user interface for displaying messages.
     */
    private void updateDeadlineBy(Tasks task, UI ui) {
        try {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            String tempBy = inputParser.formatOutput(inputParser.parseDate(userInputs[2].trim()));
            deadlineTask.setBy(tempBy);
            ui.show(UPDATE_MESSAGE);
            ui.printTaskMsg(task.toString());
        } catch (ClassCastException | DukeException e) {
            String INVALID_UPDATE_DEADLINE_BY_MESSAGE = "Invalid update deadline by meow!";
            System.err.println(INVALID_UPDATE_DEADLINE_BY_MESSAGE);
        }
    }

    /**
     * Updates the period of a task.
     *
     * @param task The deadline task to update.
     * @param ui   The user interface for displaying messages.
     */
    private void updateEDoWithInTimeBetween(Tasks task, UI ui) {

        try {
            EventTask temp = (DoWithInTimeTask) task;
            String tempStart = inputParser.formatOutput(inputParser.parseDate(userInputs[2].trim()));
            temp.setFrom(tempStart);
            if (userInputs.length >= 4) {
                String tempEnd = inputParser.formatOutput(inputParser.parseDate(userInputs[3].trim()));
                temp.setTo(tempEnd);
            }
            ui.show(UPDATE_MESSAGE);
            ui.printTaskMsg(task.toString());
        } catch (ClassCastException | DukeException e) {
            String INVALID_UPDATE_BETWEEN_MESSAGE = "Invalid update between meow!";
            System.err.println(INVALID_UPDATE_BETWEEN_MESSAGE);
        }
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as updating a task is not an exit command.
     */

    @Override
    public boolean isExit() {
        return false;
    }
}