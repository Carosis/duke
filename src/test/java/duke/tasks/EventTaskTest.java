package duke.tasks;

import duke.utility.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void testToString() {
        String description = "Birthday party";
        boolean isDone = false;
        String taskFrom = "10-10-2024 10:00";
        String taskTo = "10-10-2024 12:00";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        String expectedString = "["+eventTask.getTaskType() +"]"
                +"["+eventTask.getStatusIcon()+"] "
                + eventTask.getDescription()
                +  " (From: "+eventTask.getFrom() +")"
                +  " (To: "+eventTask.getTo() +")";
        assertEquals(expectedString, eventTask.toString());
    }

    @Test
    public void testIsDone() {
        String description = "Conference";
        boolean isDone = true;
        String taskFrom = "15-05-2024 09:00";
        String taskTo = "15-05-2024 17:00";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        assertEquals(isDone, eventTask.isDone());
    }

    @Test
    public void testSetIsDone() throws DukeException {
        String description = "Meeting";
        boolean isDone = false;
        String taskFrom = "20-07-2024 14:00";
        String taskTo = "20-07-2024 15:00";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        eventTask.setIsDone(true);

        assertEquals(true, eventTask.isDone());
    }

    @Test
    public void testGetFrom() {
        String description = "Holiday";
        boolean isDone = false;
        String taskFrom = "01-12-2024 00:00";
        String taskTo = "07-12-2024 23:59";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        assertEquals(taskFrom, eventTask.getFrom());
    }

    @Test
    public void testGetTo() {
        String description = "Vacation";
        boolean isDone = false;
        String taskFrom = "01-08-2024 08:00";
        String taskTo = "15-08-2024 18:00";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        assertEquals(taskTo, eventTask.getTo());
    }

    @Test
    public void testSetFrom() {
        String description = "Meeting";
        boolean isDone = false;
        String taskFrom = "30-06-2024 14:00";
        String taskTo = "30-06-2024 15:00";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        String newFrom = "30-06-2024 16:00";
        eventTask.setFrom(newFrom);

        assertEquals(newFrom, eventTask.getFrom());
    }

    @Test
    public void testSetTo() {
        String description = "Project deadline";
        boolean isDone = false;
        String taskFrom = "25-09-2024 09:00";
        String taskTo = "25-09-2024 17:00";

        EventTask eventTask = new EventTask(description, isDone, taskFrom, taskTo);

        String newTo = "25-09-2024 18:00";
        eventTask.setTo(newTo);

        assertEquals(newTo, eventTask.getTo());
    }
}
