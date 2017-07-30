package konstructs.api.messages;

import konstructs.api.InventoryId;
import konstructs.api.InventoryView;

import java.util.Map;
import java.util.UUID;

/**
 * Created by petter on 2017-07-30.
 */
public class GetInventoriesView {
    private final UUID blockId;
    private final Map<InventoryId,InventoryView> inventories;

    public GetInventoriesView(UUID blockId, Map<InventoryId, InventoryView> inventories) {
        this.blockId = blockId;
        this.inventories = inventories;
    }

    public UUID getBlockId() {
        return blockId;
    }

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
