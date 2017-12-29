package konstructs.api.messages;

import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Get an inventory if it exists
 */
public class GetInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;

    /** Create an immutable message to get an inventory.
     * This uses the default inventory id "org/konstructs/STORAGE"
     *
     * @param blockId The id of the block that contains the inventory
     */
    public GetInventory(UUID blockId) {
        this(blockId, InventoryId.STORAGE);
    }

    /** Create an immutable message to get an inventory.
     *
     * @param blockId The id of the block that contains the inventory
     * @param inventoryId The inventory id of the inventory required
     */
    public GetInventory(UUID blockId, InventoryId inventoryId) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
    }

    /**
     * Returns the block id of the block in which the inventory resides
     * @return the block id of the block in which the inventory resides
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the inventory id of the inventory to get
     * @return the inventory id of the inventory to get
     */
    public InventoryId getInventoryId() {
        return inventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetInventory that = (GetInventory) o;

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
        return "GetInventory(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ')';
    }
}
