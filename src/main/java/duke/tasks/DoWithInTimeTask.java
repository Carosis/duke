/**
 * Represents a task with a specific time frame.
 * Inherits from the EventTask class and includes information about the time frame.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */

package duke.tasks;

public class DoWithInTimeTask extends EventTask {
    protected String taskBetween;// The start time of the time frame
    protected String taskAnd;// The end time of the time frame
    /**
     * Constructs a DoWithInTimeTask object with the specified description, completion status, time frame start, and end times.
     *
     * @param description The description of the time frame task.
     * @param isDone      The completion status of the time frame task.
     * @param taskBetween The start time of the time frame task.
     * @param taskAnd     The end time of the time frame task.
     */
    public DoWithInTimeTask(String description, boolean isDone, String taskBetween, String taskAnd) {
        super(description, isDone, taskBetween, taskAnd);// Calls the constructor of the superclass (EventTask)
        this.taskBetween = taskBetween;// Sets the time frame start
        this.taskAnd = taskAnd;// Sets the time frame end
        this.taskType = TaskType.B.toString();// Sets the task type to 'B' (time frame task)
    }
    /**
     * Returns a string representation of the DoWithInTimeTask object.
     * Calls the superclass method to generate the string representation.
     *
     * @return A string representation of the DoWithInTimeTask object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}