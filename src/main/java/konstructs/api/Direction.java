package konstructs.api;

public class Direction {
    public static final int UP_ENCODING = 0;
    public static final int DOWN_ENCODING = 1;
    public static final int RIGHT_ENCODING = 2;
    public static final int LEFT_ENCODING = 3;
    public static final int FORWARD_ENCODING = 4;
    public static final int BACKWARD_ENCODING = 5;

    public static final Direction UP = new Direction(UP_ENCODING);
    public static final Direction DOWN = new Direction(DOWN_ENCODING);
    public static final Direction RIGHT = new Direction(RIGHT_ENCODING);
    public static final Direction LEFT = new Direction(LEFT_ENCODING);
    public static final Direction FORWARD = new Direction(FORWARD_ENCODING);
    public static final Direction BACKWARD = new Direction(BACKWARD_ENCODING);

    private final int encoding;

    private Direction(int encoding) {
        this.encoding = encoding;
    }

    public int getEncoding() {
        return encoding;
    }

    public static Direction get(int encoding) {
        switch(encoding) {
            case UP_ENCODING:
                return UP;
            case DOWN_ENCODING:
                return DOWN;
            case RIGHT_ENCODING:
                return RIGHT;
            case LEFT_ENCODING:
                return LEFT;
            case FORWARD_ENCODING:
                return FORWARD;
            case BACKWARD_ENCODING:
                return BACKWARD;
            default:
                throw new IllegalArgumentException("No direction encoded by: " + encoding);
        }
    }

    public boolean isPositive() {
        switch(encoding) {
            case UP_ENCODING:
            case RIGHT_ENCODING:
            case FORWARD_ENCODING:
                return true;
            case DOWN_ENCODING:
            case LEFT_ENCODING:
            case BACKWARD_ENCODING:
                return false;
            default:
                throw new IllegalStateException("No such direction");
        }
    }

    public Position getVector() {
        switch(encoding) {
            case UP_ENCODING:
                return Position.UP;
            case DOWN_ENCODING:
                return Position.DOWN;
            case RIGHT_ENCODING:
                return Position.RIGHT;
            case LEFT_ENCODING:
                return Position.LEFT;
            case FORWARD_ENCODING:
                return Position.FORWARD;
            case BACKWARD_ENCODING:
                return Position.BACKWARD;
            default:
                throw new IllegalStateException("Invalid direction encoding");
        }
    }

    /* Rotate 180 degree over z axis so that UP is pointing down */
    private static final Matrix DOWN_ROTATION =
            new Matrix(
                    -1, +0, +0,
                    +0, -1, +0,
                    +0, +0, +1
            );

    /* Rotate -90 degree over z axis so that UP is pointing LEFT */
    private static final Matrix LEFT_ROTATION =
            new Matrix(
                    +0, +1, +0,
                    -1, +0, +0,
                    +0, +0, +1
            );

    /* Rotate +90 degree over z axis so that UP is pointing RIGHT */
    private static final Matrix RIGHT_ROTATION =
            new Matrix(
                    +0, -1, +0,
                    +1, +0, +0,
                    +0, +0, +1
            );


    /* Rotate -90 degree over x axis so that UP is pointing FORWARD */
    private static final Matrix FORWARD_ROTATION =
            new Matrix(
                    +1, +0, +0,
                    +0, +0, +1,
                    +0, -1, +0
            );

    /* Rotate 90 degree over x axis so that UP is pointing BACKWARD */
    private static final Matrix BACKWARD_ROTATION =
            new Matrix(
                    +1, +0, +0,
                    +0, +0, -1,
                    +0, +1, +0
            );

    public Matrix getRotationMatrix() {
        switch(encoding) {
            case UP_ENCODING:
                return Matrix.IDENTITY;
            case DOWN_ENCODING:
                return DOWN_ROTATION;
            case LEFT_ENCODING:
                return LEFT_ROTATION;
            case RIGHT_ENCODING:
                return RIGHT_ROTATION;
            case BACKWARD_ENCODING:
                return BACKWARD_ROTATION;
            case FORWARD_ENCODING:
                return FORWARD_ROTATION;
            default:
                throw new IllegalArgumentException("Invalid direction encoding");
        }
    }

}
