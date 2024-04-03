package Duke.Command;

import Duke.Tasks.DeadlineTask;
import Duke.Tasks.DoWithInTimeTask;
import Duke.Tasks.EventTask;
import Duke.Tasks.Tasks;
import Duke.Utility.DukeException;
import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;
import Duke.Utility.InputParser;

import java.time.*;

public class SnoozeTask extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();
    private final String INVALID_INPUT_MESSAGE = "Invalid input meow!";
    private final String INVALID_TASK_TYPE_MESSAGE = "Invalid task type for snoozing";


    public SnoozeTask(String[] UserInput) {
        this.userInputs = UserInput;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Tasks task = taskList.storedTaskList.get(Integer.parseInt(userInputs[0]) - 1);
        String taskType = task.getTaskType();
        String[] parseSnoozeInfo = userInputs[1].split(" ");

        String[] transferDate = snoozeType(taskType, task);
        String newDT = switch (parseSnoozeInfo[1]) {
            case "hrs" -> addHours(parseSnoozeInfo[0], transferDate[1]);
            case "days" -> addDays(parseSnoozeInfo[0], transferDate[1]);
            case "mins" -> addMinutes(parseSnoozeInfo[0], transferDate[1]);
            default -> throw new DukeException(INVALID_INPUT_MESSAGE);
        };
        UpdateTask updateTask = new UpdateTask(new String[]{userInputs[0], transferDate[0], newDT});
        updateTask.execute(taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String addMinutes(String mins, String toAdd) throws DukeException {
        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(mins));
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
                if (tsk instanceof EventTask tempE) {
                    return new String[]{"to", tempE.getTo()};
                }
                break;
            case "D":
                if (tsk instanceof DeadlineTask tempD) {
                    return new String[]{"by", tempD.getBy()};
                }
                break;
            case "B":
                if (tsk instanceof DoWithInTimeTask tempB) {
                    return new String[]{"between", tempB.getTaskAnd()};
                }
                break;
        }
        throw new DukeException(INVALID_TASK_TYPE_MESSAGE);

    }
}

