package princess.task;

import princess.command.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a deadline task with a description and a deadline.
     *
     * @param description the task description
     * @param by          the deadline date/time as a string
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parseDateTime(by);
    }

    /**
     * Returns the formatted deadline date/time.
     *
     * @return the deadline as a formatted string
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the formatted deadline task string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}