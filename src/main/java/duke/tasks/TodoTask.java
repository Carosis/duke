/**
 * Represents a simple task.
 * Inherits from the Tasks class and includes information about the deadline.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.tasks;

public class TodoTask extends Tasks {
    /**
     * Constructs a TodoTask object with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */
    public TodoTask(String description, Boolean isDone) {
        this.description = description;// Sets the task description
        this.isDone = isDone;// Sets the task status
        this.taskType = TaskType.T.toString();// Sets the task type to 'T' (todo task)
    }
    /**
     * Returns a string representation of the TodoTask object.
     * Includes the task type and the superclass's toString method for the description and completion status.
     *
     * @return A string representation of the TodoTask object.
     */
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

}