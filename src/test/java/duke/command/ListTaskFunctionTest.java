package duke.command;

import duke.tasks.Tasks;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTaskFunctionTest {

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Tasks("Task 1"));
        taskList.addTask(new Tasks("Task 2"));
        ui = new UI();
        storage = new Storage("test.txt");
    }

    @Test
    public void testExecute() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ListTaskFunction listTaskFunction = new ListTaskFunction();
        listTaskFunction.execute(taskList, ui, storage);

        String expectedOutput = "Meow! Here are the magics pending in your list:" + System.lineSeparator()
                + "1" + taskList.getTask(1).toString() + System.lineSeparator()
                + "2" + taskList.getTask(2).toString() + System.lineSeparator()
                + "Now you have 2 tasks in the Magic Book! Meow!" + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());

    }

}
