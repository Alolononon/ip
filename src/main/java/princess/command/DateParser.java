package princess.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;




/**
 * Parses date and time strings into LocalDateTime objects using a list of supported formats.
 * If the input is only a date, the time is set to 00:00.
 */
public class DateParser {

    // List of supported date and time formats
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



    /**
     * Parses a date/time string into a LocalDateTime object.
     * If the input is only a date, the time is set to 00:00.
     *
     * @param input the date/time string to parse
     * @return the parsed LocalDateTime
     * @throws IllegalArgumentException if the input doesn't match any supported format
     */
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
