package konstructs.api;

public class Rotation {
    public static final int IDENTITY_ENCODING = 0;
    public static final int LEFT_ENCODING = 1;
    public static final int RIGHT_ENCODING = 2;
    public static final int HALF_ENCODING = 3;

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
                    +1, +0, +0,
                    +0, +0, +1,
                    +0, -1, +0
            );

    private static final Matrix RIGHT_ROTATION =
            new Matrix(
                    +1, +0, +0,
                    +0, +0, -1,
                    +0, +1, +0
            );

    private static final Matrix HALF_ROTATION =
            new Matrix(
                    +1, +0, +0,
                    +0, -1, +0,
                    +0, +0, -1
            );

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

    public int getEncoding() {
        return encoding;
    }
}
