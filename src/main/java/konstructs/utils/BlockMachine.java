package konstructs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
     * Direction upwards, this is the default direction.
     */
    public static final Matrix UPWARDS =
            new Matrix(0,  1,  0,
                       1,  0,  0,
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

    private static class PosDir {
        final Position position;
        final Matrix direction;

        public PosDir(Position position, Matrix direction) {
            this.position = position;
            this.direction = direction;
        }
    }

    public Map<Position, BlockTypeId> interpret(String program, Position initPos) {
        return interpret(program, initPos, UPWARDS);
    }

    public Map<Position, BlockTypeId> interpret(String program, Position initPos, Matrix initDir) {

        Stack<PosDir> stack = new Stack<>();
        Map<Position, BlockTypeId> blocks = new HashMap<>();

        Position pos = initPos;
        Matrix dir = initDir;

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
                    stack.push(new PosDir(pos, dir));
                    break;
                case ']':
                    PosDir old = stack.pop();
                    pos = old.position;
                    dir = old.direction;
                    break;
                default:
                    if(overwrite || !blocks.containsKey(pos)) {
                        BlockTypeId type = alphabet.get(c);
                        if(type == null)
                            type = BlockTypeId.VACUUM;
                        blocks.put(pos, type);
                    }
                    pos = pos.add(dir.getAdg());
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
