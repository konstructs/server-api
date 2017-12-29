package konstructs.api.messages;

import konstructs.api.Inventory;
import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * The result of the {@link GetInventory} message.
 *
 * Note that inventory may be null if no inventory exists.
 */
public class GetInventoryResult {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final Inventory inventory;

    /**
     * Create an immutable message
     * @param blockId The id of the block where the inventory resides
     * @param inventoryId The inventory id of the inventory
     * @param inventory The inventory itself or null if no inventory was found
     */
    public GetInventoryResult(UUID blockId, InventoryId inventoryId, Inventory inventory) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.inventory = inventory;
    }

    /**
     * Returns the id of the block where the inventory resides
     * @return The id of the block where the inventory resides
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the inventory id of the inventory
     * @return The inventory id of the inventory
     */
    public InventoryId getInventoryId() {
        return inventoryId;
    }

    /**
     * Returns the inventory itself or null if no inventory was found
     * @return The inventory itself or null if no inventory was found
     */
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetInventoryResult that = (GetInventoryResult) o;

        if (!blockId.equals(that.blockId)) return false;
        if (!inventoryId.equals(that.inventoryId)) return false;
        return inventory != null ? inventory.equals(that.inventory) : that.inventory == null;

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        result = 31 * result + (inventory != null ? inventory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GetInventoryResult(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ", inventory=" + inventory +
                ')';
    }
}
