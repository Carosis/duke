package Duke.Tasks;

public class EventTask extends Tasks {
    protected String taskFrom;
    protected String taskTo;

    public EventTask(String description, boolean isDone, String taskFrom, String taskTo) {
        super(description);
        this.isDone = isDone;
        this.taskType = TaskType.E.toString();
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    public String getFrom() {
        return taskFrom;
    }

    public String getTo() {
        return taskTo;
    }

    public void setFrom(String from) {
        this.taskFrom = from;
    }

    public void setTo(String to) {
        this.taskTo = to;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (From: " + taskFrom + ") (To: " + taskTo + ")";
    }
}
