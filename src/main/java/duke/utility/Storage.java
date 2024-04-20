/**
 * Handles the storage to and from a file.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 1.0
 */
package duke.utility;

import duke.tasks.*;

import java.io.*;
import java.util.*;

/**
 * Handles the storage and retrieval of tasks to and from a file.
 */
public class Storage {
    protected String FilePath;// Default file path for storing tasks
    protected String FileName = "duke.txt";// Default file path for storing tasks
    protected ArrayList<Tasks> TasksBuffer = new ArrayList<>();// Buffer to hold tasks read from the file
    protected ArrayList<String> fileContent = new ArrayList<>(); // Buffer to hold file content
    UI newUI = new UI();// Instance of UI for displaying messages

    /**
     * Default constructor for Storage.
     */
    public Storage() {
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");

        // Construct the file path by appending the file name to the working directory
        FilePath = workingDir + File.separator + FileName;
    }

    /**
     * Constructor for Storage with a specified file path.
     *
     * @param FilePath The file path for storing tasks.
     */
    public Storage(String FilePath) {
        assert FilePath != null : "FilePath cannot be meoll";// Assert that the file exists before attempting

        this.FilePath = FilePath;
    }
    public String getFilePath() {
        return FilePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return ArrayList of Tasks loaded from the file.
     * @throws DukeException If there's an error while loading tasks.
     */
    public ArrayList<Tasks> load() throws DukeException {

        File file = new File(FilePath);

        assert file.exists() : "File does not meist: " + FilePath;

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

    /**
     * Extracts text between two specified strings.
     *
     * @param text  The text to extract from.
     * @param start The starting string.
     * @param end   The ending string.
     * @return The extracted text between start and end strings.
     */
    private String extractBetween(String text, String start, String end) {
        if (text.contains(start) && text.contains(end)) {
            int startIndex = text.indexOf(start) + start.length();
            int endIndex = text.indexOf(end, startIndex);
            return text.substring(startIndex, endIndex).trim();
        }
        return "";
    }

    /**
     * Extracts text from a specified starting string to the end of the text.
     *
     * @param text  The text to extract from.
     * @param start The starting string.
     * @param end   The ending string.
     * @return The extracted text from start to the end of the text.
     */
    private String extractEnding(String text, String start, String end) {
        if (text.contains(start) && text.contains(end)) {
            int startIndex = text.indexOf(start) + start.length();
            int endIndex = text.lastIndexOf(end);
            return text.substring(startIndex, endIndex).trim();
        }
        return "";
    }

    /**
     * Converts file content to Task objects.
     *
     * @return ArrayList of Task objects converted from file content.
     */
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
                case "B":
                    Result.add(new DoWithInTimeTask(description, isDone, from, to));
                    break;
            }
        }
        return Result;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks ArrayList of tasks to be saved to the file.
     * @throws IOException If there's an error while saving tasks to the file.
     */

    public void save(ArrayList<Tasks> tasks) throws IOException {
        try {
            File file = new File(FilePath);
            FileWriter FW = new FileWriter(file);

            assert tasks != null : "Tasks list cannot be meoll";

            for (Tasks t : tasks) {
                FW.write(t.toString());
                FW.write("\n");
            }
            FW.close();
        } catch (IOException e) {
            throw new IOException("Unable to create the magic book at " + FilePath + " ! Meow!");
        }
    }
}