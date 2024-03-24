package Duke.Utility;

import Duke.Tasks.*;

import java.io.*;
import java.util.*;


public class Storage {
    protected String FilePath = "src/main/java/duke.txt";
    protected ArrayList<Tasks> TasksBuffer = new ArrayList<>();
    protected ArrayList<String> fileContent = new ArrayList<>();
    UI newUI = new UI();

    public Storage() {
    }

    public Storage(String FilePath) {
        this.FilePath = FilePath;
    }

    public ArrayList<Tasks> load() throws DukeException {
        File file = new File(FilePath);
        this.TasksBuffer = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                fileContent.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            newUI.show("fileError");
        }

        TasksBuffer = convertFileToTasks();
        return TasksBuffer;
    }

    private String extractBetween(String text, String start, String end) {
        if (text.contains(start) && text.contains(end)) {
            int startIndex = text.indexOf(start) + start.length();
            int endIndex = text.indexOf(end, startIndex);
            return text.substring(startIndex, endIndex).trim();
        }
        return "";
    }
    private String extractEnding(String text, String start, String end) {
        if (text.contains(start) && text.contains(end)) {
            int startIndex = text.indexOf(start) + start.length();
            int endIndex = text.lastIndexOf(end);
            return text.substring(startIndex, endIndex).trim();
        }
        return "";
    }

    public ArrayList<Tasks> convertFileToTasks() {
        ArrayList<Tasks> Result = new ArrayList<>();
        for (String s : fileContent) {
            String description = s.substring(6).split("\\(", 2)[0].trim();
            boolean isDone = s.charAt(4) == 'X';

            String by = extractBetween(s, "by: ", ")");
            String from = extractBetween(s, "From: ", ") (To: ");
            String to = extractEnding(s, "(To: ", ")");

            switch (s.substring(1, 2)) {
                case "T":
                    Result.add(new TodoTask(description, isDone));
                    break;
                case "D":
                    Result.add(new DeadlineTask(description, isDone, by));
                    break;
                case "E":
                    Result.add(new EventTask(description, isDone, from, to));
                    break;
            }
        }
        return Result;
    }

    public void save(ArrayList<Tasks> tasks) throws IOException {
        try {
            File file = new File(FilePath);
            FileWriter FW = new FileWriter(file);

            for (Tasks t : TasksBuffer) {
                FW.write(t.toString());
                FW.write("\n");
            }
            FW.close();
        } catch (IOException e) {
            throw new IOException("Unable to create the magic book at " + FilePath + " ! Meow!");
        }
    }
}