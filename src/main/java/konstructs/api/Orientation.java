package konstructs.api;

/**
 * Created by petter on 2016-07-10.
 */
public class Orientation {
    public static final Orientation NORMAL = new Orientation(Direction.UP, Rotation.IDENTITY);

    public static final Orientation create(int direction, int rotation) {
        /* TODO: Return static instances from a finite set of the 24 orientations */
        return new Orientation(Direction.get(direction), Rotation.get(rotation));
    }

    public static final Orientation create(Direction direction, Rotation rotation) {
        /* TODO: Return static instances from a finite set of the 24 orientations */
        return new Orientation(direction, rotation);
    }

    private final Direction direction;
    private final Rotation rotation;

    private Orientation(Direction direction, Rotation rotation) {
        this.direction = direction;
        this.rotation = rotation;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Matrix getRotationMatrix() {
        return direction.getRotationMatrix().multiply(rotation.getMatrix());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orientation that = (Orientation) o;

        if (direction != that.direction) return false;
        return rotation == that.rotation;

    }

    @Override
    public int hashCode() {
        int result = direction.hashCode();
        result = 31 * result + rotation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Orientation(" +
                "direction=" + direction +
                ", rotation=" + rotation +
                ')';
    }
}
