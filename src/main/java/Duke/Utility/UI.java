package Duke.Utility;

import Duke.Tasks.*;

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
    private static final String Logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private final HashMap<String, String> messagesMap;

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
    }

    public void show(String key) {
        System.out.println(messagesMap.get(key));
    }

    public void printTaskMsg(String toString) {
        System.out.println("  " + toString);
    }

    public void printTaskList(TaskList tskList) {
        tskList.printTaskList(tskList.storedTaskList);
    }

    public void showError(String error) {
        System.out.println(error);
    }


    public String readCommand() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim();
    }

    public void printNumberOfTask(TaskList tskList) {
        System.out.println("Now you have " + tskList.storedTaskList.size() + " tasks in the Magic Book! Meow!");
    }

    public void printIndividualTask(Tasks tsk) {
        System.out.println(tsk.toString());
    }
}