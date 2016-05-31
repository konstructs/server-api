package konstructs.api;

import java.io.Serializable;

/**
 * BlockState is a class that holds all relevant information regarding
 * a block's physical state. There are only four instances of this class,
 * representing the four states of matter in konstructs:
 * {@link #SOLID}, {@link #LIQUID}. {@link #GAS} and {@link #PLASMA}.
 *
 * The class is immutable and serializable.
 */
public class BlockState implements Serializable {
    public static final BlockState SOLID = new BlockState("solid");
    public static final BlockState LIQUID = new BlockState("liquid");
    public static final BlockState GAS = new BlockState("gas");
    public static final BlockState PLASMA = new BlockState("plasma");

    /**
     * Returns the state that matches the string or throws a IllegalArgumentException.
     * @param state String to name one of four state. "solid", "liquid", "gas" or "plasma"
     * @return {@link #SOLID}, {@link #LIQUID}. {@link #GAS} or {@link #PLASMA}
     * @throws IllegalArgumentException if string does not match a state
     */
    public static BlockState fromString(String state) {
        if(state.equals(SOLID.getState())) {
            return SOLID;
        } else if(state.equals(LIQUID.getState())) {
            return LIQUID;
        } else if(state.equals(GAS.getState())) {
            return GAS;
        } else if(state.equals(PLASMA.getState())) {
            return PLASMA;
        } else {
            throw new IllegalArgumentException("No known state: " + state);
        }
    }

    private final String state;

    private BlockState(String state) {
        this.state = state;
    }

    /**
     * Returns the string representation of the state
     * @return String representation of state
     */
    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockState that = (BlockState) o;

        return state.equals(that.state);

    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public String toString() {
        return "BlockState(" +
                "state='" + state + '\'' +
                ')';
    }
}
