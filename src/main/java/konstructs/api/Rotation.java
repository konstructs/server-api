package konstructs.api;

/**
 * Rotation is a class that represents a rotation. A block placed in a specific {@link Direction direction}
 * can have one of four rotations. You can think of a dice placed with the 1 side pointing upwards. It
 * can have either 2, 3, 4 or 5 pointing forwards, while 1 side is still pointing upwards,
 */
public class Rotation {
    public static final int IDENTITY_ENCODING = 0;
    public static final int LEFT_ENCODING = 1;
    public static final int RIGHT_ENCODING = 2;
    public static final int HALF_ENCODING = 3;

    public static final String IDENTITY_STRING_ENCODING = "identity";
    public static final String LEFT_STRING_ENCODING = "left";
    public static final String RIGHT_STRING_ENCODING = "right";
    public static final String HALF_STRING_ENCODING = "half";

    /**
     * No rotation at all
     */
    public static final Rotation IDENTITY = new Rotation(IDENTITY_ENCODING);
    /**
     * Rotated to the left
     */
    public static final Rotation LEFT = new Rotation(LEFT_ENCODING);
    /**
     * Rotated to the right
     */
    public static final Rotation RIGHT = new Rotation(RIGHT_ENCODING);
    /**
     * Rotated 180 degrees (half of a full rotation)
     */
    public static final Rotation HALF = new Rotation(HALF_ENCODING);

    /**
     * Returns the Orientation associated the provided encoding
     * @param encoding The encoded value of the orientation
     * @return The singleton instance of the Orientation
     */
    public static Rotation get(int encoding) {
        switch(encoding) {
            case IDENTITY_ENCODING:
                return IDENTITY;
            case LEFT_ENCODING:
                return LEFT;
            case RIGHT_ENCODING:
                return RIGHT;
            case HALF_ENCODING:
                return HALF;
            default:
                throw new IllegalArgumentException("No rotation encoded by: " + encoding);
        }
    }

    private final int encoding;

    private Rotation(int encoding) {
        this.encoding = encoding;
    }

    private static final Matrix LEFT_ROTATION =
            new Matrix(
                    +0, +0, +1,
                    +0, +1, +0,
                    -1, +0, +0
            );

    private static final Matrix RIGHT_ROTATION =
            new Matrix(
                    +0, +0, -1,
                    +0, +1, +0,
                    +1, +0, +0
            );

    private static final Matrix HALF_ROTATION =
            new Matrix(
                    -1, +0, +0,
                    +0, +1, +0,
                    +0, +0, -1
            );

    /**
     * Returns a matrix representing this rotation.
     * This matrix can be used to rotate a vector.
     * @return The rotation matrix of this rotation.
     */
    public Matrix getMatrix() {
        switch(encoding) {
            case IDENTITY_ENCODING:
                return Matrix.IDENTITY;
            case LEFT_ENCODING:
                return LEFT_ROTATION;
            case RIGHT_ENCODING:
                return RIGHT_ROTATION;
            case HALF_ENCODING:
                return HALF_ROTATION;
            default:
                throw new IllegalStateException("Rotation invalidly encoded!");
        }
    }

    /**
     * Returns the inverse of a matrix representing this rotation.
     * This matrix can be used to rotate a vector.
     * @return The inverse of a rotation matrix of this rotation.
     */
    public Matrix getInverseMatrix() {
        switch(encoding) {
            case IDENTITY_ENCODING:
                return Matrix.IDENTITY;
            case LEFT_ENCODING:
                return RIGHT_ROTATION;
            case RIGHT_ENCODING:
                return LEFT_ROTATION;
            case HALF_ENCODING:
                return HALF_ROTATION;
            default:
                throw new IllegalStateException("Rotation invalidly encoded!");
        }
    }

    /**
     * Returns the integer encoding of this rotation
     * @return The integer encoding
     */
    public int getEncoding() {
        return encoding;
    }

    /**
     * Returns the string encoding of this rotation
     * @return The string encoding
     */
    public String getStringEncoding() {
        switch(encoding) {
            case IDENTITY_ENCODING:
                return IDENTITY_STRING_ENCODING;
            case LEFT_ENCODING:
                return LEFT_STRING_ENCODING;
            case RIGHT_ENCODING:
                return RIGHT_STRING_ENCODING;
            case HALF_ENCODING:
                return HALF_STRING_ENCODING;
            default:
                throw new IllegalStateException("Rotation invalidly encoded!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rotation rotation = (Rotation) o;

        return encoding == rotation.encoding;

    }

    @Override
    public int hashCode() {
        return encoding;
    }

    @Override
    public String toString() {
        return "Rotation(" +
                "encoding=" + getStringEncoding() + "["  + encoding + "]" +
                ')';
    }
}
