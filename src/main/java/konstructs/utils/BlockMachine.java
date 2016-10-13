package konstructs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import konstructs.api.Direction;
import konstructs.api.Matrix;
import konstructs.api.Position;
import konstructs.api.BlockTypeId;

/**
 * BlockMachine is a immutable class that represents a block machine for a given alphabet.
 * A block machine can be seen as a <a href="https://en.wikipedia.org/wiki/Turtle_graphics">drawing turtle</a>
 * acting in three dimensions. Instead of drawing, the BLockMachine generates a map of blocks to be placed
 * into the world. The BlockMachine generates this map from a program. The program is a mix of the built in commands
 * and a user specified alphabet.
 * <p>
 * The user specified alphabet is a map from a character to a BlockTypeId. Whenever the BlockMachine reads a character
 * from the program it will look into this map and add the corresponding BlockTypeId to the map of blocks to be
 * placed. If the character is not found in the map a vacuum block is placed (BlockTypeId.VACUUM) instead. If the
 * character refers to one of the build in commands, the command is run instead of placing a block.
 * </p>
 * <p>
 * The built in commands are used to alter the build direction of the BlockMachine. From the beginning the
 * BlockMachine is building in the upwards direction. The following build in commands are available:
 * </p>
 * <ul>
 *     <li>
 *         <code>&amp;</code> will modify the direction by turning downwards.
 *     </li>
 *     <li>
 *         <code>^</code> will modify the direction by turning upwards.
 *     </li>
 *     <li>
 *         <code>+</code> will modify the direction by turning to the left.
 *     </li>
 *     <li>
 *         <code>-</code> will modify the direction by turning to the right.
 *     </li>
 *     <li>
 *         <code>\</code> will modify the direction by rolling to the left.
 *     </li>
 *     <li>
 *         <code>/</code> will modify the direction by rolling to the right.
 *     </li>
 *     <li>
 *         <code>[</code> pushes the current position and direction to a stack.
 *     </li>
 *     <li>
 *         <code>]</code> recalls the current position and direction from a stack.
 *     </li>
 *     <li>
 *         <code>&curren;</code> increases the radius of the cube built with 1.
 *     </li>
 *     <li>
 *         <code>#</code> decreases the radius of the cube built with 1.
 *     </li>
 *     <li>
 *         <code>&sect;</code> increases the step taken by 1.
 *     </li>
 *     <li>
 *         <code>!</code> decreases the step taken by 1.
 *     </li>
 *     <li>
 *         <code>&lt;</code> increases the radius by 1 and set the step so that the cubes built are
 *         directly adjacent to each other (2 * radius + 1).
 *     </li>
 *     <li>
 *         <code>&gt;</code> decreases the radius by 1 and set the step so that the cubes built are
 *         directly adjacent to each other (2 * radius + 1)
 *     </li>
 *     <li>
 *         <code>*</code> use a spherical shaped brush
 *     </li>
 *     <li>
 *         <code>#</code> use a cube shaped brush (default)
 *     </li>
 *     <li>
 *         <code>½</code> let randomly one in ten blocks of outer layer of the brush fail (imperfect drawing)
 *     </li>
 *     <li>
 *         <code>1</code> all blocks of the brush are perfectly drawn
 *     </li>
 * </ul>
 * <p>
 * These commands changes the BlockMachines direction relative to the current direction. Just like if you
 * would be flying an airplane, turning left will have a different meaning if you are currently heading
 * e.g. upwards or downwards. The movement of the "cursor" is done when a block is placed. This means that
 * the block is always placed at the current position and then the cursor is moved in the current direction.
 * </p>
 * <p>
 *     A simple program to exemplify:
 * </p>
 * <pre>
 *     {@code
 *      Map<Character, BlockTypeId> alphabet = new HashMap<>();
 *      alphabet.put('a', new BlockTypeId("org/konstructs", "dirt"));
 *      BlockMachine machine = new BlockMachine(alphabet);
 *      machine.interpret("a-aa", new Position(0,0,0));
 *     }
 * </pre>
 * <p>
 * The block mapping returned by interpret would look like this:
 * </p>
 * <pre>
 *   {
 *   Position(x=0, y=0, z=0) = BlockTypeId(namespace='org/konstructs', name='dirt'),
 *   Position(x=0, y=1, z=0) = BlockTypeId(namespace='org/konstructs', name='dirt'),
 *   Position(x=1, y=1, z=0) = BlockTypeId(namespace='org/konstructs', name='dirt')
 *   }
 * </pre>
 * <p>
 *     The first <code>a</code> is placed on 0,0,0, then the "cursor" is moved in the upwards direction (y axis)
 *     to position 0,1,0. Then the direction is changed by a turn to the right. The next <code>a</code> is then
 *     placed on position 0,1,0, so there is no moment when placing a block, only after. This means that after this
 *     block was placed the "cursor" is now moved in the rightwards direction (x axis) to position 1,1,0. The last
 *     <code>a</code> is then placed on this position (1,1,0) and the cursor is further moved rightwards to 2,1,0,
 *     but no more blocks are placed since the program has ended. It can be slightly confusing at the beginning,
 *     that placing a block doesn't place it in the current direction, but rather on the current position and that
 *     the current position is then changed to the next one in the current direction.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Turtle_graphics">drawing turtle</a>
 */
