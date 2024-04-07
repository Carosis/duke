package duke.utility;

import duke.tasks.Tasks;

import java.io.File;
import java.util.ArrayList;

public class TaskList extends Tasks {
    protected String FilePath = new Storage().FilePath;
    public ArrayList<Tasks> storedTaskList = new ArrayList<>();

    public TaskList(ArrayList<Tasks> loadedTasks) throws DukeException {
        File f = new File(FilePath);
        this.storedTaskList = loadedTasks;
    }

    public TaskList() {
    }

    public void addTask(Tasks taskToAdd) {
        storedTaskList.add(taskToAdd);
    }

    public Tasks getTask(int index) {
        return storedTaskList.get(index - 1);
    }

    public void deleteTask(int index) {
        storedTaskList.remove(index - 1);
    }
    public int getTaskAmount(ArrayList<Tasks> taskList) {
        return taskList.size();
    }
    public ArrayList<Tasks> getAllTasks() {
        return storedTaskList;
    }

    public void printTaskList(ArrayList<Tasks> input) {
        int i = 1;
        for (Tasks t : input) {
            System.out.println(i + t.toString());
            i++;
        }
    }

    public void printTask(Tasks taskToPrint) {
        System.out.println(taskToPrint.toString());
    }
}
