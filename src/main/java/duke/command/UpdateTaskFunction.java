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


public class UpdateTaskFunction extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();
    private final String UPDATE_MESSAGE = "update";
    private final String INVALID_INPUT_MESSAGE = "Invalid input meow!";

    public UpdateTaskFunction(String[] UserInput) {
        this.userInputs = UserInput;
    }

    public void execute(TaskList tskList, UI ui, Storage store) {

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

    }
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
    private void updateEventFrom(Tasks task, UI ui){
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
    private void updateEventTo(Tasks task, UI ui){
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
    private void updateDeadlineBy(Tasks task, UI ui){
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
    private void updateEDoWithInTimeBetween(Tasks task, UI ui){

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
        }catch (ClassCastException | DukeException e) {
            String INVALID_UPDATE_BETWEEN_MESSAGE = "Invalid update between meow!";
            System.err.println(INVALID_UPDATE_BETWEEN_MESSAGE);
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}