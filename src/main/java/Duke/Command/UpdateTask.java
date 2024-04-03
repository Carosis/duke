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


public class UpdateTask extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();
    private final String UPDATE_MESSAGE = "update";
    private final String INVALID_DESCRIPTION_MESSAGE = "Invalid description meow!";
    private final String INVALID_UPDATE_EVENT_TO_MESSAGE = "Invalid update event to meow!";
    private final String INVALID_UPDATE_DEADLINE_TO_MESSAGE = "Invalid update deadline to meow!";
    private final String INVALID_INPUT_MESSAGE = "Invalid input meow!";
    private final String LINE_SEPARATOR = "line";
    public UpdateTask(String[] UserInput) {
        this.userInputs = UserInput;
    }

    public void execute(TaskList tskList, UI ui, Storage store) {

        Tasks tsk = tskList.storedTaskList.get(Integer.parseInt(userInputs[0]) - 1);
        System.out.println(userInputs[1]);
        switch (userInputs[1]) {
            case "description":
                try {
                    tsk.setDescription(userInputs[2]);
                    ui.show(UPDATE_MESSAGE);
                    ui.printTaskMsg(tsk.toString());
                } catch (ClassCastException e) {
                    System.err.println(INVALID_DESCRIPTION_MESSAGE);
                }
                break;
            case "from":
                try {
                    EventTask temp = (EventTask) tsk;
                    String tempFrom = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
                    temp.setFrom(tempFrom);
                    if (userInputs.length >= 4) {
                        String tempTo = inputParser.formatOutput(inputParser.parseDate(userInputs[3]));
                        temp.setTo(tempTo);
                    }
                    ui.show(UPDATE_MESSAGE);
                    ui.printTaskMsg(tsk.toString());
                }catch (ClassCastException | DukeException e) {
                    System.err.println(INVALID_UPDATE_EVENT_TO_MESSAGE);
                }
                break;
            case "to":
                try {
                    EventTask temp = (EventTask) tsk;
                    String tempTo = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
                    temp.setTo(tempTo);
                    ui.show(UPDATE_MESSAGE);
                    ui.printTaskMsg(tsk.toString());
                } catch (ClassCastException | DukeException e) {
                    System.err.println(INVALID_UPDATE_DEADLINE_TO_MESSAGE);
                }
                break;
            case "by":
                try {
                    DeadlineTask deadlineTask = (DeadlineTask) tsk;
                    String tempBy = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
                    deadlineTask.setBy(tempBy);
                    ui.show(UPDATE_MESSAGE);
                    ui.printTaskMsg(tsk.toString());
                } catch (ClassCastException | DukeException e) {
                    System.err.println(INVALID_INPUT_MESSAGE);
                }
                break;
            case "between":
                try {
                    EventTask temp = (DoWithInTimeTask) tsk;
                    String tempStart = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
                    temp.setFrom(tempStart);
                    if (userInputs.length >= 4) {
                        String tempEnd = inputParser.formatOutput(inputParser.parseDate(userInputs[3]));
                        temp.setTo(tempEnd);
                    }
                    ui.show(UPDATE_MESSAGE);
                    ui.printTaskMsg(tsk.toString());
                }catch (ClassCastException | DukeException e) {
                    System.err.println(INVALID_INPUT_MESSAGE);
                }
                break;
            default:
                System.err.println(INVALID_INPUT_MESSAGE);
                ui.show(LINE_SEPARATOR);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}