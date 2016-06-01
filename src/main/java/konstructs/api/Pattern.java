package konstructs.api;

import java.util.Arrays;

/**
 * Pattern is a class that stores a a square area (defined by a number of
 * rows and columns) and an array of stacks within this area forming a pattern.
 * The simplest pattern is a single stack in an area of 1x1. All rows and
 * columns of a pattern always at least contains one stack, e.g. a 2x2 pattern
 * with the first row only containing nulls would be reduced to a 1x2 pattern.
 *
 * Patterns are used with Inventories to find if an inventory contains a given
 * pattern. This is the basis of crafting.
 *
 * @see Inventory
 * @see InventoryView
 */
public final class Pattern {
    private final Stack[] stacks;
    private final int columns;
    private final int rows;

    /**
     * Constructs an immutable Pattern
     * @param stacks The array of stacks forming the pattern
     * @param rows The number of rows of this pattern
     * @param columns The number of columns of this pattern
     */
    public Pattern(Stack[] stacks, int rows, int columns) {
        this.stacks = stacks;
        this.columns = columns;
        this.rows = rows;
    }

    /**
     * Get the stacks of this pattern
     * @return The stacks of the pattern
     */
    public Stack[] getStacks() {
        return stacks;
    }

    /**
     * Get the number of columns of this pattern
     * @return The number of columns of this pattern
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Get the number of rows of this pattern
     * @return The number of rows of this pattern
     */
    public int getRows() {
        return rows;
    }

    /**
     * Get the size of this pattern
     * @return The size of this pattern
     */
    public int size() {
        return stacks.length;
    }

    /**
     * Check whether this pattern is a super set of the given pattern template.
     * This means that from this pattern the pattern template given can be produced,
     * i.e. this pattern contains the same layout of stacks of at least
     * the same size as the pattern template. In crafting, if this method returns true,
     * the pattern template given can be constructed from this pattern.
     * @param p The pattern to check
     * @return True if the pattern given can be constructed from this pattern.
     */
    public boolean contains(PatternTemplate p, BlockFactory factory) {
        if(p.getRows() == rows && p.getColumns() == columns && size() == p.size()) {
            for(int i = 0; i < size(); i++) {
                Stack self = stacks[i];
                StackTemplate other = p.getStacks()[i];
                if(self == null && other == null) continue;
                if(self == null) return false;
                if(!self.contains(other, factory)) return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pattern pattern = (Pattern) o;

        if (columns != pattern.columns) return false;
        if (rows != pattern.rows) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(stacks, pattern.stacks);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(stacks);
        result = 31 * result + columns;
        result = 31 * result + rows;
        return result;
    }

    @Override
    public String toString() {
        return "Pattern(" +
                "stacks=" + Arrays.toString(stacks) +
                ", columns=" + columns +
                ", rows=" + rows +
                ')';
    }
}
