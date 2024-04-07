package duke.tasks;

public class DoWithInTimeTask extends EventTask {
    protected String taskBetween;
    protected String taskAnd;

    public DoWithInTimeTask(String description, boolean isDone, String taskBetween, String taskAnd) {
        super(description, isDone, taskBetween, taskAnd);
        this.taskBetween = taskBetween;
        this.taskAnd = taskAnd;
        this.taskType = TaskType.B.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}