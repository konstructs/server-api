package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.Stack;

import java.util.UUID;

/**
 * Created by petter on 2017-07-27.
 */
public class PutStackIntoSlot {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final int slot;
    private final Stack stack;

    public PutStackIntoSlot(UUID blockId, InventoryId inventoryId, int slot, Stack stack) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.slot = slot;
        this.stack = stack;
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

    public Stack getStack() {
        return stack;
    }
}
