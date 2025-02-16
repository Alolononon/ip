package princess.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Integer getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        assert tasks != null : "Task list should never be null";
        return tasks;
    }

    public Task getElem(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds when retrieving task";
        return tasks.get(index);
    }

    public void removeElem(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds when removing task";
        tasks.remove(index);
    }

    public void addElem(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
    }

}
