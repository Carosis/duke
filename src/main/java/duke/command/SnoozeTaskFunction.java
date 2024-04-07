/**
 * Represents a command to snooze a task in the task list by a specified duration.
 * This class is part of the command pattern implementation.
 * It encapsulates the functionality needed to snooze a task.
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

import java.time.LocalDateTime;

/**
 * Represents a command for snoozing a task in the task list.
 * This command is executed when the user wants to snooze a task by a specified duration.
 */
public class SnoozeTaskFunction extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();
    private final String INVALID_INPUT_MESSAGE = "Invalid input meow!";
    private final String INVALID_TASK_TYPE_MESSAGE = "Invalid tasks type for snoozing";

    /**
     * Constructs a SnoozeTaskFunction command with the specified user inputs.
     *
     * @param userInput The user inputs, including task index and snooze duration.
     */
    public SnoozeTaskFunction(String[] userInput) {
        this.userInputs = userInput;
    }

    /**
     * Executes the snooze task command.
     *
     * @param taskList The task list containing the task to be snoozed.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for saving task data.
     * @throws DukeException If there is an error snoozing the task.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Tasks task = taskList.storedTaskList.get(Integer.parseInt(userInputs[0]) - 1);
        String taskType = task.getTaskType();
        String[] parseSnoozeInfo = userInputs[1].split(" ");

        String[] transferDate = snoozeType(taskType, task);
        String newDT;
        switch (parseSnoozeInfo[1]) {
            case "hrs":
                newDT = addHours(parseSnoozeInfo[0], transferDate[1]);
                break;
            case "days":
                newDT = addDays(parseSnoozeInfo[0], transferDate[1]);
                break;
            case "mins":
                newDT = addMinutes(parseSnoozeInfo[0], transferDate[1]);
                break;
            default:
                throw new DukeException(INVALID_INPUT_MESSAGE);
        }
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(new String[]{userInputs[0], transferDate[0], newDT});
        updateTaskFunction.execute(taskList, ui, storage);
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as snoozing a task is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the specified number of minutes to the given date and time.
     *
     * @param minutes The number of minutes to add.
     * @param toAdd   The date and time to which minutes are added.
     * @return The updated date and time after adding minutes.
     * @throws DukeException If there is an error parsing the date and time.
     */
    private String addMinutes(String minutes, String toAdd) throws DukeException {
        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(minutes));
        return inputParser.formatOutput(temp);
    }

    /**
     * Adds the specified number of hours to the given date and time.
     *
     * @param hours The number of hours to add.
     * @param toAdd The date and time to which hours are added.
     * @return The updated date and time after adding hours.
     * @throws DukeException If there is an error parsing the date and time.
     */
    private String addHours(String hours, String toAdd) throws DukeException {

        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(hours));
        return inputParser.formatOutput(temp);
    }

    /**
     * Adds the specified number of days to the given date and time.
     *
     * @param days  The number of days to add.
     * @param toAdd The date and time to which days are added.
     * @return The updated date and time after adding days.
     * @throws DukeException If there is an error parsing the date and time.
     */
    private String addDays(String days, String toAdd) throws DukeException {

        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusDays(Integer.parseInt(days));
        return inputParser.formatOutput(temp);
    }

    /**
     * Determines the type of task and retrieves relevant information for snoozing.
     *
     * @param taskType The type of the task (E for EventTask, D for DeadlineTask, B for DoWithInTimeTask).
     * @param tsk      The task for which snooze information is needed.
     * @return An array containing snooze information based on the task type.
     * @throws DukeException If the task type is invalid or does not match the actual task type.
     */
    private String[] snoozeType(String taskType, Tasks tsk) throws DukeException {
        switch (taskType) {
            case "E":
                if (tsk instanceof EventTask) {
                    EventTask tempE = (EventTask) tsk;
                    return new String[]{"to", tempE.getTo()};
                }
                break;
            case "D":
                if (tsk instanceof DeadlineTask) {
                    DeadlineTask tempD = (DeadlineTask) tsk;
                    return new String[]{"by", tempD.getBy()};
                }
                break;
            case "B":
                if (tsk instanceof DoWithInTimeTask) {
                    DoWithInTimeTask tempB = (DoWithInTimeTask) tsk;
                    return new String[]{"between", tempB.getTo()};
                }
                break;
        }
        throw new DukeException(INVALID_TASK_TYPE_MESSAGE);
    }
}

