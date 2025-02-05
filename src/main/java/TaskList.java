import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Integer getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getElem(int index) {
        return tasks.get(index);
    }

    public void removeElem(int index) {
        tasks.remove(index);
    }

    public void addElem(Task task) {
        tasks.add(task);
    }

}
