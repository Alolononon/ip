package princess;
import java.util.Scanner;

import princess.command.Command;
import princess.command.Storage;
import princess.task.TaskList;
import princess.ui.UI;



/**
 * A task management application that allows users to add, delete, mark, and unmark tasks.
 * Tasks can be of type Todo, Deadline, or Event. Tasks are saved to and loaded from a file.
 */
public class Princess {
    private Storage storage;
    private UI ui;
    private TaskList taskList;
    private String filePath = "data/saved_tasks.txt";
    private Command command;

    /**
     * Creates a Princess application with the given file path to load and save tasks.
     *
     */
    public Princess() {
        storage = new Storage(filePath);
        ui = new UI();
        taskList = new TaskList(storage.loadTasksFromFile());
        command = new Command(ui);
    }

    /**
     * The main method to run the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        new Princess().run();
    }

    /**
     * Runs the task management app: displays messages, executes commands, and saves tasks.
     */
    public void run() {


        Scanner sc = new Scanner(System.in);
        String output = "";
        output = ui.showDivider() + ui.showWelcomeMessage() + ui.showDivider() + "\n";; // Display welcome message
        System.out.print(output);
        try {
            // main command
            while (true) {
                String input = sc.nextLine();

                output = ui.showDivider();
                output += command.execute(input, taskList);
                System.out.print(output);
                if (command.isExit()) {
                    break;
                }
                System.out.print(ui.showEndingDivider()); // show ending divider after every action


            }
        } catch (Exception e) {
            System.out.println("Oops! The princess has encountered a royal error. "
                    + "Time to call in the knights of debugging! \n Terminal error: " + e.getMessage());
            System.out.println(e);
        } finally {
            System.out.print(ui.showDivider());
        }

        // Save tasks to file before exiting
        try {
            storage.writeToFile(taskList.getTasks());
        } catch (Exception e) {
            System.out.println("There was an error saving your tasks: " + e.getMessage());
        }


    }


    public String getWelcomeResponse() {
        return ui.showWelcomeMessage();
    }



    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            output += command.execute(input, taskList);
        } catch (Exception e) {
            output += "Oops! The princess has encountered a royal error. "
                    + "Time to call in the knights of debugging! \n Terminal error: " + e.getMessage();
        } finally {

        }
        return output;
    }


}

