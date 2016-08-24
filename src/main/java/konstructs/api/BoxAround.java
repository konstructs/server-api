package konstructs.api;

/**
 * Class representing a Box around a central block
 */
public class BoxAround implements BoxShape {
    private final Position center;
    private final Position radius;
    private final Box box;
    /**
     *
     * @param center The block in the center of the box
     * @param radius The number of blocks the box extends in each dimension
     */
    public BoxAround(Position center, Position radius) {
        box = new Box(center.subtract(radius), center.add(radius.add(Position.ONE)));
        this.center = center;
        this.radius = radius;
    }

    /**
     * Returns the central position
     * @return The central position of this box
     */
    public Position getCenter() {
        return center;
    }

    /**
     * Returns the radius of this box.
     * The radius is the number of blocks that this box extends out in all dimensions.
     * @return The radius
     */
    public Position getRadius() {
        return radius;
    }

    @Override
    public Box getBox() {
        return box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxAround boxAround = (BoxAround) o;

        if (!center.equals(boxAround.center)) return false;
        return radius.equals(boxAround.radius);

    }

    @Override
    public int hashCode() {
        int result = center.hashCode();
        result = 31 * result + radius.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BoxAround(" +
                "radius=" + radius +
                ", center=" + center +
                ')';
    }
}
