package konstructs.api;

import java.io.Serializable;
import java.util.Arrays;

/**
 * BlockType is a class that holds all relevant information regarding
 * a block's type. Instances of this class is generated when the
 * server is started from the server configuration. It is immutable
 * and serializable.
 */
public final class BlockType implements Serializable {
    public static final BlockClassId[] NO_CLASSES = {};
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
        this(faces, shape, obstacle, transparent, state, classes, DEFAULT_DURABILITY, DEFAULT_DAMAGE);
    }

    public BlockType(int[] faces, BlockShape blockShape, boolean obstacle, boolean transparent, BlockState blockState, BlockClassId[] classes, float durability, float damage) {
        this.faces = faces;
        this.blockShape = blockShape;
        this.obstacle = obstacle;
        this.transparent = transparent;
        this.blockState = blockState;
        this.classes = classes;
        this.durability = durability;
        this.damage = damage;
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
     * @see #getFaces()
     */
    public BlockType withFaces(int[] faces) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage);
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
        return new BlockType(faces, BlockShape.fromString(shape), obstacle, transparent, blockState, classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the shape
     * field.
     * @param shape the new value for shape
     * @return the copy
     * @see #getBlockShape()
     */
    public BlockType withBlockShape(BlockShape shape) {
        return new BlockType(faces, shape, obstacle, transparent, blockState, classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the obstacle
     * field.
     * @param obstacle the new value for obstacle
     * @return the copy
     * @see #isObstacle()
     */
    public BlockType withObstacle(boolean obstacle) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the transparent
     * field.
     * @param transparent the new value for transparent
     * @return the copy
     * @see #isTransparent()
     */
    public BlockType withTransparent(boolean transparent) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage);
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
        return new BlockType(faces, blockShape, obstacle, transparent, BlockState.fromString(state), classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the state
     * field.
     * @param state the new value for state
     * @return the copy
     * @see #getBlockState()
     */
    public BlockType withBlockState(BlockState state) {
        return new BlockType(faces, blockShape, obstacle, transparent, state, classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the classes
     * field.
     * @param classes the new list of classes
     * @return the copy
     * @see #getClasses()
     */
    public BlockType withClasses(BlockClassId[] classes) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the durability field.
     * @param durability the new durability value
     * @return the copy
     * @see #getDurability()
     */
    public BlockType withDurability(float durability) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage);
    }

    /**
     * Returns a copy with a new value for the damage field.
     * @param damage the new damage value
     * @return the copy
     * @see #getDurability()
     */
    public BlockType withDamage(float damage) {
        return new BlockType(faces, blockShape, obstacle, transparent, blockState, classes, durability, damage);
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
        if (!Arrays.equals(faces, blockType.faces)) return false;
        if (!blockShape.equals(blockType.blockShape)) return false;
        if (!blockState.equals(blockType.blockState)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(classes, blockType.classes);

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
                ')';
    }
}
