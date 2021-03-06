package konstructs.api;

import java.util.UUID;

/**
 * Block is a class that represents a real block. A real block is
 * a block that was once placed in the world or a block that is to
 * be placed in the world (BlockTypeId is used to identify blocks
 * in general, when there is no need for a specific block).
 * <p>
 * What makes the block real (or unique) is the associated ID.
 * This id is saved by the server when the block is placed in the
 * world and retrieved by the server when the block is removed
 * from the world. This means that this ID can be counted on
 * uniquely and persistently identifying this block.
 * </p>
 * <p>
 * Why is this useful?
 * It is useful when building plugins that require to keep track
 * of blocks. Let's say you are building a chest block. You will
 * now want to store the contents of the chest in a way so that
 * they are associated with the block. By saving the contents of
 * the chest with the id of the block as the key, you will always
 * be able to access them for the block.
 * </p>
 *  @see BlockTypeId
 */
public final class Block {
    private final UUID id;
    private final BlockTypeId type;
    private final Health health;
    private final Orientation orientation;

    /**
     * Factory method that creates a new block with an associated random id.
     * @param t The block type
     * @return A new block with the specified type and a random ID
     */
    public static Block createWithId(BlockTypeId t) {
        return new Block(UUID.randomUUID(), t);
    }

    /**
     * Factory method that creates a new block with an associated random id.
     * @param t The block type (as a full string, e.g. "org/konstructs/grass")
     * @return A new block with the specified type and a random ID
     */
    public static Block createWithId(String t) {
        return createWithId(BlockTypeId.fromString(t));
    }

    /**
     * Factory method that creates a new block without an ID. This is the default
     * case, if uncertain use this method.
     * @param t The block type
     * @return A new block without an ID
     */
    public static Block create(BlockTypeId t) {
        return new Block(null, t);
    }

    /**
     * Factory method that creates a new block without an ID. This is the default
     * case, if uncertain use this method.
     * @param t The block type (as a full string, e.g. "org/konstructs/grass")
     * @return A new block without an ID
     */
    public static Block create(String t) {
        return create(BlockTypeId.fromString(t));
    }

    /**
     * Factory method that creates a new block with a given id and type
     * @param id Id of the block
     * @param type The block type
     * @return A new block without an ID
     */
    public static Block create(UUID id, BlockTypeId type) {
        return new Block(id, type);
    }

    private Block() {
        /*
        * This constructor is used by JSON loader, set defaults for fields
        * that requires backwards compatibility.
        */
        this.id = null;
        this.type = null;
        this.health = Health.PRISTINE;
        this.orientation = Orientation.NORMAL;
    }

    /**
     * Constructs a immutable Block in {@link Health#PRISTINE pristine} condition and
     * normal rotation.
     *
     * @param id   The ID that should be associated with the Block (may be null)
     * @param type The type of the block
     */
    public Block(UUID id, BlockTypeId type) {
        this(id, type, Health.PRISTINE);
    }

    /**
     * Constructs a immutable Block oriented upwards and not rotated
     *
     * @param id     The ID that should be associated with the Block (may be null)
     * @param type   The type of the block
     * @param health The health level of this block
     */
    public Block(UUID id, BlockTypeId type, Health health) {
        this(id, type, health, Orientation.NORMAL);
    }

    /**
     * Constructs an immutable Bloc
     * @param id          The ID that should be associated with the Block (may be null)
     * @param type        The type of the block
     * @param health      The health level of this block
     * @param orientation The orientation of this block
     */
    public Block(UUID id, BlockTypeId type, Health health, Orientation orientation) {
        this.id = id;
        this.type = type;
        this.health = health;
        this.orientation = orientation;
    }

    /**
     * Returns the ID of the block. It may be null if no ID is associated with
     * this block (default case).
     * @return The ID of the block (may be null)
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the type of the block.
     * @return The type of the block
     */
    public BlockTypeId getType() {
        return type;
    }

    /**
     * Return whether this block is of the given type
     * @param typeId The type id to check against
     * @return true if this block is of the given type, otherwise false
     */
    public boolean isType(BlockTypeId typeId) {
        return type.equals(typeId);
    }

    /**
     * Returns the health level of this block.
     *
     * @return The health level of this block
     */
    public Health getHealth() {
        return health;
    }

    /**
     * Returns the orientation of this block.
     * @return The orientation of this block
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns a new block with the given ID set
     * @param id The new ID to set
     * @return The new block with the ID set
     */
    public Block withId(UUID id) {
        return new Block(id, type, health, orientation);
    }

    /**
     * Returns a new block with the given type set
     * @param type The type to set
     * @return The new block with the type set
     */
    public Block withType(BlockTypeId type) {
        return new Block(id, type, health, orientation);
    }

    /**
     * Returns a new block with the given health set
     * @param health The health to set
     * @return The new block with the ID set
     */
    public Block withHealth(Health health) {
        return new Block(id, type, health, orientation);
    }

    /**
     * Returns a new block with the given orientation
     * @param orientation The orientation to set
     * @return The new block with the orientation set
     */
    public Block withOrientation(Orientation orientation) {
        return new Block(id, type, health, orientation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (id != null ? !id.equals(block.id) : block.id != null) return false;
        if (!type.equals(block.type)) return false;
        if (!health.equals(block.health)) return false;
        return orientation.equals(block.orientation);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        result = 31 * result + health.hashCode();
        result = 31 * result + orientation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Block(" +
                "id=" + id +
                ", type=" + type +
                ", health=" + health +
                ", orientation=" + orientation +
                ')';
    }
}
