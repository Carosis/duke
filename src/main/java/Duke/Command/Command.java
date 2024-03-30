
package Duke.Command;

import Duke.Utility.DukeException;
import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}