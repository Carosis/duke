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
    private final String NO_TASKS_FOUND_MESSAGE = "Meow? Nothing found meow!";
    private final String SEARCH_MESSAGE = "search";
    private final String LINE_SEPARATOR = "line";
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
            throw new DukeException(NO_TASKS_FOUND_MESSAGE);
        } else {
            ui.show(SEARCH_MESSAGE);
            for (Tasks t : foundTasks) {
                tskList.printTask(t);
            }
        }
        ui.show(LINE_SEPARATOR);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}