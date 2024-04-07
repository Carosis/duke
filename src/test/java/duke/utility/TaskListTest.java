package duke.utility;

import duke.tasks.Tasks;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Tasks task = new Tasks("Sample Task");
        taskList.addTask(task);

        ArrayList<Tasks> tasks = taskList.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Sample Task", tasks.get(0).getDescription());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Tasks task1 = new Tasks("Task 1");
        Tasks task2 = new Tasks("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(2, taskList.getTaskAmount(taskList.getAllTasks()));

        taskList.deleteTask(1);
        assertEquals(1, taskList.getTaskAmount(taskList.getAllTasks()));
        assertEquals("Task 2", taskList.getTask(1).getDescription());
    }

    @Test
    public void testPrintTaskList() {
        TaskList taskList = new TaskList();
        Tasks task1 = new Tasks("Task 1");
        Tasks task2 = new Tasks("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        ArrayList<Tasks> tasks = taskList.getAllTasks();
        assertEquals(2, tasks.size());

        taskList.printTaskList(tasks);  // This will print the task list to the console for manual verification.
    }
}
