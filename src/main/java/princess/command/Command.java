package princess.command;

import princess.PrincessException;

import princess.task.*;
import princess.ui.UI;
import java.util.ArrayList;


/**
 * Handles different user commands and executes the corresponding actions.
 */
public class Command {

    public static final String NO_MATCHING_TASKS_FOUND_MESSAGE = "     No matching tasks found.\n";
    private UI ui;
    private boolean isExit = false;
    private String princessResponse = "";

    /**
     * Constructor to initialize the Command object with a UI.
     *
     * @param ui The UI to interact with the user.
     */
    public Command(UI ui) {
        this.ui = ui;
    }

    /**
     * Executes the command based on user input.
     *
     * @param taskList The list of tasks.
     * @throws Exception If an error occurs during command execution.
     */
    public String execute(String input, TaskList taskList) throws Exception {
        princessResponse = "";
        // Convert input into CommandType
        String[] inputParts = input.split(" ");
        CommandType commandType = CommandType.fromString(inputParts[0]);

        try {

            // Commands
            switch (commandType) {
            // Exit the application
            case BYE:
                handleExitCommand();
                break;
            case LIST:
                handleListCommand(taskList);
                break;
            case DELETE:
                handleDeleteCommand(taskList, inputParts);
                break;
            case MARK:
                handleMarkCommand(taskList, inputParts);
                break;
            case UNMARK:
                handleUnmarkCommand(taskList, inputParts);
                break;
            case TODO:
                handleAddToDo(inputParts, input, taskList);
                break;
            case DEADLINE:
                handleAddDeadline(inputParts, input, taskList);
                break;
            case EVENT:
                handleAddEvent(inputParts, input, taskList);
                break;
            case HELP:
                princessResponse += ui.showHelpMessage(); // Display help message
                break;
            case FIND:
                handleFindFunction(inputParts, input, taskList);
                break;
            case INVALID:
                throw new PrincessException(ui.showInvalidCommandMessage());
            }


        } catch (PrincessException e) {
            princessResponse += e.getMessage() + "\n";
        }

        return princessResponse;
    }

