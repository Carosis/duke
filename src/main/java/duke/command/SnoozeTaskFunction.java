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

public class SnoozeTaskFunction extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();
    private final String INVALID_INPUT_MESSAGE = "Invalid input meow!";
    private final String INVALID_TASK_TYPE_MESSAGE = "Invalid tasks type for snoozing";


    public SnoozeTaskFunction(String[] UserInput) {
        this.userInputs = UserInput;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }

    private String addMinutes(String minutes, String toAdd) throws DukeException {
        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(minutes));
        return inputParser.formatOutput(temp);
    }

    private String addHours(String hrs, String toAdd) throws DukeException {

        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(hrs));
        return inputParser.formatOutput(temp);
    }

    private String addDays(String days, String toAdd) throws DukeException {

        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusDays(Integer.parseInt(days));
        return inputParser.formatOutput(temp);
    }

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
                    return new String[]{"between", tempB.getTaskAnd()};
                }
                break;
        }
        throw new DukeException(INVALID_TASK_TYPE_MESSAGE);

    }
}

