package Duke.Command;

import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;

public class PrintBye extends Command {
    public void execute(TaskList tskList, UI ui, Storage store) {
        ui.show("goodbye");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}