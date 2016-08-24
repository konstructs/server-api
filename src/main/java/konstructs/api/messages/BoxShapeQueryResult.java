package konstructs.api.messages;

import konstructs.api.BlockTypeId;
import konstructs.api.BoxShape;

import java.util.Arrays;
/**
 * BoxShapeQueryResult is a message received as a response to the {@link BoxShapeQuery}
 * message. It contains the BoxShape of the original query as well as an
 * array of blocks that matched the box shape of the query.
 *
 * Please see the individual BoxShape ({@link konstructs.api.BoxAround}, {@link konstructs.api.InclusiveBox},
 * {@link konstructs.api.DirectionalLine} and {@link konstructs.api.Box}) for how to get the appropriate block / index
 * from the array returned by {@link #getBlocks()}.
 */
public class BoxShapeQueryResult {
    private final BoxShape box;
    private final BlockTypeId[] blocks;

    /**
     * Creates a new immutable BoxShapeQueryResult instance
     * @param box The BoxShape that was queried for
     * @param blocks The blocks that matched the BoxShape
     */
    public BoxShapeQueryResult(BoxShape box, BlockTypeId[] blocks) {
        this.box = box;
        this.blocks = blocks;
    }

    /**
     * Returns the BoxShape that was queried
     * @return The BoxShape
     */
    public BoxShape getBox() {
        return box;
    }

    /**
     * Array of blocks that matched the BoxShape queried for
     * @return The matched blocks
     */
    public BlockTypeId[] getBlocks() {
        return blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxShapeQueryResult that = (BoxShapeQueryResult) o;

        if (!box.equals(that.box)) return false;
        return Arrays.equals(blocks, that.blocks);

    }

    @Override
    public int hashCode() {
        int result = box.hashCode();
        result = 31 * result + Arrays.hashCode(blocks);
        return result;
    }

    @Override
    public String toString() {
        return "BoxShapeQueryResult(" +
                "box=" + box +
                ", blocks=" + Arrays.toString(blocks) +
                ')';
    }
}
