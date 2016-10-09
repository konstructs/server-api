package konstructs.api;

/**
 * Represents a line shape that extends from a certain position in a certain direction.
 */
public class DirectionalLine implements BoxShape {
    private final Direction direction;
    private final int length;
    private final InclusiveBox box;

    /**
     * Creates a new immutable DirectionalLine
     * @param start The start position of the line
     * @param direction The direction of the line
     * @param length The length of the line (in number of blocks)
     */
    public DirectionalLine(Position start, Direction direction, int length) {
        this.box = new InclusiveBox(start, start.add(direction.getVector().multiply(length)));
        this.direction = direction;
        this.length = length;
    }

    /**
     * Returns the start position of the line
     * @return The start position
     */
    public Position getStart() {
        return box.getStart();
    }

    /**
     * Returns the end position of the line
     * @return The end
     */
    public Position getEnd() {
        return box.getEnd();
    }

    /**
     * Returns the direction of the line
     * @return The direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the length of the line
     * @return The length
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns an array index local to the line, starting at {@link #getStart()}
     * @param index The index of the block on the line
     * @return The index of the block in the array
     */
    public int arrayIndexLocal(int index) {
        return getBox().arrayIndex(box.getStart().add(direction.getVector().multiply(index)));
    }

    /**
     * Returns a block from an array based on a local index
     * @param index The index of the block on the line
     * @param blocks The array of blocks
     * @param <T> The type of the block
     * @return The block
     * @see #arrayIndexLocal(int)
     */
    public <T> T getLocal(int index, T blocks[]) {
        return blocks[arrayIndexLocal(index)];
    }

    @Override
    public Box getBox() {
        return box.getBox();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectionalLine that = (DirectionalLine) o;

        return box.equals(that.box);

    }

    @Override
    public int hashCode() {
        return box.hashCode();
    }

    @Override
    public String toString() {
        return "DirectionalLine(" +
                "direction=" + direction +
                ", length=" + length +
                ", start=" + getStart() +
                ')';
    }
}
