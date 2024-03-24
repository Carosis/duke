package Duke.Tasks;

public class Tasks {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Tasks() {
    }

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = " ";
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}