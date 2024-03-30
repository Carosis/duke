package Duke.Tasks;

import Duke.Utility.DukeException;

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
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean done) throws DukeException {
        if (isDone == done) {
            throw new DukeException("Meow!!! The mark almeowdy done.");
        } else {
            isDone = done;
        }
    }
    public void setIsNotDone(boolean done) throws DukeException {
        if (isDone == done) {
            throw new DukeException("Meow!!! The mark malmeowdy not done.");
        } else {
            isDone = done;
        }
    }
    public String getTaskType() {
        return taskType;
    }
    public void setTaskType(String description) {
        this.taskType = taskType;
    }


    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}