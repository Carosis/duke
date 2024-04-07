package duke.utility;

import duke.tasks.TodoTask;
import duke.tasks.Tasks;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.DoWithInTimeTask;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void testLoadAndSave() {
        Storage storage = new Storage("src/main/java/testStorage.txt");
        ArrayList<Tasks> tasksToSave = new ArrayList<Tasks>();
        tasksToSave.add(new TodoTask("Task 1", false));
        tasksToSave.add(new DeadlineTask("Task 2", false, "2024-04-10 12:00"));
        tasksToSave.add(new EventTask("Task 3", false, "2024-04-11 14:00", "2024-04-11 16:00"));
        tasksToSave.add(new DoWithInTimeTask("Task 4", false, "2024-04-12 10:00", "2024-04-12 12:00"));

        try {
            System.out.println("File Path: " + storage.FilePath);
            for (Tasks task : tasksToSave) {
                System.out.println(task);
            }
            storage.save(tasksToSave);
            ArrayList<Tasks> loadedTasks = storage.load();

            System.out.println("Loaded Tasks:");
            for (Tasks task : loadedTasks) {
                System.out.println(task);
            }

            assertEquals(tasksToSave.size(), loadedTasks.size());

            for (int i = 0; i < tasksToSave.size(); i++) {
                assertEquals(tasksToSave.get(i).toString(), loadedTasks.get(i).toString());
            }
        } catch (IOException | DukeException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testLoadNonExistentFile() {
        Storage storage = new Storage("nonexistent.txt");
        try {
            storage.load();
        } catch (DukeException e) {
            assertEquals("YumiKunKun failed to find the magic file meow!", e.getMessage());
        }
    }

    @Test
    public void testSaveIOException() {
        Storage storage = new Storage("invalid/path/test.txt");
        ArrayList<Tasks> tasksToSave = new ArrayList<>();
        tasksToSave.add(new TodoTask("Task 1", false));

        try {
            storage.save(tasksToSave);
            fail("Expected IOException");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Unable to create the magic book"));
        }
    }
}
