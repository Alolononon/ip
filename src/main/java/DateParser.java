import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateParser {
    private static final List<DateTimeFormatter> DATE_FORMATS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),        // 2000-03-01
            DateTimeFormatter.ofPattern("MMM dd yyyy"),     // Mar 01 2000
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),        // 01-03-2000
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),

            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),   // 2000-03-01 1800
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),   // 01-03-2000 1800
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"),  // Mar 01 2000 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),   // 01/03/2000 1800

            DateTimeFormatter.ofPattern("yyyy-MM-dd h:mma"),   // 2000-03-01 4pm
            DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma"),   // 01-03-2000 4pm
            DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"),  // Mar 01 2000 4pm
            DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma"),    // 01/03/2000 4pm
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")  // Mar 01 2000, 4pm

    );




    public static LocalDateTime parseDateTime(String input) {
        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                if (input.length() > 11) { // If time is included
                    return LocalDateTime.parse(input, formatter);
                } else { // If only date is given, assume 00:00 time
                    return LocalDate.parse(input, formatter).atStartOfDay();
                }
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + input);
    }
}
