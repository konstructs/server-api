package konstructs.api;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Direction is a class representing one of the 6 possible directions a block can be placed in.
 * This can most easily be understood by imagining the block to be a die. The direction is
 * the face of the dice pointing upwards. E.g. the {@link Direction#UP} will point the upward
 * face of the dice upwards, i.e. the orientation is unchanged. If the direction {@link Direction#DOWN}
 * is used instead, the downward face will be pointing upwards, i.e. the dice is upside down.
 * <p>
 *     This class also supports has functions to get a directional unit vector in the given direction
 *     as well as a rotational matrix that can rotate a vector from the normal position (up is pointing up)
 *     to a the given direction.
 * </p>
 */
public class Direction {
    public static final String UP_STRING_ENCODING = "up";
    public static final String DOWN_STRING_ENCODING = "down";
    public static final String RIGHT_STRING_ENCODING = "right";
    public static final String LEFT_STRING_ENCODING = "left";
    public static final String FORWARD_STRING_ENCODING = "forward";
    public static final String BACKWARD_STRING_ENCODING = "backward";

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

    /**
     * Returns the Direction associated the provided encoding
     * @param encoding The encoded version of the direction
     * @return The singleton instance of the Direction
     */
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

    /**
     * Returns the Direction associated with the provided string encoding
     * @param encoding The string encoding the direction
     * @return The singleton instance of the Direction
     */
    public static Direction get(String encoding) {
        if(encoding.equals(UP_STRING_ENCODING)) {
            return UP;
        } else if(encoding.equals(DOWN_STRING_ENCODING)) {
            return DOWN;
        } else if(encoding.equals(RIGHT_STRING_ENCODING)) {
            return RIGHT;
        } else if(encoding.equals(LEFT_STRING_ENCODING)) {
            return LEFT;
        } else if(encoding.equals(FORWARD_STRING_ENCODING)) {
            return FORWARD;
        } else if(encoding.equals(BACKWARD_STRING_ENCODING)) {
            return BACKWARD;
        } else {
            throw new IllegalArgumentException("No direction encoded by: " + encoding);
        }
    }

    /**
     * Returns the Direction associated with a directional unit vector.
     * @param vector The vector that encodes the direction
     * @return The singleton instance of the Direction
     */
    public static Direction get(Position vector) {
        if(vector.equals(Position.UP)) {
            return UP;
        } else if(vector.equals(Position.DOWN)) {
            return DOWN;
        } else if(vector.equals(Position.LEFT)) {
            return LEFT;
        } else if(vector.equals(Position.RIGHT)) {
            return RIGHT;
        } else if(vector.equals(Position.FORWARD)) {
            return FORWARD;
        } else if(vector.equals(Position.BACKWARD)) {
            return BACKWARD;
        } else {
            throw new IllegalArgumentException("Not a directional vector" + vector);
        }
    }

    /**
     * Returns an evenly distributed random direction
     * @return The random direction selected
     */
    public static Direction getRandom() {
        return get(ThreadLocalRandom.current().nextInt(6));
    }

    private final int encoding;

    private Direction(int encoding) {
        this.encoding = encoding;
    }

    /**
     * Returns the integer encoding of this direction
     * @return The integer encoding of this direction
     */
    public int getEncoding() {
        return encoding;
    }

    /**
     * Returns whether this direction is positive or not
     * @return True if this direction is positive, otherwise false
     */
    public boolean isPositive() {
        switch(encoding) {
            case UP_ENCODING:
            case RIGHT_ENCODING:
            case BACKWARD_ENCODING:
                return true;
            case DOWN_ENCODING:
            case LEFT_ENCODING:
            case FORWARD_ENCODING:
                return false;
            default:
                throw new IllegalStateException("No such direction");
        }
    }

    /**
     * Returns the inverse (opposite) direction
     * @return The inverse direction
     */
    public Direction inverse() {
        switch(encoding) {
            case UP_ENCODING:
                return DOWN;
            case DOWN_ENCODING:
                return UP;
            case RIGHT_ENCODING:
                return LEFT;
            case LEFT_ENCODING:
                return RIGHT;
            case FORWARD_ENCODING:
                return BACKWARD;
            case BACKWARD_ENCODING:
                return FORWARD;
            default:
                throw new IllegalStateException("Invalid direction encoding");
        }
    }

    /**
     * Returns the directional unit vector of the direction
     * @return The directions unit vector
     */
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

    /* Rotate -90 degree over z axis so that UP is pointing RIGHT */
    private static final Matrix RIGHT_ROTATION =
            new Matrix(
                    +0, -1, +0,
                    +1, +0, +0,
                    +0, +0, +1
            );

    /* Rotate +90 degree over z axis so that UP is pointing LEFT */
    private static final Matrix LEFT_ROTATION =
            new Matrix(
                    +0, +1, +0,
                    -1, +0, +0,
                    +0, +0, +1
            );


    /* Rotate -90 degree over x axis so that UP is pointing BACKWARD */
    private static final Matrix BACKWARD_ROTATION =
            new Matrix(
                    +1, +0, +0,
                    +0, +0, +1,
                    +0, -1, +0
            );

    /* Rotate 90 degree over x axis so that UP is pointing FORWARD */
    private static final Matrix FORWARD_ROTATION =
            new Matrix(
                    +1, +0, +0,
                    +0, +0, -1,
                    +0, +1, +0
            );

    /**
     * Returns the rotation matrix releated to this direction
     * @return The related rotation matrix
     */
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

    /**
     * Returns the inverse of the rotation matrix related to this direction
     * @return The inverse of the related rotation matrix
     */
    public Matrix getInverseRotationMatrix() {
        switch(encoding) {
            case UP_ENCODING:
                return Matrix.IDENTITY;
            case DOWN_ENCODING:
                return DOWN_ROTATION;
            case LEFT_ENCODING:
                return RIGHT_ROTATION;
            case RIGHT_ENCODING:
                return LEFT_ROTATION;
            case BACKWARD_ENCODING:
                return FORWARD_ROTATION;
            case FORWARD_ENCODING:
                return BACKWARD_ROTATION;
            default:
                throw new IllegalArgumentException("Invalid direction encoding");
        }
    }


    /**
     * Returns the string encoding of this direction
     * @return String encoding
     */
    public String getStringEncoding() {
        switch(encoding) {
            case UP_ENCODING:
                return UP_STRING_ENCODING;
            case DOWN_ENCODING:
                return DOWN_STRING_ENCODING;
            case LEFT_ENCODING:
                return LEFT_STRING_ENCODING;
            case RIGHT_ENCODING:
                return RIGHT_STRING_ENCODING;
            case BACKWARD_ENCODING:
                return BACKWARD_STRING_ENCODING;
            case FORWARD_ENCODING:
                return FORWARD_STRING_ENCODING;
            default:
                throw new IllegalArgumentException("Invalid direction encoding");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        return encoding == direction.encoding;

    }

    @Override
    public int hashCode() {
        return encoding;
    }

    @Override
    public String toString() {
        return "Direction(" +
                "encoding=" + getStringEncoding() + "[" + encoding + "]" +
                ')';
    }
}
