package duke.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "This is an error message.";
        DukeException dukeException = new DukeException(errorMessage);
        assertEquals(errorMessage, dukeException.getMessage());
    }
}