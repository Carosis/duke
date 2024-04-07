package duke.utility;

import duke.command.Command;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTest {

    @Test
    public void testParseValidInput() {
        InputParser inputParser = new InputParser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        try {
            Command command = inputParser.parse("todo Test the parser");
            assertNotNull(command);
        } catch (DukeException e) {
            fail("Should not throw DukeException for valid input");
        }
    }

    @Test
    public void testParseInvalidInput() {
        InputParser inputParser = new InputParser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        try {
            inputParser.parse("invalid command");
            fail("Should throw DukeException for invalid input");
        } catch (DukeException e) {
            assertEquals("Meow? Unknown command!", e.getMessage());
        }
    }

    @Test
    public void testParseEmptyInput() {
        InputParser inputParser = new InputParser();
        TaskList taskList = new TaskList();
        UI ui = new UI();
        try {
            inputParser.parse("");
            fail("Should throw DukeException for empty input");
        } catch (DukeException e) {
            assertEquals("Meow? Unknown command!", e.getMessage());
        }
    }

    @Test
    public void testParseValidDateTime() throws DukeException {
        InputParser inputParser = new InputParser();
        LocalDateTime parsedDateTime = inputParser.parseDate("10-10-2024 12:00");
        assertEquals("10-10-2024 12:00", inputParser.formatOutput(parsedDateTime));
    }
}
