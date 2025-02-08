package princess.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {

    private UI ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ui = new UI();
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
    }

    @Test
    public void testShowWelcomeMessage() {
        ui.showWelcomeMessage();
        String output = outputStream.toString();
        assertTrue(output.contains("Hello! I'm your Beautiful princess"), "Welcome message is incorrect.");
    }

    @Test
    public void testShowHelpMessage() {
        ui.showHelpMessage();
        String output = outputStream.toString();
        assertTrue(output.contains("list"), "Help message should contain 'list' command.");
        assertTrue(output.contains("delete [index]"), "Help message should contain 'delete' command.");
    }

    @Test
    public void testShowEndingMessage() {
        ui.showEndingMessage();
        String output = outputStream.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"), "Ending message is incorrect.");
    }



}
