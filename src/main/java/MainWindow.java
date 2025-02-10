import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
                    DialogBox.getDukeDialog(princess.getWelcomeResponse(), princessImage)
            );
        }
    }

    /** Injects the Duke instance */
    public void setDuke(Princess p) {
        princess = p;

        // Show the welcome message when the bot is set
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(princess.getWelcomeResponse(), princessImage)
        );
    }





    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = princess.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, princessImage)
        );
        userInput.clear();
    }
}
