/**
 * Represents a generic task.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.tasks;

import duke.utility.DukeException;
/**
 * Represents a generic task with a description, status, and type.
 */
public class Tasks {
    protected String description;// The description of the task
    protected boolean isDone;// The status of the task
    protected String taskType;// The type of the task
    /**
     * Constructs a Tasks object.
     */
    public Tasks() {
    }
    /**
     * Constructs a Tasks object with the specified description.
     *
     * @param description The description of the task.
     */
    public Tasks(String description) {
        this.description = description;// Sets the task description
        this.isDone = false;// Sets the status to false (not done)
        this.taskType = " ";// Sets the task type to an empty string initially
    }
    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the task.
     *
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Sets the completion status of the task.
     *
     * @param done The new completion status to set.
     * @throws DukeException If trying to mark a task that is already marked as done or not done.
     */
    public void setIsDone(boolean done) throws DukeException {
        if (isDone == done) {
            throw new DukeException("Meow!!! The mark almeowdy done.");
        } else {
            isDone = done;
        }
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * Sets the type of the task.
     *
     * @param taskType The new task type to set.
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    /**
     * Returns a string representation of the task, including its completion status icon and description.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * Gets the completion status icon of the task.
     *
     * @return The completion status icon ('X' for done, ' ' for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done tasks with X
    }

}