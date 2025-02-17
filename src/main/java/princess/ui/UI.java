package princess.ui;

import princess.task.Task;
import princess.task.TaskList;

/**
 * The UI class handles the display of messages and dividers in the terminal.
 * It provides methods for showing welcome messages, help messages, and task-related information.
 */
public class UI {

    // Common UI Messages
    public static final String SPACE = "     ";
    public static final String MESSAGE_LIST_HEADER = "     Here are the tasks in your list for your Princess!\n";
    public static final String MESSAGE_EMPTY_LIST = "     " + "      ---there is nothing in your list---\n";


    /**
     * Constructs a UI object.
     */
    public UI() {
    }

    /**
     * Displays a divider line for formatting in the terminal.
     */
    public String showDivider() {
        return "    ____________________________________________________________\n";
    }

    /**
     * Displays a welcome message to the user.
     * This method also shows a divider before and after the message.
     */
    public String showWelcomeMessage() {
        return "     Hello! I'm your Beautiful princess\n"
            + "     What can I do for you?\n";

    }

    /**
     * Displays a help message with a list of available commands for the user.
     */
    public String showHelpMessage() {
        return "     below are the commands! Command me boi!\n"
                + "       list\n"
                + "       delete [index]\n"
                + "       mark [index]\n"
                + "       unmark [index]\n"
                + "       todo [taskname] /at [place]\n"
                + "       deadline [taskname] /by [deadline] /at [place]\n"
                + "       event [taskname] /from [date/time] /to [date/time] /at [place]\n"
                + "               (note: /at command is optional)\n"
                + "       find [keyword]\n"
                + "       bye";
    }

    /**
     * Displays an ending divider line for formatting in the terminal.
     */
    public String showEndingDivider() {
        return showDivider() + "\n";
    }

    /**
     * Displays an ending message to the user, indicating the program is about to end.
     * A divider is shown before and after the message.
     */
    public String showEndingMessage() {
        return "     Bye. Hope to see you again soon!\n";
    }

    public String showTaskAdded(Task task, TaskList taskList) {
        assert task != null : "Task cannot be null";
        assert taskList != null : "TaskList cannot be null";

        taskList.addElem(task);
        return "     " + "Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + taskList.getSize() + " tasks in the list.\n";
    }

    public String showInvalidCommandMessage() {
        return "     " + "OH NOOO!!! I don't understand what that means... "
                + "type 'help' for help";
    }

    public void println(String str) {
        System.out.println(str);
    }



}
