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
        commandMap.put("unmark", this::unmarkTask);
        commandMap.put("update", this::updateTask);
        commandMap.put("DoWithInPeriod", this::AddBTask);
        commandMap.put("snooze", this::snoozeTask);
        commandMap.put("find", this::searchTask);
    }

    public Command parse(String input) throws DukeException {
        String[] parts = input.split(" ");
        String command ;
        String argument ;
        CommandHandler handler;
        if (Arrays.asList(parts).contains("between") && Arrays.asList(parts).contains("and")) {
            argument = input.toLowerCase();
            handler = commandMap.get("DoWithInPeriod");
        } else {
            String[] actualParts = input.split(" ",2);
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

    private Command AddBTask(String argument) throws DukeException {
        String[] eventInfo = argument.split("between|and");
        if (eventInfo.length != 3) {
            throw new DukeException("Invalid Do Within Period Task format!");
        }
        String description = eventInfo[0].trim();
        String from = formatOutput(parseDate(eventInfo[1].trim()));
        String to = formatOutput(parseDate(eventInfo[2].trim()));
        return new AddTask(new DoWithInTimeTask(description, false, from, to));
    }

    private Command AddDTask(String argument) throws DukeException {
        String[] parts = argument.split("/by");
        String deadlineInfo = parts[0].trim();
        String by = formatOutput(parseDate(parts[1].trim()));
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
        String from = formatOutput(parseDate(eventInfo[1].trim()));
        String to = formatOutput(parseDate(eventInfo[2].trim()));
        return new AddTask(new EventTask(description, false, from, to));
    }

    private Command updateTask(String argument) throws DukeException {
        String[] parts = argument.split(" ");
        String[] updateInfo = argument.split("\\b(?:from | to |by |description |between |and )\\b");

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
        if (parts[1].equals("between") && Arrays.asList(parts).contains("and")) {
            transferInfo = new String[]{parts[0], parts[1], updateInfo[1], updateInfo[2]};
        }
        try {
            return new UpdateTask(transferInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The update format incorrect meow.");
        }
    }
    private Command snoozeTask(String argument) throws DukeException {

        String[] snoozeInfo = argument.split("\\b(?: for )\\b");
        if (snoozeInfo.length < 2) {
            throw new DukeException("Invalid snooze format: " + argument);
        }
        String[] transferInfo =  new String[]{snoozeInfo[0], snoozeInfo[1]};

        try {
            return new SnoozeTask(transferInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The Snooze format incorrect meow.");
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
        String[] markInfo = {"mark",argument};
        try {
            return new MarkTask(markInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The mark must come with int meow.");
        }
    }
    private Command unmarkTask(String argument) throws DukeException {
        String[] unmarkInfo = {"unmark",argument};
        try {
            return new MarkTask(unmarkInfo);
        } catch (NumberFormatException e) {
            throw new DukeException(" Meow!!! The find must come with keywords meow.");
        }
    }
    private Command searchTask(String argument) throws DukeException {
        try {
            return new FindTask(argument);
        } catch (Exception e) {
            throw new DukeException(" Meow!!! The description of a todo cannot be empty.");
        }
    }

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
        }else if (isValidDayOfWeek(argument)) {
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

    public String formatOutput(TemporalAccessor temporalAccessor) {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(temporalAccessor);
    }


    private boolean isValidDayOfWeek(String input) {
        try {
            DayOfWeek.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

}
