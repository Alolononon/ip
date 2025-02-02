import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateParser.parseDateTime(from);
        this.to = DateParser.parseDateTime(to);
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}