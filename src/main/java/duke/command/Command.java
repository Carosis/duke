
package duke.command;

import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}