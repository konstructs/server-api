package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.Stack;

import java.util.UUID;

/**
 * This message adds a Stack to an existing inventory
 */
public class AddToInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final Stack stack;

    /**
     * Create a new immutable message
     * @param blockId The block that contains the inventory
     * @param inventoryId The ID of the inventory
     * @param stack The stack to be added
     */
    public AddToInventory(UUID blockId, InventoryId inventoryId, Stack stack) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.stack = stack;
    }

    /**
     * Returns the id of the block that contains the inventory.
     * @return The id of the block that contains the inventory
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the id of the inventory.
     * @return The invetory ID
     */
    public InventoryId getInventoryId() {
        return inventoryId;
    }

    /**
     * Returns the stack to be added.
     * @return The stack to be added
     */
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