public final class BlockMachine {
    /**
     * Spherical brush
     */
    public static final int SPHERE = 1;
    /**
     * Cube brush
     */
    public static final int CUBE = 0;
    /**
     * Direction upwards, this is the default direction.
     */
    public static final Matrix UPWARDS =
            new Matrix(0,  1,  0,
                       1,  0,  0,
                       0,  0,  1);
    public static final Matrix DOWNWARDS =
            new Matrix( 0,  1,  0,
                       -1,  0,  0,
                        0,  0,  -1);
    public static final Matrix FORWARDS =
            new Matrix( 0,  1,  0,
                        0,  0, -1,
                       -1,  0,  0);
    public static final Matrix BACKWARDS =
            new Matrix( 0,  1,  0,
                        0,  0,  1,
                        1,  0,  0);
    public static final Matrix RIGHTWARDS =
            new Matrix( 1,  0,  0,
                        0,  1,  0,
                        0,  0,  1);
    public static final Matrix LEFTWARDS =
            new Matrix(-1,  0,  0,
                        0, -1,  0,
                        0,  0,  1);

    private static final Matrix LEFT =
            new Matrix(0,  1,  0,
                       -1, 0,  0,
                       0,  0,  1);
    private  static final Matrix RIGHT =
            new Matrix(0, -1,  0,
                       1,  0,  0,
                       0,  0,  1);
    private static final Matrix DOWN =
            new Matrix(0,  0, -1,
                       0,  1,  0,
                       1,  0,  0);
    private static final Matrix UP =
            new Matrix(0,  0,  1,
                       0,  1,  0,
                       -1, 0,  0);
    private static final Matrix ROLL_LEFT =
            new Matrix(1,  0,  0,
                       0,  0, -1,
                       0,  1,  0);
    private static final Matrix ROLL_RIGHT =
            new Matrix(1,  0,  0,
                       0,  0,  1,
                       0, -1,  0);

    private static Matrix getDirectionMatrix(Direction direction) {
        switch(direction.getEncoding()) {
            case Direction.UP_ENCODING:
                return UPWARDS;
            case Direction.DOWN_ENCODING:
                return DOWNWARDS;
            case Direction.RIGHT_ENCODING:
                return RIGHTWARDS;
            case Direction.LEFT_ENCODING:
                return LEFTWARDS;
            case Direction.FORWARD_ENCODING:
                return FORWARDS;
            case Direction.BACKWARD_ENCODING:
                return BACKWARDS;
            default:
                throw new IllegalArgumentException("No direction encoded by: " + direction.getEncoding());
        }
    }

    /**
     * BlockMachine that Uses vacuum for all blocks in the alphabet. Useful to remove a previously
     * placed program by using the same program with this block machine.
     */
    public static final BlockMachine VACUUM_MACHINE =
            new BlockMachine(new HashMap<Character, BlockTypeId>());

