/**
 * Represents the user interface for interacting with the Duke program.
 * Manages input/output messages and commands.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.utility;

import duke.tasks.Tasks;

import java.util.HashMap;
import java.util.Scanner;

public class UI {
    private static final String LineOfLine = "____________________________________________________________\n";
    private static final String HelloMsg = "Myeollo! I'm KunKun ! What can I do for you, meow?\n";
    private static final String ByeBye = "Myeow. Hope to see you again meow!\n";
    private static final String Marked = "Meowked! Magic successfully cast:";
    private static final String Unmarked = "Unmeowked! Magic not yet cast:";
    private static final String ListPrint = "Meow! Here are the magics pending in your list:";
    private static final String TaskAdded = "Meowks~ I've added this magics:";
    private static final String TaskDeleted = "Meowted. I've removed this magics:";
    private static final String FileError = "YumiKunKun failed to find the magic file meow!";
    private static final String updateMsg = "YumiKunKun updated the magic file meow!";
    private static final String searchMsg = "YumiKunKun found the magic file meow!";
    private static final String Logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____    /\\\\_/\\\\\n"
                    + "| | | | | | | |/ / _ \\   ( o.o )\n"
                    + "| |_| | |_| |   <  __/    > ^ <\n"
                    + "|____/ \\__,_|_|\\_\\___|  KunKunMeow~\n";

    private static final String userguideMsg =
            " Simple user guide meow:\n"
                    + "list : list all tasks\n"
                    + "list {taskID} : list a single tasks\n"
                    + "todo {description} : add todo tasks\n"
                    + "Date format DD-MM-YYYY (HH:MM) \n"
                    + "deadline {description} /by {dateline}: add deadline tasks\n"
                    + "event {description} /from {dateline} /to {dateline} : add event tasks\n"
                    + "{description} between {dateline} and {dateline} : add DoWithInPeriod tasks\n"
                    + "delete {taskID} : remove a task from list\n"
                    + "update {taskID} : \n"
                    + " from {dateline} (Optional: to {dateline}): update event task dateline \n"
                    + " between {dateline} (Optional: and {dateline}): update event task dateline \n"
                    + " by {dateline} : update deadline task deadline \n"
                    + " description {description} : update task details \n"
                    + "snooze {taskID} for {number} {units (days/minutes/etc)} : snooze a task for a period\n"
                    + "bye : You can try it meow~ \n";

    private final HashMap<String, String> messagesMap;
    /**
     * Constructs a UI object and initializes the messages map.
     */
    public UI() {
        messagesMap = new HashMap<>();
        messagesMap.put("welcome", LineOfLine + HelloMsg + LineOfLine);
        messagesMap.put("goodbye", ByeBye);
        messagesMap.put("marked", Marked);
        messagesMap.put("unmarked", Unmarked);
        messagesMap.put("list", ListPrint);
        messagesMap.put("added", TaskAdded);
        messagesMap.put("deleted", TaskDeleted);
        messagesMap.put("fileError", FileError);
        messagesMap.put("line", LineOfLine);
        messagesMap.put("logo", "Hello from\n" + Logo);
        messagesMap.put("update", updateMsg);
        messagesMap.put("search", searchMsg);
        messagesMap.put("userguide", userguideMsg);
    }
    /**
     * Displays a message based on the provided key.
     *
     * @param key The key corresponding to the message to display.
     */
    public void show(String key) {
        System.out.println(messagesMap.get(key));
    }
    /**
     * Prints a message for a task.
     *
     * @param toString The message to print.
     */
    public void printTaskMsg(String toString) {
        System.out.println("  " + toString);
    }
    /**
     * Prints the task list.
     *
     * @param tskList The task list to print.
     */
    public void printTaskList(TaskList tskList) {
        tskList.printTaskList(tskList.storedTaskList);
    }
    /**
     * Displays an error message.
     *
     * @param error The error message to display.
     */
    public void showError(String error) {
        System.out.println(error);
    }
    /**
     * Reads a command from the user.
     *
     * @return The user's command input.
     */
    public String readCommand() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {
            return scan.nextLine().trim();
        } else {
            return "goodbye";
        }
    }
    /**
     * Prints the number of tasks in the task list.
     *
     * @param tskList The task list containing the tasks.
     */
    public void printNumberOfTask(TaskList tskList) {
        System.out.println("Now you have " + tskList.storedTaskList.size() + " tasks in the Magic Book! Meow!");
    }
    /**
     * Prints an individual task.
     *
     * @param tsk The task to print.
     */
    public void printIndividualTask(Tasks tsk) {
        System.out.println(tsk.toString());
    }
}