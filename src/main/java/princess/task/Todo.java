package princess.task;

/**
 * Represents a Todo task.
 * A Todo is a task that only has a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Returns the string representation of the Todo task.
     *
     * @return A formatted string indicating the task type and its details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}