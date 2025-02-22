package princess.task;

/**
 * Represents a named location.
 */
public class Place {

    private String placeName = null;

    /**
     * Creates a new Place without a specified name.
     */
    public Place() {
    }

    /**
     * Sets the name of this place.
     *
     * @param placeName the name of this place
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    /**
     * Returns the name of the place.
     *
     * @return The place name.
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * Returns a string representation of this place.
     *
     * @return a string containing the place name if set, or an empty string otherwise
     */
    @Override
    public String toString() {
        if (placeName != null) {
            return " (at: " + placeName + ")";
        } else {
            return "";
        }
    }

}
