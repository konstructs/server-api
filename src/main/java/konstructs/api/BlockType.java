package konstructs.api;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * BlockType is a class that holds all relevant information regarding
 * a block's type. Instances of this class is generated when the
 * server is started from the server configuration. It is immutable
 * and serializable.
 */
public final class BlockType implements Serializable {
    public static final BlockClassId[] NO_CLASSES = {};
    public static final Map<BlockOrClassId, Float> NO_MULTIPLIERS = Collections.emptyMap();
    public static final float DEFAULT_DURABILITY = 20.0f;
    public static final float DEFAULT_DAMAGE = 1.0f;

    /**
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Use {@link BlockShape#BLOCK} instead
     */
    @Deprecated
    public static final String SHAPE_BLOCK = BlockShape.BLOCK.getShape();

    /**
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Use {@link BlockShape#PLANT} instead
     */
    @Deprecated
    public static final String SHAPE_PLANT = BlockShape.PLANT.getShape();

    /**
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Use {@link BlockState#SOLID} instead
     */
    @Deprecated
    public static final String STATE_SOLID = BlockState.SOLID.getState();

    /**
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Use {@link BlockState#LIQUID} instead
     */
    @Deprecated
    public static final String STATE_LIQUID = BlockState.LIQUID.getState();

    /**
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Use {@link BlockState#GAS} instead
     */
    @Deprecated
    public static final String STATE_GAS = BlockState.GAS.getState();

    /**
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Use {@link BlockState#PLASMA} instead
     */
    @Deprecated
    public static final String STATE_PLASMA = BlockState.PLASMA.getState();

    private final int[] faces;
    private final BlockShape blockShape;
    private final boolean obstacle;
    private final boolean transparent;
    private final BlockState blockState;
    private final BlockClassId[] classes;
    private final float durability;
    private final float damage;
    private final Map<BlockOrClassId, Float> damageMultipliers;
    private final boolean orientable;
    private final BlockTypeId destroyedAs;

    /**
     * Constructs an immutable BlockType.
     * @param faces the texture index for all faces of a BlockType
     * @param shape the shape of the block
     * @param obstacle is this BlockType an obstacle?
     * @param transparent is this BlockType transparent?
     * @deprecated As of API version 0.1.3 (will be removed in 0.2.+)
     *             Replaced by {@link #BlockType(int[], String, boolean, boolean, String)}.
     *             This constructor always sets state to {@link #STATE_SOLID}
     *             This constructor always uses an empty list of {@link BlockClassId classes}
     * @see #getFaces()
     * @see #getShape()
     * @see #isObstacle()
     * @see #isTransparent()
     */
    @Deprecated
    public BlockType(int[] faces, String shape, boolean obstacle, boolean transparent) {
        this(faces, BlockShape.fromString(shape), obstacle,transparent, BlockState.SOLID, NO_CLASSES);
    }

    /**
     * Constructs an immutable BlockType.
     * @param faces the texture index for all faces of a BlockType
     * @param shape the shape of the block
     * @param obstacle is this BlockType an obstacle?
     * @param transparent is this BlockType transparent?
     * @param state the state of the block
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+)
     *             Replaced by {@link #BlockType(int[], BlockShape, boolean, boolean, BlockState, BlockClassId[])}
     *             This constructor always uses an empty list of {@link BlockClassId classes}
     * @see #getFaces()
     * @see #getShape()
     * @see #isObstacle()
     * @see #isTransparent()
     * @see #getState()
     */
    @Deprecated
    public BlockType(int[] faces, String shape, boolean obstacle, boolean transparent, String state) {
        this(faces, BlockShape.fromString(shape), obstacle, transparent, BlockState.fromString(state), NO_CLASSES);
    }

    /**
     * Constructs an immutable BlockType with default durability and damage.
     * @param faces the texture index for all faces of a BlockType
     * @param shape the shape of the block
     * @param obstacle is this BlockType an obstacle?
     * @param transparent is this BlockType transparent?
     * @param state the state of the block
     * @param classes the list of classes for this block type
     * @see #getFaces()
     * @see #getShape()
     * @see #isObstacle()
     * @see #isTransparent()
     * @see #getState()
     */
    public BlockType(int[] faces, BlockShape shape, boolean obstacle, boolean transparent, BlockState state,
                     BlockClassId[] classes) {
        this(faces, shape, obstacle, transparent, state, classes, DEFAULT_DURABILITY, DEFAULT_DAMAGE, NO_MULTIPLIERS);
    }

