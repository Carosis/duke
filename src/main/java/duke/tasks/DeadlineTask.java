package duke.tasks;

public class DeadlineTask extends Tasks {
    protected String by;

    public DeadlineTask(String description, boolean isDone, String by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
        this.taskType = TaskType.D.toString();
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "["+taskType+"]" + super.toString() + " (by: " + by + ")";
    }
}
