import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime  by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parseDateTime(by);
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}