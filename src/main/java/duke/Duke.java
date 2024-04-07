/**
 * Represents the main class for the Duke program.
 * Manages the initialization of Duke and runs the program.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 1.0
 */

package duke;

import duke.command.Command;
import duke.tasks.Tasks;

import duke.utility.InputParser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.UI;
import duke.utility.DukeException;

public class Duke extends Tasks {
    private final Storage storage;
    private TaskList taskList;
    private final UI ui;
    private final InputParser parser;
    private final String LOGO_MESSAGE = "logo";
    private final String FILE_ERROR_MESSAGE = "fileError";
    private final String WELCOME_MESSAGE = "welcome";
    private final String LINE_SEPARATOR = "line";
    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Duke(String filePath) {

        ui = new UI();
        storage = new Storage(filePath);
        parser = new InputParser();
        ui.show(LOGO_MESSAGE);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.show(FILE_ERROR_MESSAGE);
            taskList = new TaskList();
        }
    }
    /**
     * Runs the Duke program, displaying welcome messages and handling user commands.
     */
    public void run() {
        ui.show(WELCOME_MESSAGE);
        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.show(LINE_SEPARATOR);
                Command temp = parser.parse(userCommand);
                isExit = temp.isExit();
                temp.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.show(LINE_SEPARATOR);
            }
        }

    }
    /**
     * The main method to start the Duke program.
     *
     * @param args Command-line arguments (not used in this context).
     * @throws DukeException If there are issues with Duke's initialization or running.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("src/main/java/duke.txt").run();
    }
}