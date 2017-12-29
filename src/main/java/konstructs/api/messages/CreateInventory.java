package konstructs.api.messages;

import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Message that creates an inventory managed by the server.
 */
public class CreateInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final int size;

    /**
     * Create an immutable message to create an inventory with the {@link InventoryId#STORAGE default STORAGE inventory id}
     * @param blockId The id of the block that contains the inventory
     * @param size The size of the inventory
     */
    public CreateInventory(UUID blockId, int size) {
        this(blockId, InventoryId.STORAGE, size);
    }

    /**
     * Create an immutable message
     * @param blockId The id of the block that contains the inventory
     * @param inventoryId The id of the inventory
     * @param size The size of the inventory
     */
    public CreateInventory(UUID blockId, InventoryId inventoryId, int size) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.size = size;
    }

    /**
     * Return the id of the block that contains the inventory.
     * @return The id of the block that contains the inventory
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the inventory id.
     * @return The inventory id
     */
    public InventoryId getInventoryId() {
        return inventoryId;
    }

    /**
     * Returns the size of the inventory.
     * @return The size of the inventory
     */
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateInventory that = (CreateInventory) o;

        if (size != that.size) return false;
        if (!blockId.equals(that.blockId)) return false;
        return inventoryId.equals(that.inventoryId);

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventoryId.hashCode();
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "CreateInventory(" +
                "blockId=" + blockId +
                ", inventoryId=" + inventoryId +
                ", size=" + size +
                ')';
    }
}
