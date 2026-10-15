import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Simple command-line menu for the catalog. */
public class CatalogApp {

    // One catalog of the parent type, so it can hold every kind of item.
    private final Catalog<LibraryItem> catalog = new Catalog<>();
    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new CatalogApp().run();
    }

    private void run() {
        System.out.println("=== Library Catalog ===");
        boolean running = true;
        while (running) {
            printMenu();
            try {
                String choice = ask("Choose an option: ");
                switch (choice) {
                    case "1" -> addBook();
                    case "2" -> addDvd();
                    case "3" -> addMagazine();
                    case "4" -> removeItem();
                    case "5" -> viewAll();
                    case "6" -> viewDetails();
                    case "7" -> searchByAuthor();
                    case "0" -> running = false;
                    default  -> System.out.println("Please choose a number from the menu.");
                }
            } catch (CatalogException e) {
                // Our own, expected problems: show the message and keep going.
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                // Bad input such as a blank title or "abc" instead of a number.
                System.out.println("Invalid input: " + e.getMessage());
            } catch (NoSuchElementException e) {
                // Input ended (Ctrl+D / Ctrl+Z): stop instead of crashing.
                running = false;
            }
        }
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1) Add a book");
        System.out.println("2) Add a DVD");
        System.out.println("3) Add a magazine");
        System.out.println("4) Remove an item");
        System.out.println("5) View all items");
        System.out.println("6) View one item's details");
        System.out.println("7) Search by author");
        System.out.println("0) Exit");
    }

    private void addBook() throws CatalogException {
        String id = ask("ID: ");
        String title = ask("Title: ");
        String author = ask("Author: ");
        int pages = askInt("Pages: ");
        catalog.addItem(new Book(id, title, author, pages));
        System.out.println("Book added.");
    }

    private void addDvd() throws CatalogException {
        String id = ask("ID: ");
        String title = ask("Title: ");
        String director = ask("Director: ");
        int minutes = askInt("Length in minutes: ");
        catalog.addItem(new DVD(id, title, director, minutes));
        System.out.println("DVD added.");
    }

    private void addMagazine() throws CatalogException {
        String id = ask("ID: ");
        String title = ask("Title: ");
        String editor = ask("Editor: ");
        int issue = askInt("Issue number: ");
        catalog.addItem(new Magazine(id, title, editor, issue));
        System.out.println("Magazine added.");
    }

    private void removeItem() throws CatalogException {
        LibraryItem removed = catalog.removeItem(ask("ID to remove: "));
        System.out.println("Removed: " + removed);
    }

    private void viewAll() {
        System.out.println("Items in catalog (" + catalog.size() + "):");
        Catalog.printAll(catalog.getAllItems());
    }

    private void viewDetails() throws CatalogException {
        System.out.println(catalog.getItem(ask("ID to view: ")));
    }

    private void searchByAuthor() {
        List<LibraryItem> found = catalog.findByAuthor(ask("Author: "));
        Catalog.printAll(found);
    }

    // ---------- Small input helpers (avoid repeating code) ----------

    private String ask(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private int askInt(String prompt) {
        String text = ask(prompt);
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            // Turn it into IllegalArgumentException so run() has one catch for bad input.
            throw new IllegalArgumentException("\"" + text + "\" is not a whole number.");
        }
    }
}

