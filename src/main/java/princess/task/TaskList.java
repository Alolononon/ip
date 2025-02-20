package princess.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * The collection of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the specified initial tasks.
     *
     * @param tasks the initial list of tasks
     * @throws IllegalArgumentException if tasks is null
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public Integer getSize() {
        return tasks.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return the underlying list of tasks
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null : "Task list should never be null";
        return tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getElem(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds when retrieving task";
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index the index of the task to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void removeElem(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds when removing task";
        tasks.remove(index);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if the task is null
     */
    public void addElem(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
    }

}
