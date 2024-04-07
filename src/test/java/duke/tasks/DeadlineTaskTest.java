package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void testGetBy() {
        String description = "Finish assignment";
        boolean isDone = false;
        String by = "2024-04-30";

        DeadlineTask deadlineTask = new DeadlineTask(description, isDone, by);

        assertEquals(by, deadlineTask.getBy());
    }

    @Test
    public void testSetBy() {
        String description = "Buy groceries";
        boolean isDone = false;
        String by = "2024-05-10";

        DeadlineTask deadlineTask = new DeadlineTask(description, isDone, "2024-04-30");

        deadlineTask.setBy(by);

        assertEquals(by, deadlineTask.getBy());
    }

    @Test
    public void testToString() {
        String description = "Submit report";
        boolean isDone = true;
        String by = "2024-04-15";

        DeadlineTask deadlineTask = new DeadlineTask(description, isDone, by);

        String expectedString = "["+deadlineTask.getTaskType() +"]"+"["+deadlineTask.getStatusIcon()+"] "
        +deadlineTask.getDescription() +  " (by: "+deadlineTask.getBy() +")";

        assertEquals(expectedString, deadlineTask.toString());
    }
}
