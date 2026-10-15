/** Thrown when we add an item whose ID is already used. */
public class DuplicateItemException extends CatalogException {
    public DuplicateItemException(String itemID) {
        super("An item with ID \"" + itemID + "\" already exists in the catalog.");
    }
}