    private final Map<Character, BlockTypeId> alphabet;
    private final boolean overwrite;

    /**
     * Construct an immutable BlockMachine
     * @param alphabet The alphabet to be used
     * @param overwrite Is the machine alowed to overwrite itself?
     */
    public BlockMachine(Map<Character, BlockTypeId> alphabet, boolean overwrite) {
        this.alphabet = alphabet;
        this.overwrite = overwrite;
    }

    /**
     * Construct an immutable BlockMachine
     * @param alphabet The alphabet to be used
     */
    public BlockMachine(Map<Character, BlockTypeId> alphabet) {
        this.alphabet = alphabet;
        this.overwrite = false;
    }

    private static class StackData {
        final Position position;
        final Matrix direction;
        final int radius;
        final int step;
        final int brush;

        public StackData(Position position, Matrix direction, int radius, int step, int brush) {
            this.position = position;
            this.direction = direction;
            this.radius = radius;
            this.step = step;
            this.brush = brush;
        }
    }

    /**
     * Interpret a program and generate a Position to BlockTypeId map
     * based on the alphabet of this block machine.
     * @param program The program to be intepreted
     * @param initPos The starting position for the program
     * @return The result of the program as a Position to BlockTypeId map
     */
    public Map<Position, BlockTypeId> interpret(String program, Position initPos) {
        return interpret(program, initPos, Direction.UP);
    }

    /**
     * Interpret a program and generate a Position to BlockTypeId map
     * based on the alphabet of this block machine.
     * @param program The program to be intepreted
     * @param initPos The starting position for the program
     * @param initDir The starting direction of the program execution
     * @return The result of the program as a Position to BlockTypeId map
     */
    public Map<Position, BlockTypeId> interpret(String program, Position initPos, Direction initDir) {
        return interpret(program, initPos, initDir, 0);
    }

    /**
     * Interpret a program and generate a Position to BlockTypeId map
     * based on the alphabet of this block machine.
     * @param program The program to be intepreted
     * @param initPos The starting position for the program
     * @param initDir The starting direction of the program execution
     * @param initRadius The initial (and minimum) radius of the brush
     * @return The result of the program as a Position to BlockTypeId map
     */
    public Map<Position, BlockTypeId> interpret(String program, Position initPos, Direction initDir, int initRadius) {
        return interpret(program, initPos, initDir, initRadius, initRadius * 2 + 1);
    }

    /**
     * Interpret a program and generate a Position to BlockTypeId map
     * based on the alphabet of this block machine.
     * @param program The program to be intepreted
     * @param initPos The starting position for the program
     * @param initDir The starting direction of the program execution
     * @param initRadius The initial (and minimum) radius of the brush
     * @param initStep The initial (and minimal) stepping distance between block placements
     * @return The result of the program as a Position to BlockTypeId map
     */
    public Map<Position, BlockTypeId> interpret(String program, Position initPos, Direction initDir, int initRadius,
                                                int initStep) {
        return interpret(program, initPos, initDir, initRadius, initStep, CUBE);
    }

    /**
     * Interpret a program and generate a Position to BlockTypeId map
     * based on the alphabet of this block machine.
     * @param program The program to be intepreted
     * @param initPos The starting position for the program
     * @param initDir The starting direction of the program execution
     * @param initRadius The initial (and minimum) radius of the brush
     * @param initStep The initial (and minimal) stepping distance between block placements
     * @param initBrush The initial brush to use {@link #CUBE} or {@link #SPHERE}
     * @return The result of the program as a Position to BlockTypeId map
     */
    public Map<Position, BlockTypeId> interpret(String program, Position initPos, Direction initDir, int initRadius,
                                                int initStep, int initBrush) {
        return interpret(program, initPos, initDir, initRadius, initStep, initBrush, 0);
    }

