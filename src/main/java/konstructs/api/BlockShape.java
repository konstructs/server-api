package konstructs.api;

import java.io.Serializable;

/**
 * BlockShape is a class that holds all relevant information regarding
 * a block's physical shape. There are only two instances of this class,
 * representing the two shapes of blocks in konstructs:
 * {@link #BLOCK} and {@link #PLANT}.
 *
 * The class is immutable and serializable.
 */
public class BlockShape implements Serializable {
    public static final BlockShape BLOCK = new BlockShape("block");
    public static final BlockShape PLANT = new BlockShape("plant");

    /**
     * Returns the shape that matches the string or throws a IllegalArgumentException.
     * @param shape String to name one of two shapes: "block" or "plant"
     * @return {@link #BLOCK} or {@link #PLANT}
     * @throws IllegalArgumentException if string does not match a shape
     */
    public static BlockShape fromString(String shape) {
        if(shape.equals(BLOCK.getShape())) {
            return BLOCK;
        } else if(shape.equals(PLANT.getShape())) {
            return PLANT;
        } else {
            throw new IllegalArgumentException("No known shape: " + shape);
        }
    }

    private final String shape;

    private BlockShape(String shape) {
        this.shape = shape;
    }

    /**
     * Returns the string representation of the shape
     * @return String representation of shape
     */
    public String getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockShape that = (BlockShape) o;

        return shape.equals(that.shape);

    }

    @Override
    public int hashCode() {
        return shape.hashCode();
    }

    @Override
    public String toString() {
        return "BlockShape(" +
                "shape='" + shape + '\'' +
                ')';
    }
}
