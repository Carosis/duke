package duke.command;

import duke.tasks.Tasks;
import duke.utility.Storage;
import duke.utility.UI;
import duke.utility.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTaskFunctionTest {

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Tasks("Task 1"));
        ui = new UI();
        storage = new Storage("test.txt");
    }

    @Test
    public void testExecute() throws IOException {
        int tasksBeforeDeletion = taskList.getTaskAmount(taskList.getAllTasks());

        DeleteTaskFunction deleteTaskFunction = new DeleteTaskFunction(1);
        deleteTaskFunction.execute(taskList, ui, storage);

        int tasksAfterDeletion = taskList.getTaskAmount(taskList.getAllTasks());

        assertEquals(tasksBeforeDeletion - 1, tasksAfterDeletion);
    }
}