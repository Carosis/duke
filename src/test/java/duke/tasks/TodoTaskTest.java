package duke.tasks;

import duke.utility.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void testToString() {
        String description = "Buy milk";
        boolean isDone = false;

        TodoTask todoTask = new TodoTask(description, isDone);

        String expectedString = "["+todoTask.getTaskType() +"]"+"["+todoTask.getStatusIcon()+"] "
                +todoTask.getDescription();

        assertEquals(expectedString, todoTask.toString());
    }

    @Test
    public void testIsDone() {
        String description = "Read book";
        boolean isDone = true;

        TodoTask todoTask = new TodoTask(description, isDone);

        assertEquals(isDone, todoTask.isDone());
    }

    @Test
    public void testSetIsDone() throws DukeException {
        String description = "Clean room";
        boolean isDone = false;

        TodoTask todoTask = new TodoTask(description, isDone);

        todoTask.setIsDone(true);

        assertEquals(true, todoTask.isDone());
    }
}
