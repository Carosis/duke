package duke.command;

import duke.tasks.DeadlineTask;
import duke.tasks.DoWithInTimeTask;
import duke.tasks.EventTask;
import duke.tasks.Tasks;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTaskFunctionTest {

    private TaskList taskList;
    private UI ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Tasks("Task 1"));
        taskList.addTask(new EventTask("Task 2", false, "07-04-2024 16:00", "08-04-2024 16:00"));
        taskList.addTask(new DeadlineTask("Task 3", false, "07-04-2024 16:00"));
        taskList.addTask(new DoWithInTimeTask("Task 4", false, "07-04-2024 16:00", "08-04-2024 16:00"));
        ui = new UI();
        storage = new Storage("test.txt");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testUpdateDescription() {
        String[] userInput = {"1", "description", "Updated Description"};
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(userInput);
        updateTaskFunction.execute(taskList, ui, storage);
        assertEquals( "Updated Description",taskList.getTask(1).getDescription());
    }

    @Test
    public void testUpdateFrom() {
        String[] userInput = {"2", "from", "10-10-2024 00:00"};
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(userInput);
        updateTaskFunction.execute(taskList, ui, storage);
        String updatedFrom = ((EventTask) taskList.getTask(2)).getFrom();
        assertEquals("10-10-2024 00:00", updatedFrom);
    }

    @Test
    public void testUpdateTo() {
        String[] userInput = {"2", "to", "10-10-2024 00:00"};
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(userInput);
        updateTaskFunction.execute(taskList, ui, storage);
        String updatedTo = ((EventTask) taskList.getTask(2)).getTo();
        assertEquals("10-10-2024 00:00", updatedTo);
    }

    @Test
    public void testUpdateFromTo() {
        String[] userInput = {"2", "from", "10-10-2024 00:00", "10-11-2024 00:00"};
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(userInput);
        updateTaskFunction.execute(taskList, ui, storage);
        String updatedFrom = ((EventTask) taskList.getTask(2)).getFrom();
        assertEquals("10-10-2024 00:00", updatedFrom);
        String updatedTo = ((EventTask) taskList.getTask(2)).getTo();
        assertEquals("10-11-2024 00:00", updatedTo);
    }

    @Test
    public void testUpdateBy() {
        String[] userInput = {"3", "by", "10-10-2024 00:00"};
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(userInput);
        updateTaskFunction.execute(taskList, ui, storage);
        String updatedBy = ((DeadlineTask) taskList.getTask(3)).getBy();
        assertEquals(updatedBy, "10-10-2024 00:00");
    }

    @Test
    public void testUpdateBetween() {
        String[] userInput = {"4", "between", "10-10-2024 00:00", "10-11-2024 00:00"};
        UpdateTaskFunction updateTaskFunction = new UpdateTaskFunction(userInput);
        updateTaskFunction.execute(taskList, ui, storage);
        String updatedFrom = ((DoWithInTimeTask) taskList.getTask(4)).getFrom();
        String updatedTo = ((DoWithInTimeTask) taskList.getTask(4)).getTo();
        assertEquals("10-10-2024 00:00", updatedFrom);
        assertEquals("10-11-2024 00:00", updatedTo);
    }
}