    /**
     * Constructs an immutable BlockType.
     * @param faces the texture index for all faces of a BlockType
     * @param blockShape the shape of the block
     * @param obstacle is this BlockType an obstacle?
     * @param transparent is this BlockType transparent?
     * @param blockState the state of the block
     * @param classes the list of classes for this block type
     * @param durability the durability of this block when damaged
     * @param damage the damage given by this block when used
     * @param damageMultipliers the damage mutipliers whent his block is used
     * @see #getFaces()
     * @see #getShape()
     * @see #isObstacle()
     * @see #isTransparent()
     * @see #getState()
     */
    public BlockType(int[] faces, BlockShape blockShape, boolean obstacle, boolean transparent, BlockState blockState, BlockClassId[] classes, float durability, float damage, Map<BlockOrClassId, Float> damageMultipliers) {
        this(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, false);
    }

    /**
     * Constructs an immutable BlockType.
     * @param faces the texture index for all faces of a BlockType
     * @param blockShape the shape of the block
     * @param obstacle is this BlockType an obstacle?
     * @param transparent is this BlockType transparent?
     * @param blockState the state of the block
     * @param classes the list of classes for this block type
     * @param durability the durability of this block when damaged
     * @param damage the damage given by this block when used
     * @param damageMultipliers the damage mutipliers whent his block is used
     * @param orientable can this block be oriented?
     * @see #getFaces()
     * @see #getShape()
     * @see #isObstacle()
     * @see #isTransparent()
     * @see #getState()
     */
    public BlockType(int[] faces, BlockShape blockShape, boolean obstacle, boolean transparent, BlockState blockState, BlockClassId[] classes, float durability, float damage, Map<BlockOrClassId, Float> damageMultipliers, boolean orientable) {
        this(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, BlockTypeId.SELF);
    }

    /**
     * Constructs an immutable BlockType.
     * @param faces the texture index for all faces of a BlockType
     * @param blockShape the shape of the block
     * @param obstacle is this BlockType an obstacle?
     * @param transparent is this BlockType transparent?
     * @param blockState the state of the block
     * @param classes the list of classes for this block type
     * @param durability the durability of this block when damaged
     * @param damage the damage given by this block when used
     * @param damageMultipliers the damage mutipliers whent his block is used
     * @param orientable can this block be oriented?
     * @param destroyedAs when destroyed, which block is created
     * @see #getFaces()
     * @see #getShape()
     * @see #isObstacle()
     * @see #isTransparent()
     * @see #getState()
     */
    public BlockType(int[] faces, BlockShape blockShape, boolean obstacle, boolean transparent, BlockState blockState, BlockClassId[] classes, float durability, float damage, Map<BlockOrClassId, Float> damageMultipliers, boolean orientable, BlockTypeId destroyedAs) {
        this.faces = faces;
        this.blockShape = blockShape;
        this.obstacle = obstacle;
        this.transparent = transparent;
        this.blockState = blockState;
        this.classes = classes;
        this.durability = durability;
        this.damage = damage;
        this.damageMultipliers = damageMultipliers;
        this.orientable = orientable;
        this.destroyedAs = destroyedAs;
    }

    /**
     * Returns the texture indexes for this block type. A texture index
     * is an index generated by the server for the position at which
     * the texture for each of the block's faces is stored.
     * @return the array containing the texture indexes for this block type
     */
    public int[] getFaces() {
        return faces;
    }

    /**
     * Returns the shape of this block type. The shape of the block can
     * currently only be either of <code>BlockType.SHAPE_BLOCK</code> or
     * <code>BlockType.SHAPE_PLANT</code>.
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+). Use {@link #getBlockShape()} instead.
     * @return the shape of the block
     */
    @Deprecated
    public String getShape() {
        return blockShape.getShape();
    }

    /**
     * Returns the shape of this block type.
     * @return the shape of the block
     */
    public BlockShape getBlockShape() {
        return blockShape;
    }

    /**
     * Checks if this block type is seen as an obstacle or not. A block with
     * an obstacle type can not be passed through by the player.
     * @return true if this block is an obstacle, otherwise false
     */
    public boolean isObstacle() {
        return obstacle;
    }

