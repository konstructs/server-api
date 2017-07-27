package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.StackAmount;

import java.util.UUID;

/**
 * Created by petter on 2017-07-27.
 */
public class RemoveStackFromSlot {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final int slot;
    private final StackAmount amount;

    public RemoveStackFromSlot(UUID blockId, InventoryId inventoryId, int slot, StackAmount amount) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.slot = slot;
        this.amount = amount;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public int getSlot() {
        return slot;
    }

    public StackAmount getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoveStackFromSlot that = (RemoveStackFromSlot) o;

        if (slot != that.slot) return false;
        if (!blockId.equals(that.blockId)) return false;
        if (!inventoryId.equals(that.inventoryId)) return false;
        return amount == that.amount;

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        result = 31 * result + slot;
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RemoveStackFromSlot(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ", slot=" + slot +
                ", amount=" + amount +
                ')';
    }
}
