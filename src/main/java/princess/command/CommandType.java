package princess.command;

/**
 * Enum representing different command types in the application.
 */
public enum CommandType {
    BYE, LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT, HELP, FIND, INVALID;

    /**
     * Converts a string into a CommandType, ensuring case-insensitive matching.
     *
     * @param command The user input string.
     * @return The corresponding CommandType, or INVALID if not recognized.
     */
    public static CommandType fromString(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID; // Return INVALID if the command is not recognized
        }
    }
}
