package princess.task;

import princess.command.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an event task with a description, start date/time, and end date/time.
     *
     * @param description the task description
     * @param from        the start date/time as a string
     * @param to          the end date/time as a string
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Event description cannot be null or empty";
        assert from != null && !from.trim().isEmpty() : "Event start time cannot be null or empty";
        assert to != null && !to.trim().isEmpty() : "Event end time cannot be null or empty";
        this.from = DateParser.parseDateTime(from);
        this.to = DateParser.parseDateTime(to);
    }

    /**
     * Returns the formatted start date/time.
     *
     * @return the start date/time as a formatted string
     */
    public String getFrom() {
        assert from != null : "Start time should never be null";
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    /**
     * Returns the formatted end date/time.
     *
     * @return the end date/time as a formatted string
     */
    public String getTo() {
        assert to != null : "End time should never be null";
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }


    /**
     * Returns the string representation of the event task.
     *
     * @return the formatted event task string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")"
                + super.getPlace().toString();
    }
}