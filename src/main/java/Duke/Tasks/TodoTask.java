package Duke.Tasks;

public class TodoTask extends Tasks {
    public TodoTask(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = TaskType.T.toString();
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

}