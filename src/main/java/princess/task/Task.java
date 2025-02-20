package princess.task;

/**
 * Represents a task with a name and a completion status.
 */
public class Task {
    private String taskName;
    private boolean isDone;
    private Place place;

    /**
     * Constructs a new Task with the specified name.
     * By default, the task is not completed.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        assert taskName != null && !taskName.trim().isEmpty() : "Task name cannot be null or empty";
        this.taskName = taskName;
        this.isDone = false;
        this.place = new Place();
    }

    /**
     * Gets the name or description of the task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        assert this.taskName != null : "Task name should never be null";
        return this.taskName;
    }

    /**
     * Checks if the task is completed.
     *
     * @return {@code true} if the task is done, otherwise {@code false}.
     */
    public boolean getIsTaskDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }


    public void setPlace(String place) {
        this.place.setPlaceName(place);
    }

    public Place getPlace() {
        return this.place;
    }


    /**
     * Returns the string representation of the task,
     * indicating its completion status and name.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
    }

}
