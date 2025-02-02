import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileStoringRetrieving {
    // Class implementation will go here

    public static void ensureFileExists(String filePath) {
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
    
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void loadTasksFromFile(String filePath, ArrayList<Task> storage) {
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




    public static void writeToFile(String filePath, ArrayList<Task> storage) throws IOException {
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