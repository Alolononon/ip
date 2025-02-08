package princess.command;

import princess.PrincessException;
import princess.ui.UI;
import princess.task.*;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles different user commands and executes the corresponding actions.
 */
public class Command {

    UI ui;

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
    public void execute(TaskList taskList) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine(); String[] inputParts = input.split(" "); String code = inputParts[0];

                // Exit the application
                if (input.equals("bye")) {
                    break;
                }

                ui.showDivider();

                if (input.equals("list")) {
                    handleListCommand(taskList);
                } else if (code.equals("delete")) {
                    handleDeleteCommand(taskList, inputParts);
                } else if (code.equals("mark")) {
                    handleMarkCommand(taskList, inputParts);
                } else if (code.equals("unmark")) {
                    handleUnmarkCommand(taskList, inputParts);
                } else if (code.equals("todo") || code.equals("deadline") || code.equals("event")) {
                    try {
                        if (code.equals("todo")) {     //adding  new item into storage
                            handleAddToDo(inputParts, input, taskList);
                        } else if (code.equals("deadline")) {
                            handleAddDeadline(inputParts,input, taskList);
                        } else if (code.equals("event")) {
                            handleAddEvent(inputParts, input, taskList);
                        }
                    } catch (IllegalArgumentException e) {          // throw error for invalid date format
                        System.out.println("     " + e.getMessage());
                        System.out.println("     " + "try again");
                    }
                } else if (code.equals("help")) {
                    ui.showHelpMessage();       // Display help message
                } else if (code.equals("find")) {
                    handleFindFunction(inputParts, input, taskList);
                } else {
                    throw new PrincessException("     " + "OH NOOO!!! I don't understand what that means... " +
                            "type 'help' for help");
                }

            } catch (PrincessException e) {
                System.out.println(e.getMessage());
            }

            ui.showEndingDivider();     // show ending divider after every action
        }
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList The list of tasks to be displayed.
     */
    private static void handleListCommand(TaskList taskList) {
        //listing out all tasks
        System.out.println("     Here are the tasks in your list for your Princess!");
        int num = 1;
        if (taskList.isEmpty()) {
            System.out.println("     " + "      ---there is nothing in your list---");
        }
        for (Task elem : taskList.getTasks()) {
            System.out.println("     " + Integer.toString(num++) + "." +
                    elem.toString());
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
        if (inputParts.length < 2)
            throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
        else if (!isInteger(inputParts[1]) || Integer.parseInt(inputParts[1]) < 1 ||
                Integer.parseInt(inputParts[1]) > taskList.getSize())
            throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

        int elemNum = Integer.parseInt(inputParts[1]);
        System.out.println("     Alrighty, Princess have removed this task:");
        System.out.println("       " + taskList.getElem(elemNum-1).toString());
        taskList.removeElem(elemNum-1);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.getSize());
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
        if (inputParts.length < 2)
            throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
        else if (!isInteger(inputParts[1]) || Integer.parseInt(inputParts[1]) < 1 ||
                Integer.parseInt(inputParts[1]) > taskList.getSize())
            throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

        int elemNum = Integer.parseInt(inputParts[1]);
        Task elem = taskList.getElem(elemNum-1);
        elem.markTask();
        System.out.println("     Nice! You are the best! Princess have help you marked this task as done:");
        System.out.println("       " + elem.toString());
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
        if (inputParts.length < 2)
            throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
        else if (!isInteger(inputParts[1]) || Integer.parseInt(inputParts[1]) < 1 ||
                Integer.parseInt(inputParts[1]) > taskList.getSize())
            throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

        int elemNum = Integer.parseInt(inputParts[1]);
        Task elem = taskList.getElem(elemNum-1);
        elem.unmarkTask();
        System.out.println("     Whattt?!?! Alright... Princess have marked this task as undone:");
        System.out.println("       " + elem.toString());
    }

    /**
     * Adds a ToDo task.
     *
     * @param inputParts The command input split into parts.
     * @param input The full user input.
     * @param taskList The list of tasks.
     * @throws PrincessException If the input is invalid.
     */
    private void handleAddToDo(String[] inputParts, String input, TaskList taskList) throws PrincessException{
        if (inputParts.length < 2) {
            throw new PrincessException("     " + "OH NOOO!!! Sweetheart, There is no task description!");
        }

        String taskname = input.substring(5);

        Task newTask = new Todo(taskname);
        ui.showTaskAdded(newTask, taskList);
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
        if (inputParts.length < 4 || !input.contains("/by")) {
            throw new PrincessException("     " + "OH NOOO!!! Sweetheart, please follow the deadline format: " +
                    "deadline [task name] /by [deadline]");
        }

        String[] stringArr = input.substring(9).split("/by ");

        String taskName = stringArr[0];
        String by = stringArr[1];

        Task newTask = new Deadline(taskName, by);
        ui.showTaskAdded(newTask, taskList);
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
        if (inputParts.length < 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new PrincessException("     " + "OH NOOO!!! Sweetheart, please follow the deadline format: " +
                    "event [taskname] /from [date/time] /to [date/time]");
        }

        String[] stringArr = input.substring(6).split("/from | /to ");

        String taskName = stringArr[0];
        String from = stringArr[1];
        String to = stringArr[2];


        Task newTask = new Event(taskName, from, to);
        ui.showTaskAdded(newTask, taskList);
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
        if (inputParts.length < 2)
            throw new PrincessException("     " + "OH NOOO!!! There is missing keyword. Please input a keyword to search.");

        String keyword = input.substring(5).toLowerCase();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task eachtask : taskList.getTasks()) {
            if (eachtask.getTaskName().toLowerCase().contains(keyword)) {
                matchingTasks.add(eachtask);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("     No matching tasks found.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + matchingTasks.get(i));
            }
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
