/** A DVD. The "author" is the director. Extra detail: running time. */
public class DVD extends LibraryItem {

    private final int minutes;

    public DVD(String itemID, String title, String director, int minutes) {
        super(itemID, title, director); // reuse the author field for the director
        if (minutes <= 0) {
            throw new IllegalArgumentException("A DVD must run for at least 1 minute.");
        }
        this.minutes = minutes;
    }

    public int getMinutes() { return minutes; }

    @Override public String getType() { return "DVD"; }
    @Override public String getExtraDetails() { return minutes + " min"; }
}