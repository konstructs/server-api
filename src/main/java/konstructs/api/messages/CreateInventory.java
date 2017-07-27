package konstructs.api.messages;

import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Message that creates an inventory inside the inventory actor
 */
public class CreateInventory {
    private final UUID blockId;
    private final InventoryId inventoryId;
    private final int size;

    public CreateInventory(UUID blockId, int size) {
        this(blockId, InventoryId.STORAGE, size);
    }
    public CreateInventory(UUID blockId, InventoryId inventoryId, int size) {
        this.blockId = blockId;
        this.inventoryId = inventoryId;
        this.size = size;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

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
