package princess.task;

public class Place {

    private String placeName = null;

    public Place() {
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String toString() {
        if (placeName != null) {
            return " (at: " + placeName + ")";
        } else {
            return "";
        }
    }

}
