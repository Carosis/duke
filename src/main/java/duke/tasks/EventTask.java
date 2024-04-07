/**
 * Represents an event task with a start time and end time.
 * Inherits from the Tasks class and includes information about the deadline.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.tasks;

public class EventTask extends Tasks {
    protected String taskFrom;// The start time of the event
    protected String taskTo;// The end time of the event
    /**
     * Constructs an EventTask object with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the event task.
     * @param isDone      The completion status of the event task.
     * @param taskFrom    The start time of the event task.
     * @param taskTo      The end time of the event task.
     */
    public EventTask(String description, boolean isDone, String taskFrom, String taskTo) {
        super(description);// Calls the constructor of the superclass (Tasks)
        this.isDone = isDone;// Sets the task status
        this.taskType = TaskType.E.toString();// Sets the task type to 'E' (event task)
        this.taskFrom = taskFrom;// Sets the start time
        this.taskTo = taskTo;// Sets the end time
    }
    /**
     * Gets the start time of the event task.
     *
     * @return The start time of the event task.
     */
    public String getFrom() {
        return taskFrom;
    }
    /**
     * Gets the end time of the event task.
     *
     * @return The end time of the event task.
     */
    public String getTo() {
        return taskTo;
    }
    /**
     * Sets the start time of the event task.
     *
     * @param from The new start time to set.
     */
    public void setFrom(String from) {
        this.taskFrom = from;
    }

    /**
     * Sets the end time of the event task.
     *
     * @param to The new end time to set.
     */
    public void setTo(String to) {
        this.taskTo = to;
    }

    /**
     * Returns a string representation of the EventTask object.
     * Includes the task type, description, completion status, start time, and end time.
     *
     * @return A string representation of the EventTask object.
     */
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (From: " + taskFrom + ") (To: " + taskTo + ")";
    }
}