    /**
     * Checks if the program should exit.
     *
     * @return {@code true} if the exit command has been issued, otherwise {@code false}.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Handles the exit command by setting the exit flag and displaying the exit message.
     * This method marks the application as ready to terminate.
     */
    private void handleExitCommand() {
        isExit = true;
        princessResponse += ui.showEndingMessage(); // show exiting message when closing app
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList The list of tasks to be displayed.
     */
    private void handleListCommand(TaskList taskList) {
        //listing out all tasks
        princessResponse += UI.MESSAGE_LIST_HEADER;
        if (taskList.isEmpty()) {
            princessResponse += UI.MESSAGE_EMPTY_LIST;
        } else {
            generateTaskListString(taskList);
        }
    }

    private void generateTaskListString(TaskList taskList) {
        int num = 1;
        for (Task elem : taskList.getTasks()) {
            princessResponse += "     " + Integer.toString(num++) + "." + elem.toString() + "\n";
        }
    }


    /**
     * Deletes a task from the task list.
     *
     * @param taskList The list of tasks.
     * @param inputParts The command input split into parts.
     * @throws PrincessException If the input is invalid.
     */
    private void handleDeleteCommand(TaskList taskList, String[] inputParts) throws PrincessException {
        // Delete a task
        validateTaskIndex(taskList, inputParts);
        int elemNum = Integer.parseInt(inputParts[1]);
        Task taskToRemove = taskList.getElem(elemNum - 1); // Retrieve task before removal
        showDeleteMessage(taskList, taskToRemove, elemNum);
        showTaskCountMessage(taskList);
    }

    private void showDeleteMessage(TaskList taskList, Task taskToRemove, int elemNum) {
        princessResponse += "     Alrighty, Princess have removed this task:\n"
                + "       " + taskToRemove.toString() + "\n";
        taskList.removeElem(elemNum - 1);
    }

    private void showTaskCountMessage(TaskList taskList) {
        princessResponse += "     Now you have " + taskList.getSize() + " tasks in the list.\n";
    }

    private static void validateTaskIndex(TaskList taskList, String[] inputParts) throws PrincessException {
        if (inputParts.length < 2) {
            throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
        } else if (!isInteger(inputParts[1]) || Integer.parseInt(inputParts[1]) < 1
                || Integer.parseInt(inputParts[1]) > taskList.getSize()) {
            throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param taskList The list of tasks.
     * @param inputParts The command input split into parts.
     * @throws PrincessException If the input is invalid.
     */
    private void handleMarkCommand(TaskList taskList, String[] inputParts) throws PrincessException {
        // Mark a task as done
        validateTaskIndex(taskList, inputParts);

        int elemNum = Integer.parseInt(inputParts[1]);
        Task taskToMark = taskList.getElem(elemNum - 1);
        taskToMark.markTask();
        showTaskMarkedMessage(taskToMark);
    }

    private void showTaskMarkedMessage(Task taskToMark) {
        princessResponse += "     Nice! You are the best! Princess have help you marked this task as done:\n"
                          + "       " + taskToMark.toString() + "\n";
    }

    /**
     * Marks a task as undone.
     *
     * @param taskList The list of tasks.
     * @param inputParts The command input split into parts.
     * @throws PrincessException If the input is invalid.
     */
    private void handleUnmarkCommand(TaskList taskList, String[] inputParts) throws PrincessException {
        // Mark a task as undone
        validateTaskIndex(taskList, inputParts);

        int elemNum = Integer.parseInt(inputParts[1]);
        Task taskToUnmark = taskList.getElem(elemNum - 1);
        taskToUnmark.unmarkTask();
        showTaskUnmarkedMessage(taskToUnmark);
    }

    private void showTaskUnmarkedMessage(Task elem) {
        princessResponse += "     Whattt?!?! Alright... Princess have marked this task as undone:\n"
                          + "       " + elem.toString() + "\n";
    }

    /**
     * Adds a ToDo task.
     *
     * @param inputParts The command input split into parts.
     * @param input The full user input.
     * @param taskList The list of tasks.
     * @throws PrincessException If the input is invalid.
     */
    private void handleAddToDo(String[] inputParts, String input, TaskList taskList) throws PrincessException {
        validateTaskDescription(inputParts);
        String taskName = extractTaskName(input);
        Task newTask = new Todo(taskName);
        showTaskAddedMessage(taskList, newTask);
    }

    private void showTaskAddedMessage(TaskList taskList, Task newTask) {
        princessResponse += ui.showTaskAdded(newTask, taskList);
    }

    private static String extractTaskName(String input) {
        return input.substring(5);
    }

    private static void validateTaskDescription(String[] inputParts) throws PrincessException {
        if (inputParts.length < 2) {
            throw new PrincessException("     " + "OH NOOO!!! Sweetheart, There is no task description!");
        }
    }

    /**
     * Adds a Deadline task.
     *
     * @param inputParts The command input split into parts.
     * @param input The full user input.
     * @param taskList The list of tasks.
     * @throws PrincessException If the input is invalid.
     */
    private void handleAddDeadline(String[] inputParts, String input, TaskList taskList) throws PrincessException {
        validateDeadlineInputFormat(inputParts, input);
        extractDeadlineDetails deadlineDetails = getDeadlineDetails(input);
        try {
            Task newTask = new Deadline(deadlineDetails.taskName(), deadlineDetails.by());
            showTaskAddedMessage(taskList, newTask);
        } catch (IllegalArgumentException e) { // throw error for invalid date format
            showInvalidDateInputMessage(e);
        }
    }

    private void showInvalidDateInputMessage(IllegalArgumentException e) {
        princessResponse += "     " + e.getMessage() + "\n"
                          + "     " + "try again\n";
    }

    private static extractDeadlineDetails getDeadlineDetails(String input) {
        String[] stringArr = input.substring(9).split("/by ");
        String taskName = stringArr[0];
        String by = stringArr[1];
        extractDeadlineDetails result = new extractDeadlineDetails(taskName, by);
        return result;
    }

    private record extractDeadlineDetails(String taskName, String by) {
    }

    private static void validateDeadlineInputFormat(String[] inputParts, String input) throws PrincessException {
        if (inputParts.length < 4 || !input.contains("/by")) {
            throw new PrincessException("     " + "OH NOOO!!! Sweetheart, please follow the deadline format: "
                    + "deadline [task name] /by [deadline]");
        }
    }

    /**
     * Adds an Event task.
     *
     * @param inputParts The command input split into parts.
     * @param input The full user input.
     * @param taskList The list of tasks.
     * @throws PrincessException If the input is invalid.
     */
    private void handleAddEvent(String[] inputParts, String input, TaskList taskList) throws PrincessException {
        validateEventInputFormat(inputParts, input);
        GetEventDetails eventDetails = getEventDetails(input);
        try {
            Task newTask = new Event(eventDetails.taskName(), eventDetails.from(), eventDetails.to());
            showTaskAddedMessage(taskList, newTask);
        } catch (IllegalArgumentException e) { // throw error for invalid date format
            showInvalidDateInputMessage(e);
        }
    }

    private static GetEventDetails getEventDetails(String input) {
        String[] stringArr = input.substring(6).split("/from | /to ");
        String taskName = stringArr[0];
        String from = stringArr[1];
        String to = stringArr[2];
        GetEventDetails eventDetails = new GetEventDetails(taskName, from, to);
        return eventDetails;
    }

    private record GetEventDetails(String taskName, String from, String to) {
    }

    private static void validateEventInputFormat(String[] inputParts, String input) throws PrincessException {
        if (inputParts.length < 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new PrincessException("     " + "OH NOOO!!! Sweetheart, please follow the deadline format: "
                    + "event [taskname] /from [date/time] /to [date/time]");
        }
    }


    /**
     * Searches for tasks matching the keyword.
     *
     * @param inputParts The command input split into parts.
     * @param input The full user input.
     * @param taskList The list of tasks.
     * @throws PrincessException If the input is invalid.
     */
    private void handleFindFunction(String[] inputParts, String input, TaskList taskList) throws PrincessException {
        validateFindInput(inputParts);
        String keyword = extractKeyword(input);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        filterMatchingTasks(taskList, keyword, matchingTasks);
        if (matchingTasks.isEmpty()) {
            princessResponse += NO_MATCHING_TASKS_FOUND_MESSAGE;
        } else {
            generateMatchingTasksMessage(matchingTasks);
        }
    }

    private void generateMatchingTasksMessage(ArrayList<Task> matchingTasks) {
        princessResponse += "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            princessResponse += "     " + (i + 1) + "." + matchingTasks.get(i) + "\n";
        }
    }

    private static void filterMatchingTasks(TaskList taskList, String keyword, ArrayList<Task> matchingTasks) {
        for (Task eachtask : taskList.getTasks()) {
            if (eachtask.getTaskName().toLowerCase().contains(keyword)) {
                matchingTasks.add(eachtask);
            }
        }
    }

    private static String extractKeyword(String input) {
        String keyword = input.substring(5).toLowerCase();
        return keyword;
    }

    private static void validateFindInput(String[] inputParts) throws PrincessException {
        if (inputParts.length < 2) {
            throw new PrincessException("     "
                    + "OH NOOO!!! There is missing keyword. Please input a keyword to search.");
        }
    }


    /**
     * Checks if the given string can be parsed as an integer.
     *
     * @param str The string to check.
     * @return True if the string is an integer, false otherwise.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Successfully parsed, it's an integer
        } catch (NumberFormatException e) {
            return false; // Not an integer
        }
    }

}
