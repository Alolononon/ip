import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import princess.Princess;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Princess princess;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaPrince.png"));
    private Image princessImage = new Image(this.getClass().getResourceAsStream("/images/DaPrincess.png"));

    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Display welcome message from Princess
        if (princess != null) {
            dialogContainer.getChildren().add(
                    DialogBox.getPrincessDialog(princess.getWelcomeResponse(), princessImage)
            );
        }

    }

    /** Injects the Princess instance */
    public void setPrincess(Princess p) {
        princess = p;

        // Show the welcome message when the bot is set
        dialogContainer.getChildren().add(
                DialogBox.getPrincessDialog(princess.getWelcomeResponse(), princessImage)
        );

        // Check and display file loading error message
        String errorMessage = princess.getStorage().getStorageError();
        if (!errorMessage.isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getPrincessDialog("Warning: Some saved data is corrupted and may be lost. "
                            + "Error loading task: \n"
                            + errorMessage, princessImage)
            );
        }
    }





    /**
     * Creates two dialog boxes, one echoing user input and the other containing Princess's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = princess.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPrincessDialog(response, princessImage)
        );
        userInput.clear();

        // solution below was retrieved from chatgpt at 21 feb 2025
        // original prompt: help me close a window after a slight delay
        // If user types "bye", delay closing the application
        if (princess.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2-second delay
            delay.setOnFinished(event -> Platform.exit()); // Close app after delay
            delay.play();
        }
    }
}
