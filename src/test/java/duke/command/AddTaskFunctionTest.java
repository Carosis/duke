package duke.command;

import duke.tasks.Tasks;
import duke.utility.DukeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddTaskFunctionTest {

    static Tasks tsk;

    @BeforeAll
    public static void setUp() {
        tsk = new Tasks("TaskTest");
    }


    @Test
    public void testCreation() {
        assertEquals("TaskTest", tsk.getDescription(), "Description not matched");
        assertFalse(tsk.isDone(), "New tasks should be undone upon creation");
        assertEquals(" ", tsk.getTaskType(), "New tasks should be empty string at first ");
    }

    @Test
    public void testToString() {
        Tasks tsk = new Tasks("TaskTest");
        String test = tsk.toString();
        assertEquals("[ ] TaskTest", test, "toString methods is not matching");
    }

    @Test
    public void testGetStatus() throws DukeException {
        Tasks tsk = new Tasks("TaskTest");
        tsk.setIsDone(true);
        String test = tsk.getStatusIcon();
        assertEquals("X", test, "getStatus methods is not matching");
    }
}
