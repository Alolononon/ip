package princess.command;

import princess.task.Deadline;
import princess.task.Event;
import princess.task.Task;
import princess.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles file operations such as ensuring file existence,
 * reading from a file, writing to a file, and loading tasks.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
        ensureFileExists();
    }



    /**
     * Ensures that the specified file exists. If not, it creates the necessary directories and file.
     *
     */
    public void ensureFileExists() {
        assert filePath != null : "File path should not be null";
        File f = new File(filePath);
        if (!f.exists()) {
            File folder = f.getParentFile(); // Get "data/" folder

            if (!folder.exists()) {
                folder.mkdirs(); // Create "data/" folder if missing
            }
            if (!f.exists()) {
                try {
                    f.createNewFile(); // Create "saved_tasks.txt" if missing
                    assert f.exists() : "File should exist after creation";
                } catch (IOException e) {
                    System.out.println("Error creating file: " + e.getMessage());
                }
            }
        }

    }

    /**
     * Prints the contents of a specified file.
     *
     * @param filePath The path of the file to read.
     * @throws FileNotFoundException If the file does not exist.
     */
    public void printFileContents(String filePath) throws FileNotFoundException {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Loads tasks from a file and stores them in the provided ArrayList.
     *
     * @return storage an {@code ArrayList} of {@code Task} objects loaded from the file.
     *
     */
    public ArrayList<Task> loadTasksFromFile() {
        assert filePath != null : "File path should not be null";
        ArrayList<Task> storage = new ArrayList<>();
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
        return storage;
    }



    /**
     * Writes tasks from an ArrayList to a file.
     *
     * @param storage  The ArrayList of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void writeToFile(ArrayList<Task> storage) throws IOException {
        assert filePath != null : "File path should not be null";
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
        fw.close();
    }
}