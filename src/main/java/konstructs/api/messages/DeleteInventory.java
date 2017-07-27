package konstructs.api.messages;

import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Created by petter on 2017-07-30.
 */
public class DeleteInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;

    public DeleteInventory(UUID blockId, InventoryId inventoryId) {
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
