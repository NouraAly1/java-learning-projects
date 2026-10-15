/**
 * Parent of all catalog problems. It is a checked exception on purpose:
 * Java forces the caller to deal with it, so errors like "item not found"
 * cannot be ignored by accident.
 */
public class CatalogException extends Exception {
    public CatalogException(String message) {
        super(message);
    }
}
