/**
 * Represents a task with a deadline.
 * Inherits from the Tasks class and includes information about the deadline.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.tasks;

public class DeadlineTask extends Tasks {
    protected String by;// The deadline of the task

    /**
     * Constructs a DeadlineTask object with the specified description, completion status, and deadline.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param by          The deadline of the task.
     */
    public DeadlineTask(String description, boolean isDone, String by) {
        super(description);// Calls the constructor of the superclass (Tasks)
        this.isDone = isDone;// Sets the completion status
        this.by = by;// Sets the deadline
        this.taskType = TaskType.D.toString();// Sets the task type to 'D' (deadline task)
    }

    /**
     * Sets the deadline of the task.
     *
     * @param by The new deadline to set.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the DeadlineTask object.
     * Includes the task type, description, completion status, and deadline.
     *
     * @return A string representation of the DeadlineTask object.
     */
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + by + ")";
    }
}
