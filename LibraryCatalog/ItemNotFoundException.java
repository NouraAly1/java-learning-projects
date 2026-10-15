/** Thrown when we look for or remove an ID that is not in the catalog. */
public class ItemNotFoundException extends CatalogException {
    public ItemNotFoundException(String itemID) {
        super("No item with ID \"" + itemID + "\" was found in the catalog.");
    }
}
