package Duke.Command;

import Duke.Tasks.*;
import Duke.Utility.*;

import java.time.*;

public class SnoozeTask extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();

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
            default -> throw new DukeException("Invalid input meow!");
        };
        UpdateTask updateTask = new UpdateTask(new String[]{userInputs[0], transferDate[0], newDT});
        updateTask.execute(taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String addMinutes(String mins,String toAdd) throws DukeException {
        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(mins));
        return inputParser.formatOutput(temp);
    }
    private String addHours(String hrs,String toAdd) throws DukeException {

        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusMinutes(Integer.parseInt(hrs));
        return inputParser.formatOutput(temp);
    }
    private String addDays(String days,String toAdd) throws DukeException {

        LocalDateTime temp = inputParser.parseDate(toAdd);
        temp = temp.plusDays(Integer.parseInt(days));
        return inputParser.formatOutput(temp);
    }
    private String[] snoozeType(String taskType, Tasks tsk) throws DukeException {
        switch (taskType) {
            case "E":
                if (tsk instanceof EventTask tempE) {
                    String snoozeEDT = tempE.getTo();
                    return new String[]{"to", snoozeEDT};
                } else {
                    throw new DukeException("Invalid task type for snoozing");
                }
            case "D":
                if (tsk instanceof DeadlineTask tempD) {
                    String snoozeDDT = tempD.getBy();
                    return new String[]{"by", snoozeDDT};
                } else {
                    throw new DukeException("Invalid task type for snoozing");
                }
            case "B":
                if (tsk instanceof DoWithInTimeTask tempB) {
                    String snoozeBDT = tempB.getTaskAnd();
                    return new String[]{"between", snoozeBDT};
                } else {
                    throw new DukeException("Invalid task type for snoozing");
                }
            default:
                throw new DukeException("Invalid task type for snoozing");
        }
    }


}