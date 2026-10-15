/**
 * Base class for everything the library can lend out.
 * It is abstract because a plain "item" does not exist in real life:
 * it is always a book, a DVD or a magazine.
 */
public abstract class LibraryItem {

    // final: an item's ID and details should not change after it is created,
    // otherwise the catalog's lookup key could get out of sync with the item.
    private final String itemID;
    private final String title;
    private final String author;

    public LibraryItem(String itemID, String title, String author) {
        // Fail early with a clear message instead of storing bad data.
        if (itemID == null || itemID.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be empty.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        this.itemID = itemID.trim();
        this.title = title.trim();
        this.author = author.trim();
    }

    public String getItemID() { return itemID; }
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }

    /** Each subclass says what kind of item it is ("Book", "DVD"...). */
    public abstract String getType();

    /** Each subclass adds its own extra details (pages, length...). */
    public abstract String getExtraDetails();

    @Override
    public String toString() {
        return getType() + " [" + itemID + "] \"" + title + "\" by " + author
                + " (" + getExtraDetails() + ")";
    }
}