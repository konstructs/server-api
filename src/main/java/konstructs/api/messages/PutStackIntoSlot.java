package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.Stack;

import java.util.UUID;

/**
 * Message t put a stack into a specific slot of an inventory. If the specified slot already contains a stack,
 * that stack is returned via the {@link ReceiveStack} message. If the blockid/inventory/slot does not exists,
 * the stack itself is returned via the {@link ReceiveStack} message.
 */
public class PutStackIntoSlot {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final int slot;
    private final Stack stack;

    /**
     * Create an immutable message to put a stack into a slot of an inventory.
     * @param blockId The id of the block that contains the inventory
     * @param inventoryId The inventory id of the inventory to put the stack into
     * @param slot The slot of the inventory into which the stack should be put
     * @param stack The stack itself
     */
    public PutStackIntoSlot(UUID blockId, InventoryId inventoryId, int slot, Stack stack) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.slot = slot;
        this.stack = stack;
    }

    /**
     * Returns the block id of the block in which the inventory resides
     * @return the block id of the block in which the inventory resides
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the inventory id of the inventory into which the stack should be put
     * @return the inventory id of the inventory into which the stack should be put
     */
    public InventoryId getInventoryId() {
        return inventoryId;
    }

    /**
     * Returns the slot of the inventory into which the stack should be put
     * @return The slot of the inventory into which the stack should be put
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Returns the stack itself
     * @return The stack itself
     */
    public Stack getStack() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PutStackIntoSlot that = (PutStackIntoSlot) o;

        if (slot != that.slot) return false;
        if (!blockId.equals(that.blockId)) return false;
        if (!inventoryId.equals(that.inventoryId)) return false;
        return stack.equals(that.stack);

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        result = 31 * result + slot;
        result = 31 * result + stack.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PutStackIntoSlot(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ", slot=" + slot +
                ", stack=" + stack +
                ')';
    }
}
