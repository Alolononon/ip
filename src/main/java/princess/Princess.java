package princess;


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



    public static void main(String[] args) {
        String filePath = "data/saved_tasks.txt";
        new Princess(filePath).run();
    }

    public Princess(String filePath) {
        storage = new Storage(filePath);
        ui = new UI();
        taskList = new TaskList(storage.loadTasksFromFile());
    }

    public void run() {



        ui.showWelcomeMessage();    // Display welcome message

        // main command
        try {
            Command command = new Command();
            command.execute(ui, storage, taskList);
        } catch (Exception e){
            System.out.println("Oops! The princess has encountered a royal error. " +
                    "Time to call in the knights of debugging! \n Terminal error: " + e.getMessage());
            ui.showEndingDivider();
        } finally {
            ui.showEndingMessage();         // show exiting message when closing app
        }

        // Save tasks to file before exiting
        try {
            storage.writeToFile(taskList.getTasks());
        } catch (Exception e) {
            System.out.println("There was an error saving your tasks: " + e.getMessage());
        }


    }








}

