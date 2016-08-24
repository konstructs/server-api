package konstructs.api;

/**
 * Class that represents a {@link Box box} starting at a certain position and
 * ending at another certain position.
 * It differs from {@link Box box} (which it also extends) in the sense that
 * end is inclusive and start maybe be smaller than end in all dimensions.
 *
 */

public class InclusiveBox implements BoxShape {
    private final Position start;
    private final Position end;
    private final Box box;

    private static Position getUntil(Position start, Position end) {
        Position f = start;
        Position t = end;

        /* Swap around dimensions that are not increasing */
        if(f.getX() > t.getX()) {
            t = t.withX(f.getX());
        }

        if(f.getY() > t.getY()) {
            t = t.withY(f.getY());
        }

        if(f.getZ() > t.getZ()) {
            t = t.withZ(f.getZ());
        }

        /* Extend end position to become inclusive */
        t = t.add(Position.ONE);

        return t;
    }

    private static Position getFrom(Position start, Position end) {
        Position f = start;
        Position t = end;

        /* Swap around dimensions that are not increasing */
        if(f.getX() > t.getX()) {
            f = f.withX(t.getX());
        }

        if(f.getY() > t.getY()) {
            f = f.withY(t.getY());
        }

        if(f.getZ() > t.getZ()) {
            f = f.withZ(t.getZ());
        }

        return f;
    }

    /**
     * Creates a new immutable InclusiveBox
     * @param start The start position of the box
     * @param end The end position (inclusive) of the box
     */
    public InclusiveBox(Position start, Position end) {
        this.box = new Box(getFrom(start, end), getUntil(start, end));
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start position of the box
     * @return The start position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Returns the end position of the box
     * @return The end position
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Returns an array index for a position relative to start
     * @param position The requested position, relative to start
     * @return
     */
    public int arrayIndexLocal(Position position) {
        return box.arrayIndex(start.add(position));
    }

    /**
     * Return a block from an array using a psoition relative to start
     * @param p The position relative to start
     * @param blocks The array of blocks
     * @param <T> The type of the block
     * @return The block at the position
     */
    public <T> T getLocal(Position p, T blocks[]) {
        return blocks[arrayIndexLocal(p)];
    }

    @Override
    public Box getBox() {
        return box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InclusiveBox that = (InclusiveBox) o;

        if (!start.equals(that.start)) return false;
        return end.equals(that.end);

    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "InclusiveBox(" +
                "start=" + start +
                ", end=" + end +
                ')';
    }

}
