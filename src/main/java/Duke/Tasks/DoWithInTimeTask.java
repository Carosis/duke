package Duke.Tasks;

public class DoWithInTimeTask extends EventTask {
    protected String taskBetween;
    protected String taskAnd;

    public DoWithInTimeTask(String description, boolean isDone, String taskBetween, String taskAnd) {
        super(description, isDone, taskBetween, taskAnd);
        this.taskBetween = taskBetween;
        this.taskAnd = taskAnd;
        this.taskType = TaskType.B.toString();
    }

    public String getTaskBetween() {
        return taskBetween;
    }

    public String getTaskAnd() {
        return taskAnd;
    }

    public void setTaskBetween(String taskBetween) {
        this.taskBetween = taskBetween;
    }

    public void setTaskAnd(String taskAnd) {
        this.taskAnd = taskAnd;
    }

    @Override
    public String toString() {
      return super.toString();
    }
}