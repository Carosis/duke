package Duke.Command;

import Duke.Tasks.*;
import Duke.Utility.*;

public class UpdateTask extends Command {
    private final String[] userInputs;
    private final InputParser inputParser = new InputParser();

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
                    ui.show("update");
                    ui.printTaskMsg(tsk.toString());
                } catch (ClassCastException e) {
                    System.err.println("Invalid description meow!");
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
                    ui.show("update");
                    ui.printTaskMsg(tsk.toString());
                }catch (ClassCastException | DukeException e) {
                    System.err.println("Invalid update event to meow!");
                }
                break;
            case "to":
                try {
                    EventTask temp = (EventTask) tsk;
                    String tempTo = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
                    temp.setTo(tempTo);
                    ui.show("update");
                    ui.printTaskMsg(tsk.toString());
                } catch (ClassCastException | DukeException e) {
                    System.err.println("Invalid update event to meow!");
                }
                break;
            case "by":
                try {
                    DeadlineTask deadlineTask = (DeadlineTask) tsk;
                    System.out.println("userInputs[2]: " + userInputs[2]);
                    String tempBy = inputParser.formatOutput(inputParser.parseDate(userInputs[2]));
                    deadlineTask.setBy(tempBy);
                    ui.show("update");
                    ui.printTaskMsg(tsk.toString());
                } catch (ClassCastException | DukeException e) {
                    System.err.println("Invalid update deadline to meow!");
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
                    ui.show("update");
                    ui.printTaskMsg(tsk.toString());
                }catch (ClassCastException | DukeException e) {
                    System.err.println("Invalid update event to meow!");
                }
                break;
            default:
                System.err.println("Invalid input meow!");
                ui.show("line");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}