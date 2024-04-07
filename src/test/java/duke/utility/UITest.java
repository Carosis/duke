package duke.utility;

import duke.tasks.Tasks;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {

    @Test
    public void testReadCommand() {
        UI ui = new UI();
        assertEquals("goodbye", ui.readCommand());
    }

    @Test
    public void testPrintNumberOfTask() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UI ui = new UI();
        TaskList taskList = new TaskList();
        taskList.addTask(new Tasks("Sample Task 1"));
        taskList.addTask(new Tasks("Sample Task 2"));

        ui.printNumberOfTask(taskList);

        System.setOut(System.out);

        String expectedString = "Now you have 2 tasks in the Magic Book! Meow!"+ System.lineSeparator();

        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void testPrintIndividualTask() {
        UI ui = new UI();
        Tasks task = new Tasks("Sample Task");
        assertEquals("Sample Task", task.getDescription());
    }
}
