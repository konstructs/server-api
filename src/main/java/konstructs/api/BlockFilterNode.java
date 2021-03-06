package konstructs.api;

import java.util.Arrays;

/**
 * BlockFilterNode is a BlockFilter that is used to aggregate a
 * set of properties of a block that needs to be matched. Each
 * property (set using the with* methods) is required to match
 * for the filter as a whole to match. In technical terms it can
 * be said that the BlockFilterNode uses logical AND operator to
 * aggregate the properties to be checked.
 *
 * <p>
 *     Note: The easiest way to create a BlockFilter is to use the
 *     BlockFilterFactory!
 * </p>
 *
 * @see BlockFilterFactory
 */
public class BlockFilterNode extends BlockFilter {
    private final String namespace;
    private final String name;
    private final BlockShape shape;
    private final Boolean transparent;
    private final Boolean obstacle;
    private final BlockState state;
    private final BlockClassId[] classes;

    /**
     * Constructs an immutable BlockFilterNode. All parameters may be
     * null (i.e. unset).
     * <p>
     *     Note: The easiest way to create a BlockFilter is to use the
     *     BlockFilterFactory!
     * </p>
     *
     * @param namespace The namespace of the BlockTypeId
     * @param name The names of the BlockTypeId
     * @param shape The shape of the BlockType
     * @param transparent Is the BlockType transparent?
     * @param obstacle Is the BlockType an obstacle?
     * @deprecated As of API version 0.1.3 (will be removed in 0.2.+)
     *             Replaced by {@link #BlockFilterNode(String, String, String, Boolean, Boolean, String)}.
     *             This constructor always sets state to null (i.e. leaves it unset)
     * @see BlockFilterFactory
     */
    @Deprecated
    public BlockFilterNode(String namespace, String name, String shape, Boolean transparent, Boolean obstacle) {
        this(namespace, name, shape, transparent, obstacle, null);
    }

    /**
     * Constructs an immutable BlockFilterNode. All parameters may be
     * null (i.e. unset).
     * <p>
     *     Note: The easiest way to create a BlockFilter is to use the
     *     BlockFilterFactory!
     * </p>
     *
     * @param namespace The namespace of the BlockTypeId
     * @param name The names of the BlockTypeId
     * @param shape The shape of the BlockType
     * @param transparent Is the BlockType transparent?
     * @param obstacle Is the BlockType an obstacle?
     * @param state The state of this block
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+).
     * Use {@link #BlockFilterNode(String, String, BlockShape, Boolean, Boolean, BlockState, BlockClassId[])} instead
     * @see BlockFilterFactory
     */
    @Deprecated
    public BlockFilterNode(String namespace, String name, String shape, Boolean transparent, Boolean obstacle, String state) {
        this.namespace = namespace;
        this.name = name;
        this.shape = shape != null ? BlockShape.fromString(shape) : null;
        this.transparent = transparent;
        this.obstacle = obstacle;
        this.state = state != null ? BlockState.fromString(state) : null;
        this.classes = BlockType.NO_CLASSES;
    }

    /**
     * Constructs an immutable BlockFilterNode. All parameters may be
     * null (i.e. unset).
     * <p>
     *     Note: The easiest way to create a BlockFilter is to use the
     *     BlockFilterFactory!
     * </p>
     *
     * @param namespace The namespace of the BlockTypeId
     * @param name The names of the BlockTypeId
     * @param shape The shape of the BlockType
     * @param transparent Is the BlockType transparent?
     * @param obstacle Is the BlockType an obstacle?
     * @param state The state of the BlockType
     * @param classes The classes of the BlockType
     * @see BlockFilterFactory
     */
    public BlockFilterNode(String namespace, String name, BlockShape shape, Boolean transparent, Boolean obstacle, BlockState state,
        BlockClassId[] classes) {
        this.namespace = namespace;
        this.name = name;
        this.shape = shape;
        this.transparent = transparent;
        this.obstacle = obstacle;
        this.state = state;
        this.classes = classes;
    }

