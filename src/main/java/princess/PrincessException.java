package princess;

/**
 * A custom exception for handling errors specific to the Princess application.
 */
public class PrincessException extends Exception {
    /**
     * Constructs a new <code>PrincessException</code> with the specified detail message.
     *
     * @param msg the detail message
     */
    public PrincessException(String msg) {
        super(msg);
    }
}
