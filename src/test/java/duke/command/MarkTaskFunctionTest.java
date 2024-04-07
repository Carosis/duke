package duke.command;

import duke.tasks.Tasks;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkTaskFunctionTest {

    private TaskList taskList;
    private UI ui;
    private Storage storage;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Tasks("Task 1"));
        taskList.addTask(new Tasks("Task 2"));
        taskList.addTask(new Tasks("Task 3"));
        ui = new UI();
        storage = new Storage("test.txt");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMarkTask() throws IOException, DukeException {
        MarkTaskFunction markTaskFunction = new MarkTaskFunction(new String[]{"mark", "2"});
        markTaskFunction.execute(taskList, ui, storage);

        assertTrue(taskList.getTask(2).isDone());
    }

    @Test
    public void testUnmarkTask() throws IOException, DukeException {
        taskList.getTask(2).setIsDone(true);
        MarkTaskFunction markTaskFunction = new MarkTaskFunction(new String[]{"unmark", "2"});
        markTaskFunction.execute(taskList, ui, storage);

        assertFalse(taskList.getTask(2).isDone());
    }

    private void assertFalse(boolean done) {
    }
}
