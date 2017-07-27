package konstructs.api.messages;

import konstructs.api.BlockTypeId;
import konstructs.api.InventoryId;

import java.util.UUID;

/**
 * Created by petter on 2017-07-27.
 */
public class TransferBetweenInventories {
    private final UUID fromBlockId;
    private final InventoryId fromInventoryId;
    private final UUID toBlockId;
    private final InventoryId toInventoryId;
    private final BlockTypeId blockTypeId;
    private final int amount;

    public TransferBetweenInventories(UUID fromBlockId, InventoryId fromInventoryId, UUID toBlockId, InventoryId toInventoryId, BlockTypeId blockTypeId, int amount) {
        this.fromBlockId = fromBlockId;
        this.fromInventoryId = fromInventoryId;
        this.toBlockId = toBlockId;
        this.toInventoryId = toInventoryId;
        this.blockTypeId = blockTypeId;
        this.amount = amount;
    }

    public UUID getFromBlockId() {
        return fromBlockId;
    }

    public InventoryId getFromInventoryId() {
        return fromInventoryId;
    }

    public UUID getToBlockId() {
        return toBlockId;
    }

    public InventoryId getToInventoryId() {
        return toInventoryId;
    }

    public BlockTypeId getBlockTypeId() {
        return blockTypeId;
    }

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
