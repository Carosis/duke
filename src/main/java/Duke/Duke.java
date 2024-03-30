package Duke;

import Duke.Command.Command;
import Duke.Tasks.Tasks;

import Duke.Utility.InputParser;
import Duke.Utility.Storage;
import Duke.Utility.TaskList;
import Duke.Utility.UI;
import Duke.Utility.DukeException;

public class Duke extends Tasks {
    private final Storage storage;
    private TaskList taskList;
    private final UI ui;
    private final InputParser parser;

    public Duke(String filePath) {

        ui = new UI();
        storage = new Storage(filePath);
        parser = new InputParser();
        ui.show("logo");

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.show("fileError");
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.show("welcome");
        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.show("line");
                Command temp = parser.parse(userCommand);
                isExit = temp.isExit();
                temp.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.show("line");
            }
        }

    }

    public static void main(String[] args) throws DukeException {
        new Duke("src/main/java/duke.txt").run();
    }
}