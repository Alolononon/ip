package princess.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;


/**
 * Parses date and time strings into LocalDateTime objects using a list of supported formats.
 * If the input is only a date, the time is set to 00:00.
 */
public class DateParser {

    /**
     * Helper method to create a case-insensitive formatter.
     * If defaultMinute is true, it sets the minute to 0 if it's not provided in the input.
     */
    private static DateTimeFormatter createFormatter(String pattern, boolean defaultMinute) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern(pattern);
        if (defaultMinute) {
            builder.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0);
        }
        return builder.toFormatter(Locale.ENGLISH);
    }

    // List of supported date and time formats
    private static final List<DateTimeFormatter> DATE_FORMATS = List.of(
            createFormatter("yyyy-MM-dd", false), // 2000-03-01
            createFormatter("MMM dd yyyy", false), // Mar 01 2000
            createFormatter("dd-MM-yyyy", false), // 01-03-2000
            createFormatter("dd/MM/yyyy", false),

            createFormatter("yyyy-MM-dd HHmm", false), // 2000-03-01 1800
            createFormatter("dd-MM-yyyy HHmm", false), // 01-03-2000 1800
            createFormatter("MMM dd yyyy HHmm", false), // Mar 01 2000 1800
            createFormatter("dd/MM/yyyy HHmm", false), // 01/03/2000 1800

            createFormatter("yyyy-MM-dd h:mma", false), // 2000-03-01 4:00PM
            createFormatter("dd-MM-yyyy h:mma", false), // 01-03-2000 4:00PM
            createFormatter("MMM dd yyyy h:mma", false), // Mar 01 2000 4:00PM
            createFormatter("MMM dd yyyy, h:mma", false), // Mar 01 2000 4:00PM
            createFormatter("dd/MM/yyyy h:mma", false), // 01/03/2000 4:00PM

            // Formats that support "4PM" without minutes.
            createFormatter("yyyy-MM-dd ha", true), // 2000-03-01 4PM
            createFormatter("dd-MM-yyyy ha", true), // 01-03-2000 4PM
            createFormatter("MMM dd yyyy ha", true), // Mar 01 2000 4PM
            createFormatter("dd/MM/yyyy ha", true) // 01/03/2000 4PM

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
        input = input.trim(); // Remove unnecessary spaces
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
        throw new IllegalArgumentException("Invalid date format: " + input
        + "\nPerhaps, you can follow this format: jan 20 2000 1300");
    }
}
