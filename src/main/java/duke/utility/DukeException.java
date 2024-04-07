/**
 * Represents a custom exception specific to the Duke application.
 * This exception is used to handle errors and exceptional conditions within the Duke application.
 *
 * @author Zijing
 * @version 1.0
 * @since 1.0
 */
package duke.utility;

public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}