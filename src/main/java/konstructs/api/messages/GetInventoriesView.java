package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.InventoryView;

import java.util.Map;
import java.util.UUID;

/**
 * A message that asks the server to generate a {@link konstructs.api.View} from a set of inventories contained by
 * a block. Inventories are mapped to a specific area of the view using the the {@link InventoryView} class.
 */
public class GetInventoriesView {
    private final UUID blockId;
    private final Map<InventoryId,InventoryView> inventories;

    /**
     * Create an immutable message
     * @param blockId The block id that contains the inventories to be displayed
     * @param inventories A mapping of inventory ids and where they are to be displayed
     */
    public GetInventoriesView(UUID blockId, Map<InventoryId, InventoryView> inventories) {
        this.blockId = blockId;
        this.inventories = inventories;
    }

    /**
     * Returns the id of the bock that contains the invetories to be displayed.
     * @return The id of the bock that contains the invetories to be displayed
     */
    public UUID getBlockId() {
        return blockId;
    }

    /**
     * Returns the mapping between {@link InventoryId} and {@link InventoryView}.
     * @return The mapping between {@link InventoryId} and {@link InventoryView}.
     */
    public Map<InventoryId, InventoryView> getInventories() {
        return inventories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetInventoriesView that = (GetInventoriesView) o;

        if (!blockId.equals(that.blockId)) return false;
        return inventories.equals(that.inventories);

    }

    @Override
    public int hashCode() {
        int result = blockId.hashCode();
        result = 31 * result + inventories.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GetInventoriesView(" +
                "blockId=" + blockId +
                ", inventories=" + inventories +
                ')';
    }
}
