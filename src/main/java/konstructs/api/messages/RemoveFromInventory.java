package konstructs.api.messages;

import konstructs.api.BlockTypeId;
import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Created by petter on 2017-07-27.
 */
public class RemoveFromInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final BlockTypeId blockTypeId;
    private final int amount;

    public RemoveFromInventory(UUID blockId, InventoryId inventoryId, BlockTypeId blockTypeId, int amount) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.blockTypeId = blockTypeId;
        this.amount = amount;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public BlockTypeId getBlockTypeId() {
        return blockTypeId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoveFromInventory that = (RemoveFromInventory) o;

        if (amount != that.amount) return false;
        if (!blockId.equals(that.blockId)) return false;
        if (!inventoryId.equals(that.inventoryId)) return false;
        return blockTypeId.equals(that.blockTypeId);

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        result = 31 * result + blockTypeId.hashCode();
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "RemoveFromInventory(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ", blockTypeId=" + blockTypeId +
                ", amount=" + amount +
                ')';
    }
}
