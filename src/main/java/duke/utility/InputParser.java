/**
 * The InputParser class handles parsing user input and converting it into executable commands.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 1.0
 */

package duke.utility;

import duke.command.AddTaskFunction;
import duke.command.Command;
import duke.command.SnoozeTaskFunction;
import duke.command.UpdateTaskFunction;
import duke.command.DeleteTaskFunction;
import duke.command.PrintByeFunction;
import duke.command.ListTaskFunction;
import duke.command.MarkTaskFunction;
import duke.command.FindTaskFunction;
import duke.tasks.Tasks;
import duke.tasks.TodoTask;
import duke.tasks.DeadlineTask;
import duke.tasks.DoWithInTimeTask;
import duke.tasks.EventTask;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Parses user input and generates corresponding commands for the Duke application.
 * Supports various commands such as adding tasks, updating tasks, marking tasks as done, and more.
 */
public class InputParser {
    private final Map<String, CommandHandler> commandMap;

    /**
     * Initializes the command map with supported commands and their corresponding handlers.
     */
    public InputParser() {
        commandMap = new HashMap<>();
        commandMap.put("bye", this::printBye);
        commandMap.put("list", this::listTask);
        commandMap.put("todo", this::AddTTask);
        commandMap.put("event", this::AddETask);
        commandMap.put("delete", this::deleteATask);
        commandMap.put("deadline", this::AddDTask);
        commandMap.put("mark", this::markTask);
        commandMap.put("unmark", this::unmarkTask);
        commandMap.put("update", this::updateTask);
        commandMap.put("DoWithInPeriod", this::AddBTask);
        commandMap.put("snooze", this::snoozeTask);
        commandMap.put("find", this::searchTask);
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input to be parsed.
     * @return The generated command based on the input.
     * @throws DukeException If the input is invalid or unsupported.
     */
    public Command parse(String input) throws DukeException {

        assert input != null : "Input cannot be meoll.";

        String command;
        String argument;
        CommandHandler handler;

        String[] actualParts = input.split(" ", 2);

        // Checking if the input contains 'between' followed by 'and' to handle 'DoWithInPeriod' command
        boolean containsAndAfterBetween = input.matches(".*\\bbetween\\b.*\\band\\b.*");

        // Checking if the command is supported based on the first part of the input
        boolean handlerMatched = commandMap.containsKey(actualParts[0].toLowerCase());

        // Handling 'DoWithInPeriod' command if the input contains 'between' followed by 'and'
        if (containsAndAfterBetween && !handlerMatched) {
            argument = input.toLowerCase();
            handler = commandMap.get("DoWithInPeriod");
        } else {
            command = actualParts[0].toLowerCase();
            argument = actualParts.length > 1 ? actualParts[1] : "";
            handler = commandMap.get(command);
        }

        if (handler != null) {
            return handler.handle(argument);
        } else {
            throw new DukeException("Meow? Unknown command!");
        }
    }

    /**
     * Functional interface for command handlers.
     */
    public interface CommandHandler {
        Command handle(String argument) throws DukeException;
    }

    /**
     * Handles the addition of a TodoTask based on the input argument.
     *
     * @param argument The argument containing the description of the task.
     * @return The AddTaskFunction command to add the TodoTask.
     * @throws DukeException If the description of the todo task is empty.
     */
    private Command AddTTask(String argument) throws DukeException {
        try {
            return new AddTaskFunction(new TodoTask(argument, false));
        } catch (Exception e) {
            throw new DukeException(" Meow!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Handles the addition of a DoWithInTimeTask based on the input argument.
     *
     * @param argument The argument containing the details of the task.
     * @return The AddTaskFunction command to add the DoWithInTimeTask.
     * @throws DukeException If the input format for DoWithInTimeTask is invalid.
     */
    private Command AddBTask(String argument) throws DukeException {
        int lastBetweenIndex = argument.lastIndexOf(" between ");
        if (lastBetweenIndex == -1) {
            throw new DukeException("Invalid argument format for between task.");
        }

        String description = argument.substring(0, lastBetweenIndex).trim();
        String timeInfo = argument.substring(lastBetweenIndex + " between ".length()).trim();
        String[] eventTimeInfo = timeInfo.split(" and ");
        if (eventTimeInfo.length != 2) {
            throw new DukeException("Invalid time format for between task.");
        }

        String from = formatOutput(parseDate(eventTimeInfo[0].trim()));
        String to = formatOutput(parseDate(eventTimeInfo[1].trim()));
        return new AddTaskFunction(new DoWithInTimeTask(description, false, from, to));
    }

    /**
     * Handles the addition of a DeadlineTask based on the input argument.
     *
     * @param argument The argument containing the description and deadline of the task.
     * @return The AddTaskFunction command to add the DeadlineTask.
     * @throws DukeException If the input format for DeadlineTask is invalid.
     */
    private Command AddDTask(String argument) throws DukeException {
        String[] parts = argument.split("/by");
        String deadlineInfo = parts[0].trim();
        String by = formatOutput(parseDate(parts[1].trim()));
        if (deadlineInfo.isEmpty() || by.isEmpty()) {
            throw new DukeException("Meow!!! The description or deadline of a deadline cannot be empty.");
        }
        return new AddTaskFunction(new DeadlineTask(deadlineInfo, false, by));
    }

    /**
     * Handles the addition of an EventTask based on the input argument.
     *
     * @param argument The argument containing the details of the event task.
     * @return The AddTaskFunction command to add the EventTask.
     * @throws DukeException If the input format for EventTask is invalid.
     */
    private Command AddETask(String argument) throws DukeException {
        String[] eventInfo = argument.split("/from|/to");
        if (eventInfo.length != 3) {
            throw new DukeException("Invalid event format!");
        }
        String description = eventInfo[0].trim();
        String from = formatOutput(parseDate(eventInfo[1].trim()));
        String to = formatOutput(parseDate(eventInfo[2].trim()));
        return new AddTaskFunction(new EventTask(description, false, from, to));
    }

    /**
     * Handles the updating of a task based on the input argument.
     *
     * @param argument The argument containing the details of the task update.
     * @return The UpdateTaskFunction command to update the task.
     * @throws DukeException If the input format for task update is invalid.
     */
    private Command updateTask(String argument) throws DukeException {
        String[] parts = argument.split(" ");
        String[] updateInfo = argument.split("\\b(?:from | to |by |description |between |and )\\b");

        if (parts.length < 3) {
            throw new DukeException("Invalid update format: " + argument);
        }

        String[] transferInfo = getStrings(parts, updateInfo);

        try {
            return new UpdateTaskFunction(transferInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The update format incorrect meow.");
        }
    }

    private static String[] getStrings(String[] parts, String[] updateInfo) {
        String[] transferInfo = new String[0];
        if (parts[1].equals("by") || parts[1].equals("from") || parts[1].equals("description") || parts[1].equals("to")) {
            transferInfo = new String[]{parts[0], parts[1], updateInfo[1]};
        }
        if (parts[1].equals("from") && Arrays.asList(parts).contains("to")) {
            transferInfo = new String[]{parts[0], parts[1], updateInfo[1], updateInfo[2]};
        }
        if (parts[1].equals("between") && Arrays.asList(parts).contains("and")) {
            transferInfo = new String[]{parts[0], parts[1], updateInfo[1], updateInfo[2]};
        }
        return transferInfo;
    }

    /**
     * Handles the snoozing of a task based on the input argument.
     *
     * @param argument The argument containing the details of the task snooze.
     * @return The SnoozeTaskFunction command to snooze the task.
     * @throws DukeException If the input format for task snooze is invalid.
     */
    private Command snoozeTask(String argument) throws DukeException {

        String[] snoozeInfo = argument.split("\\b(?: for )\\b");
        if (snoozeInfo.length < 2) {
            throw new DukeException("Invalid snooze format: " + argument);
        }
        String[] transferInfo = new String[]{snoozeInfo[0], snoozeInfo[1]};

        try {
            return new SnoozeTaskFunction(transferInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The Snooze format incorrect meow.");
        }
    }

    /**
     * Handles the deletion of a task based on the input argument.
     *
     * @param argument The argument containing the index of the task to delete.
     * @return The DeleteTaskFunction command to delete the task.
     * @throws DukeException If the input format for task deletion is invalid.
     */
    private Command deleteATask(String argument) throws DukeException {
        try {
            int index = Integer.parseInt(argument);
            return new DeleteTaskFunction(index);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The delete must come with int meow.");
        }
    }

    /**
     * Handles the printing of the goodbye message.
     *
     * @param argument Unused argument for the goodbye command.
     * @return The PrintByeFunction command to print the goodbye message.
     */
    private Command printBye(String argument) {
        return new PrintByeFunction();
    }

    /**
     * Handles the listing of tasks.
     *
     * @param argument Unused argument for the list command.
     * @return The ListTaskFunction command to list tasks.
     */
    private Command listTask(String argument) throws DukeException {
        try {
            if (argument.isEmpty()) {
                return new ListTaskFunction();
            } else {
                return new ListTaskFunction(Integer.parseInt(argument));
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Must input index meow!");
        }
    }

    /**
     * Handles the marking of a task as done.
     *
     * @param argument The argument containing the index of the task to mark as done.
     * @return The MarkTaskFunction command to mark the task as done.
     * @throws DukeException If the input format for task marking is invalid.
     */
    private Command markTask(String argument) throws DukeException {
        String[] markInfo = {"mark", argument};
        try {
            return new MarkTaskFunction(markInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The mark must come with int meow.");
        }
    }

    /**
     * Handles the unmarking of a task as not done.
     *
     * @param argument The argument containing the index of the task to unmark as not done.
     * @return The MarkTaskFunction command to unmark the task as not done.
     * @throws DukeException If the input format for task unmarking is invalid.
     */
    private Command unmarkTask(String argument) throws DukeException {
        String[] unmarkInfo = {"unmark", argument};
        try {
            return new MarkTaskFunction(unmarkInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The find must come with keywords meow.");
        }
    }

    /**
     * Handles the searching of tasks based on keywords.
     *
     * @param argument The argument containing the keywords for task search.
     * @return The FindTaskFunction command to search for tasks.
     * @throws DukeException If the input format for task searching is invalid.
     */
    private Command searchTask(String argument) throws DukeException {
        try {
            return new FindTaskFunction(argument);
        } catch (Exception e) {
            throw new DukeException(" Meow!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Parses the date-time from the input argument and returns the LocalDateTime object.
     *
     * @param argument The argument containing the date-time information.
     * @return The LocalDateTime object parsed from the input.
     * @throws DukeException If the input date-time format is invalid.
     */
    public LocalDateTime parseDate(String argument) throws DukeException {
        argument = argument.toLowerCase();

        if (argument.equals("today")) {
            return LocalDateTime.now();
        } else if (argument.equals("tomorrow")) {
            return LocalDateTime.now().plusDays(1);
        } else if (argument.equals("yesterday")) {
            return LocalDateTime.now().minusDays(1);
        } else if (argument.equals("now")) {
            return LocalDateTime.now();
        } else if (isValidDayOfWeek(argument)) {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(argument.toUpperCase());
            LocalDate nextOccurrence = LocalDate.now().with(TemporalAdjusters.nextOrSame(dayOfWeek));
            return nextOccurrence.atStartOfDay();
        } else {
            if (!argument.matches(".*\\d\\d:\\d\\d.*")) {
                argument += " 00:00";
            }
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            try {
                return LocalDateTime.parse(argument, customFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Unable to parse date-time: " + argument);
            }
        }
    }

    /**
     * Formats the output date-time from TemporalAccessor to a specified format.
     *
     * @param temporalAccessor The TemporalAccessor object representing date-time.
     * @return The formatted date-time string.
     */
    public String formatOutput(TemporalAccessor temporalAccessor) {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(temporalAccessor);
    }

    /**
     * Checks if the input string represents a valid day of the week.
     *
     * @param input The input string to check.
     * @return True if the input is a valid day of the week, false otherwise.
     */
    private boolean isValidDayOfWeek(String input) {
        try {
            DayOfWeek.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

}
