/** A magazine. The "author" is the editor. Extra detail: issue number. */
public class Magazine extends LibraryItem {

    private final int issueNumber;

    public Magazine(String itemID, String title, String editor, int issueNumber) {
        super(itemID, title, editor);
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be 1 or more.");
        }
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }

    @Override public String getType() { return "Magazine"; }
    @Override public String getExtraDetails() { return "issue " + issueNumber; }
}