    /**
     * Checks if this block type is seen as transparent by the player.
     * @return true if this block is transparent otherwise false
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * Returns the state of this block type. The state of the block
     * can currently be {@link #STATE_SOLID}, {@link #STATE_LIQUID},
     * {@link #STATE_GAS}, {@link #STATE_PLASMA}
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+). Use {@link #getBlockState()} instead.
     * @return the state of this block type
     */
    @Deprecated
    public String getState() {
        return blockState.getState();
    }

    /**
     * Returns the state of this block type.
     * @return the state of this block type
     */
    public BlockState getBlockState() {
        return blockState;
    }

    /**
     * Returns the list of classes for this block type.
     * @return the list of classes for this type
     */
    public BlockClassId[] getClasses() {
        return classes;
    }

    /**
     * Returns the durability for this block type.
     * @return the durability for this type
     */
    public float getDurability() {
        return durability;
    }

    /**
     * Returns the damage given by this block type
     * @return the damage given by this block type
     */
    public float getDamage() {
        return damage;
    }

    /**
     * Returns the damage given by this block type if used on the given type
     * @param typeId The ID of the block type
     * @param type The block type
     * @return the damage given by this block type if used on given type
     */
    public float getDamageWithMultiplier(BlockTypeId typeId, BlockType type) {
        return damage * getDamageMultipler(typeId, type);
    }

    /**
     * Returns the damage multipliers of this block type
     * @return the damage multiplier
     */
    public Map<BlockOrClassId, Float> getDamageMultipliers() {
        return damageMultipliers;
    }

    /**
     * Is it possible to orient this block when placing it?
     * @return True if it is possible to orient this block, or false if not
     */
    public boolean isOrientable() {
        return orientable;
    }

    /**
     * Return the damage multiplier for a given block type or class ID or 1.0 if no
     * multiplier is set.
     * @param id Block type or class ID
     * @return the damage multiplier for the given block type or class ID or 1.0
     */
    public float getDamageMultiplier(BlockOrClassId id) {
        if(damageMultipliers.containsKey(id)) {
            return damageMultipliers.get(id);
        } else {
            return 1.0f;
        }
    }

    /**
     * Return the damage multiplier for a given block typ ID or 1.0 if no
     * multiplier is set.
     * @param id Block type ID
     * @return the damage multiplier for the given block type ID or 1.0
     */
    public float getDamageMultiplier(BlockTypeId id) {
        return getDamageMultiplier(new BlockOrClassId(id));
    }

    /**
     * Return the damage multiplier for a given block class ID or 1.0 if no
     * multiplier is set.
     * @param id Block class ID
     * @return the damage multiplier for the given block class ID or 1.0
     */
    public float getDamageMultiplier(BlockClassId id) {
        return getDamageMultiplier(new BlockOrClassId(id));
    }

    /**
     * Get the block type id of the block that this block is destroyed into.
     * This can be {@link BlockTypeId#SELF} when the block destroys into it self
     * @return The block type id of the block this block type destroys into or {@link BlockTypeId#SELF}
     */
    public BlockTypeId getDestroyedAs() {
        return destroyedAs;
    }

    /**
     * Returns the damage multiplier for this block type if used on a given block type
     * If there exists a multiplier for the specific block type ID given, it will be used.
     * Otherwise the class with the highest matching multiplier will be used.
     * If there is no match, the multiplier is 1.0
     * @param typeId The ID of the block type
     * @param type The block type
     * @return The appropriate damage multiplier or 1.0
     */
    public float getDamageMultipler(BlockTypeId typeId, BlockType type) {
        BlockOrClassId t = new BlockOrClassId(typeId);
        if(damageMultipliers.containsKey(t)) {
            return damageMultipliers.get(t);
        } else {
            float damage = 1.0f;
            for(Map.Entry<BlockOrClassId, Float> e: damageMultipliers.entrySet()) {
                BlockOrClassId key = e.getKey();
                if(key.isBlockClassId() && type.hasClass(key.getBlockClassId()) && e.getValue() > damage) {
                    damage = e.getValue();
                }
            }
            return damage;
        }
    }

