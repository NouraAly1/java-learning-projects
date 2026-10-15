import java.util.List;

/**
 * Plain Java tests (no JUnit install needed). Each check prints PASS or FAIL
 * and we count them, so the screenshot clearly shows the result.
 */
public class CatalogTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAddAndGet();
        testDuplicateId();
        testRemove();
        testRemoveMissing();
        testGetMissing();
        testMixedTypes();
        testTypedCatalogs();
        testFindByAuthor();
        testFindByType();
        testBadInput();

        System.out.println();
        System.out.println("Result: " + passed + " passed, " + failed + " failed.");
    }

    // ----- helper: one place that prints and counts, so tests stay short -----
    private static void check(String name, boolean condition) {
        if (condition) { passed++; System.out.println("PASS: " + name); }
        else           { failed++; System.out.println("FAIL: " + name); }
    }

    private static void testAddAndGet() {
        Catalog<Book> c = new Catalog<>();
        try {
            c.addItem(new Book("B1", "Clean Code", "Robert Martin", 464));
            check("add a book, size becomes 1", c.size() == 1);
            check("get the book back by ID", c.getItem("B1").getTitle().equals("Clean Code"));
        } catch (CatalogException e) {
            check("add and get should not throw", false);
        }
    }

    private static void testDuplicateId() {
        Catalog<Book> c = new Catalog<>();
        try {
            c.addItem(new Book("B1", "Clean Code", "Robert Martin", 464));
            c.addItem(new Book("B1", "Other Book", "Someone", 100));
            check("duplicate ID is rejected", false); // should never get here
        } catch (DuplicateItemException e) {
            check("duplicate ID is rejected", c.size() == 1);
        }
    }

    private static void testRemove() {
        Catalog<DVD> c = new Catalog<>();
        try {
            c.addItem(new DVD("D1", "Inception", "Christopher Nolan", 148));
            DVD removed = c.removeItem("D1");
            check("remove returns the removed DVD", removed.getTitle().equals("Inception"));
            check("catalog is empty after removing", c.isEmpty());
        } catch (CatalogException e) {
            check("remove should not throw", false);
        }
    }

    private static void testRemoveMissing() {
        Catalog<Book> c = new Catalog<>();
        try {
            c.removeItem("NOPE");
            check("removing a missing item throws", false);
        } catch (ItemNotFoundException e) {
            check("removing a missing item throws with a clear message",
                    e.getMessage().contains("NOPE"));
        }
    }

    private static void testGetMissing() {
        Catalog<Book> c = new Catalog<>();
        try {
            c.getItem("NOPE");
            check("getting a missing item throws", false);
        } catch (ItemNotFoundException e) {
            check("getting a missing item throws", true);
        }
    }

    private static void testMixedTypes() {
        Catalog<LibraryItem> c = new Catalog<>();
        try {
            c.addItem(new Book("B1", "Clean Code", "Robert Martin", 464));
            c.addItem(new DVD("D1", "Inception", "Christopher Nolan", 148));
            c.addItem(new Magazine("M1", "National Geographic", "Editor Team", 12));
            check("one catalog holds book + DVD + magazine", c.size() == 3);
            Catalog.printAll(c.getAllItems()); // shows all three on screen
        } catch (CatalogException e) {
            check("mixed catalog should not throw", false);
        }
    }

    private static void testTypedCatalogs() {
        // Catalog<Magazine> would refuse a Book at COMPILE time; we can only
        // prove the good side here, because the bad line would not compile.
        Catalog<Magazine> c = new Catalog<>();
        try {
            c.addItem(new Magazine("M1", "Wired", "Editor Team", 5));
            check("Catalog<Magazine> accepts a magazine", c.size() == 1);
        } catch (CatalogException e) {
            check("typed catalog should not throw", false);
        }
    }

    private static void testFindByAuthor() {
        Catalog<LibraryItem> c = new Catalog<>();
        try {
            c.addItem(new Book("B1", "Book One", "Ann Lee", 100));
            c.addItem(new DVD("D1", "Film One", "Ann Lee", 90));
            c.addItem(new Book("B2", "Book Two", "Bob Ray", 200));
            check("findByAuthor finds 2 items (case ignored)", c.findByAuthor("ann lee").size() == 2);
            check("findByAuthor returns empty for unknown author", c.findByAuthor("Nobody").isEmpty());
        } catch (CatalogException e) {
            check("findByAuthor setup should not throw", false);
        }
    }

    private static void testFindByType() {
        Catalog<LibraryItem> c = new Catalog<>();
        try {
            c.addItem(new Book("B1", "Book One", "Ann Lee", 100));
            c.addItem(new DVD("D1", "Film One", "Ann Lee", 90));
            List<DVD> dvds = c.findByType(DVD.class); // no cast needed
            check("findByType(DVD.class) returns only the DVD",
                    dvds.size() == 1 && dvds.get(0).getMinutes() == 90);
        } catch (CatalogException e) {
            check("findByType setup should not throw", false);
        }
    }

    private static void testBadInput() {
        try {
            new Book("B1", "", "Author", 100);
            check("blank title is rejected", false);
        } catch (IllegalArgumentException e) {
            check("blank title is rejected", true);
        }
        try {
            new Book("B1", "Title", "Author", -5);
            check("negative pages are rejected", false);
        } catch (IllegalArgumentException e) {
            check("negative pages are rejected", true);
        }
    }
}
