package grw.qut.inventory.api;

public class InventoryItemIdExistsException extends RuntimeException {
    public InventoryItemIdExistsException() {
        super("Inventory Item Id already exists");
    }
}
