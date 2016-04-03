package konstructs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import konstructs.api.Matrix;
import konstructs.api.Position;
import konstructs.api.BlockTypeId;

public final class BlockMachine {
    public static final Matrix UPWARDS =
            new Matrix(0,  1,  0,
                       1,  0,  0,
                       0,  0,  1);
    public static final Matrix LEFT =
            new Matrix(0,  1,  0,
                       -1, 0,  0,
                       0,  0,  1);
    public  static final Matrix RIGHT =
            new Matrix(0, -1,  0,
                       1,  0,  0,
                       0,  0,  1);
    public static final Matrix DOWN =
            new Matrix(0,  0, -1,
                       0,  1,  0,
                       1,  0,  0);
    public static final Matrix UP =
            new Matrix(0,  0,  1,
                       0,  1,  0,
                       -1, 0,  0);
    public static final Matrix ROLL_LEFT =
            new Matrix(1,  0,  0,
                       0,  0, -1,
                       0,  1,  0);
    public static final Matrix ROLL_RIGHT =
            new Matrix(1,  0,  0,
                       0,  0,  1,
                       0, -1,  0);
    public static final BlockMachine VACUUM_MACHINE =
            new BlockMachine(new HashMap<Character, BlockTypeId>());

    private final Map<Character, BlockTypeId> alphabet;
    private final boolean overwrite;

    public BlockMachine(Map<Character, BlockTypeId> alphabet, boolean overwrite) {
        this.alphabet = alphabet;
        this.overwrite = overwrite;
    }

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
