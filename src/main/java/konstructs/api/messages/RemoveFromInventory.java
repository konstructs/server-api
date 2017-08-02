package konstructs.api.messages;

import konstructs.api.BlockTypeId;
import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Remove an exact amount of a specific block type from any slot in an inventory.'
 * The response is a {@link ReceiveStack} with either a stack of the exact amount or
 * null if the amount was not available.
 */
public class RemoveFromInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final BlockTypeId blockTypeId;
    private final int amount;

    /**
     * Create an immutable message
     * @param blockId The block id that contains the inventory
     * @param inventoryId The inventory id of the inventory
     * @param blockTypeId The block type id to remove
     * @param amount The amount of blocks to remove
     */
    public RemoveFromInventory(UUID blockId, InventoryId inventoryId, BlockTypeId blockTypeId, int amount) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.blockTypeId = blockTypeId;
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
     * Returns the block type id to remove
     * @return The block type id to remove
     */
    public BlockTypeId getBlockTypeId() {
        return blockTypeId;
    }

    /**
     * Returns the amount of blocks to remove
     * @return The amount of blocks to remove
     */
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
