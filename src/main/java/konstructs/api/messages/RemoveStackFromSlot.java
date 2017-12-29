package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.StackAmount;

import java.util.UUID;

/**
 * A message to remove a stack from a specific inventory slot.
 * The response is a {@link ReceiveStack} with either a stack, half stack, single block or null if the slot was empty.
 * @see StackAmount
 */
public class RemoveStackFromSlot {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final int slot;
    private final StackAmount amount;

    /**
     * Create an immutable message that removes a stack from an inventory slot
     * @param blockId The block id that contains the inventory
     * @param inventoryId The inventory id of the inventory
     * @param slot The slot from which the stack should be removed
     * @param amount The amount to be removed
     */
    public RemoveStackFromSlot(UUID blockId, InventoryId inventoryId, int slot, StackAmount amount) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.slot = slot;
        this.amount = amount;
    }

    /**
     * Returns the block id that contains the inventory
     * @return The block id that contains the inventory
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
     * Returns the slot from which the stack should be removed
     * @return The slot from which the stack should be removed
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Returns the amount to be removed
     * @return The amount to be removed
     */
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
