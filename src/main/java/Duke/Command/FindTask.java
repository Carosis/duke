package Duke.Command;

import Duke.Utility.DukeException;
import Duke.Utility.Storage;
import Duke.Utility.UI;
import Duke.Utility.TaskList;
import Duke.Tasks.Tasks;

import java.util.ArrayList;
import java.util.List;


public class FindTask extends Command {
    private final String keyword;

    public FindTask(String UserInput) {
        this.keyword = UserInput;
    }

    public void execute(TaskList tskList, UI ui, Storage store) throws DukeException {


        ArrayList<Tasks> input = tskList.getAllTasks();
        List<Tasks> foundTasks = new ArrayList<>();
        for (Tasks t : input) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(t);
            }
        }
        if (foundTasks.isEmpty()) {
            throw new DukeException("Meow? Nothing found meow!");
        } else {
            ui.show("search");
            for (Tasks t : foundTasks) {
                tskList.printTask(t);
            }
        }
        ui.show("line");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}