    /**
     * Interpret a program and generate a Position to BlockTypeId map
     * based on the alphabet of this block machine.
     * @param program The program to be intepreted
     * @param initPos The starting position for the program
     * @param initDir The starting direction of the program execution
     * @param initRadius The initial (and minimum) radius of the brush
     * @param initStep The initial (and minimal) stepping distance between block placements
     * @param initBrush The initial brush to use {@link #CUBE} or {@link #SPHERE}
     * @param initImperfection The intial and default imperfection. One block per number given here is
     *                         randomly selected to be excluded from the brush's outer layers
     * @return The result of the program as a Position to BlockTypeId map
     */
    public Map<Position, BlockTypeId> interpret(String program, Position initPos, Direction initDir, int initRadius,
                                                int initStep, int initBrush, int initImperfection) {
        Random random = new Random();
        Stack<StackData> stack = new Stack<>();
        Map<Position, BlockTypeId> blocks = new HashMap<>();

        Position pos = initPos;
        Matrix dir = getDirectionMatrix(initDir);
        int radius = initRadius;
        int step = initStep;
        int brush = initBrush;
        int imperfection = initImperfection;

        final int len = program.length();
        for (int i = 0; i < len; i++) {
            char c = program.charAt(i);
            switch(c) {
                case '&':
                    dir = dir.multiply(DOWN);
                    break;
                case '^':
                    dir = dir.multiply(UP);
                    break;
                case '+':
                    dir = dir.multiply(LEFT);
                    break;
                case '-':
                    dir = dir.multiply(RIGHT);
                    break;
                case '\\':
                    dir = dir.multiply(ROLL_LEFT);
                    break;
                case '/':
                    dir = dir.multiply(ROLL_RIGHT);
                    break;
                case '[':
                    stack.push(new StackData(pos, dir, radius, step, brush));
                    break;
                case ']':
                    StackData old = stack.pop();
                    pos = old.position;
                    dir = old.direction;
                    radius = old.radius;
                    step = old.step;
                    brush = old.brush;
                    break;
                case '¤':
                    radius = radius + 1;
                    break;
                case '%':
                    radius = radius - 1;
                    if(radius < initRadius) radius = initRadius;
                    break;
                case '§':
                    step = step + 1;
                    break;
                case '!':
                    step = step - 1;
                    if(step < initStep) step = initStep;
                    break;
                case '<':
                    radius = radius + 1;
                    step = radius * 2 + 1;
                    break;
                case '>':
                    radius = radius - 1;
                    if(radius < initRadius) radius = initRadius;
                    step = radius * 2 + 1;
                    break;
                case '*':
                    brush = SPHERE;
                    break;
                case '#':
                    brush = CUBE;
                    break;
                case '½':
                    imperfection = initImperfection;
                    break;
                case '1':
                    imperfection = 0;
                    break;
                default:
                    if(overwrite || !blocks.containsKey(pos)) {
                        BlockTypeId type = alphabet.get(c);
                        if(type != null) {
                            for (int x = -radius; x <= radius; x++) {
                                for (int y = -radius; y <= radius; y++) {
                                    for (int z = -radius; z <= radius; z++) {
                                        if (brush == SPHERE) {
                                            double r = Math.sqrt(x * x + y * y + z * z + 1);
                                            if (r > (double) radius)
                                                continue;
                                            if (imperfection != 0 && r > radius - 1 && random.nextInt(imperfection) == 0)
                                                continue;
                                        } else {
                                            if (imperfection != 0
                                                    && (x >= radius - 1 || y >= radius - 1 || z >= radius - 1
                                                    || x <= -radius + 1 || y <= -radius + 1 || z <= -radius + 1)
                                                    && random.nextInt(imperfection) == 0)
                                                continue;
                                        }
                                        Position offset = new Position(x, y, z);
                                        blocks.put(pos.add(offset), type);
                                    }
                                }
                            }
                        }
                    }
                    pos = pos.add(dir.getAdg().multiply(step));
                    break;
            }
        }
        return blocks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockMachine that = (BlockMachine) o;

        if (overwrite != that.overwrite) return false;
        return alphabet.equals(that.alphabet);

    }

    @Override
    public int hashCode() {
        int result = alphabet.hashCode();
        result = 31 * result + (overwrite ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockMachine(" +
                "alphabet=" + alphabet +
                ", overwrite=" + overwrite +
                ')';
    }
}
