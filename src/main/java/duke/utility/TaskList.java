/**
 * Represents a list of tasks.
 * Inherits from the Tasks class and manages operations related to tasks.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.utility;

import duke.tasks.Tasks;

import java.io.File;
import java.util.ArrayList;
/**
 * Represents a list of tasks and provides methods for managing tasks.
 * */

public class TaskList extends Tasks {
    protected String FilePath = new Storage().FilePath;
    public ArrayList<Tasks> storedTaskList = new ArrayList<>();
    /**
     * Constructs a TaskList object with the specified list of loaded tasks.
     *
     * @param loadedTasks The list of loaded tasks.
     * @throws DukeException If there is an issue with the loaded tasks.
     */
    public TaskList(ArrayList<Tasks> loadedTasks) throws DukeException {
        File f = new File(FilePath);
        this.storedTaskList = loadedTasks;
    }
    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
    }
    /**
     * Adds a task to the list of tasks.
     *
     * @param taskToAdd The task to add.
     */
    public void addTask(Tasks taskToAdd) {
        storedTaskList.add(taskToAdd);
    }
    /**
     * Gets a task from the list of tasks based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Tasks getTask(int index) {
        return storedTaskList.get(index - 1);
    }
    /**
     * Deletes a task from the list of tasks based on its index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        storedTaskList.remove(index - 1);
    }
    /**
     * Gets the number of tasks in the list.
     *
     * @param taskList The list of tasks.
     * @return The number of tasks in the list.
     */
    public int getTaskAmount(ArrayList<Tasks> taskList) {
        return taskList.size();
    }
    /**
     * Gets all tasks from the list.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Tasks> getAllTasks() {
        return storedTaskList;
    }

    /**
     * Prints the task list.
     *
     * @param input The list of tasks to print.
     */
    public void printTaskList(ArrayList<Tasks> input) {
        int i = 1;
        for (Tasks t : input) {
            System.out.println(i + t.toString());
            i++;
        }
    }
    /**
     * Prints a specific task.
     *
     * @param taskToPrint The task to print.
     */
    public void printTask(Tasks taskToPrint) {
        System.out.println(taskToPrint.toString());
    }
}
