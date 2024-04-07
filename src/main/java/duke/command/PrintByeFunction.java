package duke.command;

import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;

public class PrintByeFunction extends Command {
    private final String GOODBYE_MESSAGE = "goodbye";
    public void execute(TaskList tskList, UI ui, Storage store) {
        ui.show(GOODBYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}