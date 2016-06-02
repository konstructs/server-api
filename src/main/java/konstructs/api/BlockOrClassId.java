package konstructs.api;

/**
 * BlockOrClassId is an immutable class that represents either a {@link BlockTypeId} or a {@link BlockClassId}.
 * Its sole purpose is to allow {@link StackTemplate} to handle both of them.
 */
public class BlockOrClassId {
    /**
     * Factory method that creates a BlockOrClassId from a string. It uses the fact that
     * classes always start with a capital letter to distinguish between the two of them.
     * @param blockOrClassId The string that is either a block type or class ID
     * @return A BlockClassId instance
     */
    public static BlockOrClassId fromString(String blockOrClassId) {
        int lastSlash = blockOrClassId.lastIndexOf('/');
        String namespace = blockOrClassId.substring(0, lastSlash);
        String name = blockOrClassId.substring(lastSlash + 1);
        return new BlockOrClassId(namespace, name);
    }

    private final BlockTypeId blockTypeId;
    private final BlockClassId blockClassId;

    /**
     * Constructs an immutable BlockOrClassId.  It uses the fact that the name of
     * classes always start with a capital letter to distinguish between the two of them.
     * @param namespace The namespace of the class or block type ID
     * @param name The name of the ID. If it is capital it becomes a class ID, if not it is a block type ID
     */
    public BlockOrClassId(String namespace, String name) {
        if(name.length() == 0) {
            throw new IllegalArgumentException("Name must be at least one character");
        }
        if(Character.isUpperCase(name.charAt(0))) {
            this.blockTypeId = null;
            this.blockClassId = new BlockClassId(namespace, name);
        } else {
            this.blockTypeId = new BlockTypeId(namespace, name);
            this.blockClassId = null;
        }
    }

    /**
     * Constructs an immutable BlockOrClassId from an existing BlockTypeId
     * @param blockTypeId The block type ID to wrap
     */
    public BlockOrClassId(BlockTypeId blockTypeId) {
        this.blockTypeId = blockTypeId;
        this.blockClassId = null;
    }

    /**
     * Constructs an immutable BlockOrClassId from an existing BlockClassId
     * @param blockClassId The block class ID to wrap
     */
    public BlockOrClassId(BlockClassId blockClassId) {
        this.blockTypeId = null;
        this.blockClassId = blockClassId;
    }

    /**
     * Returns the BlockTypeId stored or null this class represents a BlockClassId
     * @return The BlockTypeId or null
     * @see #isBlockClassId()
     */
    public BlockTypeId getBlockTypeId() {
        return blockTypeId;
    }

    /**
     * Return the BlockClassId stored or null if this class represents a BlockTypeid
     * @return The BlockClassId or null
     * @see #isBlockTypeId()
     */
    public BlockClassId getBlockClassId() {
        return blockClassId;
    }

    /**
     * Returns true if this class represents a BlockTypeId
     * @return true if this is a BlockTypeId
     */
    public boolean isBlockTypeId() {
        return blockTypeId !=null;
    }

    /**
     * Returns true if this class represents a BlockClassId
     * @return true if this is a BlockClassId
     */
    public boolean isBlockClassId() {
        return blockClassId != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockOrClassId that = (BlockOrClassId) o;

        if (blockTypeId != null ? !blockTypeId.equals(that.blockTypeId) : that.blockTypeId != null) return false;
        return blockClassId != null ? blockClassId.equals(that.blockClassId) : that.blockClassId == null;

    }

    @Override
    public int hashCode() {
        int result = blockTypeId != null ? blockTypeId.hashCode() : 0;
        result = 31 * result + (blockClassId != null ? blockClassId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockOrClassId(" +
                "blockTypeId=" + blockTypeId +
                ", blockClassId=" + blockClassId +
                ')';
    }
}
