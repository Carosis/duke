package Duke.Command;

import Duke.Tasks.*;
import Duke.Utility.DukeException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddTaskTest {

    static Tasks tsk;

    @BeforeAll
    public static void setUp() {
        tsk = new Tasks("TaskTest");
    }


    @Test
    public void testCreation() {
        assertEquals("TaskTest", tsk.getDescription(), "Description not matched");
        assertFalse(tsk.isDone(), "New task should be undone upon creation");
        assertEquals(" ", tsk.getTaskType(), "New task should be empty string at first ");
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
