package konstructs.api.messages;

import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Message that deletes a previously created inventory.
 * @see CreateInventory
 */
public class DeleteInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;

    /**
     * Create an immutable message.
     * @param blockId The id of the bock that contains the inventory
     * @param inventoryId The id of the inventory
     */
    public DeleteInventory(UUID blockId, InventoryId inventoryId) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
    }

    /**
     * Return the id of the block that contains the inventory.
     * @return The id of the block that contains the inventory
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the inventory id.
     * @return The inventory id
     */
    public InventoryId getInventoryId() {
        return inventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteInventory that = (DeleteInventory) o;

        if (!blockId.equals(that.blockId)) return false;
        return inventoryId.equals(that.inventoryId);

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeleteInventory(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ')';
    }
}
