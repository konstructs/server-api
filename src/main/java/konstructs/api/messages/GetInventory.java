package konstructs.api.messages;

import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Get an inventory if it exists
 */
public class GetInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;

    public GetInventory(UUID blockId) {
        this(blockId, InventoryId.STORAGE);
    }

    public GetInventory(UUID blockId, InventoryId inventoryId) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
    }

    public UUID getBlockId() {
        return blockId;
    }

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