    /**
     * Checks if all classes in the argument are present in this
     * block type's classes array.
     * @param classes The array of classes to check for
     * @return True if all classes in the argument are present, otherwise false
     */
    public boolean hasClasses(BlockClassId[] classes) {
        for(BlockClassId f: classes) {
            boolean match = false;
            for(BlockClassId t: this.classes) {
                if(f.equals(t)) {
                    match = true;
                }
            }
            if(!match) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a class is present this block type's classes array
     * @param clazz the class to check for
     * @return True if the class is present, otherwise false
     */
    public boolean hasClass(BlockClassId clazz) {
        boolean match = false;
        for(BlockClassId t: this.classes) {
            if(clazz.equals(t)) {
                match = true;
            }
        }
        return match;
    }

    /**
     * Returns a copy with a new value for the faces
     * field.
     * @param faces the new value for faces
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #getFaces()
     */
    @Deprecated
    public BlockType withFaces(int[] faces) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the shape
     * field.
     * @param shape the new value for shape
     * @deprecated As of 0.1.7 (will be removed in 0.2.+). Use {@link #withBlockShape(BlockShape)} instead.
     * @return the copy
     * @see #getShape()
     */
    @Deprecated
    public BlockType withShape(String shape) {
        return new BlockType(faces, BlockShape.fromString(shape), obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the shape
     * field.
     * @param shape the new value for shape
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #getBlockShape()
     */
    @Deprecated
    public BlockType withBlockShape(BlockShape shape) {
        return new BlockType(faces, shape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the obstacle
     * field.
     * @param obstacle the new value for obstacle
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #isObstacle()
     */
    @Deprecated
    public BlockType withObstacle(boolean obstacle) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the transparent
     * field.
     * @param transparent the new value for transparent
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #isTransparent()
     */
    @Deprecated
    public BlockType withTransparent(boolean transparent) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the state
     * field.
     * @param state the new value for state
     * @deprecated As of 0.1.7 (will be removed in 0.2.+). Use {@link #withBlockState(BlockState)} instead.
     * @return the copy
     * @see #getState()
     */
    @Deprecated
    public BlockType withState(String state) {
        return new BlockType(faces, blockShape, obstacle, transparent, BlockState.fromString(state), classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the state
     * field.
     * @param state the new value for state
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #getBlockState()
     */
    @Deprecated
    public BlockType withBlockState(BlockState state) {
        return new BlockType(faces, blockShape, obstacle, transparent, state, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the classes
     * field.
     * @param classes the new list of classes
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #getClasses()
     */
    @Deprecated
    public BlockType withClasses(BlockClassId[] classes) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the durability field.
     * @param durability the new durability value
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #getDurability()
     */
    @Deprecated
    public BlockType withDurability(float durability) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    /**
     * Returns a copy with a new value for the damage field.
     * @param damage the new damage value
     * @return the copy
     * @deprecated This method is deprecated in version 0.1.10 (it will be removed in 0.2.+)
     * @see #getDurability()
     */
    @Deprecated
    public BlockType withDamage(float damage) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage, damageMultipliers, orientable, destroyedAs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockType blockType = (BlockType) o;

        if (obstacle != blockType.obstacle) return false;
        if (transparent != blockType.transparent) return false;
        if (Float.compare(blockType.durability, durability) != 0) return false;
        if (Float.compare(blockType.damage, damage) != 0) return false;
        if (orientable != blockType.orientable) return false;
        if (!Arrays.equals(faces, blockType.faces)) return false;
        if (!blockShape.equals(blockType.blockShape)) return false;
        if (!blockState.equals(blockType.blockState)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(classes, blockType.classes)) return false;
        if (!damageMultipliers.equals(blockType.damageMultipliers)) return false;
        return destroyedAs.equals(blockType.destroyedAs);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(faces);
        result = 31 * result + blockShape.hashCode();
        result = 31 * result + (obstacle ? 1 : 0);
        result = 31 * result + (transparent ? 1 : 0);
        result = 31 * result + blockState.hashCode();
        result = 31 * result + Arrays.hashCode(classes);
        result = 31 * result + (durability != +0.0f ? Float.floatToIntBits(durability) : 0);
        result = 31 * result + (damage != +0.0f ? Float.floatToIntBits(damage) : 0);
        result = 31 * result + damageMultipliers.hashCode();
        result = 31 * result + (orientable ? 1 : 0);
        result = 31 * result + destroyedAs.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BlockType(" +
                "faces=" + Arrays.toString(faces) +
                ", blockShape=" + blockShape +
                ", obstacle=" + obstacle +
                ", transparent=" + transparent +
                ", blockState=" + blockState +
                ", classes=" + Arrays.toString(classes) +
                ", durability=" + durability +
                ", damage=" + damage +
                ", damageMultipliers=" + damageMultipliers +
                ", orientable=" + orientable +
                ", destroyedAs=" + destroyedAs +
                ')';
    }
}
