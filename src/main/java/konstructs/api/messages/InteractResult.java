package konstructs.api.messages;

import konstructs.api.Block;
import konstructs.api.Position;

public class InteractResult {
    private final Position position;
    private final Block block;
    private final Block blockAtPosition;

    public InteractResult(Position position, Block block, Block blockAtPosition) {
        this.position = position;
        this.block = block;
        this.blockAtPosition = blockAtPosition;
    }

    public Position getPosition() {
        return position;
    }

    public Block getBlock() {
        return block;
    }

    public Block getBlockAtPosition() {
        return blockAtPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteractResult that = (InteractResult) o;

        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (block != null ? !block.equals(that.block) : that.block != null) return false;
        return blockAtPosition != null ? blockAtPosition.equals(that.blockAtPosition) : that.blockAtPosition == null;

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + (blockAtPosition != null ? blockAtPosition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InteractResult(" +
                "position=" + position +
                ", block=" + block +
                ", blockAtPosition=" + blockAtPosition +
                ')';
    }
}
