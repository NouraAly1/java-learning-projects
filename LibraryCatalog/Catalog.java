import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A generic catalog. T is the kind of item it stores.
 *   Catalog<Book>        -> only books
 *   Catalog<LibraryItem> -> any mix of books, DVDs and magazines
 * "extends LibraryItem" is the bound: it guarantees every T has getItemID(),
 * getTitle() and getAuthor(), so we can use them without casting.
 */
public class Catalog<T extends LibraryItem> {

    // Map with itemID as key: lookup and duplicate checks are fast, and
    // LinkedHashMap keeps the order in which items were added (nicer output).
    private final Map<String, T> items = new LinkedHashMap<>();

    /** Adds an item. Throws if the ID is already used. */
    public void addItem(T item) throws DuplicateItemException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item.");
        }
        if (items.containsKey(item.getItemID())) {
            throw new DuplicateItemException(item.getItemID());
        }
        items.put(item.getItemID(), item);
    }

    /** Removes an item by ID and returns it. Throws if it does not exist. */
    public T removeItem(String itemID) throws ItemNotFoundException {
        T removed = items.remove(itemID == null ? null : itemID.trim());
        if (removed == null) { // remove() gives null when the key is missing
            throw new ItemNotFoundException(itemID);
        }
        return removed;
    }

    /** Gets one item by ID. Throws if it does not exist. */
    public T getItem(String itemID) throws ItemNotFoundException {
        T found = items.get(itemID == null ? null : itemID.trim());
        if (found == null) {
            throw new ItemNotFoundException(itemID);
        }
        return found;
    }

    /** Returns a COPY of the items, so outside code cannot change our map. */
    public List<T> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public int size() { return items.size(); }

    public boolean isEmpty() { return items.isEmpty(); }

    // ---------- Generic methods ----------

    /**
     * Generic method #1: finds items by author and returns them as a list of T.
     * The return type matches the catalog's type, so Catalog<Book> gives
     * back List<Book> with no casting.
     */
    public List<T> findByAuthor(String author) {
        List<T> matches = new ArrayList<>();
        for (T item : items.values()) {
            if (item.getAuthor().equalsIgnoreCase(author.trim())) {
                matches.add(item);
            }
        }
        return matches;
    }

    /**
     * Generic method #2: finds only items of one subclass, e.g. all DVDs.
     * R is any subtype of T; Class<R> is a "type token" so Java knows
     * which subclass to filter on (generics are erased at run time).
     */
    public <R extends T> List<R> findByType(Class<R> type) {
        List<R> matches = new ArrayList<>();
        for (T item : items.values()) {
            if (type.isInstance(item)) {
                matches.add(type.cast(item)); // safe: we just checked
            }
        }
        return matches;
    }

    /**
     * Generic method #3 (static): prints any collection of library items.
     * "? extends LibraryItem" means "a collection of Books, DVDs..., read-only".
     * That is why one method works for a List<Book> and a List<LibraryItem>.
     */
    public static void printAll(Collection<? extends LibraryItem> toPrint) {
        if (toPrint.isEmpty()) {
            System.out.println("  (nothing to show)");
            return;
        }
        for (LibraryItem item : toPrint) {
            System.out.println("  " + item);
        }
    }
}
