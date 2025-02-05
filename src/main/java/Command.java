import java.util.ArrayList;
import java.util.Scanner;

public class Command {



    public Command() {
    }

    public void execute(UI ui, Storage storage, TaskList taskList) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine(); String[] inputss = input.split(" "); String code = inputss[0];
                Task task;


                // Exit the application
                if (input.equals("bye"))
                    break;

                ui.showDivider();

                if (input.equals("list")) {
                    //listing out all tasks
                    System.out.println("     Here are the tasks in your list for your princess!");
                    int num = 1;
                    if (taskList.isEmpty()) {
                        System.out.println("     " + "      ---there is nothing in your list---");
                    }
                    for (Task elem : taskList.getTasks()) {
                        System.out.println("     " + Integer.toString(num++) + "." +
                                elem.toString());
                    }

                } else if (code.equals("delete")) {
                    // Delete a task
                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
                    else if (!isInteger(inputss[1]) || Integer.parseInt(inputss[1]) < 1 ||
                            Integer.parseInt(inputss[1]) > taskList.getSize())
                        throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

                    int elemNum = Integer.parseInt(inputss[1]);
                    System.out.println("     Alrighty, Princess have removed this task:");
                    System.out.println("       " + taskList.getElem(elemNum-1).toString());
                    taskList.removeElem(elemNum-1);
                    System.out.printf("     Now you have %d tasks in the list.\n", taskList.getSize());


                } else if (code.equals("mark")) {
                    // Mark a task as done
                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
                    else if (!isInteger(inputss[1]) || Integer.parseInt(inputss[1]) < 1 ||
                            Integer.parseInt(inputss[1]) > taskList.getSize())
                        throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

                    int elemNum = Integer.parseInt(inputss[1]);
                    Task elem = taskList.getElem(elemNum-1);
                    elem.markTask();
                    System.out.println("     Nice! You are the best! Princess have help you marked this task as done:");
                    System.out.println("       " + elem.toString());

                } else if (code.equals("unmark")) {
                    // Mark a task as undone
                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
                    else if (!isInteger(inputss[1]) || Integer.parseInt(inputss[1]) < 1 ||
                            Integer.parseInt(inputss[1]) > taskList.getSize())
                        throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

                    int elemNum = Integer.parseInt(inputss[1]);
                    Task elem = taskList.getElem(elemNum-1);
                    elem.unmarkTask();
                    System.out.println("     Whattt?!?! Alright... Princess have marked this task as undone:");
                    System.out.println("       " + elem.toString());

                } else if (code.equals("todo") || code.equals("deadline") || code.equals("event")) {
                    // Add a new task

                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! Sweetheart, There is no task description!");

                    try {
                        if (code.equals("todo")) {     //adding  new item into storage

                            System.out.println("     " + "Got it. I've added this task:");
                            String taskname = input.substring(5);
                            task = new Todo(taskname);

                        } else if (code.equals("deadline")) {
                            String[] stringArr = input.substring(9).split("/by ");
                            if (stringArr.length < 2)
                                throw new PrincessException("     " + "OH NOOO!!! There is no Due Date! Please use '/by' to set Due Date.");

                            String taskname = stringArr[0];
                            String by = stringArr[1];

                            task = new Deadline(taskname, by);
                            System.out.println("     " + "Got it. I've added this task:");


                        } else {        //if (code.equals("event")) {
                            String[] stringArr = input.substring(6).split("/from | /to ");
                            if (stringArr.length < 3)
                                throw new PrincessException("     " + "OH NOOO!!! There is missing 'from' and 'to' timings! Please use '/from' and '/to'.");
                            String taskname = stringArr[0];
                            String from = stringArr[1];
                            String to = stringArr[2];

                            System.out.println("     " + "Got it. I've added this task:");
                            task = new Event(taskname, from, to);

                        }

                        System.out.println("       " + task.toString());
                        taskList.addElem(task);
                        System.out.printf("     Now you have %d tasks in the list.\n", taskList.getSize());
                    } catch (IllegalArgumentException e) {
                        System.out.println("     " + e.getMessage());
                        System.out.println("     " + "try again");
                    }


                } else if (code.equals("help")) {

                    ui.showHelpMessage();       // Display help message

                } else if (code.equals("find")) {
                    if (inputss.length < 2)
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


                } else {
                    throw new PrincessException("     " + "OH NOOO!!! I don't understand what that means... type 'help' for help");
                }

            } catch (PrincessException e) {
                System.out.println(e.getMessage());
            }

            ui.showEndingDivider();     // show ending divider after every action

        }
    }


    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Successfully parsed, it's an integer
        } catch (NumberFormatException e) {
            return false; // Not an integer
        }
    }


}
