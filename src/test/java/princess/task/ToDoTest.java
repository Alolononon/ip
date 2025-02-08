package princess.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    /**
     * Tests the constructor and ensures the description is correctly set.
     */
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Buy milk");
        assertEquals("[T][ ] Buy milk", todo.toString());
    }

    /**
     * Tests marking a Todo task as done.
     */
    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Buy milk");
        todo.markTask();
        assertEquals("[T][X] Buy milk", todo.toString());
    }

    /**
     * Tests unmarking a Todo task.
     */
    @Test
    public void testUnmark() {
        Todo todo = new Todo("Buy milk");
        todo.markTask();
        todo.unmarkTask();
        assertEquals("[T][ ] Buy milk", todo.toString(), "Todo should be unmarked.");
    }

}
