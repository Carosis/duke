package Duke.Utility;

import Duke.Command.*;
import Duke.Tasks.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.time.*;
import java.time.temporal.*;
import java.time.format.*;

public class InputParser {
    private Map<String, CommandHandler> commandMap;

    public InputParser() {
        commandMap = new HashMap<>();
        commandMap.put("bye", this::printBye);
        commandMap.put("list", this::listTask);
        commandMap.put("todo", this::AddTTask);
        commandMap.put("event", this::AddETask);
        commandMap.put("delete", this::deleteATask);
        commandMap.put("deadline", this::AddDTask);
        commandMap.put("mark", this::markTask);
        commandMap.put("unmark", this::markTask);
        commandMap.put("update", this::updateTask);
    }

    public Command parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        CommandHandler handler = commandMap.get(command);
        if (handler != null) {
            return handler.handle(argument);
        } else {
            throw new DukeException("Meow? Unknown command!");
        }
    }

    public interface CommandHandler {
        Command handle(String argument) throws DukeException;
    }

    private Command AddTTask(String argument) throws DukeException {
        try {
            return new AddTask(new TodoTask(argument, false));
        } catch (Exception e) {
            throw new DukeException(" Meow!!! The description of a todo cannot be empty.");
        }
    }

    private Command AddDTask(String argument) throws DukeException {
        String[] parts = argument.split("/by");
        String deadlineInfo = parts[0].trim();
        String by = parseDate(parts[1].trim());
        if (deadlineInfo.isEmpty() || by.isEmpty()) {
            throw new DukeException("Meow!!! The description or deadline of a deadline cannot be empty.");
        }
        return new AddTask(new DeadlineTask(deadlineInfo, false, by));
    }

    private Command AddETask(String argument) throws DukeException {
        String[] eventInfo = argument.split("/from|/to");
        if (eventInfo.length != 3) {
            throw new DukeException("Invalid event format!");
        }
        String description = eventInfo[0].trim();
        String from = parseDate(eventInfo[1].trim());
        String to = parseDate(eventInfo[2].trim());
        return new AddTask(new EventTask(description, false, from, to));
    }

    private Command updateTask(String argument) throws DukeException {
        String[] parts = argument.split(" ");
        String[] updateInfo = argument.split("\\b(?:from | to |by |description )\\b");

        if (parts.length < 3) {
            throw new DukeException("Invalid update format: " + argument);
        }

        String[] transferInfo = new String[0];
        if (parts[1].equals("by") || parts[1].equals("from") || parts[1].equals("description") || parts[1].equals("to")) {
            transferInfo = new String[]{parts[0], parts[1], updateInfo[1]};
        }
        if (parts[1].equals("from") && Arrays.asList(parts).contains("to")) {
            transferInfo = new String[]{parts[0], parts[1], updateInfo[1], updateInfo[2]};
        }
        try {
            return new UpdateTask(transferInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The update format incorrect meow.");
        }
    }

    private Command deleteATask(String argument) throws DukeException {
        try {
            int index = Integer.parseInt(argument);
            return new DeleteTask(index);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The delete must come with int meow.");
        }
    }

    private Command printBye(String argument) {
        return new printBye();
    }

    private Command listTask(String argument) {
        return new listTask();
    }

    private Command markTask(String argument) throws DukeException {
        String[] markInfo = argument.split(" ");
        if (markInfo.length == 2) {
            try {
                return new MarkTask(markInfo);
            } catch (NumberFormatException e) {
                throw new DukeException(" Meow!!! The mark must come with int meow.");
            }
        } else {
            throw new DukeException("Meow? Invalid mark action!");
        }
    }

    public String parseDate(String argument) throws DukeException {
        argument = argument.toLowerCase();

        if (argument.equals("today")) {
            return formatOutput(LocalDate.now());
        } else if (argument.equals("tomorrow")) {
            return formatOutput(LocalDate.now().plusDays(1));
        } else if (argument.equals("yesterday")) {
            return formatOutput(LocalDate.now().minusDays(1));
        } else if (isValidDayOfWeek(argument)) {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(argument.toUpperCase());
            LocalDate nextOccurrence = LocalDate.now().with(TemporalAdjusters.nextOrSame(dayOfWeek));
            return formatOutput(nextOccurrence);
        } else {
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            try {
                LocalDate parsedDate = LocalDate.parse(argument, customFormatter);
                return formatOutput(parsedDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("Unable to parse date: " + argument);
            }
        }
    }

    private boolean isValidDayOfWeek(String input) {
        try {
            DayOfWeek.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    private String formatOutput(LocalDate date) {
        return date.getDayOfMonth() + " " + date.getMonthValue() + " " + date.getYear();
    }
}
