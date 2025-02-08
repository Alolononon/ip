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
    public void showDivider() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a welcome message to the user.
     * This method also shows a divider before and after the message.
     */
    public void showWelcomeMessage() {
        showDivider();
        System.out.println("     Hello! I'm your Beautiful princess");
        System.out.println("     What can I do for you?");
        showDivider();
        System.out.println();
    }

    /**
     * Displays a help message with a list of available commands for the user.
     */
    public void showHelpMessage() {
        System.out.println("     " + "below are the commands! Command me boi!");
        System.out.println("       " + "list");
        System.out.println("       " + "delete [index]");
        System.out.println("       " + "mark [index]");
        System.out.println("       " + "unmark [index]");
        System.out.println("       " + "todo [taskname]");
        System.out.println("       " + "deadline [taskname] /by [deadline]");
        System.out.println("       " + "event [taskname] /from [date/time] /to [date/time]");
        System.out.println("       " + "find [keyword]");
        System.out.println("       " + "bye");
    }

    /**
     * Displays an ending divider line for formatting in the terminal.
     */
    public void showEndingDivider() {
        showDivider();
        System.out.println();
    }

    /**
     * Displays an ending message to the user, indicating the program is about to end.
     * A divider is shown before and after the message.
     */
    public void showEndingMessage() {
        showDivider();
        System.out.println("     Bye. Hope to see you again soon!");
        showDivider();
    }


    public void showTaskAdded(Task task, TaskList taskList) {
        System.out.println("     " + "Got it. I've added this task:");

        System.out.println("       " + task.toString());
        taskList.addElem(task);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.getSize());

    }

}
