package konstructs.api;

/**
 * Orientation is a class that represents an orientation of a block. An orientation is a combination
 * of a {@link Direction direction} and a {@link Rotation rotation}. Together they represent any possible
 * orientation that a block can have,
 */
public class Orientation {
    public static final Orientation NORMAL = new Orientation(Direction.UP, Rotation.IDENTITY);

    /**
     * Returns the orientation related to the encoded direction and rotation
     * @param direction The encoded direction
     * @param rotation The encoded orientation
     * @return The orientation instance
     */
    public static final Orientation get(int direction, int rotation) {
        return get(Direction.get(direction), Rotation.get(rotation));
    }

    /**
     * Returns the orientation constructed by the given direction and rotation
     * @param direction The given direction
     * @param rotation The given rotation
     * @return The orientation instance
     */
    public static final Orientation get(Direction direction, Rotation rotation) {
        /* TODO: Return static instances for all of the 24 orientations */
        if(direction.equals(Direction.UP) && rotation.equals(Rotation.IDENTITY)) {
            return NORMAL;
        } else {
            return new Orientation(direction, rotation);
        }
    }

    private final Direction direction;
    private final Rotation rotation;

    private Orientation(Direction direction, Rotation rotation) {
        this.direction = direction;
        this.rotation = rotation;
    }

    /**
     * Returns the direction of this orientation
     * @return The direction of this orientation
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the rotation of this orientation
     * @return The rotation of this orientation
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Return the rotation matrix of this orientation for finding a face pointed at.
     * This matrix rotates a vector in relation to both the direction and rotation of this orientation in a way that
     * the direction pointed at by the user is translated into the direction of the face pointed at.
     * @return The rotation matrix
     * @see #translateFacePointedAt(Direction) for a usage example
     */
    public Matrix getFacePointedAtRotationMatrix() {
        return rotation.getMatrix().multiply(direction.getRotationMatrix());
    }

    /**
     * Return the rotation matrix of this orientation for finding the direction a face is pointing in.
     * This matrix rotates a vector in relation to both the direction and rotation of this orientation in a way that
     * the direction the face pointing is translated into the direction of the face that is pointing in this direction.
     * @return The rotation matrix
     * @see #translateFacePointingIn(Direction) for a usage example
     */
    public Matrix getFacePointingInRotationMatrix() {
        return direction.getInverseRotationMatrix().multiply(rotation.getInverseMatrix());
    }

    /**
     * Translate (rotate) a face pointed at using this orientation.
     * If the direction is seen as a face pointed at, then this method returns the direction of the face translated
     * by this orientation. E.g. if passed the upward direction (block was pointed at from above) and the orientation
     * has a direction of downward then face downward will be returned, since it is pointing up.
     *
     * This means that this method can be used when one want to know which face that was interacted with when a block
     * is rotated.
     * @param dir The direction to be translated
     * @return The translated direction
     */
    public Direction translateFacePointedAt(Direction dir) {
        return Direction.get(getFacePointedAtRotationMatrix().multiply(dir.getVector()));
    }

    /**
     * Translate (rotate) a face pointing in a certain direction using this orientation.
     * If the direction is the direction a certain face of a block is pointing towards in the normal
     * (not rotated) case, e.g. a the direction of the beam from a light, then the translated direction is
     * the direction the face is pointing towards after the block was rotated. In the example, by knowing
     * in which direction the light is normally heading, then this function provides the direction of the light
     * after the block was rotated.
     * @param dir The direction of the face in the original rotation
     * @return The direction of the face after the block was rotated
     */
    public Direction translateFacePointingIn(Direction dir) {
        return Direction.get(getFacePointingInRotationMatrix().multiply(dir.getVector()));
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
