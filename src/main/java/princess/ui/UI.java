package princess.ui;

import princess.task.Task;
import princess.task.TaskList;

/**
 * The UI class handles the display of messages and dividers in the terminal.
 * It provides methods for showing welcome messages, help messages, and task-related information.
 */
public class UI {

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
                + "       todo [taskname]\n"
                + "       deadline [taskname] /by [deadline]\n"
                + "       event [taskname] /from [date/time] /to [date/time]\n"
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
        taskList.addElem(task);
        return "     " + "Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + taskList.getSize() + " tasks in the list.\n";

    }

    public void println(String str) {
        System.out.println(str);
    }

}
