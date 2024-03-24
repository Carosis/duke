package Duke.Command;

import Duke.Utility.*;

public class printBye extends Command {
    public void execute(TaskList tskList, UI ui, Storage store) {
        ui.show("goodbye");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}