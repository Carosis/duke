package Duke.Command;

import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;

public class PrintBye extends Command {
    private final String GOODBYE_MESSAGE = "goodbye";
    public void execute(TaskList tskList, UI ui, Storage store) {
        ui.show(GOODBYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}