    /*
     * Creates an empty block filter that matches everything
     */
    BlockFilterNode() {
        this.namespace = null;
        this.name = null;
        this.shape = null;
        this.transparent = null;
        this.obstacle = null;
        this.state = null;
        this.classes = null;
    }
    /**
     * Create a new BlockFilterNode with the specific namespace set
     * @param namespace The namespace to set
     * @return The new BlockFilterNode with the namespace set
     */
    public BlockFilterNode withNamespace(String namespace) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the specific name set
     * @param name The name to set
     * @return The new BlockFilterNode with name set
     */
    public BlockFilterNode withName(String name) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the specific BlockTypeId set.
     * This method sets both namespace and name.
     * @param blockTypeId The BlockTypeId to set
     * @return The new BlockFilterNode with both the namespace and name set
     */
    public BlockFilterNode withBlockTypeId(BlockTypeId blockTypeId) {
        return withNamespace(blockTypeId.getNamespace())
                .withName(blockTypeId.getName());
    }

    /**
     * Create a new BlockFilterNode with the specific shape set
     * @param shape The shape to set
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+).
     *             Use {@link #withBlockShape(BlockShape)} instead
     * @return The new BlockFilterNode with the shape set
     */
    @Deprecated
    public BlockFilterNode withShape(String shape) {
        return new BlockFilterNode(namespace, name, BlockShape.fromString(shape), transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the specific shape set
     * @param shape The shape to set
     * @return The new BlockFilterNode with the shape set
     */
    public BlockFilterNode withBlockShape(BlockShape shape) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the transparent property set
     * @param transparent Must the block be transparent?
     * @return The new BlockFilterNode with the transparent property set
     */
    public BlockFilterNode withTransparent(Boolean transparent) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the obstacle property set
     * @param obstacle Must the block be an obstacle?
     * @return The new BlockFilterNode with the obstacle property set
     */
    public BlockFilterNode withObstacle(Boolean obstacle) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the state property set
     * @param state The state to be matched
     * @deprecated As of API 0.1.7 (will be removed in 0.2.+).
     *             Use {@link #withBlockState(BlockState)} instead
     * @return The new BlockFilterNode with the state property set
     */
    @Deprecated
    public BlockFilterNode withState(String state) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, BlockState.fromString(state), classes);
    }

    /**
     * Create a new BlockFilterNode with the state property set
     * @param state The state to be matched
     * @return The new BlockFilterNode with the state property set
     */
    public BlockFilterNode withBlockState(BlockState state) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with the classes property set
     * @param classes The classes to be matched
     * @return The new BlockFilterNode with the classes property set
     */
    public BlockFilterNode withClasses(BlockClassId[] classes) {
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, classes);
    }

    /**
     * Create a new BlockFilterNode with a single class added to its array of
     * classes. Each time this method is called the array is extended. It can
     * therefore be called several times.
     * @param clazz the class to be added to the array of classes to be matched
     * @return The new BlockFilterNode with the class added to the array
     */
    public  BlockFilterNode withClassAdded(BlockClassId clazz) {
        BlockClassId[] newClasses = Arrays.copyOf(classes, classes.length + 1);
        newClasses[classes.length] = clazz;
        return new BlockFilterNode(namespace, name, shape, transparent, obstacle, state, newClasses);
    }

    @Override
    public boolean matches(BlockTypeId blockTypeId, BlockType blockType) {
        return ((namespace == null || namespace.equals(blockTypeId.getNamespace()))
                && (name == null || name.equals(blockTypeId.getName()))
                && (shape == null || shape.equals(blockType.getBlockShape()))
                && (transparent == null || transparent.equals(blockType.isTransparent()))
                && (obstacle == null || obstacle.equals(blockType.isObstacle()))
                && (state == null || state.equals(blockType.getBlockState()))
                && (classes == null || blockType.hasClasses(classes))
            );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockFilterNode that = (BlockFilterNode) o;

        if (namespace != null ? !namespace.equals(that.namespace) : that.namespace != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (shape != null ? !shape.equals(that.shape) : that.shape != null) return false;
        if (transparent != null ? !transparent.equals(that.transparent) : that.transparent != null) return false;
        if (obstacle != null ? !obstacle.equals(that.obstacle) : that.obstacle != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return Arrays.equals(classes, that.classes);

    }

    @Override
    public int hashCode() {
        int result = namespace != null ? namespace.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shape != null ? shape.hashCode() : 0);
        result = 31 * result + (transparent != null ? transparent.hashCode() : 0);
        result = 31 * result + (obstacle != null ? obstacle.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(classes);
        return result;
    }

    @Override
    public String toString() {
        return "BlockFilterNode(" +
                "namespace='" + namespace + '\'' +
                ", name='" + name + '\'' +
                ", shape=" + shape +
                ", transparent=" + transparent +
                ", obstacle=" + obstacle +
                ", state=" + state +
                ", classes=" + Arrays.toString(classes) +
                ')';
    }
}
