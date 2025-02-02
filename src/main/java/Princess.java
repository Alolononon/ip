import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;



public class Princess {
    public static void main(String[] args) {

        ArrayList<Task> storage = new ArrayList<>();


        Scanner sc = new Scanner(System.in);

        String filePath = "data/saved_tasks.txt";

        FileStoringRetrieving.ensureFileExists(filePath);
        FileStoringRetrieving.loadTasksFromFile(filePath, storage);





        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm your Beautiful Princess");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();

        while (true) {
            try {
                String input = sc.nextLine(); String[] inputss = input.split(" "); String code = inputss[0];
                Task task;



                if (input.equals("bye"))    //exit loop
                    break;

                System.out.println("    ____________________________________________________________");

                if (input.equals("list")) {     //listing out the storage
                    System.out.println("     Here are the tasks in your list for your princess!");
                    int num = 1;
                    if (storage.isEmpty()) {
                        System.out.println("     " + "      ---there is nothing in your list---");
                    }
                    for (Task elem : storage) {
                        System.out.println("     " + Integer.toString(num++) + "." +
                                elem.toString());
                    }

                } else if (code.equals("delete")) {
                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
                    else if (!isInteger(inputss[1]) || Integer.parseInt(inputss[1]) < 1 || Integer.parseInt(inputss[1]) > storage.size())
                        throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

                    int elemNum = Integer.parseInt(inputss[1]);
                    System.out.println("     Alrighty, Princess have removed this task:");
                    System.out.println("       " + storage.get(elemNum-1).toString());
                    storage.remove(elemNum-1);
                    System.out.printf("     Now you have %d tasks in the list.\n", storage.size());


                } else if (code.equals("mark")) {
                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
                    else if (!isInteger(inputss[1]) || Integer.parseInt(inputss[1]) < 1 || Integer.parseInt(inputss[1]) > storage.size())
                        throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

                    int elemNum = Integer.parseInt(inputss[1]);
                    Task elem = storage.get(elemNum-1);
                    elem.markTask();
                    System.out.println("     Nice! You are the best! Princess have help you marked this task as done:");
                    System.out.println("       " + elem.toString());

                } else if (code.equals("unmark")) {
                      if (inputss.length < 2)
                          throw new PrincessException("     " + "OH NOOO!!! There is missing value. Please input a value.");
                      else if (!isInteger(inputss[1]) || Integer.parseInt(inputss[1]) < 1 ||
                              Integer.parseInt(inputss[1]) > storage.size())
                          throw new PrincessException("     " + "OH NOOO!!! INVALID input!! Please input a proper value.");

                      int elemNum = Integer.parseInt(inputss[1]);
                      Task elem = storage.get(elemNum-1);
                      elem.unmarkTask();
                      System.out.println("     Whattt?!?! Alright... Princess have marked this task as undone:");
                      System.out.println("       " + elem.toString());

                } else if (code.equals("todo") || code.equals("deadline") || code.equals("event")) {

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
                        storage.add(task);
                        System.out.printf("     Now you have %d tasks in the list.\n", storage.size());
                    } catch (IllegalArgumentException e) {
                        System.out.println("     " + e.getMessage());
                        System.out.println("     " + "try again");
                    }

                } else if (code.equals("help")) {
                    System.out.println("     " + "below are the commands! Command me boi!");
                    System.out.println("       " + "list");
                    System.out.println("       " + "delete [index]");
                    System.out.println("       " + "mark [index]");
                    System.out.println("       " + "unmark [index]");
                    System.out.println("       " + "todo [taskname]");
                    System.out.println("       " + "deadline [taskname] /by [deadline]");
                    System.out.println("       " + "evemt [taskname] /from [date/time] /to [date/time]");
                    System.out.println("       " + "bye");

                } else {
                    throw new PrincessException("     " + "OH NOOO!!! I don't understand what that means... type 'help' for help");
                }

          } catch (PrincessException e) {
            System.out.println(e.getMessage());
          }


            System.out.println("    ____________________________________________________________");
            System.out.println();

        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");


        try {
            FileStoringRetrieving.writeToFile(filePath, storage);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
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

