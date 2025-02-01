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

        ensureFileExists(filePath);
        loadTasksFromFile(filePath, storage);





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
                }






                else if (code.equals("todo") || code.equals("deadline") || code.equals("event")) {

                    if (inputss.length < 2)
                        throw new PrincessException("     " + "OH NOOO!!! Sweetheart, There is no task description!");

                    if (code.equals("todo")) {     //adding  new item into storage

                        System.out.println("     " + "Got it. I've added this task:");
                        String taskname = input.substring(5); task = new Todo(taskname);

                    } else if (code.equals("deadline")) {
                        String[] stringArr = input.substring(9).split("/by ");
                        if (stringArr.length < 2)
                            throw new PrincessException("     " + "OH NOOO!!! There is no Due Date! Please use '/by' to set Due Date.");

                        String taskname = stringArr[0]; String by = stringArr[1];
                        System.out.println("     " + "Got it. I've added this task:");
                        task = new Deadline(taskname, by);

                  } else {        //if (code.equals("event")) {
                      String[] stringArr = input.substring(6).split("/from | /to ");
                      if (stringArr.length < 3)
                          throw new PrincessException("     " + "OH NOOO!!! There is missing 'from' and 'to' timings! Please use '/from' and '/to'.");
                      String taskname = stringArr[0]; String from = stringArr[1]; String to = stringArr[2];

                      System.out.println("     " + "Got it. I've added this task:");
                      task = new Event(taskname, from, to);

                  }

                    System.out.println("       " + task.toString());
                    storage.add(task);
                    System.out.printf("     Now you have %d tasks in the list.\n", storage.size());


                } else {
                    throw new PrincessException("     " + "OH NOOO!!! I don't understand what that means...");
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
            writeToFile(filePath, storage);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    private static void ensureFileExists(String filePath) {
        //============================ file creation ==================
        File f = new File(filePath);
        if (f.exists()) {
//            System.out.println("File exists");
//            try {
//                printFileContents(filePath);
//            } catch (FileNotFoundException e) {
//                System.out.println("File not found");
//            }
        } else {
//            System.out.println("File does not exist");
            File folder = f.getParentFile(); // Get "data/" folder

            if (!folder.exists()) {
                folder.mkdirs(); // Create "data/" folder if missing
            }
            if (!f.exists()) {
                try {
                    f.createNewFile(); // Create "saved_tasks.txt" if missing
//                    System.out.println("Created file: " + f.toString());
                } catch (IOException e) {
                    System.out.println("Error creating file: " + e.getMessage());
                }
            }
        }
        //===============================================================================
    }


    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Successfully parsed, it's an integer
        } catch (NumberFormatException e) {
            return false; // Not an integer
        }
    }





    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void loadTasksFromFile(String filePath, ArrayList<Task> storage) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String lineArr[] = line.split(" \\| ");

                if (lineArr.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String type = lineArr[0];
                boolean isDone = lineArr[1].equals("1");
                String description = lineArr[2];
                Task task = null;

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (lineArr.length < 4)
                        task = null; // Ensure valid deadline format
                    task = new Deadline(description, lineArr[3]); // parts[3] is due date
                    break;
                case "E":
                    if (lineArr.length < 5)
                        task = null; // Ensure valid event format
                    task = new Event(description, lineArr[3], lineArr[4]); // parts[3] = from, parts[4] = to
                    break;
                }

                if (task != null) {
                    storage.add(task); // Add parsed task to storage
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }




    private static void writeToFile(String filePath, ArrayList<Task> storage) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : storage) {
            if (task instanceof Todo) {
                fw.write("T | " + (task.getIsTaskDone() ? "1" : "0") + " | " + task.getTaskName() + "\n");
            } else if (task instanceof Deadline) {
                fw.write("D | " + (task.getIsTaskDone() ? "1" : "0") + " | " + task.getTaskName()
                        + " | " + ((Deadline) task).getBy() + "\n");
            } else if (task instanceof Event) {
                fw.write("E | " + (task.getIsTaskDone() ? "1" : "0") + " | " + task.getTaskName()
                        + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + "\n");
            }
        }
//        fw.write(textToAdd);
        fw.close();
    }
}

