
package Duke.Command;

import Duke.Utility.*;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}