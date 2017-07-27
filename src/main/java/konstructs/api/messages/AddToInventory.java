package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.Stack;

import java.util.UUID;

/**
 * Created by petter on 2017-07-27.
 */
public class AddToInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final Stack stack;

    public AddToInventory(UUID blockId, InventoryId inventoryId, Stack stack) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.stack = stack;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Stack getStack() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddToInventory that = (AddToInventory) o;

        if (!blockId.equals(that.blockId)) return false;
        if (!inventoryId.equals(that.inventoryId)) return false;
        return stack.equals(that.stack);

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        result = 31 * result + stack.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AddToInventory(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ", stack=" + stack +
                ')';
    }
}
