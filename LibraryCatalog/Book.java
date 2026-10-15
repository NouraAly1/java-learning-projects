/** A book. Extra detail: number of pages. */
public class Book extends LibraryItem {

    private final int pages;

    public Book(String itemID, String title, String author, int pages) {
        super(itemID, title, author);
        if (pages <= 0) {
            throw new IllegalArgumentException("A book must have at least 1 page.");
        }
        this.pages = pages;
    }

    public int getPages() { return pages; }

    @Override public String getType() { return "Book"; }
    @Override public String getExtraDetails() { return pages + " pages"; }
}
