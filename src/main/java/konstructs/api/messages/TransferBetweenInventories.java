package konstructs.api.messages;

import konstructs.api.BlockTypeId;
import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * A message that transfers exactly an amount of a given block type from one inventory to another (or nothing at all)
 * giving no acknowledgement on if the transfer was successful or not. Such an acknowledgment would not be useful,
 * since when it is received it is not possible to know the state of the receiving inventory. If the blocks transferred
 * to the receiving inventory are required, please us a {@link RemoveFromInventory} message to get them, and please
 * note that this may not successfully return them as the state of the receiving inventory may already ahve changed.
 */
public class TransferBetweenInventories {
    private final UUID fromBlockId;
    private final InventoryId fromInventoryId;
    private final UUID toBlockId;
    private final InventoryId toInventoryId;
    private final BlockTypeId blockTypeId;
    private final int amount;

    /**
     * Create an immutable message to transfer blocks of a given type from one inventory to another.
     * @param fromBlockId The block id of the block that contain the inventory to transfer from
     * @param fromInventoryId The inventory id of the inventory to transfer from
     * @param toBlockId The block id of the block that contain the inventory to transfer to
     * @param toInventoryId The inventory id of the inventory to transfer to
     * @param blockTypeId The block type to transfer
     * @param amount The amount of blocks to transfer
     */
    public TransferBetweenInventories(UUID fromBlockId, InventoryId fromInventoryId, UUID toBlockId, InventoryId toInventoryId, BlockTypeId blockTypeId, int amount) {
        this.fromBlockId = fromBlockId;
        this.fromInventoryId = fromInventoryId;
        this.toBlockId = toBlockId;
        this.toInventoryId = toInventoryId;
        this.blockTypeId = blockTypeId;
        this.amount = amount;
    }

    /**
     * Returns the block id of the block that contain the inventory to transfer from
     * @return The block id of the block that contain the inventory to transfer from
     */
    public UUID getFromBlockId() {
        return fromBlockId;
    }

    /**
     * Returns the inventory id of the inventory to transfer from
     * @return The inventory id of the inventory to transfer from
     */
    public InventoryId getFromInventoryId() {
        return fromInventoryId;
    }

    /**
     * Returns the block id of the block that contain the inventory to transfer to
     * @return The block id of the block that contain the inventory to transfer to
     */
    public UUID getToBlockId() {
        return toBlockId;
    }

    /**
     * Returns the inventory id of the inventory to transfer to
     * @return The inventory id of the inventory to transfer to
     */
    public InventoryId getToInventoryId() {
        return toInventoryId;
    }

    /**
     * Returns the block type to transfer
     * @return The block type to transfer
     */
    public BlockTypeId getBlockTypeId() {
        return blockTypeId;
    }

    /**
     * Returns the amount of blocks to transfer
     * @return The amount of blocks to transfer
     */
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferBetweenInventories that = (TransferBetweenInventories) o;

        if (amount != that.amount) return false;
        if (!fromBlockId.equals(that.fromBlockId)) return false;
        if (!fromInventoryId.equals(that.fromInventoryId)) return false;
        if (!toBlockId.equals(that.toBlockId)) return false;
        if (!toInventoryId.equals(that.toInventoryId)) return false;
        return blockTypeId.equals(that.blockTypeId);

    }

    @Override
    public int hashCode() {
        int result = fromBlockId.hashCode();
        result = 31 * result + fromInventoryId.hashCode();
        result = 31 * result + toBlockId.hashCode();
        result = 31 * result + toInventoryId.hashCode();
        result = 31 * result + blockTypeId.hashCode();
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "TransferBetweenInventories(" +
                "fromBlockId=" + fromBlockId +
                ", fromInventoryId=" + fromInventoryId +
                ", toBlockId=" + toBlockId +
                ", toInventoryId=" + toInventoryId +
                ", blockTypeId=" + blockTypeId +
                ", amount=" + amount +
                ')';
    }
}
