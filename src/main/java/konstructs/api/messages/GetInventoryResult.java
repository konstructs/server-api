package konstructs.api.messages;

import konstructs.api.Inventory;
import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Created by petter on 2017-07-27.
 */
public class GetInventoryResult {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final Inventory inventory;

    public GetInventoryResult(UUID blockId, InventoryId inventoryId, Inventory inventory) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.inventory = inventory;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

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
