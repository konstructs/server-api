package konstructs.api;

/**
 * StackTemplate is an immutable class that describes a stack that is part of a {@link PatternTemplate}.
 * The main difference between StackTemplate and {@link Stack} is that StackTemplate only holds a block type
 * and the number of blocks in the stack, while {@link Stack} holds the real blocks themselves. This is because
 * stack templates are only used to match {@link Stack} when used in a PatternTemplate.
 */
public class StackTemplate {
    private final BlockOrClassId id;
    private final int size;

    /**
     * Constructs an immutable StackTemplate
     * @param id The block type or class ID of this stack
     * @param size the number of blocks in this stack
     */
    public StackTemplate(BlockOrClassId id, int size) {
        this.id = id;
        this.size = size;
    }

    /**
     * Returns the block type or class ID of this stack template
     * @return The block type or class ID
     */
    public BlockOrClassId getId() {
        return id;
    }

    /**
     * Returns the size of this stack
     * @return The size
     */
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StackTemplate that = (StackTemplate) o;

        if (size != that.size) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "StackTemplate(" +
                "id=" + id +
                ", size=" + size +
                ')';
    }
}
