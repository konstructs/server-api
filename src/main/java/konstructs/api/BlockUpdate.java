package konstructs.api;

/**
 * This class describes an update of the bock world and contains the block before and after the update.
 */
public class BlockUpdate {
    private final Block before;
    private final Block after;

    /**
     * Create an immutable instance
     * @param before the block before the update
     * @param after the block after the update
     */
    public BlockUpdate(Block before, Block after) {
        this.before = before;
        this.after = after;
    }

    /**
     * Returns the block before the update
     * @return The block before the update
     */
    public Block getBefore() {
        return before;
    }

    /**
     * Returns the block after the update
     * @return The block after the update
     */
    public Block getAfter() {
        return after;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockUpdate that = (BlockUpdate) o;

        if (!before.equals(that.before)) return false;
        return after.equals(that.after);

    }

    @Override
    public int hashCode() {
        int result = before.hashCode();
        result = 31 * result + after.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BlockUpdate(" +
                "before=" + before +
                ", after=" + after +
                ')';
    }
}
