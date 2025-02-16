package princess.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import princess.task.Task;
import princess.task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {

    private UI ui;

    @BeforeEach
    public void setUp() {
        ui = new UI();
    }

    @Test
    void testShowDivider() {
        assertEquals("    ____________________________________________________________\n", ui.showDivider());
    }

    @Test
    void testShowWelcomeMessage() {
        assertEquals("     Hello! I'm your Beautiful princess\n"
                + "     What can I do for you?\n", ui.showWelcomeMessage());
    }

    @Test
    void testShowHelpMessage() {
        assertEquals("     below are the commands! Command me boi!\n"
                + "       list\n"
                + "       delete [index]\n"
                + "       mark [index]\n"
                + "       unmark [index]\n"
                + "       todo [taskname]\n"
                + "       deadline [taskname] /by [deadline]\n"
                + "       event [taskname] /from [date/time] /to [date/time]\n"
                + "       find [keyword]\n"
                + "       bye", ui.showHelpMessage());
    }

    @Test
    void testShowEndingDivider() {
        assertEquals("    ____________________________________________________________\n\n", ui.showEndingDivider());
    }

    @Test
    void testShowEndingMessage() {
        assertEquals("     Bye. Hope to see you again soon!\n", ui.showEndingMessage());
    }

    @Test
    void testShowTaskAdded() {
        // Creating real instances instead of mocks
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Task("Read book");

        // Call the method
        String result = ui.showTaskAdded(task, taskList);

        // Expected result
        String expected = "     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + taskList.getSize() + " tasks in the list.\n";

        assertEquals(expected, result);
    }


}
